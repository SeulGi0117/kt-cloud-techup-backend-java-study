package study.seulgi.java.ch01.basics.optional;

import java.util.Map;
import java.util.Optional;

import javax.swing.*;

import study.seulgi.java.ch01.basics.generic.SoutUtil;


public class OptionalMain {
	public static void main(String[] args) {
		String name = null;
		var repository = new MemoryRepository();

		//null을 리턴하면 버그 원인이 되기 쉬우니, Optional을 대신 사용하라는 의미입니다.
		var p1 = repository.findByName("철호");
		var p2 = repository.findByName("슬기");

		System.out.println(p1);
		System.out.println(p2);

		System.out.println(p1.get().name);
		// System.out.println(p2.get().name);

		// 객체가 존재하면 이름출력
		// Optional.get()
		// 값이 없으면 NoSuchElementException 발생
		// p1.ifPresent(person -> System.out.println(person.name));
		// p2.ifPresent(person -> System.out.println(person.name)); // 터짐

		// if(!p2.isEmpty()){
		// 	System.out.println("찾을 수 없어요.");
		// }
		// 어? 잠만, Java Doc에서 orElse에서는 값 없으면 기본값 반환이라고 되어있네?

		// var p3 = p2.orElse(new Person("찾을 수 없어요"));
		// p1.ifPresentOrElse(person -> System.out.println(person.name),
		// 	() -> System.out.println("찾을 수 없어요."));

		// p2.ifPresentOrElse(
		// 	person -> System.out.println(person.name),
		// 	() -> emptyPrint);

		// p2.orElseThrow(IllegalArgumentException::new);
		// var a = p2.orElse(new Person("dd"));
		// var b = p2.orElseGet(()-> new Person("dd"));

		JFrame frame = new JFrame("스윙 찍먹");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
}