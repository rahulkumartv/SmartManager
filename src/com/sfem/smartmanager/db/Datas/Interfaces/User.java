package com.sfem.smartmanager.db.Datas.Interfaces;

import java.io.Serializable;

public interface User{
	//User Id
	String GetUserId();
	void SetUserId(String id);
	//UserName
	String GetUserName();
	void SetUserName(String username);
	//Password
	String GetPassword();
	void SetPassword(String paswd);
	//First Name
	String GetUserFirstName();
	void SetUserFirstName(String firstname);
    //Last name
	void SetUserLastName( String lastname);
	String GeUserLastName();
	//Email
	String GetUserEmail();
	void SetUserEmail(String email);
	//UserEmail
	String GetUserMobile();
	void SetUserMobile(String mobile);
	
	//Get or Set Group . Determine Station or enery manger
	//1-station
	//2-Energy Manger default is 1
	Group GetGroupDetails();
	void SetGroupDetails( Group grp);
	
	// Set or get user permission
	//1-Admin
	//2-Engineer
	//3- Operator defulat is 2
	Permissions GetPermissionsDetails();
	void SetPermissionsDetails( Permissions permission);
}
