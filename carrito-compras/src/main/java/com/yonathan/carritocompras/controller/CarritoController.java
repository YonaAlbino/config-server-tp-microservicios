package com.yonathan.carritocompras.controller;

import com.yonathan.carritocompras.model.Carrito;
import com.yonathan.carritocompras.repository.IProductoApi;
import com.yonathan.carritocompras.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @PostMapping("/carrito")
    public void saveCarrito(@RequestParam Long idCarrito, @RequestParam Long idProducto){
        carritoService.saveCarrito(idCarrito, idProducto);
    }

    @GetMapping("/carrito")
    public List<Carrito> getCarritos(){
        return carritoService.getCarrito();
    }

    @DeleteMapping("/carrito")
    public void borrarProdcutoLista(@RequestParam Long idCarrito, @RequestParam String codigoProducto){
        carritoService.borrarProductoListaCarrito(idCarrito, codigoProducto);
    }

    @PutMapping("/carritoSetPrecio")
    public void setTotalCarrito(@RequestBody Carrito carrito){
        carritoService.carritoSetPrecio(carrito);
    }

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/findcarrito/{id}")
    public Carrito findCarrito(@PathVariable Long id){
        System.out.println("Server port " + serverPort);
        return carritoService.findCarrito(id);
    }

}
