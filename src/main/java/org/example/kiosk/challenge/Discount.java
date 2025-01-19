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


    private final String number;
    private final String type;
    private final double discountRate;

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

    public abstract double discount(double total);

    // 입력받은 문자열과 매칭되는 클래스인지 확인하는 정적 메서드
    public static double fromDiscount(String inputNumber, double total) throws InvalidInputRangeException{
        for (Discount discount : values()) {
            if (discount.getNumber().equals(inputNumber)) {
                return discount.discount(total);
            }
        }
        throw new InvalidInputRangeException();
    }

    public static void showInformation(){
        for (Discount discount : values()) {
            System.out.println(discount.getNumber() + ". " + discount.getType() + " : " + discount.getDiscountRate() * 100 + "%");
        }
    }
}
