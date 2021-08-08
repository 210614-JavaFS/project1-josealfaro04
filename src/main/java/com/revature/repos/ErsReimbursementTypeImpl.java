package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ErsReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ErsReimbursementTypeImpl implements ErsReimbursementTypeDAO{

	@Override
	public ErsReimbursementType findErsReimbursementTypeId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);	
			ResultSet result = statement.executeQuery();
			
			ErsReimbursementType type = new ErsReimbursementType();
			
			while(result.next()) {
				type.setReimbTypeId(result.getInt("reimb_type_id"));
				type.setReimbType(result.getString("reimb_type"));
			}
			
			return type;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
