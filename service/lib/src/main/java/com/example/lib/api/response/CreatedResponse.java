package com.example.lib.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreatedResponse<T> extends ResponseEntity<T>
{
    public CreatedResponse(T _body)
    {
        super(_body, HttpStatus.CREATED);
    }
}
