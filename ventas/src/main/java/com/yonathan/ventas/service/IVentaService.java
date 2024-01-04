package com.yonathan.ventas.service;

import com.yonathan.ventas.model.Producto;
import com.yonathan.ventas.model.Venta;

import java.util.List;

public interface IVentaService {
    public List<Venta> getVentas();
    public void saveVenta(Venta venta);
    Double getPrecioVenta(Venta venta);
    List<Producto> getProductosEnVenta(Venta venta);
}
