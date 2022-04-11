package com.postservice.Exception;

public class PostIdExistsException extends RuntimeException{
    public PostIdExistsException(String msg){
        super(msg);
    }
}
