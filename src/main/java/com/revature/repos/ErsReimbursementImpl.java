package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.ErsReimbursement;
import com.revature.utils.ConnectionUtil;

public class ErsReimbursementImpl implements ErsReimbursementDAO {
	
	private static Logger log = LoggerFactory.getLogger(ErsReimbursementImpl.class);
	private static ErsUsersDAO ersUserDao = new ErsUsersImpl();

	@Override
	public List<ErsReimbursement> findAllReimbursements() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement;";
			Statement statement = conn.createStatement();
 			ResultSet result = statement.executeQuery(sql);
 			
 			List<ErsReimbursement> list = new ArrayList<>();
 			
 			while(result.next()) {
 				ErsReimbursement reimbursement = new ErsReimbursement();
 				reimbursement.setReimbId(result.getInt("reimb_id"));
 				reimbursement.setReimbAmount(result.getDouble("reimb_amount"));
 				reimbursement.setReimbSubmitted(result.getDate("reimb_submitted").toString());
 				reimbursement.setReimbResolved(result.getDate("reimb_resolved").toString());
 				reimbursement.setReimbDescription(result.getString("reimb_description"));
 				//reimbursement.setReceipt(result.getString("reimb_receipt"));
 				reimbursement.setReimbAuthor(result.getString("reimb_author"));
 				reimbursement.setReimbResolver(result.getString("reimb_resolver"));
 				reimbursement.setReimbStatusId(result.getString("reimb_status_id"));
 				reimbursement.setReimbTypeId(result.getString("reimb_type_id"));
 				
 				list.add(reimbursement);
 				
 			}
			
 			return list;
 			
		}catch(SQLException e) {
			e.printStackTrace();
			log.warn("Error in ErsReimbursementImpl, when looking for reimbursements");
			
		}
		
		
		return null;
	}
	
	@Override
	public boolean addReimbursement(ErsReimbursement reimbursement) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES (?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int authorId = ersUserDao.getByUserId(reimbursement.getReimbAuthor());
			int typeId = getByTypeId(reimbursement.getReimbTypeId());
			int statusId = getByStatusId("pending");
			
			int index = 0;
			statement.setDouble(++index, reimbursement.getReimbAmount());
			statement.setString(++index, reimbursement.getReimbDescription());
			statement.setInt(++index, authorId);
			statement.setInt(++index, statusId);
			statement.setInt(++index, typeId);
			statement.execute();
			return true;
			

		}catch(SQLException e) {
			e.printStackTrace();
			log.warn("Error in ErsReimbursementImpl, when adding reimbursement");

		}
		
		
		return false;
	}

	@Override
	public boolean changeStatus(int reimbId, String status, String resolver) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE ers_reimbursement SET reimb_status_id=?, reimb_resolver=? WHERE reimb_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			int statusId = getByStatusId(status);
			int resolverId = ersUserDao.getByUserId(resolver);
			
			statement.setInt(++index, statusId);
			statement.setInt(++index, resolverId);
			statement.setInt(++index, reimbId);
			statement.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int getByStatusId(String status) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT reimb_status_id FROM ers_reimbursement_status WHERE reimb_status = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, status);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getInt("reimb_status_id");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int getByTypeId(String type) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT reimb_type_id FROM ers_reimbursement_type WHERE reimb_type = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, type);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getInt("reimb_type_id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ErsReimbursement> findEmployeeReimbursementsById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT FROM ers_reimbursement WHERE reimb_author = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			List<ErsReimbursement> list = new ArrayList<>();
			
			while(result.next()) {
				ErsReimbursement reimbursement = new ErsReimbursement();
				reimbursement.setReimbId(result.getInt("reimb_id"));
				reimbursement.setReimbAmount(result.getDouble("reimb_amount"));
				reimbursement.setReimbSubmitted(result.getString("reimb_submitted"));
				reimbursement.setReimbResolved(result.getString("reimb_resolved"));
				reimbursement.setReimbDescription(result.getString("reimb_description"));
				reimbursement.setReimbAuthor(result.getString("reimb_author"));
				reimbursement.setReimbResolver(result.getString("reimb_resolver"));
				reimbursement.setReimbStatusId(result.getString("reimb_status_id"));
				reimbursement.setReimbTypeId(result.getString("reimb_type_id"));
				
				list.add(reimbursement);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	

	
	
	@Override
	public ErsReimbursement findByReimbursementId(int id) {
		return null;
	}
	@Override
	public boolean updateReimbursement(ErsReimbursement reimbursement) {
		return false;
	}



	

	
	

}
