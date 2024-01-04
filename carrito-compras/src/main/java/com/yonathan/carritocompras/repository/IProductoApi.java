package com.yonathan.carritocompras.repository;

import com.yonathan.carritocompras.dto.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "producto-service")
public interface IProductoApi {


    @GetMapping("/codigo/{id}")
    public String getCodigoProducto(@PathVariable Long id);

    @PostMapping("/carritoSetPrecio")
    public Double getTotalCarrito(@RequestBody List<String> listaCodigos);
}
