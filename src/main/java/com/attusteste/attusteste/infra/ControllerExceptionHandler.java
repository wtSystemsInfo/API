package com.attusteste.attusteste.infra;

import com.attusteste.attusteste.DTO.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author William Toloto
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Já existe uma pessoa cadastrada com esses dados!", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
    
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Pessoa não localizada no cadastro!", "404");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
    
}
