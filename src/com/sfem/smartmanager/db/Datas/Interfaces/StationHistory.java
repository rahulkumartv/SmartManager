package com.sfem.smartmanager.db.Datas.Interfaces;

import java.io.Serializable;
import java.util.List;

public interface StationHistory{
	
	//Set or get History Id
	public String GetStnHistoryId();
	void SetStnHistoryId( String id );

	// Get or set History Details
	public List<String> GetStnHistory( String stationId);
	public void SetStnHistory( List<String> hostories);
}
