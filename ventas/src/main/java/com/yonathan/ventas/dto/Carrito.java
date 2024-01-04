package com.yonathan.ventas.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {

    private Long id;
    private Double precio;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> listaProductos;
}
