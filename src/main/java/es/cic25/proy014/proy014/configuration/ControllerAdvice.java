package es.cic25.proy014.proy014.configuration;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic25.proy014.proy014.controler.SecurityCreationException;
import es.cic25.proy014.proy014.controler.SecurityModificationException;
import es.cic25.proy014.proy014.service.MaxPlazasException;
import es.cic25.proy014.proy014.service.PlazaOcupadaException;

@RestControllerAdvice
public class ControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecurityCreationException.class)
    public void ModificationControl(){
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecurityModificationException.class)
    public void CreationControl(){
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxPlazasException.class)
    public void MaxPlazaControl(){
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PlazaOcupadaException.class)
    public void PlazaOcupadaControl(){
        
    }
}
