package com.yonathan.ventas.controller;

import com.yonathan.ventas.model.Producto;
import com.yonathan.ventas.model.Venta;
import com.yonathan.ventas.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping("/venta")
    public List<Venta> getVentas(){
        return ventaService.getVentas();
    }

    @PostMapping("/venta")
    public void saveVenta(@RequestBody Venta venta){
        ventaService.saveVenta(venta);
    }

    @PostMapping("/ventaPrecio")
    public Double getPrecioVenta(@RequestBody Venta venta){
        return ventaService.getPrecioVenta(venta);
    }

    @PostMapping("/getProductosDeVenta")
    public List<Producto> getProductosDeVenta(@RequestBody Venta venta){
        return ventaService.getProductosEnVenta(venta);
    }
}
