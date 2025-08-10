package es.cic25.proy014.proy014.controler;

public class SecurityCreationException extends RuntimeException{

    public SecurityCreationException(){

    }

    public SecurityCreationException(String message){
        super(message);
    }

    public SecurityCreationException(String message, Throwable throwable){
        super(message,throwable);
    }
}

