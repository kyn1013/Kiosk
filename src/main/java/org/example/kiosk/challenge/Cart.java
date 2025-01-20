package org.example.kiosk.challenge;

import org.example.kiosk.common.exception.MenuItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cart {
    private List<MenuItem> cartItems = new ArrayList<>(); // 장바구니에 넣은 선택한 물건을 담는 리스트
    private double total;
    private Scanner sc = new Scanner(System.in);

    // 장바구니에 물건 추가
    public void addCart(MenuItem menuItem){
        cartItems.add(menuItem);
        System.out.println(menuItem.getName() + "이 장바구니에 추가되었습니다.");
    }

    // 장바구니에 담긴 물건 조회
    public void showCartItems() {
        this.total = 0;
        System.out.println("[ ORDER MENU ]");
        cartItems.forEach(cartItem -> {
            System.out.println(cartItem.getName() + " | W" + cartItem.getPrice() + " | " + cartItem.getDescription());
            total += cartItem.getPrice();
        });
    }

    // 장바구니 물건 합계 출력
    public void showTotalCost() {
        System.out.println("[ Orders ]");
        System.out.println("W " + getTotal());
    }

    // 장바구니에 메뉴를 추가할지 묻는 메서드
    public void showCartAddMsg(MenuItem menuItem) {
        System.out.println("선택한 메뉴 : " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
    }

    // 장바구니 메뉴 삭제
    public void deleteCartItem() throws MenuItemNotFoundException{
        System.out.print("삭제할 메뉴 이름을 입력하세요 : ");
        String name = sc.next();

        // 사용자가 입력한 값이 장바구니에 존재하는 메뉴 이름인지 확인
        boolean existsItemName = cartItems.stream()
                .anyMatch(cartItem -> cartItem.getName().equals(name.toLowerCase()));

        if (existsItemName) { // 존재한다면 해당 메뉴를 제외하고 다시 리스트 생성
            cartItems = cartItems.stream()
                    .filter(cartItem -> !cartItem.getName().equals(name))
                    .collect(Collectors.toList());
        } else { // 존재하지 않는다면 예외
            throw new MenuItemNotFoundException();
        }
    }

    // 장바구니 물건 전체 삭제
    public void cancelCart() {
        cartItems.clear();
    }

    public double getTotal() {
        return total;
    }

    public boolean isCartEmpty(){
        return cartItems.isEmpty();
    }

}