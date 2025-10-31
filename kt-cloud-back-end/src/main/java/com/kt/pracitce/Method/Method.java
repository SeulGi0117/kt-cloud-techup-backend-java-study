// package com.kt.pracitce.Method;
//
// import java.util.Map;
//
// public class Method {
// 	// Method 함수 = ?? -> 심부름
//
// 	// 내가 난생 처음 심부름을 떠나게 됨
// 	// 엄마가 걱정이 많아서 이것저것 알려줌
// 	// 돈 만원 줄테니까 요 앞에 마트가서 사과 한 팩 사와
// 	// 남는 돈으로 과자도 하나 사오렴
// 	// 아주머니한테 ㅇ니사 잘하고 횡단보도 건널 때 차 조심하고
// 	// 알앗지? 다녀와~
//
// 	// 다음날 ( 첫날보다 설명이 줄었다)
// 	// 엄마 : 만원 줄테니 사과 한 팩 사와
// 	// 나: 알았어.
//
// 	// 다음날 (명령이 더 줄어들었다)
// 	// 엄마: 사과 한 팩
//
// 	// 이걸 함수로 바꾸면?=>
//
// 	// 인자 = 파라미터 = parmeter = 매개변수 => 엄마의 요구 조건을 전달하는 창구
// 	// 리턴 => 요구조건을 수행하고 엄마에게 결과를 전달함.
// 	public void Map(String, String) apple_packge(
// 	String product, int money)
//
// 	{
// 		// 사과는 5000원,받은돈만원 || 바나나는 7000원, 받은 돈 2만원
// 		// 내 과자는 500원
// 		// if를 사용하는 타이밍
// 		// 만약에 사과면 5000을 빼고, 바나나면 7000원을 뺀다.
//
// 		int change = 0;
// 		if (product.equals("사과")) {
// 			change = money - 5000;
// 		} else
// 			change = money - 7000;
// 		change -= 500;
//
// 		// 지금 이건 엄마에게 잔돈을 준다는 행위를 알 수 없다. 잔돈을 삥땅치고 있는 상황
// 		System.out.println("돈 " + money + "원 줄테니까 요 앞에 마트가서 " + product + " 판 팩 사와");
// 		System.out.println("남는 돈으로 과자도 하나 사오렴");
// 		System.out.println("아주머니한테 인사 잘하고 횡단보도 건널 때 차 조심하고");
// 		System.out.println("알앗지 ? 다녀와 ~");
// 		System.out.println("최종 잔돈: " + change + "원");
// 		System.out.println();
//
// 		// 지금 엄머아게 사과도 안주고잔돈도 안주고 있음.
// 		return Map.of(
// 			"product", product,
// 			"change", change + "원"
// 		);
// 	}
// 	// 자바는 하나의 메소드에서 리턴이 하나밖에 안된다... 개충격적이군
// 	// Map -> Key, Value로 이루어진 자료구조
// 	// Set, List -> 배열
// 	// Pair -> Tuple 자바에는 없고 스프링에있다
//
// 	public static void main(String[] args) {
//
// 		apple_packege("사과", 10_000);  // 숫자에 _ 언더바는 아무 의미 없다. 숫자가 커지면 알기 힘드니까 _로 표시해서 직관적으로 알기슁게 하기 위함임
// 		apple_packege("바나나과", 20_000);
//
// 		Map<String, String> res = apple_package("사과", 10_000);
// 		System.out.println("사온물건: " + res.get("product"));
// 		System.out.println("잔돈: " + res.get("change"));
// 		System.out.println("엄마: " + res.get("product") + "잘 사왔네~");
//
// 	}
// }
