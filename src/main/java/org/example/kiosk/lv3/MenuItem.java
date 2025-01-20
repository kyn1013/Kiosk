package org.example.kiosk.lv3;

public class MenuItem {
    private final String name; // 메뉴 이름
    private final double price; // 메뉴 가격
    private final String description; // 메뉴 설명

    MenuItem (String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
