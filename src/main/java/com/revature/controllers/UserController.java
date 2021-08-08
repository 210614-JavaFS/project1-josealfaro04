package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ErsUsers;
import com.revature.services.UserServices;

public class UserController {
	
	private static UserServices userService = new UserServices();
	private ObjectMapper objectMapper = new ObjectMapper();
	
//	public void getOneUser(String username) {
//		ErsUsers user = userService.getOneUser(username);
//		if(user == null) {
//			System.out.println("failure, no such user");
//		}
//		return;
//	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		ErsUsers user = objectMapper.readValue(body, ErsUsers.class);
		
		if(userService.login(user.getErsUsername(), user.getErsPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getErsUsername());
			ErsUsers passedUser = userService.getOneUser(user.getErsUsername());
			String json = objectMapper.writeValueAsString(passedUser);
			System.out.println(json);
			response.getWriter().print(json);
			response.setStatus(200);
			
		}
//		
//		String signInUser = user.getErsUsername();
//		String signInPass = user.getErsPassword();
//		ErsUsers passedUser = userService.getOneUser(signInUser);
//		String passedPass = passedUser.getErsPassword();
//		boolean temp = false;
//		temp = BCrypt.checkpw(signInPass, passedPass);
//		
//		if(passedUser.getErsUsername() == null) {
//			//TO-DO
//			System.out.println("ERROr avccount not found");
//			response.setStatus(404);
//			return false;
//			
//		} else if (passedUser.getErsUsername().equals(signInUser) && temp) {
//			System.out.println("login sucessssassss");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("userId", passedUser.getErsUsersId());
//			session.setAttribute("username", passedUser.getErsUsername());
//			String json = objectMapper.writeValueAsString(passedUser);
//			response.getWriter().print(json);
//			session.setAttribute("password", passedUser.getErsPassword());
//			session.setAttribute("email", passedUser.getUserEmail());
//			session.setAttribute("userRoleId", passedUser.getUserRoleId());
//			session.setAttribute("userRole", passedUser.getUserRole());
//			response.setStatus(200);
//			return true;
//			
			
		 else {
			System.out.println("no bueno");
			response.setStatus(401);
		}
		
		
	}
//	
//	public void addUser(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
//		BufferedReader reader = request.getReader();
//		
//		StringBuilder stringBuilder = new StringBuilder();
//		
//		String line = reader.readLine();
//		
//		while(line != null) {
//			stringBuilder.append(line);
//			line = reader.readLine();
//		}
//		
//		String body = new String(stringBuilder);
//		ErsUsers user = objectMapper.readValue(body, ErsUsers.class);
//		
//		if(userService.addUser(user)) {
//			response.setStatus(201);
//		} else {
//			response.setStatus(406);
//		}
//		
//	}
//	
//	
	

}
