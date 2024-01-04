package com.yonathan.ventas.repository;

import com.yonathan.ventas.dto.Carrito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito-service")
public interface ICarritoApi {

    @GetMapping("/findcarrito/{id}")
    public Carrito findCarrito(@PathVariable Long id);
}
