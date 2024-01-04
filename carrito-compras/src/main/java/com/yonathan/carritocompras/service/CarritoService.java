package com.yonathan.carritocompras.service;

import com.yonathan.carritocompras.dto.Producto;
import com.yonathan.carritocompras.excepciones.CarritoNullException;
import com.yonathan.carritocompras.excepciones.CodigoNullException;
import com.yonathan.carritocompras.model.Carrito;
import com.yonathan.carritocompras.repository.ICarritoRepository;
import com.yonathan.carritocompras.repository.IProductoApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService implements ICarritoService{

    @Autowired
    private ICarritoRepository carritoRepo;

    @Autowired
    private IProductoApi productoApi;
    @Override
    public List<Carrito> getCarrito() {
       return carritoRepo.findAll();
    }

    @Override
    public Carrito findCarrito(Long id) {
        return carritoRepo.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name =  "producto-service",fallbackMethod = "fallbackSaveCarrito")
    @Retry(name = "producto-service")
    public void saveCarrito(Long idCarrito, Long idProducto) {
        String codigo = productoApi.getCodigoProducto(idProducto);

        Carrito carrito = carritoRepo.findById(idCarrito)
                .orElseGet(() -> {
                    Carrito newCarrito = new Carrito();
                    newCarrito.setListaProductos(new ArrayList<>());
                    return newCarrito;
                });

        carrito.getListaProductos().add(codigo);
        carritoRepo.save(carrito);

    }

    public void fallbackSaveCarrito(Exception ex){
        System.out.println("Error al procesar la venta " + ex.getMessage());
    }


    @Override
    public void deleteCarrito(Long id) {
        carritoRepo.deleteById(id);
    }

    @Override
    public void editCarrito(Carrito carrito) {
        carritoRepo.save(carrito);
    }

    @Override
    public void borrarProductoListaCarrito(Long idCarrito, String codigoProducto) {
        Optional<Carrito> otionalCarrito = carritoRepo.findById(idCarrito);

        otionalCarrito.ifPresent(carrito -> {
            if (carrito.getListaProductos().remove(codigoProducto))
                carritoRepo.save(carrito);
        });
    }


    @Override
    @CircuitBreaker(name = "producto-service", fallbackMethod = "fallbackCarritoSetPrecio")
    @Retry(name = "prodcuto-service")
    public void carritoSetPrecio(Carrito carrito) {
        carrito.setPrecio(productoApi.getTotalCarrito(carrito.getListaProductos()));
        carritoRepo.save(carrito);
    }

    public void fallbackCarritoSetPrecio(Exception ex){
        System.out.println("Error al conectar con productoApi " + ex.getMessage());
    }
}
