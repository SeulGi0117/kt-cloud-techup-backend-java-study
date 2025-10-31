package com.kt.bootcontroller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// controller는 제일 앞에서 요청응ㄹ 처리해주는 역할과 책임.
// 컨트롤러라고 불리면 제일 앞에서 우리 서비스랑 어떻게 연결하고 처리할지 책임을 지는 아이눅나 라고 생각하면 된다.
// @Controller // 원래 컨트롤러라는 메타 어노테이션을 붙여야하긴함.
// @RestController ResquestMapping랑 Controller 같이 붙어있는 어노테이션. 맨 위에 써서 사용가능.
@RestController
// @RequestMapping("/users") prefix 이렇게 붙이기 가능.
public class HelloBootController {
	@RequestMapping(name ="/hello", method = RequestMethod.GET)
	// reqParm에 name으로 지정해주면 받아서 닉네임에 넣기 가능. 이거 되게 많이씀.
	// 변수랑 파라미터 키 값이 같으면 name 안써도 됨.
	public String hellowolrd(@RequestParam(name ="name") String nickname){
		return nickname +"! Hello, World!";
	}

	@RequestMapping(path="/hi", method = RequestMethod.GET)
	public String say(){
		return "hi";
	}

}

