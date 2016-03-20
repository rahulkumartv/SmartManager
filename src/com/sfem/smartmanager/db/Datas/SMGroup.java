package com.sfem.smartmanager.db.Datas;

import java.io.Serializable;

import com.sfem.smartmanager.db.Datas.Interfaces.Group;

public class SMGroup implements Group {

	private String groupId;
	private Integer groupValue;
	public SMGroup( )
	{
		groupValue = 1;
	}
	public SMGroup( int value)
	{
		groupValue = value;
	}
	public String GetGroupId() {
		// TODO Auto-generated method stub
		return groupId;
	}

	
	public int GetGroupValue() {
		// TODO Auto-generated method stub
		return groupValue;
	}


	public void SetGroupValue(int value) {
		// TODO Auto-generated method stub
		groupValue = value;
	}
	@Override
	public void SetGroupId(String id) {
		// TODO Auto-generated method stub
		groupId = id;
	}

}
