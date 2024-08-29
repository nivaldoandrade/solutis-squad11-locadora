package com.squad11.locadora.exceptions;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException() {
        super("The token has expired");
    }
}
