package org.example.kiosk.lv4;

public class MenuItem {

    private String name; // 메뉴 이름
    private double price; // 메뉴 가격
    private String description; // 메뉴 설명

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
