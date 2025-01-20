package org.example.kiosk.lv5;

import org.example.kiosk.common.exception.InvalidInputException;
import org.example.kiosk.common.exception.InvalidInputRangeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final List<Menu> menus;
    private final String NUMBER_REG = "[0-9]+"; // 숫자만 입력했는지 검증하기 위한 정규표현식
    private final List<Integer> indexList = new ArrayList<>(); // [ MAIN MENU ] 창에서 보여지는 카테고리 인덱스 리스트 [1, 2, 3]

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        setIndexList(); // 메인 메뉴에서 보여지는 카테고리 인덱스 리스트 세팅 ([ MAIN MENU ])
        while (true) {
            try {
                showCategories();

                String inputCategoryIndex = sc.next();
                if ("0".equals(inputCategoryIndex)) {
                    System.out.println("프로그램이 종료되었습니다.");
                    break;
                }

                // 입력값 검증
                validInputValue(inputCategoryIndex);
                int categoryIndex = Integer.parseInt(inputCategoryIndex) - 1;
                validInputRangeValue(categoryIndex, this.getIndexList());

                // 해당 번호에 맞는 메뉴 조회
                Menu menu = this.menus.get(categoryIndex);
                showMainMenes(menu);

                while (true){
                    String inputMenuIndex = sc.next();
                    if ("0".equals(inputMenuIndex)) {
                        System.out.println("메인 화면으로 돌아갑니다.");
                        break;
                    }

                    // 입력값 검증
                    validInputValue(inputMenuIndex);
                    int menuIndex = Integer.parseInt(inputMenuIndex) - 1;
                    validInputRangeValue(menuIndex, menu.getMenuItemIndexList());

                    // 메뉴 인덱스 번호에 맞는 메뉴 출력
                    MenuItem menuItem = menu.getMenuItems().get(menuIndex);
                    System.out.println("선택한 메뉴 : " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
                }

            } catch (InvalidInputException | InvalidInputRangeException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public List<Integer> getIndexList() {
        return this.indexList;
    }

    // [ MAIN MENU ] 창에서 보여질 인덱스 리스트 세팅
    public void setIndexList() {
        for(int i = 0; i < menus.size(); i++){
            indexList.add(i+1);
        }
    }

    private void showCategories() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println(i + 1 + ". " + this.menus.get(i).getCategory());
        }
        System.out.println("0. 종료 | 종료");
    }

    private void showMainMenes(Menu menu) {
        System.out.println("[ " + menu.getCategory()+ " MENU ]");
        menu.showMenuItems();
        System.out.println("0. 뒤로가기");
    }

    // 사용자가 숫자만 입력했는지 검증하는 메서드
    private void validInputValue(String inputIndex) throws InvalidInputException {
        if (!inputIndex.matches(NUMBER_REG)){
            throw new InvalidInputException();
        }
    }

    // 사용자가 인덱스 리스트 값에 해당되는 숫자만 입력해는지 검증하는 메서드
    private void validInputRangeValue(int inputIndex, List<Integer> validIndexList) throws InvalidInputRangeException {
        if (!validIndexList.contains(inputIndex)){
            throw new InvalidInputRangeException();
        }
    }

}
