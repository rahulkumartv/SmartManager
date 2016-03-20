package com.sfem.smartmanager.db.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionClass {

	Connection getConnection()throws SQLException;
	void rollBackAndClose(Connection con)throws SQLException;
	void commitAndClose(Connection con)throws SQLException;
}
