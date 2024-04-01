package com.example.FinalAssessmentSpringBoot.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message){
        super(message);
    }

}
