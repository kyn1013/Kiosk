package org.example.kiosk.common.exception;

// 메뉴를 삭제할 때, 장바구니 리스트에 없는 이름을 입력한 경우 발생하는 예외 객체
public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("장바구니에 존재하지 않는 메뉴 이름입니다!");
    }
}
