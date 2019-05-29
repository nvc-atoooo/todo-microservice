package com.example.lib.exception;


public class TokenViolationException extends Exception {

    public TokenViolationException()
    {
        super();
    }

    public TokenViolationException(String _message)
    {
        super(_message);
    }
}
