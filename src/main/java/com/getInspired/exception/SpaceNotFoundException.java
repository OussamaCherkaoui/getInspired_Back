package com.getInspired.exception;

public class SpaceNotFoundException  extends RuntimeException{
    public SpaceNotFoundException(){
        super("Space not found!");
    }
}
