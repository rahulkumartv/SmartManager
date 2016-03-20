package com.sfem.smartmanager.db.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;

import com.sfem.smartmanager.db.Datas.DBConstants;
import com.sfem.smartmanager.db.Enums.DBDataSources;
import com.sfem.smartmanager.db.dao.interfaces.ConnectionClass;

public class DerbyConnectionClass implements ConnectionClass{
	public static final DBDataSources DATABASE_DATASOURCE = DBDataSources.DERBY_DATASOURCE;
	public static final DBDataSources DERBY_CONNECTION_URL = DBDataSources.DERBY_CONNECTION_URL;
	//private EmbeddedDataSource dataSourceObj;

	public DerbyConnectionClass()throws NamingException {
		// Obtain our environment naming context
		/*Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		// Look up our data source
		dataSourceObj = new EmbeddedDataSource();//envCtx.lookup(DATABASE_DATASOURCE.toString());
		dataSourceObj.setUser(DBConstants.DERBYDBUSER);
		dataSourceObj.setPassword(DBConstants.DERBYDBPSWD);
		dataSourceObj.setCreateDatabase("create");*/
	}

	public Connection getConnection() throws SQLException {
		//String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String driver = "org.apache.derby.jdbc.ClientDriver";
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String protocol = "jdbc:derby:SmartManagerDB";
		String protocol = "jdbc:derby://localhost:1527/SmartManagerDB";
		Properties properties = new Properties();
		properties.put("create", "true");
		properties.put("user", DBConstants.DERBYDBUSER);
		properties.put("password", DBConstants.DERBYDBPSWD);
		Connection con = DriverManager.getConnection(protocol , properties);//dataSourceObj.getConnection();
		con.setAutoCommit(true);
		return con;
	}

	public void rollBackAndClose(Connection con) throws SQLException {
		con.rollback();
		con.close();
	}

	public void commitAndClose(Connection con) throws SQLException {
		con.commit();
		con.close();
	}
}
