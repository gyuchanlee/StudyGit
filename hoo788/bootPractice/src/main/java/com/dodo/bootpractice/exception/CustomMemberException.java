package com.dodo.bootpractice.exception;

public class CustomMemberException extends RuntimeException {

    public CustomMemberException() {
        super();
    }

    public CustomMemberException(String message) {
        super(message);
    }

    public CustomMemberException(String message, Throwable cause) {
        super(message, cause);
    }


}
