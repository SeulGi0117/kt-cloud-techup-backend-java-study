package study.seulgi.java.ch01.basics.generic;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GenericMain {
	public static void main(String[] args) {
		// Genderic => 타입을 외부에서 정해서 사용한다.
		// 타입을 미리 정하지 않고, 나중에 정해서 사용할 수 있는 문법
		// 클래스, 메서드, 인터페이스에 적용 가능 + 컬렉션에서 엄청나게 많이쓴다.// List, Map, Set 이런 컬렉션 프레임워크
		// 제네릭이왜 나왔는지?

		List drinks = List.of("콜라", "사이다", "환타"); // ⇒ 문제 없음

		List drinks1 = List.of("콜라", "사이다", "환타", 1, 1.5); // ⇒ 갑자기 1 추가해도 여기에선 오류안남. 기술적한계
		// 근데 print하면 Integer를 String으로 바꿀수 없다는 형변환 에러남.
		// => 500에러 바로남. 아주 비일비재하다. 그래서 Generic 이라는 기능이 나왔다.

		// 개발자 특: 애매하면 아예 못하게 차단해버린다. 애매한게 명확하게 해결되면 하게 해준다.
		// <T> Type, <E> Entity, <K, V> Key, Value
		// <Type> 일때는 ReferenceType만
		// List<String> drinks2 = List.of("콜라", "사이다", "환타", 1, 1.5); // 바로 of에 에러남.
		List<String> drinks3 = List.of("콜라", "사이다", "환타");

		// Object로 하게 되면 문제 생겨도 막을 수가 없다. 타입의 안정성을 가져다 주기 위한 안정성 장치로 쓰이게 된다
		List<Object> drinks4 = List.of("콜라", "사이다", "환타",1, 1.5);

		// 이런 애들이 500에러의 주범
		for (int i=0; i< drinks.size(); i++){
			// String drink = drinks.get(i); // get에 오류남. 이 친구의 타입을 모름
			// 스트링 = 리스트. 이렇게 다름.
			// String drink = (String)drinks.get(i); //1.8 이전까지 이렇게 사용햇어야됫음
			String drink = drinks3.get(i);
			System.out.println();

		}

		// List<Object> persons = List.of(
		List<Person> persons = List.of(
			new Person("슬기", 10),
			new Person("민지", 20),
			new Person("지수", 30)
		);
		// Object는 Person의 속성을 모른다.

		// for(Object person: persons){
		for(Person person: persons){
			// var p = (Person) person; // 오브젝트는 무조건 다운캐스팅 해줘야 안의 내용 확인가능
			System.out.println(person.name() + ", " + person.age());
		}

		new Box("철호");
		new Box2(1);
		// 이거 박스 2개 하나로 합치면 안됨? 불편한디 =>
		// Box<Integer> box2 = new Box<Integer>(100);
		// Box<Integer> box2 = new Box<앞에 써서 여기 쓸필요 없다.>(100);
		var box1 = new Box<>("슬기");
		var box2 = new Box<>(100);

		var nameBoxes = new ArrayList<Box<String>>();
		nameBoxes.add(new Box<>("슬기"));
		nameBoxes.add(new Box<>("지수"));

		var quantityBoxes = new ArrayList<Box<Integer>>();
		quantityBoxes.add(new Box<>(1));
		quantityBoxes.add(new Box<>(2));

		// Integer면 아 이거 수량 적힌 박스네. String이면 아 이거 이름 적힌 박스네

		// print 유틸리티 만들어보기. static 안붙이면
		// new SoutUtil() = SoutUtil print() => 뭐 ㅇㅈㄹ로 사용해야됨.
		var numbers = List.of(1,2,3,4,5);
		SoutUtil.print(numbers.toArray());
		SoutUtil.print(quantityBoxes.toArray());

		// ======================= Calcaulate =======================

		// new Calculate<Integer>().add(); 이렇게 new 할때 integer 해줘야 타입을 알지, 그전까지는 그냥 T만 쓰면 모름. 무슨 타입을 받아올지.
		var result = new Calculate<Integer>().add(2,3);
		System.out.println(result);
		System.out.println(Calculate.subtract(1, 2));

		// Object 모든 객체의 상위 클래스. 얘만 같다써도 구조적으로는 문제가 없다는 뜻임.
		// Generic에도 Object 같은 최강자가 존재한다. => 와일드 카드 <?>

		List<Double> doubleList = List.of(1.1, 2.2, 3.3);
		List<String> stringList = List.of("A", "B", "C");
		SoutUtil.print(doubleList);
		SoutUtil.print(stringList);

		// generic
		// 형변환이 필요없고
		// 컴파일 단계에서 타입체크를 해줌
		// 코드의 재사용성이 높음
		// 유지보수 효율이 올라간다.
	}
}
