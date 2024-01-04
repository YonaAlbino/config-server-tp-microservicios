package com.yonathan.ventas.service;

import com.yonathan.ventas.dto.Carrito;
import com.yonathan.ventas.excepciones.ConexionFallidaException;
import com.yonathan.ventas.model.Producto;
import com.yonathan.ventas.model.Venta;
import com.yonathan.ventas.repository.ICarritoApi;
import com.yonathan.ventas.repository.IProdcutoApi;
import com.yonathan.ventas.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private ICarritoApi carritoApi;

    @Autowired
    private IProdcutoApi prodcutoApi;

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public void saveVenta(Venta venta) {
     ventaRepo.save(venta);
    }

    @Override
    @CircuitBreaker(name = "carrito-service", fallbackMethod = "fallbackGetPrecioVenta")
    @Retry(name = "carrito-service")
    public Double getPrecioVenta(Venta venta) {
        Carrito carrito = carritoApi.findCarrito(venta.getIdCarrito());
        return carrito.getPrecio();
    }

    public Double fallbackGetPrecioVenta(Throwable throwable){
        return null;
    }

    @Override
    @CircuitBreaker(name = "producto-service", fallbackMethod = "fallbackGetProdcutos")
    @Retry(name = "producto-service")
    public List<Producto> getProductosEnVenta(Venta venta) {
            Carrito carrito = carritoApi.findCarrito(venta.getIdCarrito());
            List<String> codigosProdcutosCarrito = carrito.getListaProductos();
            List<Producto> listaProductos = prodcutoApi.getProdcutos();

            List<Producto> productosEnCarrito = listaProductos.stream()
                    .filter(producto -> codigosProdcutosCarrito.contains(producto.getCodigo()) )
                    .collect(Collectors.toList());

            return productosEnCarrito;
    }

    public List<Producto> fallbackGetProdcutos(Throwable throwable){
        List<Producto> listaProducto = new ArrayList<>();
        listaProducto.add(new Producto(0L,"fallido","fallido","fallido", 0000.0));
        return listaProducto;
    }



}
