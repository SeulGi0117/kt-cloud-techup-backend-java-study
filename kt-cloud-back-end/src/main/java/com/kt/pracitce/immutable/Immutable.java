// package com.kt.pracitce.immutable;
//
// public class Immutable {
// 	// immutatble: 불변의, 변경할 수 없는
// 	// mutable: 변경할 수 있는
//
// 	// 변경이라는 것은 변화하는 것
//
// 	public static void main(String[] args) {
// 		int a = 1;
// 		// 만약 이 a 라는 코드 다음으로 5000줄의 코드가 있다고 하자,
// 		// 이렇게 수 많은 코드 중에서 a의 변화를 추적하기 힘들다.
// 		// 그렇기 때문에 불변성을 지켜야 한다.
// 		a = 2;
// 		// 만약 이 a 라는 코드 다음으로 5000줄의 코드가 있다고 하자,
// 		System.out.println(a);
//
// 		// javascript 에서 var a로 선언하고 찍으면 언디파인이라고 나온다.
// 		var a;
// 		System.out.println(a);
// 		a = 1;// 하지만 이렇게 밑에서 1을 설정하고 찍으면 이 위에도 언디파인이 아닌 1이 나온다.
// 		// => 불변성을 지키지 못했기 때문이다.
// 		System.out.println(a);
//
// 	}
// }
