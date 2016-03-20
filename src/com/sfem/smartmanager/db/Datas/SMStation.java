package com.sfem.smartmanager.db.Datas;

import java.io.Serializable;

import com.sfem.smartmanager.db.Datas.Interfaces.Station;

public class SMStation implements Station{
	
	private String StationId;
	private String StationValue;
	public String GetStationId()
	{
		return StationId;
		
	}
	public void SetStatitionID(String id) {
		// TODO Auto-generated method stub
		StationId = id;
	}	
	
	public String GetStationPath()
	{
		return StationValue;
		
	}
	
	public void SetStationPath( String path)
	{
		StationValue = path;
	}

}
