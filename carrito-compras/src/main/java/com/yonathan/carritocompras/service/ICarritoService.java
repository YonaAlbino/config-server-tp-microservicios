package com.yonathan.carritocompras.service;

import com.yonathan.carritocompras.model.Carrito;

import java.util.List;

public interface ICarritoService {

    public List<Carrito> getCarrito();
    public Carrito findCarrito(Long id);
    public void saveCarrito(Long idCarrito,Long idProducto);
    public void deleteCarrito(Long id);
    public void editCarrito(Carrito carrito);
    public void borrarProductoListaCarrito(Long idCarrito, String codigoProducto);

    void carritoSetPrecio(Carrito carrito);
}
