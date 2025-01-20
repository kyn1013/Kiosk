package org.example.kiosk.common.exception;

// 숫자가 아닌 다른 값을 입력할 경우 발생하는 예외 객체
public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super("번호를 입력해주세요!");
    }
}
