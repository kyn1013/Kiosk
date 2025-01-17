package org.example.kiosk.challenge;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    // 장바구니에 넣은 선택한 물건을 담는 리스트
    private List<MenuItem> cartItems = new ArrayList<>();
    private double total;

    public void showCartItems() {
        this.total = 0;
        System.out.println("[ ORDER MENU ]");
        for (MenuItem cartItem : cartItems) {
            System.out.println(cartItem.getName() + " | W" + cartItem.getPrice() + " | " + cartItem.getDescription());
            this.total += cartItem.getPrice();
        }
    }

    public void showTotal() {
        System.out.println("[ Orders ]");
        System.out.println("W " + getTotal());
    }

    public void completeOrder() {
        System.out.println("주문이 완료되었습니다. 금액은 W" + getTotal() + " 입니다");
    }

    public void addCart(MenuItem menuItem){
        this.cartItems.add(menuItem);
    }

    public void cancelCart() {
        this.cartItems.clear();
    }

    public double getTotal() {
        return this.total;
    }

    public boolean isCartEmpty(){
        return this.cartItems.isEmpty();
    }

}