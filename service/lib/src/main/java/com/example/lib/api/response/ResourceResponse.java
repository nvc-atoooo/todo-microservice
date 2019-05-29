package com.example.lib.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResourceResponse<T> extends ResponseEntity<T>
{
    public ResourceResponse(T _body)
    {
        super(_body, HttpStatus.OK);
    }
}