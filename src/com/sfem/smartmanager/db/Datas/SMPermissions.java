package com.sfem.smartmanager.db.Datas;

import java.io.Serializable;

import com.sfem.smartmanager.db.Datas.Interfaces.Permissions;

public class SMPermissions implements Permissions {
	private String PermissionsId;
	private Integer PermissionsValue;

	public String GetPermissionsId() {
		// TODO Auto-generated method stub
		return PermissionsId;
	}


	public int GetPermissionsValue() {
		// TODO Auto-generated method stub
		return PermissionsValue;
	}

	public void SetPermissionsValue(int value) {
		// TODO Auto-generated method stub
		PermissionsValue = value;
	}


	@Override
	public void SetPermissionsId(String id) {
		// TODO Auto-generated method stub
		PermissionsId = id;
	}

	
}
