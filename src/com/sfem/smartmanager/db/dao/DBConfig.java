package com.sfem.smartmanager.db.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sfem.smartmanager.db.Datas.DBConstants;

//Since dont have any time just hard core the database structure. this can be moved to configuration xml later
public class DBConfig {

	@SuppressWarnings("serial")
	private static final Map<String, String> creatTableSQLMap = new HashMap<String, String>()
		{
		{
			put(DBConstants.USRES_TABLE,"CREATE TABLE "+DBConstants.USRES_TABLE + 
										"(USER_ID INTEGER NOT NULL "
										+ "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
										+ "(START WITH 1000, INCREMENT BY 1), " +
										" USER_NAME VARCHAR(25) NOT NULL, " + 
										" PASSWORD VARCHAR(25) NOT NULL, " + 
										" FIRST_NAME VARCHAR(50) NOT NULL, " + 
										" LAST_NAME VARCHAR(50), " + 
										" EMAIL VARCHAR(25) NOT NULL, " + 
										" MOBILE_NUMBER NUMERIC(15))"
										);
			put(DBConstants.GROUPS_TABLE,"CREATE TABLE "+DBConstants.GROUPS_TABLE + 
					"(GROUP_ID INTEGER NOT NULL "
					+ "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
					+ "(START WITH 100, INCREMENT BY 1), " +
					" USER_ID INTEGER NOT NULL, " + 
					" GROUP_VALUE INTEGER DEFAULT 1 NOT NULL)"
					);
			put(DBConstants.PERMISSIONS_TABLE,"CREATE TABLE "+DBConstants.PERMISSIONS_TABLE + 
					"(PERMISSION_ID INTEGER NOT NULL "
					+ "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
					+ "(START WITH 200, INCREMENT BY 1), " +
					" USER_ID INTEGER NOT NULL, " + 
					" GROUP_ID INTEGER NOT NULL, " + 
					" PERMISSION_VALUE INTEGER DEFAULT 2 NOT NULL)"
					);
			put(DBConstants.STATION_TABLE,"CREATE TABLE "+DBConstants.PERMISSIONS_TABLE + 
					"(STATION_ID INTEGER NOT NULL "
					+ "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
					+ "(START WITH 300, INCREMENT BY 1), " +
					" USER_ID INTEGER NOT NULL, " + 
					" DISPLAY_PATH VARCHAR(200) NOT NULL)"
					);
			put(DBConstants.STATION_HISTORY_TABLE,"CREATE TABLE "+DBConstants.PERMISSIONS_TABLE + 
					"(HISTORY_ID INTEGER NOT NULL "
					+ "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
					+ "(START WITH 500, INCREMENT BY 1) , " +
					" STATION_ID INTEGER NOT NULL, " + 				
					" HISTORY_VALUE VARCHAR(4096))"
					);
			put(DBConstants.ENERGY_CONSUMPTION_TABLE,"CREATE TABLE "+DBConstants.ENERGY_CONSUMPTION_TABLE + 
					"(CONSUMPTION_ID INTEGER NOT NULL "
					+ "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
					+ "(START WITH 600, INCREMENT BY 1) , " +
					" APLIANCE_NAME VARCHAR(4096) NOT NULL, " + 				
					" APLIANCE_VALUE VARCHAR(4096) NOT NULL, " +
					"DATE DATE NOT NULL)"
					);
		}
		};
		
	@SuppressWarnings("serial")
	private static final Map<String, ArrayList<String>> dbTableFieldMap = new HashMap<String, ArrayList<String>>()
		{
		{
			put(DBConstants.USRES_TABLE,new ArrayList<String>(Arrays.asList("USER_ID","USER_NAME","PASSWORD","FIRST_NAME","LAST_NAME","EMAIL","MOBILE_NUMBER")));
			put(DBConstants.GROUPS_TABLE,new ArrayList<String>(Arrays.asList("GROUP_ID","USER_ID","GROUP_VALUE")));
			put(DBConstants.PERMISSIONS_TABLE,new ArrayList<String>(Arrays.asList("PERMISSION_ID","USER_ID","GROUP_ID","PERMISSION_VALUE")));
			put(DBConstants.STATION_TABLE,new ArrayList<String>(Arrays.asList("STATION_ID","USER_ID","DISPLAY_PATH")));
			
			put(DBConstants.STATION_HISTORY_TABLE,new ArrayList<String>(Arrays.asList("HISTORY_ID","STATION_ID","HISTORY_VALUE")));
			put(DBConstants.ENERGY_CONSUMPTION_TABLE,new ArrayList<String>(Arrays.asList("CONSUMPTION_ID","APLIANCE_NAME","APLIANCE_VALUE","DATE")));
		}
		};
	public static String GetTableCreationSQL(String tableNameKey )
	{
		return creatTableSQLMap.get(tableNameKey).toString();
	}
	public static ArrayList<String> GetTableFieldList(String tableNameKey )
	{
		ArrayList<String> values = dbTableFieldMap.get(tableNameKey);
		return values;
	}
}
