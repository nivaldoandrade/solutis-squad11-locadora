package com.squad11.locadora.exceptions;


import java.time.LocalDateTime;
public record RestErrorResponse(
        int status,

        String message,

        LocalDateTime timestamp
) {
}
