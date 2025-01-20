package org.example.kiosk.lv4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final String category; // 메뉴 카테고리
    private final List<MenuItem> menuItems;
    private final List<Integer> menuItemIndexList = new ArrayList<>(); // 카테고리별 메뉴 인덱스 리스트

    public Menu(String category, List<MenuItem> menuItems) {
        this.category = category;
        this.menuItems = menuItems;
    }

    public void showMenuItems(){
        if (menuItemIndexList.isEmpty()){
            setMenuItemIndex();
        }
        for(int i = 0; i < menuItems.size(); i++){
            System.out.println( i + 1 + ". " + menuItems.get(i).getName() + " | W " + menuItems.get(i).getPrice() + " | " + menuItems.get(i).getDescription());
        }
    }

    public void setMenuItemIndex() {
        for(int i = 0; i < menuItems.size(); i++){
            menuItemIndexList.add(i+1);
        }
    }

    public List<MenuItem> getMenuItems(){
        return this.menuItems;
    }

    public String getCategory(){
        return this.category;
    }

    public List<Integer> getMenuItemIndexList() {
        return this.menuItemIndexList;
    }

}
