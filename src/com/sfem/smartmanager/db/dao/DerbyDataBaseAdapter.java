package com.sfem.smartmanager.db.dao;
import com.sfem.smartmanager.db.dao.interfaces.DataBaseAdapter;
import com.sfem.smartmanager.db.dao.interfaces.EnergyMngrDAO;
import com.sfem.smartmanager.db.dao.interfaces.StationDAO;
import com.sfem.smartmanager.db.dao.interfaces.UsersDAO;;

public class DerbyDataBaseAdapter implements DataBaseAdapter{
	
	@Override
	public UsersDAO getUsersDAO() {
		// TODO Auto-generated method stub
		return new SMDBUsersDAO();
	}
}
