package com.example.lib.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException()
    {
        super();
    }

    public ResourceNotFoundException(String _message)
    {
        super(_message);
    }
}
