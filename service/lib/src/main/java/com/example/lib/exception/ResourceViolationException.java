package com.example.lib.exception;


public class ResourceViolationException extends Exception {

    public ResourceViolationException()
    {
        super();
    }

    public ResourceViolationException(String _message)
    {
        super(_message);
    }
}
