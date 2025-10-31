package study.seulgi.java.ch01.sideEffect;

// 고객 등급
// 등급별로 할인율이 있다
// .. 과일을 판매한다
// 	과일 가격은 과일별로 다르다
// 	총 금액을 계산해본다
public class SideEffect01 {
	// 간접적인 사이드 이펙트. 여기에 gold 추가하면 밑에 뭐 추가된 getprice 코드가 없으니 값이 제대로 안나옴.
	public enum Grade {
		BASIC,  // 할인율 10%
		SILVER,  // 할인율 20%
		GOLD
	}

	// 공통되는 할인율 코드를 따로 만든다.
	static double discountFctor(Grade grade) {
		return switch (grade) {
			case BASIC -> 0.9;
			case SILVER -> 0.8;
			case GOLD -> 0.7;
		};
	}

	// 직접적인 사이드 이펙트
	// static public int applePrice = 1_000;
	// static public int bananaPrice = 1_200;

	// 직접적인 사이드 이펙트 해결책. final 키워드를 붙인다. 상수값은 대문자로 작성한다.
	final static public int APPLE_PRICE = 1_000;
	final static public int BANANA_PRICE = 1_200;

	// 이게 사이드 이펙트를 줄여서 깔끔해진 코드.
	static double getApplePrice1(Grade grade) {
		return APPLE_PRICE * discountFctor(grade);
	}

	static double getApplePrice(Grade grade) {
		// applePrice = 1_500; //	직접적인 사이드 이펙트 발생
		double discountPrice = 0;    // 얘도 사이드이펙트 난다. 함수안에서 값이 자꾸 변경됨
		switch (grade) {
			case BASIC:
				discountPrice = APPLE_PRICE * 0.9;
			case SILVER:
				discountPrice = APPLE_PRICE * 0.8;
		}
		return discountPrice;
	}

	static double getBpplePrice(Grade grade) {
		double discountPrice = 0;
		switch (grade) {
			case BASIC:
				discountPrice = BANANA_PRICE * 0.9;
			case SILVER:
				discountPrice = BANANA_PRICE * 0.8;
		}
		return discountPrice;
	}

	static void main() {
		System.out.println("사과 가격 기본등급: " + getApplePrice(Grade.BASIC));
		System.out.println("사과 가격 실버등급: " + getApplePrice(Grade.SILVER));
		System.out.println("바나나 가격 기본등급: " + getBpplePrice(Grade.BASIC));
	}
}
