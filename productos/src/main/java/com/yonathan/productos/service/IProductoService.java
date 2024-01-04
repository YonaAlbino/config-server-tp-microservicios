package com.yonathan.productos.service;

import com.yonathan.productos.model.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    public List<Producto> getProductos();
    public Producto findProducto(Long id);
    public void saveProdcuto(Producto producto);
    public void deleteProducto(Long id);
    public void editProducto(Producto producto);
    public String getCodigoProducto(Long id);

    public Double carritoSetPrecio(List<String> listaCodigosProductos);
}
