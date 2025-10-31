package com.kt.controller;

// @Bean 달아놓으면 포조 아닌게 눈에 바로보여야함.
public class HelloBoorController {
	public String helloWorld(String name){
		return name + "! Hello, World!"; // POJO 인게 눈에 보여야됨.
	}
}
