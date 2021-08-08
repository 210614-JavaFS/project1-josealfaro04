package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ErsReimbursementStatus;
import com.revature.utils.ConnectionUtil;

public class ErsReimbursementStatusImpl implements ErsReimbursementStatusDAO {

	@Override
	public ErsReimbursementStatus findErsReimbursementStatusId(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			ErsReimbursementStatus status = new ErsReimbursementStatus();
			
			while(result.next()) {
				status.setReimbStatusId(result.getInt("reimb_status_id"));
				status.setReimbStatus(result.getString("reimb_status"));
			}
			
			return status;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
 