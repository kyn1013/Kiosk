package org.example.kiosk.challenge;

import org.example.kiosk.common.exception.InvalidInputException;
import org.example.kiosk.common.exception.InvalidInputRangeException;

import java.util.*;

public class Kiosk {

    private final List<Menu> menus;
    private final Cart cart = new Cart();
    private final String NUMBER_REG = "[0-9]+";
    private final List<Integer> indexList = new ArrayList<>(); // 카테고리 인덱스 리스트임 1 2 3
    private final List<Integer> orderIndexList = new ArrayList<>(); // 카테고리 아래 오더메뉴 리스트 4 5
    private int orderIndex;
    private int cancelIndex;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        setIndexList();
        setOrderIndex();

        while (true) {
            try {
                System.out.println();
                showCategories();
                if (!cart.isCartEmpty()) {
                    showConfirmOrderMsg();
                }

                String inputCategoryIndex = sc.next();
                if ("0".equals(inputCategoryIndex)) {
                    System.out.println("프로그램이 종료되었습니다.");
                    break;
                }

                validInputValue(inputCategoryIndex);
                int categoryIndex = Integer.parseInt(inputCategoryIndex);

                // 4나 5를 누름
                if (orderIndexList.contains(categoryIndex)) {
                    // 4번 누르기
                    if (categoryIndex == orderIndex){
                        System.out.println("아래와 같이 주문 하시겠습니까?");
                        System.out.println();
                        cart.showCartItems();
                        System.out.println();
                        cart.showTotal();
                        System.out.println();
                        System.out.println("1. 주문      2. 메뉴판");
                        String orderCompleteIndex = sc.next();
                        if ("1".equals(orderCompleteIndex)){
                            cart.completeOrder();
                            break;
                        } else if ("2".equals(orderCompleteIndex)) {
                            continue;
                        } else {
                            throw new InvalidInputRangeException();
                        }

                    } else if (categoryIndex == cancelIndex){ //5나 그 이외 값 누르기
                        cart.cancelCart();
                        continue;
                    } else {
                        throw new InvalidInputRangeException();
                    }
                }

                validInputRangeValue(categoryIndex, this.getIndexList());

                Menu menu = this.menus.get(categoryIndex-1);
                showMainMenes(menu);

                while (true){
                    String inputMenuIndex = sc.next();
                    if ("0".equals(inputMenuIndex)) {
                        System.out.println("메인 화면으로 돌아갑니다.");
                        break;
                    }

                    validInputValue(inputMenuIndex);
                    int menuIndex = Integer.parseInt(inputMenuIndex);
                    validInputRangeValue(menuIndex, menu.getMenuItemIndexList());

                    MenuItem menuItem = menu.getMenuItems().get(menuIndex - 1);
                    System.out.println("선택한 메뉴 : " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
                    System.out.println();
                    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                    System.out.println("1. 확인       2. 취소");
                    String inputCartIndex = sc.next();
                    if ("1".equals(inputCartIndex)){
                        cart.addCart(menuItem);
                        System.out.println(menuItem.getName() + "이 장바구니에 추가되었습니다.");
                        break;
                    } else if ("2".equals(inputCartIndex)){
                        break;
                    } else {
                        throw new InvalidInputRangeException();
                    }
                }

            } catch (InvalidInputException | InvalidInputRangeException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public List<Integer> getIndexList() {
        return this.indexList;
    }

    public void showConfirmOrderMsg() {
        System.out.println("[ ORDER MENU ]");
        System.out.println( orderIndexList.get(0) + ". Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println(orderIndexList.get(1) + ". Cancel       | 진행중인 주문을 취소합니다.");
    }

    public void setIndexList() {
        for(int i = 0; i < menus.size(); i++){
            indexList.add(i+1);
        }
    }

    public void setOrderIndex() {
        int maxIndex = Collections.max(indexList);
        orderIndexList.add(maxIndex + 1);
        orderIndexList.add(maxIndex + 2);
        orderIndex = maxIndex + 1;
        cancelIndex = maxIndex + 2;
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
    private void validInputValue(String inputIndex) throws InvalidInputException {
        if (!inputIndex.matches(NUMBER_REG)){
            throw new InvalidInputException();
        }
    }
    private void validInputRangeValue(int inputIndex, List<Integer> validIndexList) throws InvalidInputRangeException {
        if (!validIndexList.contains(inputIndex)){
            throw new InvalidInputRangeException();
        }
    }

}
