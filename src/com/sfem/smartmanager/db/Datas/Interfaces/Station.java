package com.sfem.smartmanager.db.Datas.Interfaces;

import java.io.Serializable;

public interface Station{
	//Set or get station id 
	String GetStationId();
	void SetStatitionID( String id );
	// Get or set History Details
    String GetStationPath();
	void SetStationPath(String path);
}
