package es.cic25.proy014.proy014.service;

public class MaxPlazasException extends RuntimeException{
    public MaxPlazasException(){

    }

    public MaxPlazasException(String message){
        super(message);
    }

    public MaxPlazasException(String message, Throwable throwable){
        super(message,throwable);
    }
}
