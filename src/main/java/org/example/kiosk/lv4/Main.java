package org.example.kiosk.lv4;

import org.example.kiosk.common.exception.InvalidInputException;
import org.example.kiosk.common.exception.InvalidInputRangeException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Menu 객체 생성을 통해 이름 설정
        // Menu 클래스 내 있는 List<MenuItem> 에 MenuItem 객체 생성하면서 삽입

        //헴바거 메뉴 생성
        List<MenuItem> burgerMenuItems = new ArrayList<>();
        burgerMenuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Menu burgerMenu = new Menu("Burger", burgerMenuItems);

        // 음료수 메뉴 생성
        List<MenuItem> drinkMenuItems = new ArrayList<>();
        drinkMenuItems.add(new MenuItem("Shack-made Lemonade", 4.5, "상큼한 맛의 홈메이드 레모네이드"));
        drinkMenuItems.add(new MenuItem("Fresh Brewed Iced Tea", 3.0, "신선한 홍차로 만든 아이스 티"));
        drinkMenuItems.add(new MenuItem("Fifty/Fifty", 4.0, "레모네이드와 아이스 티가 섞인 음료"));
        drinkMenuItems.add(new MenuItem("Fountain Soda", 2.5, "콜라, 스프라이트 등 다양한 소다 옵션"));
        drinkMenuItems.add(new MenuItem("Shack Shake", 5.5, "초코, 바닐라, 스트로베리 등 선택 가능한 쉐이크"));

        Menu drinkMenu = new Menu("Drinks", drinkMenuItems);

        // 사이드 메뉴 생성
        List<MenuItem> sideMenuItems = new ArrayList<>();
        sideMenuItems.add(new MenuItem("Crinkle-Cut Fries", 3.9, "바삭한 크링클컷 감자튀김"));
        sideMenuItems.add(new MenuItem("Cheese Fries", 4.9, "체다 치즈가 올라간 크링클컷 감자튀김"));
        sideMenuItems.add(new MenuItem("Hot Dog", 4.5, "100% 소고기 핫도그"));
        sideMenuItems.add(new MenuItem("Chicken Bites", 5.9, "바삭하고 촉촉한 치킨 바이트"));

        Menu sideMenu = new Menu("Sides", sideMenuItems);

        List<Menu> menus = new ArrayList<>();
        menus.add(burgerMenu);
        menus.add(drinkMenu);
        menus.add(sideMenu);

        // Kiosk 객체 생성
        Kiosk kiosk = new Kiosk(menus);
        // Kiosk 내 시작하는 함수 호출
        kiosk.start();

    }
}
