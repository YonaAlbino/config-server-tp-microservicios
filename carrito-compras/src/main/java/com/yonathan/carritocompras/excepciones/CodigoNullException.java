package com.yonathan.carritocompras.excepciones;

public class CodigoNullException extends RuntimeException{
   public CodigoNullException(){
        super("El código del prodcuto no se encuentra en la basae de datos");
    }
}
