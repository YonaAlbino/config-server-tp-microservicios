package com.yonathan.productos.service;

import com.yonathan.productos.model.Producto;
import com.yonathan.productos.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepo;
    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    public void lanzarException(){
        throw new IllegalArgumentException();
    }

    @Override
    public Producto findProducto(Long id) {
       return productoRepo.findById(id).orElse(null);
    }

    @Override
    public void saveProdcuto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public void editProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public String getCodigoProducto(Long  id) {
        return productoRepo.getCodigoCarrito(id);
    }

    @Override
    public Double carritoSetPrecio(List<String> listaCodigosProductos) {
        List<Producto> listaProductos = this.getProductos();

        return listaProductos.stream()
                .filter(producto -> producto.getCodigo() != null && listaCodigosProductos.contains(producto.getCodigo()))
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
}
