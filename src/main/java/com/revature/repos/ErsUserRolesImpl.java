package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ErsUserRoles;
import com.revature.utils.ConnectionUtil;

public class ErsUserRolesImpl implements ErsUserRolesDAO {

	@Override
	public ErsUserRoles findByErsUserRoleId(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users_role WHERE ers_user_role_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			ErsUserRoles userRoles = new ErsUserRoles();
			
			while(result.next()) {
				userRoles.setErsUserRoleId(result.getInt("ers_user_role_id"));
				userRoles.setUserRole(result.getString("user_role"));
			}
			
			return userRoles;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
