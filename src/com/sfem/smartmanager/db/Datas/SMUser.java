package com.sfem.smartmanager.db.Datas;


import com.sfem.smartmanager.db.Datas.Interfaces.Group;
import com.sfem.smartmanager.db.Datas.Interfaces.Permissions;
import com.sfem.smartmanager.db.Datas.Interfaces.User;

public class SMUser implements User{

	private String UserId;
	private String UserName;
	private String Password;
	private String FirstName;
	private String LastName;
	private String UserEmail;
	private String UserMobile;
	private Group UserGrp;
	private Permissions UserPerm;
	public String GetUserId() {
		// TODO Auto-generated method stub
		return UserId;
	}
	@Override
	public void SetUserId(String id) {
		// TODO Auto-generated method stub
		UserId = id;
	}	
	public String GetUserName() {
		// TODO Auto-generated method stub
		return UserName;
	}

	public void SetUserName(String username) {
		// TODO Auto-generated method stub
		UserName = username;
	}

	public String GetPassword() {
		// TODO Auto-generated method stub
		return Password;
	}

	public void SetPassword(String paswd) {
		// TODO Auto-generated method stub
		Password = paswd;
	}

	public String GetUserFirstName() {
		// TODO Auto-generated method stub
		return FirstName;
	}

	public void SetUserFirstName(String firstname) {
		// TODO Auto-generated method stub
		FirstName = firstname;
	}

	public void SetUserLastName(String lastname) {
		// TODO Auto-generated method stub
		LastName = lastname;
	}

	public String GeUserLastName() {
		// TODO Auto-generated method stub
		return LastName;
	}

	public String GetUserEmail() {
		// TODO Auto-generated method stub
		return UserEmail;
	}

	public void SetUserEmail(String email) {
		// TODO Auto-generated method stub
		UserEmail = email;
	}

	public String GetUserMobile() {
		// TODO Auto-generated method stub
		return UserMobile;
	}

	public void SetUserMobile(String mobile) {
		// TODO Auto-generated method stub
		UserMobile = mobile;
	}

	public Group GetGroupDetails() {
		// TODO Auto-generated method stub
		return UserGrp;
	}

	public void SetGroupDetails(Group grp) {
		// TODO Auto-generated method stub
		UserGrp = grp;
	}
	
	public Permissions GetPermissionsDetails() {
		// TODO Auto-generated method stub
		return UserPerm;
	}

	public void SetPermissionsDetails(Permissions permission) {
		// TODO Auto-generated method stub
		UserPerm = permission;
	}
	
}
