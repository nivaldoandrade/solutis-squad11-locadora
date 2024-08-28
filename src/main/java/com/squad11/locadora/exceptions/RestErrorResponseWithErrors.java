package com.squad11.locadora.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record RestErrorResponseWithErrors(
        int status,

        List<ApiError> errors,

        LocalDateTime timestamp
) {
}

