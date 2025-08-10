package es.cic25.proy014.proy014.service;

public class PlazaOcupadaException extends RuntimeException {
    public PlazaOcupadaException(){

    }

    public PlazaOcupadaException(String message){
        super(message);
    }

    public PlazaOcupadaException(String message, Throwable throwable){
        super(message,throwable);
    }
}
