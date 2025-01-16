package org.example.kiosk.common.exception;

public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super("번호를 입력해주세요!");
    }
}
