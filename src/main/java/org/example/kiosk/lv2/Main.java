package org.example.kiosk.lv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("[ SHAKESHACK MENU ]");
                int index = 1;
                for (MenuItem menuItem : menuItems){
                    System.out.println(index++ + ". " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
                }
                System.out.println("0. 종료 | 종료");
                System.out.print("값을 입력해 주세요! : ");

                String inputValue = sc.next();
                if ("0".equals(inputValue)){
                    break;
                }

                switch (inputValue) {
                    case "1" -> System.out.println(menuItems.get(0).getName() + "을 선택하셨습니다!");
                    case "2" -> System.out.println(menuItems.get(1).getName() + "을 선택하셨습니다!");
                    case "3" -> System.out.println(menuItems.get(2).getName() + "을 선택하셨습니다!");
                    case "4" -> System.out.println(menuItems.get(3).getName() + "을 선택하셨습니다!");
                    default -> throw new IllegalStateException("유효하지 않은 값을 입력하셨습니다!");
                }
            } catch (IllegalStateException e) {
                System.out.println(e);
            }

        }

        System.out.println("프로그램이 종료되었습니다!");
    }
}
