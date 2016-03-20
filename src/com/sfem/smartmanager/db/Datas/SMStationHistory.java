package com.sfem.smartmanager.db.Datas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.sfem.smartmanager.db.Datas.Interfaces.StationHistory;

public class SMStationHistory implements StationHistory {
	
	private String StnHistoryId;
	private List<String> StationValue = new ArrayList<String>();
	public String GetStnHistoryId()
	{
		return StnHistoryId;
		
	}
	
	public void SetStnHistoryId(String id) {
		// TODO Auto-generated method stub
		StnHistoryId = id;
	}	
	
	public List<String> GetStnHistory( String stationId)
	{
		return StationValue;
		
	}
	
	public void SetStnHistory( List<String> hostories)
	{ 
		if( !hostories.isEmpty())
		{
			Collections.copy(StationValue, hostories);
		}
	}
}
