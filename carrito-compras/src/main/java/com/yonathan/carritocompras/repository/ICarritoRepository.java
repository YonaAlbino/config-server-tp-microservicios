package com.yonathan.carritocompras.repository;

import com.yonathan.carritocompras.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {

}
