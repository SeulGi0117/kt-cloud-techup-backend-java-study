package study.seulgi.java.ch01.basics.generic;

public class Calculate<T extends Number> {
	public double add(T a, T b) {
		// param으로 뭐가 들어올지 모름, String integer, Double Boolean
		// 난 Integer Double만 받고 싶은데? => Number를 상속 하면된다!
		// return a + b;

		return a.doubleValue() + b.doubleValue();
	}

	// static일때는 <T> 를 붙여줘야한다. 인스턴스가 생성이 될때 T라는 친구가 뭔지 모르는 단계.
	public static <T extends Number> double subtract(T a, T b){
		return a.doubleValue() - b.doubleValue();
	}
}
