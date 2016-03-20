package com.sfem.smartmanager.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sfem.smartmanager.db.Datas.DBConstants;
import com.sfem.smartmanager.db.Datas.SMStation;
import com.sfem.smartmanager.db.Datas.Interfaces.Station;
import com.sfem.smartmanager.db.dao.interfaces.StationDAO;

public class SMDBStationDAO implements StationDAO{

	private int userObjId;
	public SMDBStationDAO( int userid)
	{
		userObjId= userid;
	}
	@Override
	public boolean insertStation(Connection conn,Station userObj) {
		// TODO Auto-generated method stub
		boolean bRet = true;
		try
		{
			// check tables exist else create it
			if(!DBUtils.isTableExist(conn, DBConstants.STATION_TABLE))
			{
				if(DBUtils.ExecuteQuery(conn, DBConfig.GetTableCreationSQL(DBConstants.STATION_TABLE)))
				{
					bRet = true;
				}
				else
				{
					bRet = false;
				}
			}			
			if(bRet)
			{
				bRet = false;
				//insert station xml file path to station table
				ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.STATION_TABLE);
				String SqlQuey =String.format("INSERT INTO %s (%s, %s) VALUES (%s, '%s')",
						                      DBConstants.STATION_TABLE,tableField.get(1),tableField.get(2),						                      
						                      userObjId,userObj.GetStationPath());
				int nStnId = DBUtils.InsertInTable(conn, SqlQuey);
				if( nStnId != -1){
					bRet = true;
				}
			}
		}
		catch(Exception e)
		{
			bRet = false;
		}
		return bRet;
	}

	
	@Override
	public boolean deleteStation(Connection con,Station UserObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SMStation getStation(Connection conn) {
		// TODO Auto-generated method stub
		SMStation rsStation = null;
		try
		{
			ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.STATION_TABLE);
			String SqlQuery =String.format("SELECT * FROM %s WHERE %s = %s",
					                        DBConstants.STATION_TABLE,
					                        tableField.get(1),userObjId);
			ResultSet stnDetails = DBUtils.GetQueryResult(conn, SqlQuery);
			if( stnDetails!= null && stnDetails.next())
			{
				rsStation = new SMStation();
				rsStation.SetStatitionID(stnDetails.getString(1));
				rsStation.SetStationPath(stnDetails.getString(3));
			}
		}
		catch( Exception e)
		{
			
		}
		return rsStation;
	}

	@Override
	public boolean updateStation(Connection con,Station oldObj, Station newObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void SetStationHiStories(Connection conn,Station obj, List<String> histories) {
		// TODO Auto-generated method stub
		boolean bRet = false;
		try
		{
			// check tables exist else create it
			if(!DBUtils.isTableExist(conn, DBConstants.STATION_HISTORY_TABLE))
			{
				if(DBUtils.ExecuteQuery(conn, DBConfig.GetTableCreationSQL(DBConstants.STATION_HISTORY_TABLE)))
				{
					bRet = true;
				}
			}			
			if(bRet)
			{				
				//insert station xml file path to station table
				ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.STATION_HISTORY_TABLE);
				for( String history : histories )
				{
					String SqlQuey =String.format("INSERT INTO %s (%s, %s) VALUES (%s, %s)",
							DBConstants.STATION_HISTORY_TABLE,tableField.get(1),tableField.get(2),
							obj.GetStationId(),history );
					DBUtils.InsertInTable(conn, SqlQuey);
				}
					
			}
		}
		catch(Exception e)
		{
			bRet = false;
		}
	}

	@Override
	public List<String> GetStationHiStories(Connection conn,Station obj) {
		List<String> histories =  new ArrayList<String>();
		try
		{
			ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.STATION_HISTORY_TABLE);
			String SqlQuery =String.format("SELECT %s FROM %s WHERE %s = %s",
											tableField.get(2),
					                        DBConstants.STATION_HISTORY_TABLE,
					                        tableField.get(1),obj.GetStationId());
			ResultSet stnHistDetails = DBUtils.GetQueryResult(conn, SqlQuery);
			while(stnHistDetails.next())
			{				
				histories.add(stnHistDetails.getString(1));
			}
		}
		catch( Exception e)
		{
			
		}
		return histories;
		
	}

}
