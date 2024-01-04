package com.yonathan.ventas.excepciones;

public class ConexionFallidaException extends RuntimeException{

    public ConexionFallidaException(String nombreApi){
            super("La conexíon con la api " + nombreApi+ " no funciono");
    }
}
