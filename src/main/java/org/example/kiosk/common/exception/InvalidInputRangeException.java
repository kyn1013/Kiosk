package org.example.kiosk.common.exception;

// 화면에 출력된 값이 아닌 다른 숫자를 입력할 경우 발생하는 예외 객체
public class InvalidInputRangeException extends Exception{
    public InvalidInputRangeException() {
        super("유효하지 않은 범위의 번호를 입력하셨습니다!");
    }
}
