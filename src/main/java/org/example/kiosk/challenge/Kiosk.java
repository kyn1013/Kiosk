package org.example.kiosk.challenge;

import org.example.kiosk.common.exception.InvalidInputException;
import org.example.kiosk.common.exception.InvalidInputRangeException;
import org.example.kiosk.common.exception.MenuItemNotFoundException;

import java.util.*;

public class Kiosk {

    private final List<Menu> menus;
    private final Cart cart = new Cart();
    private final String NUMBER_REG = "[0-9]+";
    private final List<Integer> categoryIndexList = new ArrayList<>(); // 카테고리 인덱스 리스트임 1 2 3
    private final List<Integer> orderIndexList = new ArrayList<>(); // 카테고리 아래 오더메뉴 리스트 4 5
    private int orderIndex;
    private int cancelIndex;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        setCategoryIndexList();
        setOrderIndex();

        while (true) {
            try {
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

                // 4나 5를 누름 => 넘어가
                if (orderIndexList.contains(categoryIndex)) {
                    // 4번 누르기
                    if (categoryIndex == orderIndex){
                        showOrderListMsg();
                        String orderCompleteIndex = sc.next();
                        if ("1".equals(orderCompleteIndex)){
                            //여기서 할인 정보를 보여줘야 함
                            showDiscountInformation();
                            String input = sc.next();
                            double total = Discount.fromDiscount(input, cart.getTotal());
                            cart.completeOrder(total);
                            break;
                        } else if ("2".equals(orderCompleteIndex)) {
                            continue;
                        } else if ("3".equals(orderCompleteIndex)){
                            System.out.print("삭제할 메뉴 이름을 입력하세요 : ");
                            String name = sc.next();
                            cart.deleteCartItem(name);
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

                validInputRangeValue(categoryIndex, this.getCategoryIndexList());

                Menu menu = this.menus.get(categoryIndex-1);
                showSelectedCategoryMenu(menu);

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
                    showCartAddMsg(menuItem);
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

            } catch (InvalidInputException | InvalidInputRangeException | MenuItemNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void setCategoryIndexList() {
        for(int i = 0; i < menus.size(); i++){
            categoryIndexList.add(i+1);
        }
    }

    public void setOrderIndex() {
        int maxIndex = Collections.max(categoryIndexList);
        orderIndexList.add(maxIndex + 1);
        orderIndexList.add(maxIndex + 2);
        orderIndex = maxIndex + 1;
        cancelIndex = maxIndex + 2;
    }

    public List<Integer> getCategoryIndexList() {
        return this.categoryIndexList;
    }

    public void showConfirmOrderMsg() {
        System.out.println("[ ORDER MENU ]");
        System.out.println( orderIndexList.get(0) + ". Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println(orderIndexList.get(1) + ". Cancel       | 진행중인 주문을 취소합니다.");
    }

    public void showCartAddMsg(MenuItem menuItem) {
        System.out.println("선택한 메뉴 : " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
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
