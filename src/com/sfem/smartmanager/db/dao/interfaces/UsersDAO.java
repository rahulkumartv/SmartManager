package com.sfem.smartmanager.db.dao.interfaces;

import java.sql.Connection;

import com.sfem.smartmanager.Energy.Model.EnergyConsumption;
import com.sfem.smartmanager.db.Datas.SMUser;
import com.sfem.smartmanager.db.Datas.Interfaces.User;

public interface UsersDAO {
	StationDAO getStationDAO( int userid);
	EnergyMngrDAO getEnergyMngrDAO();
	
	boolean insertUser( Connection con,User userObj);
	boolean deleteCustomer( Connection con,User UserObj);
	SMUser findUser( Connection con,User UserObj);
	SMUser GetUser( Connection con,String userid);
	boolean updateUser(Connection con,User oldObj, User newObj);
}
