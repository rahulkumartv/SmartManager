package com.sfem.smartmanager.db.dao;

import com.sfem.smartmanager.Energy.Model.EnergyConsumption;
import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;
import com.sfem.smartmanager.db.Datas.DBConstants;
import com.sfem.smartmanager.db.Datas.SMStation;
import com.sfem.smartmanager.db.dao.interfaces.EnergyMngrDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SMDBEnergymngrDAO implements EnergyMngrDAO {
	
	@Override
	//SMDBEnergymngrDAO()
	public boolean insertEnergyTable(Connection conn, EnergyConsumption enrgyCon){
		
		
		boolean bRet = true;
		try
		{
			// check tables exist else create it
			if(!DBUtils.isTableExist(conn, DBConstants.ENERGY_CONSUMPTION_TABLE))
			{
				if(DBUtils.ExecuteQuery(conn, DBConfig.GetTableCreationSQL(DBConstants.ENERGY_CONSUMPTION_TABLE)))
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
				//insert energy consumption fields
				ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.ENERGY_CONSUMPTION_TABLE);
				String SqlQuey =String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s','%s')",
						                      DBConstants.ENERGY_CONSUMPTION_TABLE,tableField.get(1),tableField.get(2),						                      
						                      tableField.get(3),enrgyCon.getDeviceName(), enrgyCon.getDeviceValue(), enrgyCon.getDate());
				 System.out.println(SqlQuey);
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
	public EnergyConsumptionSearch getEnergyConsumption(Connection conn, EnergyConsumptionSearch engyConSearch) {
		
		try
		{
			ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.ENERGY_CONSUMPTION_TABLE);
			String SqlQuery =String.format("SELECT * FROM %s WHERE %s = '%s' AND %s BETWEEN '%s' AND '%s'",                       
											DBConstants.ENERGY_CONSUMPTION_TABLE,
					                        tableField.get(1),engyConSearch.getDeviceName(),tableField.get(3),engyConSearch.getStartDate(),engyConSearch.getEndDate());
			ResultSet energyConsumption = DBUtils.GetQueryResult(conn, SqlQuery);
			double totalVal=0;
			int nIdx = 1;
			List<String> valuList = new ArrayList<String>();
			//seting dummy total value
			valuList.add(0,"0");
			List<String> dateList = new ArrayList<String>();
			dateList.add(0,"2010-02-13");
			while( energyConsumption!= null && energyConsumption.next())
			{
				valuList.add(nIdx,energyConsumption.getString(3));
				dateList.add(nIdx,energyConsumption.getString(4));
				nIdx++;
				//engyConSearch.setDeviceName(energyConsumption.getString(2));
				totalVal = totalVal+ Double.parseDouble(energyConsumption.getString(3));
			}
			valuList.set(0,String.valueOf(totalVal));
			engyConSearch.setDateList(dateList);
			engyConSearch.setDeviceValue(valuList);
		}
		catch( Exception e)
		{
			
		}
		return engyConSearch;
	}

}
