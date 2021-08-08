package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import com.revature.models.ErsUsers;
import com.revature.utils.ConnectionUtil;

import jdk.internal.org.jline.utils.Log;

public class ErsUsersImpl implements ErsUsersDAO {
	
	@Override
	public int getByUserId(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT ers_users_id FROM ers_users WHERE ers_username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getInt("ers_user_id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public ErsUsers getOneUser(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ErsUsers user = new ErsUsers();
			String nameSql = "SELECT * FROM ers_users WHERE ers_username = ?;";
			String roleSql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id = ?";
			
			PreparedStatement nameStatement = conn.prepareStatement(nameSql);
			nameStatement.setString(1, username);
			ResultSet nameResult = nameStatement.executeQuery();
			
			while(nameResult.next()) {
				user.setErsUsersId(nameResult.getInt("ers_users_id"));
				user.setErsUsername(nameResult.getString("ers_username"));
				user.setErsPassword(nameResult.getString("ers_password"));
				user.setUserFirstName(nameResult.getString("user_first_name"));
				user.setUserLastName(nameResult.getString("user_last_name"));
				user.setUserEmail(nameResult.getString("user_email"));
				user.setUserRoleId(nameResult.getInt("user_role_id"));
				
			}
			
			PreparedStatement roleStatement = conn.prepareStatement(roleSql);
			roleStatement.setInt(1, user.getUserRoleId());
			ResultSet roleResult = roleStatement.executeQuery();
			
			

			while(roleResult.next()) {
				user.setUserRole(roleResult.getString("user_role"));
				
			}
			
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public boolean login(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT ers_password FROM ers_users WHERE ers_username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			String userPassword = "";
			
			if(result.next()) {
				userPassword = result.getString("ers_password");
			}
			
			if(!userPassword.isEmpty()) {
				if(BCrypt.checkpw(password, userPassword)) {
					System.out.println("matching passwords");
				} else {
					System.out.println("failure, passwords do not match");
				}
				
				return BCrypt.checkpw(password, userPassword);
			}
			
			System.out.println("username does not match in the system");
			
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
//	@Override
//	public List<ErsUsers> findAllUsers() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//
//	@Override
//	public boolean updateUser(ErsUsers user) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//


	
	
	
}
