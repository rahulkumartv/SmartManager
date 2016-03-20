package com.sfem.smartmanager.db.dao.interfaces;

import java.sql.Connection;
import java.util.List;

import com.sfem.smartmanager.db.Datas.SMStation;
import com.sfem.smartmanager.db.Datas.Interfaces.Station;

public interface StationDAO {
	//Get or set Station Details
	boolean insertStation( Connection con,Station obj);
	boolean deleteStation( Connection con,Station obj);
	SMStation getStation( Connection con);
	boolean updateStation( Connection con,Station oldObj, Station newObj);
	void SetStationHiStories( Connection con,Station obj, List<String> histories );
	List<String> GetStationHiStories( Connection con,Station obj);
}
