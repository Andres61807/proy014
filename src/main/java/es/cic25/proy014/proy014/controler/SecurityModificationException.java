package es.cic25.proy014.proy014.controler;

public class SecurityModificationException extends RuntimeException{

    public SecurityModificationException(){

    }

    public SecurityModificationException(String message){
        super(message);
    }

    public SecurityModificationException(String message, Throwable throwable){
        super(message,throwable);
    }
}
