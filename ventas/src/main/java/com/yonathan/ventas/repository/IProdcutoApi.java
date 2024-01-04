package com.yonathan.ventas.repository;

import com.yonathan.ventas.model.Producto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient(name = "producto-service")
public interface IProdcutoApi {

    @GetMapping("/productos")
    public List<Producto> getProdcutos();
}
