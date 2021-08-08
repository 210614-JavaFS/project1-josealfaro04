package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;

public class FrontControllerServlet extends HttpServlet{
	
	private static ReimbursementController reimbursementController = new ReimbursementController();
	private static UserController userController = new UserController();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		response.setContentType("application/json");
		
		response.setStatus(404); 
		
		final String URL = request.getRequestURI().replace("/project1/", ""); //strips out base url
		
		System.out.println(URL);
		
		String[] UrlSections = URL.split("/");
		
		switch(UrlSections[0]) {
			case "userLogin":
				if(UrlSections.length == 1) {
					if(request.getMethod().equals("POST")) {
						userController.login(request, response);
					}
				}
				break;
			case "logout":
				session.invalidate();
				break;
			case "employee":
				if(UrlSections.length == 1) {
					if(request.getMethod().equals("POST")) {
						reimbursementController.addReimbursement(request, response);
						System.out.println("added with success");
					} else {
						System.out.println("some error occured adding new reimbursement");
					}
					
				} else if(UrlSections.length == 2) {
					if(request.getMethod().equals("GET")) {
						
					}
				}
			
		}
		
	}

	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	@Override 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
