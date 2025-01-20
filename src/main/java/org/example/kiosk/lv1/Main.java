package org.example.kiosk.lv1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            try {
                // 메뉴 출력
                System.out.println("[ SHAKESHACK MENU ]");
                System.out.println("1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
                System.out.println("2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
                System.out.println("3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
                System.out.println("4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거");
                System.out.println("0. 종료           | 종료");
                System.out.print("값을 입력해 주세요! : ");

                String inputValue = sc.next();
                if ("0".equals(inputValue)){
                    break;
                }

                // 해당 입력값에 맞게 선택 메시지 출력
                switch (inputValue) {
                    case "1" :
                        System.out.println("ShackBurger를 선택하셨습니다!");
                        break;
                    case "2" :
                        System.out.println("SmokeShack를 선택하셨습니다!");
                        break;
                    case "3" :
                        System.out.println("Cheeseburger를 선택하셨습니다!");
                        break;
                    case "4" :
                        System.out.println("Hamburger를 선택하셨습니다!");
                        break;
                    default:
                        throw new IllegalStateException("유효하지 않은 값을 입력했습니다!");
                }
            } catch (IllegalStateException e) {
                System.out.println(e);
            }
        }

        System.out.println("프로그램을 종료합니다!");
    }
}
