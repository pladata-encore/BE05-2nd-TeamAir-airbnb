package com.air.room.exception;

public class DisabledArgumentException extends IllegalArgumentException {
    public DisabledArgumentException(String msg){
        super(msg);
    }
}
