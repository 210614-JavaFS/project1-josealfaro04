package com.revature.models;

public class ErsUserRoles {

	private int ersUserRoleId;
	private String userRole;
	
	public ErsUserRoles(int ersUserRoleId, String userRole) {
		super();
		this.ersUserRoleId = ersUserRoleId;
		this.userRole = userRole;
	}
	public ErsUserRoles() {
		super();
	}
	public int getErsUserRoleId() {
		return ersUserRoleId;
	}
	public void setErsUserRoleId(int ersUserRoleId) {
		this.ersUserRoleId = ersUserRoleId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ersUserRoleId;
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErsUserRoles other = (ErsUserRoles) obj;
		if (ersUserRoleId != other.ersUserRoleId)
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ErsUserRoles [ersUserRoleId=" + ersUserRoleId + ", userRole=" + userRole + "]";
	}
	
	
}
