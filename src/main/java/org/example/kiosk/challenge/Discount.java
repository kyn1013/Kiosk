package org.example.kiosk.challenge;

import org.example.kiosk.common.exception.InvalidInputRangeException;

public enum Discount {
    NATIONALHONOREE("1", "국가유공자", 0.1) {
        @Override
        public double discount(double total) {
            return total - total * getDiscountRate();
        }
    },
    SOLDIER("2", "군인", 0.05) {
        @Override
        public double discount(double total) {
            return total - total * getDiscountRate();
        }
    },
    STUDENT("3", "학생", 0.03) {
        @Override
        public double discount(double total) {
            return total - total * getDiscountRate();
        }
    },
    COMMONUSER("4", "일반인", 0) {
        @Override
        public double discount(double total) {
            return total - total * getDiscountRate();
        }
    };

    private final String number; // 할인 정보 인덱스 번호
    private final String type; // 할인 타입
    private final double discountRate; // 할인률

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    Discount(String number, String type, double discountRate) {
        this.number = number;
        this.type = type;
        this.discountRate = discountRate;
    }

    // 각 클래스에 맞게 할인률이 적용되어 할인을 진행할 메서드
    public abstract double discount(double total);

    // 입력받은 문자열과 매칭되는 클래스인지 확인 후, 해당 할인 클래스의 할인을 수행시킨 후 적용된 할인값을 return
    public static double fromDiscount(String inputNumber, double total) throws InvalidInputRangeException{
        for (Discount discount : values()) {
            if (discount.getNumber().equals(inputNumber)) {
                return discount.discount(total);
            }
        }
        // 해당 할인 정보 화면에서 존재하지 않는 값을 입력했다면 에러 발생
        throw new InvalidInputRangeException();
    }

    // 할인 정보를 출력하는 메서드, 굳이 인스턴스화 시킬 필요가 없을 것 같아서 static으로 선언
    public static void showInformation(){
        for (Discount discount : values()) {
            System.out.println(discount.getNumber() + ". " + discount.getType() + " : " + discount.getDiscountRate() * 100 + "%");
        }
    }
}
