package org.example.kiosk.common.exception;

public class InvalidInputRangeException extends Exception{
    public InvalidInputRangeException() {
        super("유효하지 않은 범위의 번호를 입력하셨습니다!");
    }
}
