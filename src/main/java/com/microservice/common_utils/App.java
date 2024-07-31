package com.microservice.common_utils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJ1c2VybmFtZSI6ImpvbnNub3ciLCJzdWIiOiI2IiwiaWF0IjoxNzIyNDI5NTE0LCJleHAiOjE3MjI0MzAxMTR9.txx1FkP7AcKJzZd3juPz6hi3j1-27BV7entBOjWHVoQ";
		Long userId = JwtUtil.validateTokenAndGetUserId(token);
		System.out.println("User ID: " + userId);
	}
}
