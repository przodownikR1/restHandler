package pl.java.scalatech.rest.handler;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Slf4j
public class DataIntegrityHandler {
    
    
    private MessageSource messageSource;
    
    @Autowired
    public DataIntegrityHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
        log.info("+++  DataIntegrity register...");
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConflict() {
        // TODO
        return "409+++";
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError() {
        // TODO
        return "databaseError";
    }

}
