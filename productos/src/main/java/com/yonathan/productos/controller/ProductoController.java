package com.yonathan.productos.controller;

import com.yonathan.productos.model.Producto;
import com.yonathan.productos.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Value("${server.port}")
    private int serverport;

    @GetMapping("/productos")
    public List<Producto> getProdcutos(){
        System.out.println(serverport);
        return productoService.getProductos();
    }

    @GetMapping("/producto/{id}")
    public Producto findProducto(@PathVariable Long id){
        return productoService.findProducto(id);
    }

    @GetMapping("/codigo/{id}")
    public String getCodigoProducto(@PathVariable Long id){
       return productoService.getCodigoProducto(id);
    }

    @PostMapping("/producto")
    public void saveProducto(@RequestBody Producto producto){
        productoService.saveProdcuto(producto);
    }

    @DeleteMapping("/producto/{id}")
    public void deleteProdcuto(@PathVariable Long id){
        productoService.deleteProducto(id);
    }

    @PutMapping("/producto")
    public void editProducto(@RequestBody Producto producto){
        productoService.editProducto(producto);
    }

    @PostMapping("/carritoSetPrecio")
    public Double getTotalCarrito(@RequestBody List<String> listaCodigos){
       return productoService.carritoSetPrecio(listaCodigos);
    }
}
