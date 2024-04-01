package com.example.FinalAssessmentSpringBoot.beans;

import com.example.FinalAssessmentSpringBoot.exceptions.EntityAlreadyExistsException;
import com.example.FinalAssessmentSpringBoot.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException entityNotFoundException){
        Map<String,Object> response = new HashMap<>();

        response.put("Message", entityNotFoundException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExists(EntityAlreadyExistsException entityAlreadyExistsException){
        Map<String,Object> response = new HashMap<>();

        response.put("Message", entityAlreadyExistsException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
