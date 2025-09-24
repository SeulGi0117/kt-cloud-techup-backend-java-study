package study.seulgi.java.ch01.basics;

public class Problem06_TicketPriceCalculator1 {
    enum Age {CHILD, TEEN, ADULT, SENIOR}

    enum Day {WEEKEND, WEEKDAY}

    public static int price(Age age, Day day) {
        int agePrice = 0;
        int dayPrice = 0;
        int sum = 0;

        switch (age) {
            case CHILD -> agePrice = 7000;
            case TEEN -> agePrice = 9000;
            case ADULT -> agePrice = 12000;
            case SENIOR -> agePrice = 8000;
        }
        switch (day) {
            case WEEKEND -> dayPrice = 2000;
            case WEEKDAY -> dayPrice = 0;
        }

        sum = agePrice + dayPrice;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(price(Age.ADULT, Day.WEEKDAY));  // 12000 출력
        System.out.println(price(Age.ADULT, Day.WEEKEND));  // 14000 출력
        System.out.println(price(Age.CHILD, Day.WEEKDAY));  // 7000 출력
        System.out.println(price(Age.SENIOR, Day.WEEKEND));  // 10000 출력
    }
}
