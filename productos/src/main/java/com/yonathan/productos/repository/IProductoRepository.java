package com.yonathan.productos.repository;

import com.yonathan.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT codigo FROM Producto p WHERE p.id = :id")
    public String getCodigoCarrito(Long id);


}
