package com.example.lib.api.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ErrorResponse extends ResponseEntity<ErrorResponse.Payload>
{
    public ErrorResponse(@NonNull HttpStatus status, @NonNull String message)
    {
        super(new Payload(status.value(), message), status);
    }

    @Value
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Payload
    {

        private Payload()
        {
            this.status = null;
            this.message = null;
        }

        @NonNull
        private Integer status;

        @NonNull
        private String message;
    }
}