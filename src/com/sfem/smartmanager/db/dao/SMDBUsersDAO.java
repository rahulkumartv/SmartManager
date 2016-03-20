package com.sfem.smartmanager.db.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sfem.smartmanager.Energy.Model.EnergyConsumption;
import com.sfem.smartmanager.db.Datas.DBConstants;
import com.sfem.smartmanager.db.Datas.SMGroup;
import com.sfem.smartmanager.db.Datas.SMPermissions;
import com.sfem.smartmanager.db.Datas.SMUser;
import com.sfem.smartmanager.db.Datas.Interfaces.User;
import com.sfem.smartmanager.db.dao.interfaces.EnergyMngrDAO;
import com.sfem.smartmanager.db.dao.interfaces.StationDAO;
import com.sfem.smartmanager.db.dao.interfaces.UsersDAO;

public class SMDBUsersDAO implements UsersDAO{

	@Override
	public StationDAO getStationDAO( int userid) {
		// TODO Auto-generated method stub
		return new SMDBStationDAO(userid);
	}

	@Override
	public EnergyMngrDAO getEnergyMngrDAO() {
		// TODO Auto-generated method stub
		return new SMDBEnergymngrDAO();
	}

	@Override
	public boolean insertUser( Connection conn, User userObj) {
		// TODO Auto-generated method stub
		boolean bRet = true;
		try
		{
			// check tables exist else create it
			if(!DBUtils.isTableExist(conn, DBConstants.USRES_TABLE))
			{
				if(DBUtils.ExecuteQuery(conn, DBConfig.GetTableCreationSQL(DBConstants.USRES_TABLE)))
				{
					bRet = true;
				}
				else
				{
					bRet = false;
				}
			}
			if( bRet && !DBUtils.isTableExist(conn, DBConstants.GROUPS_TABLE ))
			{
				if(DBUtils.ExecuteQuery(conn, DBConfig.GetTableCreationSQL(DBConstants.GROUPS_TABLE)))
				{
					bRet = true;
				}
				else
				{
					bRet = false;
				}
			}
			
			
			if( bRet && !DBUtils.isTableExist(conn, DBConstants.PERMISSIONS_TABLE ))
			{
				if(DBUtils.ExecuteQuery(conn, DBConfig.GetTableCreationSQL(DBConstants.PERMISSIONS_TABLE)))
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
				//insert User info to USer group and permision table
				ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.USRES_TABLE);
				String SqlQuey =String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', %s)",
						                      DBConstants.USRES_TABLE,tableField.get(1),tableField.get(2),
						                      tableField.get(3),tableField.get(4),tableField.get(5),tableField.get(6),
						                      userObj.GetUserName(),userObj.GetPassword(),
						                      userObj.GetUserFirstName(),userObj.GeUserLastName(),
						                      userObj.GetUserEmail(),userObj.GetUserMobile());
				int nUserId =DBUtils.InsertInTable(conn, SqlQuey);
				tableField = DBConfig.GetTableFieldList(DBConstants.GROUPS_TABLE);
				if( nUserId != -1 )
				{
					SqlQuey =String.format("INSERT INTO %s (%s, %s) VALUES (%s, %s)",
		                      DBConstants.GROUPS_TABLE,tableField.get(1),tableField.get(2),
		                      nUserId,userObj.GetGroupDetails().GetGroupValue()
		                      );
					int nGrpId  =DBUtils.InsertInTable(conn, SqlQuey);
					tableField = DBConfig.GetTableFieldList(DBConstants.PERMISSIONS_TABLE);
					if( nGrpId != -1 )
					{
						SqlQuey =String.format("INSERT INTO %s (%s, %s, %s) VALUES (%s, %s, %s)",
			                      DBConstants.PERMISSIONS_TABLE,tableField.get(1),tableField.get(2),tableField.get(3),
			                      nUserId,nGrpId,userObj.GetPermissionsDetails().GetPermissionsValue()
			                      );
						int nPermId  =DBUtils.InsertInTable(conn, SqlQuey);
						if( nPermId != -1 )
						{
							bRet = true;
						}
					}
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
	//this will implement later
	public boolean deleteCustomer( Connection con, User UserObj) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean bRet = true;
		try
		{
		}
		catch(Exception e)
		{
			bRet = false;
		}
		return bRet;		
	}

	@Override
	public SMUser findUser(Connection conn,User userObj) {
		// TODO Auto-generated method stub
		SMUser rsUser = null;
		try
		{
			ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.USRES_TABLE);
			String SqlQuery =String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s' ",
					                        DBConstants.USRES_TABLE,
					                        tableField.get(1),userObj.GetUserName(),
					                        tableField.get(2),userObj.GetPassword());
			ResultSet userDetails = DBUtils.GetQueryResult(conn, SqlQuery);
			if( userDetails!= null && userDetails.next())
			{
				rsUser = new SMUser();
				rsUser.SetUserId(userDetails.getString(1));
				rsUser.SetUserName(userDetails.getString(2));
				rsUser.SetPassword(userDetails.getString(3));
				rsUser.SetUserFirstName(userDetails.getString(4));
				rsUser.SetUserLastName(userDetails.getString(5));
				rsUser.SetUserEmail(userDetails.getString(6));
				rsUser.SetUserMobile(userDetails.getString(7));
				
				tableField = DBConfig.GetTableFieldList(DBConstants.GROUPS_TABLE);
				SqlQuery =String.format("SELECT * FROM %s WHERE %s = %s",
                        DBConstants.GROUPS_TABLE,
                        tableField.get(1),rsUser.GetUserId());
				ResultSet grpDetails = DBUtils.GetQueryResult(conn, SqlQuery);
				if( grpDetails!= null && grpDetails.next())
				{
						SMGroup GrpObj = new SMGroup();
						GrpObj.SetGroupId(grpDetails.getString(1));
						GrpObj.SetGroupValue(grpDetails.getInt(3));
						rsUser.SetGroupDetails(GrpObj);
						
						tableField = DBConfig.GetTableFieldList(DBConstants.PERMISSIONS_TABLE);
						SqlQuery =String.format("SELECT * FROM %s WHERE %s = %s AND %s = %s",
		                        DBConstants.PERMISSIONS_TABLE,
		                        tableField.get(1),rsUser.GetUserId(),
		                        tableField.get(2),GrpObj.GetGroupId());
						ResultSet permDetails = DBUtils.GetQueryResult(conn, SqlQuery);
						if( permDetails!= null && permDetails.next())
						{
							SMPermissions PermObj = new SMPermissions();
							PermObj.SetPermissionsId(permDetails.getString(1));
							GrpObj.SetGroupValue(permDetails.getInt(4));
							rsUser.SetPermissionsDetails(PermObj);
						}						
				}
			}
		}
		catch( Exception e)
		{
			
		}
		return rsUser;
	}
	
	public SMUser GetUser( Connection conn,String userid)
	{
		// TODO Auto-generated method stub
				SMUser rsUser = null;
				try
				{
					ArrayList<String> tableField = DBConfig.GetTableFieldList(DBConstants.USRES_TABLE);
					String SqlQuery =String.format("SELECT * FROM %s WHERE %s = %s",
							                        DBConstants.USRES_TABLE,
							                        tableField.get(0),userid );
					ResultSet userDetails = DBUtils.GetQueryResult(conn, SqlQuery);
					if( userDetails!= null && userDetails.next())
					{
						rsUser = new SMUser();
						rsUser.SetUserId(userDetails.getString(0));
						rsUser.SetUserName(userDetails.getString(1));
						rsUser.SetPassword(userDetails.getString(2));
						rsUser.SetUserFirstName(userDetails.getString(3));
						rsUser.SetUserLastName(userDetails.getString(4));
						rsUser.SetUserEmail(userDetails.getString(5));
						rsUser.SetUserMobile(userDetails.getString(6));
						
						tableField = DBConfig.GetTableFieldList(DBConstants.GROUPS_TABLE);
						SqlQuery =String.format("SELECT * FROM %s WHERE %s = %s",
		                        DBConstants.GROUPS_TABLE,
		                        tableField.get(1),userid);
						ResultSet grpDetails = DBUtils.GetQueryResult(conn, SqlQuery);
						if( grpDetails!= null && grpDetails.next())
						{
								SMGroup GrpObj = new SMGroup();
								GrpObj.SetGroupId(grpDetails.getString(0));
								GrpObj.SetGroupValue(grpDetails.getInt(2));
								rsUser.SetGroupDetails(GrpObj);
								
								tableField = DBConfig.GetTableFieldList(DBConstants.PERMISSIONS_TABLE);
								SqlQuery =String.format("SELECT * FROM %s WHERE %s = %s AND %s = %s",
				                        DBConstants.PERMISSIONS_TABLE,
				                        tableField.get(1),userid,
				                        tableField.get(2),GrpObj.GetGroupId());
								ResultSet permDetails = DBUtils.GetQueryResult(conn, SqlQuery);
								if( permDetails!= null && permDetails.next())
								{
									SMPermissions PermObj = new SMPermissions();
									PermObj.SetPermissionsId(permDetails.getString(0));
									GrpObj.SetGroupValue(permDetails.getInt(3));
									rsUser.SetPermissionsDetails(PermObj);
								}						
						}
					}
				}
				catch( Exception e)
				{
					
				}
				return rsUser;
	}
	
	
	@Override
	//this will implement later
	public boolean updateUser(Connection con,User oldObj, User newObj) {
		// TODO Auto-generated method stub
		boolean bRet = true;
		try
		{
		}
		catch(Exception e)
		{
			bRet = false;
		}
		return bRet;
	}
}
