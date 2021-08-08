package com.revature.repos;

import java.util.List;

import com.revature.models.ErsReimbursement;

public interface ErsReimbursementDAO {

	public List<ErsReimbursement> findAllReimbursements();
	public ErsReimbursement findByReimbursementId(int id);
	public boolean updateReimbursement(ErsReimbursement reimbursement);
	public boolean addReimbursement(ErsReimbursement reimbursement);
	public boolean changeStatus(int reimbId, String status, String resolver);
	public List<ErsReimbursement> findEmployeeReimbursementsById(int id);
	public int getByStatusId(String status);
	public int getByTypeId(String type);
	
	
}
