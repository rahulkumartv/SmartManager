package com.sfem.smartmanager.DataManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import com.sfem.smartmanager.Energy.Model.EnergyConsumption;
import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;
import com.sfem.smartmanager.db.Datas.SMGroup;
import com.sfem.smartmanager.db.Datas.SMPermissions;
import com.sfem.smartmanager.db.Datas.SMUser;
import com.sfem.smartmanager.db.Datas.Interfaces.Group;
import com.sfem.smartmanager.db.Datas.Interfaces.Permissions;
import com.sfem.smartmanager.db.Datas.Interfaces.User;
import com.sfem.smartmanager.db.dao.DBUtils;
import com.sfem.smartmanager.db.dao.DerbyConnectionClass;
import com.sfem.smartmanager.db.dao.DerbyDataBaseAdapter;
import com.sfem.smartmanager.db.dao.interfaces.ConnectionClass;
import com.sfem.smartmanager.db.dao.interfaces.DataBaseAdapter;
import com.sfem.smartmanager.db.dao.interfaces.UsersDAO;
import com.sfem.smartmanager.webservices.station.XMLDS.fuse;
import com.sfem.smartmanager.webservices.station.XMLDS.pole;
import com.sfem.smartmanager.webservices.station.XMLDS.sensor;
import com.sfem.smartmanager.webservices.station.XMLDS.user;

public final class SMDataManager {
	 private static SMDataManager smDataManager = null;
	 private SFEMEnergyDataMgr energyDataManager;
	 // A user storage which stores <token and , user id>
	 private final Map<String, String> LoggedUserList = new HashMap<String, String>();
	 List<SMStationDatas> stationDataCollection = new ArrayList<SMStationDatas>();
	 private SMDataManager(){
		 
		 energyDataManager = new SFEMEnergyDataMgr();
	 }
	 public static SMDataManager getInstance() {
	        if ( smDataManager == null ) {
	        	smDataManager = new SMDataManager();
	        }

	        return smDataManager;
	    }
	 
	 public User AuthenticateandLoginUser( String usreToken, String username, String password ) throws LoginException, NamingException, SQLException
	 {
		 /*if( LoggedUserList.containsKey(usreToken))
		 {
			 return usreToken;
		 }*/
		 User userObj = new SMUser();
		 userObj.SetPassword(password);
		 userObj.SetUserName(username);
		 ConnectionClass dbConnection = new DerbyConnectionClass();
		 DataBaseAdapter dbAdapter = new DerbyDataBaseAdapter();
		 UsersDAO usrDAO = dbAdapter.getUsersDAO();
		 Connection conn = null;
		 try
		 {
			 conn = dbConnection.getConnection();
		 }
		 catch(Exception e)
		 {
			 
		 }
		 if( conn != null )
		 {
			 userObj = usrDAO.findUser(conn, userObj);
			 dbConnection.commitAndClose(conn);
			 if( userObj == null)
			 {
				 return null;
			 }
			 if( !LoggedUserList.containsValue(userObj.GetUserId()))
			 {
				 LoggedUserList.put(usreToken, userObj.GetUserId());
			 }
			 /*else
			 {
				 return LoggedUserList.get(userObj.GetUserId().toString());
			 }*/
		 }
		 else
		 {
			 return null;
		 }
		 return userObj;
	 }
	 public boolean CreateUser( user cretUser ) throws NamingException, SQLException, ParseException
	 {
		 User userObj = new SMUser();
		 userObj.SetUserName(cretUser.userName);
		 userObj.SetPassword(cretUser.passWord);
		 userObj.SetUserEmail(cretUser.userEmail);
		 userObj.SetUserFirstName(cretUser.firstName);
		 userObj.SetUserLastName(cretUser.LastName);
		 userObj.SetUserMobile(cretUser.userMobile);
		 Group grpObj = new SMGroup();
		 grpObj.SetGroupValue(Integer.parseInt(cretUser.groupId));
		 userObj.SetGroupDetails(grpObj);
		 Permissions prmObj = new SMPermissions();
		 prmObj.SetPermissionsValue(Integer.parseInt(cretUser.permId));
		 userObj.SetPermissionsDetails(prmObj);
		 ConnectionClass dbConnection = new DerbyConnectionClass();
		 DataBaseAdapter dbAdapter = new DerbyDataBaseAdapter();
		 UsersDAO usrDAO = dbAdapter.getUsersDAO();
		 Connection conn = null;
		 try
		 {
			 conn = dbConnection.getConnection();
		 }
		 catch(Exception e)
		 {
			 return false;
		 }
		 if( conn != null )
		 {
			 if( usrDAO.findUser(conn, userObj) != null)
			 {
				 dbConnection.commitAndClose(conn);
				 return false;
			 }
			 boolean bRet =  usrDAO.insertUser(conn, userObj);
			 if( 2 == userObj.GetGroupDetails().GetGroupValue())
			 {
				 EnergyConsumption enrgyCon = new EnergyConsumption();
				 enrgyCon.setDeviceName("Total");
				 enrgyCon.setDeviceValue("20");
				 enrgyCon.setDate("2015-01-01");
				 bRet = dbAdapter.getUsersDAO().getEnergyMngrDAO().insertEnergyTable(conn, enrgyCon );
			 }
			 dbConnection.commitAndClose(conn);
			 return bRet;
		 }
		
		 return true;
	 }
	 
