package study.seulgi.java.ch01.basics.problem;

public class Problem06_TicketPriceCalculator2 {
	enum Age {CHILD, TEEN, ADULT, SENIOR}

	enum Day {WEEKEND, WEEKDAY}

	enum Show {MATINEE, STANDARD, LATE}

	enum Seat {STANDARD, PRIME}

	public static int price(Age age, Seat seat, Day day, Show show) {
		int agePrice = 0;
		int seatPrice = 0;
		int dayPrice = 0;
		int showPrice = 0;

		int sum = 0;

		switch (age) {
			case CHILD -> agePrice = 7000;
			case TEEN -> agePrice = 9000;
			case ADULT -> agePrice = 12000;
			case SENIOR -> agePrice = 8000;
		}
		switch (seat) {
			case STANDARD -> seatPrice = 0;
			case PRIME -> seatPrice = 3000;
		}
		switch (day) {
			case WEEKEND -> dayPrice = 2000;
			case WEEKDAY -> dayPrice = 0;
		}

		switch (show) {
			case MATINEE -> showPrice = -2000;
			case STANDARD -> showPrice = 0;
			case LATE -> showPrice = -1000;
		}

		sum = agePrice + seatPrice + dayPrice + showPrice;
		if (sum < 5000) {
			sum = 5000;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(price(Age.ADULT, Seat.STANDARD, Day.WEEKDAY, Show.STANDARD));  // 12000 출력
		System.out.println(price(Age.ADULT, Seat.PRIME, Day.WEEKEND, Show.MATINEE));  // 13000 출력
		System.out.println(price(Age.CHILD, Seat.STANDARD, Day.WEEKDAY, Show.MATINEE));  // 5000 출력
		System.out.println(price(Age.SENIOR, Seat.PRIME, Day.WEEKDAY, Show.LATE));  // 10000 출력
	}
}
