package com.revature.services;

import java.util.List;

import com.revature.models.ErsReimbursement;
import com.revature.repos.ErsReimbursementDAO;
import com.revature.repos.ErsReimbursementImpl;

public class ReimbursementService {
	
	public static ErsReimbursementDAO reimbursementDAO = new ErsReimbursementImpl();
	
	public List<ErsReimbursement> getAllReimbursements(){
		return reimbursementDAO.findAllReimbursements();
	}
	
	public boolean addReimbursement(ErsReimbursement reimbursement) {
		return reimbursementDAO.addReimbursement(reimbursement);
	}
	
	public boolean changeStatus(int reimbId, String status, String resolver) {
		return reimbursementDAO.changeStatus(reimbId, status, resolver);
	}

	public List<ErsReimbursement> findEmployeeReimbursementsById(int id){
		return reimbursementDAO.findEmployeeReimbursementsById(id);
	}
}
