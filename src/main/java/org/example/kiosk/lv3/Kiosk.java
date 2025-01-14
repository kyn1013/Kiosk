package org.example.kiosk.lv3;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<MenuItem> menuItems;

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("[ SHAKESHACK MENU ]");
                int index = 1;
                for (MenuItem menuItem : this.menuItems) {
                    System.out.println(index++ + ". " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
                }
                System.out.println("0. 종료 | 종료");
                System.out.print("값을 입력해 주세요! : ");

                String inputValue = sc.next();
                if ("0".equals(inputValue)) {
                    System.out.println("프로그램이 종료되었습니다.");
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
    }

}
