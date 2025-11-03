package study.seulgi.java.ch01.basics.generic;

// 원래 코드
// public record Box(
// 	String name){
// }

// 제네릭은 클래스 뿐만 아니라 메서드에도 사용가능하다
public record Box<T>(
	// 이렇게 해주면 String 넣으면 String되고, Integer 넣으면 Integer 된다.
	T value	// 박스의 용도에 따라서 어떤 값을 가지고 있는 의미로 사용되게.
){
}
