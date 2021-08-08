package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ErsReimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementController {

	private static ReimbursementService reimbursementService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void findEmployeeReimbursementsById(HttpServletResponse response, int id) throws IOException {
		List<ErsReimbursement> list = reimbursementService.findEmployeeReimbursementsById(id);
		
		String json = objectMapper.writeValueAsString(list);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
	}
	
	public void getAllReimbursements(HttpServletResponse response) throws IOException {
		List<ErsReimbursement> list = reimbursementService.getAllReimbursements();
		
		String json = objectMapper.writeValueAsString(list);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
		
	}
	
	public void changeStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		ErsReimbursement reimbursement = objectMapper.readValue(body, ErsReimbursement.class);
		
		if(reimbursementService.changeStatus(reimbursement.getReimbId(), reimbursement.getReimbStatusId(), reimbursement.getReimbResolver())) {
			response.setStatus(200);
		} else {
			response.setStatus(406);
		}
		
	}
	
	public void addReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		ErsReimbursement reimbursement = objectMapper.readValue(body, ErsReimbursement.class);
		
		if(reimbursementService.addReimbursement(reimbursement)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}

	

}
