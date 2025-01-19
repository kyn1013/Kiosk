package org.example.kiosk.common.exception;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("장바구니에 존재하지 않는 메뉴 이름입니다!");
    }
}
