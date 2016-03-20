package com.sfem.smartmanager.db.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtils {

	public static boolean isTableExist(Connection conn, String tablename) throws SQLException {
		// TODO Auto-generated method stub
		boolean bRet = false;
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getTables(null, null, tablename.toUpperCase(),null);
		if(rs.next())
		{
			bRet = true;
		}		
		return bRet;
	}
	
	public static boolean ExecuteQuery(Connection conn, String Sqlquery)
	{
		boolean bRet = true;
		try
		{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(Sqlquery);
		}
		catch(SQLException se)
		{
			bRet = false;
		}
		catch(Exception e)
		{
		      //Handle errors for Class.forName
			bRet = false;
		}		      
		return bRet;
    }
	
	public static ResultSet GetQueryResult(Connection conn, String Sqlquery)
	{
		ResultSet rs= null;
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(Sqlquery);
			rs = pstmt.executeQuery();
		}
		catch(SQLException se)
		{
		}
		catch(Exception e)
		{
		  
		}		      
		return rs;
    }


	public static int InsertInTable(Connection conn, String Sqlquery)
	{
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(Sqlquery,Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs != null && rs.next())
			{
				return rs.getInt(1);
	         
			}
		}
		catch(Exception e)
		{
		      //Handle errors for Class.forName
			
		}
		return -1;	
	}
	
	public static void Logging( String Message )
	{
		try
		{
			File file = new File("C:\\SFEM\\log.txt");
			FileWriter fstream = new FileWriter(file,true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(Message);
	        //Close the output stream
	        out.close();
		}
		catch(Exception ex)
		{
			
		}
	}
}