	 public boolean StoreEnergyConsumption( ArrayList<EnergyConsumption> energyConsList){
		 
		 System.out.println("StoreEnergyConsumption reached");
		 if( null != energyConsList){
			 System.out.println("energyConsList not null");
			 DataBaseAdapter dbAdapter = new DerbyDataBaseAdapter();
			 try
			 {
				 ConnectionClass dbConnection = new DerbyConnectionClass();			 
				 Connection conn;
				 conn = dbConnection.getConnection();
				 for(int index = 0; index<energyConsList.size();++index){
					 System.out.println("List is not empty");
					 dbAdapter.getUsersDAO().getEnergyMngrDAO().insertEnergyTable(conn, energyConsList.get(index));
					 }
					 
					 dbConnection.commitAndClose(conn);
					 return true;
			 }
			 catch(Exception e)
			 {
				 return false;
			 }
			 
			 
		 }
		 return false;
		 
	 }
	 
	 public EnergyConsumptionSearch GetEnergyConsumption(EnergyConsumptionSearch search){
		 
		 DataBaseAdapter dbAdapter = new DerbyDataBaseAdapter();
		 Connection conn = getDBConnection();
		 return dbAdapter.getUsersDAO().getEnergyMngrDAO().getEnergyConsumption(conn, search);
		 
	 }
	 
	 public Connection getDBConnection(){
		 
		
		 try
		 {
			 ConnectionClass dbConnection = new DerbyConnectionClass();			 
			 Connection conn;
			 conn = dbConnection.getConnection();
			 return conn;
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 
	 }
	 
	 public boolean UpdateConsumedPower(String consumedPower){
		 System.out.println("UpdateConsumedPower");
		 System.out.println(consumedPower);
		 energyDataManager.setConsumedPower(consumedPower);
		 return true;
	 }
	 
	 public String getConsumedPower(){
	 
		 System.out.println("getConsumedPower");
		 System.out.println(energyDataManager.getConsumedPower());
		 return energyDataManager.getConsumedPower();
	 }
	 
	 public boolean DisconnectUser( String usreToken )
	 {
		 if( LoggedUserList.containsKey(usreToken))
		 {
			 LoggedUserList.remove(usreToken);
			 return true;
		 }
		 return false;
	 }
	 
	 public List<pole> getPoles(String usreToken) throws NamingException 
	 {
		 List<pole> poles  = null;
		 InitializeStationDataCollection(usreToken);
		 for (SMStationDatas smStationDatas : stationDataCollection)
		 {
			 
			 if( smStationDatas.getUserId().compareToIgnoreCase(LoggedUserList.get(usreToken)) == 0)
			 {
				 poles = smStationDatas.getPoles();
				 break;
			 }
		 }
		 return poles;
	 }
	 public List<fuse> getFuses(String usreToken) throws NamingException 
	 {
		 List<fuse> fuses = null;
		 InitializeStationDataCollection(usreToken);
		 for (SMStationDatas smStationDatas : stationDataCollection)
		 {
			 if( smStationDatas.getUserId().compareToIgnoreCase(LoggedUserList.get(usreToken)) == 0)
			 {
				 fuses = smStationDatas.getFuses();
				 break;
			 }
		 }
		 return fuses;
	 }
	 public List<sensor> getSensor(String usreToken) throws NamingException 
	 {
		 List<sensor> sensors = null;
		 InitializeStationDataCollection(usreToken);
		 for (SMStationDatas smStationDatas : stationDataCollection)
		 {
			 if( smStationDatas.getUserId().compareToIgnoreCase(LoggedUserList.get(usreToken)) == 0)
			 {
				 sensors = smStationDatas.getSensors();
				 break;
			 }
		 }
		 return sensors;
	 }

	 private void InitializeStationDataCollection( String usreToken ) throws NamingException
	 {
		 boolean stationAdded = false;
		 for (SMStationDatas smStationDatas : stationDataCollection)
		 {
			 if( smStationDatas.getUserId().compareToIgnoreCase(LoggedUserList.get(usreToken)) == 0)
			 {
				 stationAdded = true;
			 }
		 }
		 if( !stationAdded)
		 {
			 SMStationDatas stationData = new SMStationDatas(LoggedUserList.get(usreToken));
			 stationDataCollection.add(stationData);
		 }
	 }
	 
	 public void RefreshStationData(String cntrlName, String value)
	 {
		 for (SMStationDatas smStationDatas : stationDataCollection)
		 {
			 try {
				smStationDatas.RefreshStationData(cntrlName, value);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
}
