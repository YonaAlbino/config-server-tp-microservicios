package com.yonathan.carritocompras.excepciones;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class CarritoNullException extends  RuntimeException{

    public CarritoNullException(){
        super("El carrito no existe en la base de datos");
    }


}
