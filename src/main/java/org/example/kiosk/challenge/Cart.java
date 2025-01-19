package org.example.kiosk.challenge;

import org.example.kiosk.common.exception.MenuItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    // 장바구니에 넣은 선택한 물건을 담는 리스트
    private List<MenuItem> cartItems = new ArrayList<>();
    private double total;

    public void showCartItems() {
        this.total = 0;
        System.out.println("[ ORDER MENU ]");
        cartItems.forEach(cartItem -> {
            System.out.println(cartItem.getName() + " | W" + cartItem.getPrice() + " | " + cartItem.getDescription());
            total += cartItem.getPrice();
        });
    }

    public void deleteCartItem(String name) throws MenuItemNotFoundException{
        boolean exists = cartItems.stream()
                .anyMatch(cartItem -> cartItem.getName().equals(name.toLowerCase()));  // 해당 이름이 존재하는지 확인

        if (exists) {
            cartItems = cartItems.stream()
                    .filter(cartItem -> !cartItem.getName().equals(name))
                    .collect(Collectors.toList());
        } else {
            throw new MenuItemNotFoundException();
        }
    }

    public void showTotal() {
        System.out.println("[ Orders ]");
        System.out.println("W " + getTotal());
    }

    public void completeOrder(double total) {
        System.out.println("주문이 완료되었습니다. 금액은 W" + total + " 입니다");
    }

    public void addCart(MenuItem menuItem){
        cartItems.add(menuItem);
    }

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