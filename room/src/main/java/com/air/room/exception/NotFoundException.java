package com.air.room.exception;

public class NotFoundException extends IllegalArgumentException{
    public NotFoundException(String msg){
        super(msg);
    }
}
