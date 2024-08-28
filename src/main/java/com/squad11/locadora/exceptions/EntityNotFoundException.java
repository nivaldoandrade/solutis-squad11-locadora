package com.squad11.locadora.exceptions;
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> theClass) {
        super(String.format("The %s not found", theClass.getSimpleName()));
    }
}
