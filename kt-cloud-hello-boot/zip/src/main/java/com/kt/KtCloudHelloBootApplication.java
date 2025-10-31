package com.kt;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;

import com.kt.controller.HelloBoorController;
import com.kt.controller.PlayController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
public class KtCloudHelloBootApplication {

	public static void main(String[] args)
	{ // 이거 run 지우면 실행안됨.
		// SpringApplication.run(KtCloudHelloBootApplication.class, args);

		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBean(HelloBoorController.class);
		context.registerBean(PlayController.class);
		context.refresh();
			// 헬로부트컨트롤러가 bean으로 등록됨

		// 이 코드들은 스프링을 직접 사용한게 아니라 톰캣을 사용해서 직접 사용하던 것들이다.

		// 내장된 톰켓을 이용해서 스프링 부트를 실행해 볼까함
		var tomcat = new TomcatServletWebServerFactory();

		// 컨테스트를 익명함수로 받늘 람다로 구현해볼까함 servelt 서블렛
		// 톰켓을 이용해서 섭르렛을 구현을 할숭밖에없다 얜 스프링부트의 근본느낌이라 지울순없고 숨긴거. 근데 한땀한땀 만들려고 하면 만들어야하긴함. 톰캣으로 만들어볼라고
		var webServer = tomcat.getWebServer(servletContext -> {
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override // 이 서비스가 doGet Post를 아우르는 느낌 개념이라
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// hello 라는 요청을 보내면 HelloBootController가 처리하도록 Hello Wolrd가 출력되도록 처리
					// localhost:8080/hello?name=ktcloud 이렇게 했을때
					// name의 값을 콘솔에 출력. ? 뒤에 시작하는걸 파라미터
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals("GET")){
						var controller = context.getBean(HelloBoorController.class);

						var name = req.getParameter("name");
						var result = controller.helloWorld(name);

						System.out.println("name: "+name); // 콘솔에 이름찍기.
						System.out.println("hello1");
						// 헬로월드에 전달해서 + 이름이랑 같이 찍어볼까? 헬로부트 컨트롤러에서.

						// responce
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
						resp.getWriter().println(result);
					}
					if (req.getRequestURI().equals("/game") && req.getMethod().equals("GET")){
						var controller = context.getBean(PlayController.class);

						var name = req.getParameter("name");
						var result = controller.play(name);
					}

					/*
					* 반복적으로 들어오는 get 파라미터 같은거 스프링부트에서 해준다
					* */

				}
			}).addMapping("/*");
		});
		webServer.start();
	}

}
