package study.seulgi.java.ch01.basics.machine;

import java.util.Objects;

public class Drink {
	private final int id;
	private final String name;
	private final Money price;

	public Drink(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = new Money(price);
		// 콜라 5000원 사이다 5000 원, 이렇게 머니가 여러개 만들어져도 똑같은 5000원이라 new money해도 상관없다
		//
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Money getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())    // 여기 통과하면 파일멸 똑같다는 뜻
			return false;
		// (Drink) o; 이걸 다움 캐스팅이라고 부른다. // Object 상위클래스에서 Drink로 다운 캐스팅함.
		// 형번환 해서 타입이 같은지 비교하는 거임
		Drink drink = (Drink)o;
		// id는 값이 똑같으면 다른 콜라더라도 다 같은 콜라라고 본다.
		// 5만원 돈을 10만장 찍어내도 5만==5만 이렇게 똑같다고 보자라는 뜻
		return id == drink.id && price == drink.price && Objects.equals(name, drink.name);

		// Drink 인데 (Money)가 들어오게 되는경우 막아줌
		// Drink drink1과 drink2가 똑같냐? 파일명(drink money) 다르면
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price);
		// 드링크 (1, 콜라, 1000), 드링크 (1, 펩시콜라23, 1200)
		// => 이러면 컴퓨터는 다른건줄 아니까 id 1번 코드를 보고 아 얘네는 똑같은 콜라야 라고 인식함
	}

	@Override
	public String toString() {
		return String.format("%d. %s (%d원)\n", id, name, price.getAmount());
	}
}

