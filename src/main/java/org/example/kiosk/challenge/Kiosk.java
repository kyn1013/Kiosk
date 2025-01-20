package org.example.kiosk.challenge;

import org.example.kiosk.common.exception.InvalidInputException;
import org.example.kiosk.common.exception.InvalidInputRangeException;
import org.example.kiosk.common.exception.MenuItemNotFoundException;

import java.util.*;

public class Kiosk {

    private final List<Menu> menus;
    private final Cart cart = new Cart();
    private final String NUMBER_REG = "[0-9]+"; // 숫자만 입력했는지 검증하기 위한 정규표현식
    private final List<Integer> categoryIndexList = new ArrayList<>(); // [ MAIN MENU ] 창에서 보여지는 카테고리 인덱스 리스트 [1, 2, 3]
    private final List<Integer> orderIndexList = new ArrayList<>(); // [ ORDER MENU ] 창에 해당하는 메뉴 인덱스 리스트 [4, 5]
    private int orderIndex; // [ ORDER MENU ] 창에서 메뉴 주문 인덱스 ex) 4
    private int cancelIndex; // [ ORDER MENU ] 창에서 주문 취소 인덱스 ex) 5
    private Scanner sc = new Scanner(System.in);

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        setCategoryIndexList(); // 메인 메뉴에서 보여지는 카테고리 인덱스 리스트 세팅 ([ MAIN MENU ])
        setOrderIndex(); // 주문을 할 건지 물어볼 때 보여지는 인덱스 리스트 세팅 ([ ORDER MENU ])

        while (true) {
            try {
                // 카테고리 출력
                showCategories();
                if (!cart.isCartEmpty()) { // 장바구니가 비지 않았다면 [ ORDER MENU ] 창 출력
                    showOrderConfirmMsg();
                }

                // 사용자로부터 카테고리 인덱스 번호 입력받음
                String inputCategoryIndex = sc.next();
                if ("0".equals(inputCategoryIndex)) {
                    System.out.println("프로그램이 종료되었습니다.");
                    break;
                }

                // 숫자만 입력했는지 확인
                validInputValue(inputCategoryIndex);
                int categoryIndex = Integer.parseInt(inputCategoryIndex);

                // [ ORDER MENU ] 인덱스 번호를 누를 경우
                if (orderIndexList.contains(categoryIndex)) {
                    // 장바구니 확인 후 주문
                    if (categoryIndex == orderIndex){
                        showOrderListMsg();
                        String orderCompleteIndex = sc.next();
                        if ("1".equals(orderCompleteIndex)){
                            completeOrder();
                            break;
                        } else if ("2".equals(orderCompleteIndex)) {
                            continue;
                        } else if ("3".equals(orderCompleteIndex)){
                            cart.deleteCartItem();
                            continue;
                        } else {
                            throw new InvalidInputRangeException();
                        }

                    // 진행중인 주문 취소 (장바구니 비우기)
                    } else if (categoryIndex == cancelIndex){
                        cart.cancelCart();
                        continue;
                    } else {
                        throw new InvalidInputRangeException();
                    }
                }

                // [ ORDER MENU ] 인덱스에 포함되지 않은 번호를 누른 경우, 입력값 검증
                validInputRangeValue(categoryIndex, this.getCategoryIndexList());
                Menu menu = this.menus.get(categoryIndex-1);
                showSelectedCategoryMenu(menu);

                while (true) {
                    // 사용자로부터 [ BURGERS MENU ] 창에서 주문할 메뉴 아이템 인덱스 입력받음
                    String inputMenuIndex = sc.next();
                    if ("0".equals(inputMenuIndex)) {
                        System.out.println("메인 화면으로 돌아갑니다.");
                        break;
                    }

                    // 검증 및 변환
                    validInputValue(inputMenuIndex);
                    int menuIndex = Integer.parseInt(inputMenuIndex);
                    validInputRangeValue(menuIndex, menu.getMenuItemIndexList());

                    MenuItem menuItem = menu.getMenuItems().get(menuIndex - 1);
                    cart.showCartAddMsg(menuItem);
                    String inputCartIndex = sc.next();
                    if ("1".equals(inputCartIndex)){
                        cart.addCart(menuItem);
                        break;
                    } else if ("2".equals(inputCartIndex)){
                        break;
                    } else {
                        throw new InvalidInputRangeException();
                    }
                }

            } catch (InvalidInputException | InvalidInputRangeException | MenuItemNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

    }

    // [ MAIN MENU ] 창에서 보여질 인덱스 리스트 세팅
    public void setCategoryIndexList() {
        for(int i = 0; i < menus.size(); i++){
            categoryIndexList.add(i+1);
        }
    }

    // [ ORDER MENU ] 창에서 보여질 인덱스 리스트 세팅, 변수와 리스트에 각각 저장
    public void setOrderIndex() {
        int maxIndex = Collections.max(categoryIndexList);
        orderIndexList.add(maxIndex + 1);
        orderIndexList.add(maxIndex + 2);
        orderIndex = maxIndex + 1;
        cancelIndex = maxIndex + 2;
    }

    // 주문 완료 메서드, 할인 정보를 보여주고 최종 가격을 보여줌
    public void completeOrder() throws InvalidInputRangeException {
        showDiscountInformation();
        String input = sc.next();
        double total = Discount.fromDiscount(input, cart.getTotal());
        System.out.println("주문이 완료되었습니다. 금액은 W" + total + " 입니다");
        cart.cancelCart();
    }

    public List<Integer> getCategoryIndexList() {
        return this.categoryIndexList;
    }

    /*
    * 키오스크에서 입력 상황에 맞게 출력될 메시지 메서드
     */
    public void showOrderConfirmMsg() {
        System.out.println("[ ORDER MENU ]");
        System.out.println( orderIndexList.get(0) + ". Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println(orderIndexList.get(1) + ". Cancel       | 진행중인 주문을 취소합니다.");
    }

    public void showOrderListMsg() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        cart.showCartItems();
        cart.showTotalCost();
        System.out.println("1. 주문      2. 메뉴판      3. 메뉴 삭제");
    }

    private void showCategories() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println(i + 1 + ". " + this.menus.get(i).getCategory());
        }
        System.out.println("0. 종료 | 종료");
    }

    private void showSelectedCategoryMenu(Menu menu) {
        System.out.println("[ " + menu.getCategory()+ " MENU ]");
        menu.showMenuItems();
        System.out.println("0. 뒤로가기");
    }

    private void showDiscountInformation() {
        System.out.println("[ 할인 정보를 입력해주세요. ]");
        Discount.showInformation();
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
