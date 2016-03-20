package com.sfem.smartmanager.DataManager;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.sfem.smartmanager.db.Datas.SMStation;
import com.sfem.smartmanager.db.dao.DBUtils;
import com.sfem.smartmanager.db.dao.DerbyConnectionClass;
import com.sfem.smartmanager.db.dao.DerbyDataBaseAdapter;
import com.sfem.smartmanager.db.dao.interfaces.ConnectionClass;
import com.sfem.smartmanager.db.dao.interfaces.DataBaseAdapter;
import com.sfem.smartmanager.db.dao.interfaces.StationDAO;
import com.sfem.smartmanager.db.dao.interfaces.UsersDAO;
import com.sfem.smartmanager.webservices.station.XMLDS.fuse;
import com.sfem.smartmanager.webservices.station.XMLDS.pole;
import com.sfem.smartmanager.webservices.station.XMLDS.sensor;
import com.sfem.smartmanager.xml.StationDisplay.Pole;
import com.sfem.smartmanager.xml.StationDisplay.Poles;
import com.sfem.smartmanager.xml.StationHwConfig.Controller;
import com.sfem.smartmanager.xml.StationHwConfig.Controllers;

public class SMStationDatas {

	private final String BINDING_XML_PATH = "C:\\SFEM\\Station\\binding\\binding.xml";
	private List<pole> stationPolesList =  new ArrayList<pole>();
	private List<fuse> stationFusesList =  new ArrayList<fuse>();
	private List<sensor> stationSensorsList =  new ArrayList<sensor>();
	Poles stationDisplayData = null;
	private String UserId;
	Controllers cntrlDatas;
	public SMStationDatas( String userId) throws NamingException
	{
		cntrlDatas = null;
		UserId = userId;
		InitilizeStationData();
	}
	public String getUserId()
	{
		return UserId;
	}
	private void InitilizeStationData( ) throws NamingException
	{
		//ConnectionClass dbConnection = new DerbyConnectionClass();
		//Connection conn = null;	
		try
		{
			
			//DataBaseAdapter dbAdapter = new DerbyDataBaseAdapter();
			//StationDAO stationDAOObj = dbAdapter.getUsersDAO().getStationDAO(Integer.parseInt(UserId));
			
				
			//SMStationDatas.conn = dbConnection.getConnection();
			//SMStation stationObj= stationDAOObj.getStation(conn);
			//if( stationObj!= null)
			if(cntrlDatas == null)
			{
				Serializer serializer = new Persister();
				String strPath = "C:\\SFEM\\Station\\display\\FeederConfig.xml";
				File stnDisplayxml = new File(strPath);
				stationDisplayData = serializer.read(Poles.class, stnDisplayxml);
				
				File bindingXml = new File(BINDING_XML_PATH);
				cntrlDatas = serializer.read(Controllers.class, bindingXml);
				PrepareStationDatas(stationDisplayData.getPoles(),cntrlDatas.getControllers());
			}
			else
			{				
				PrepareStationDatas(stationDisplayData.getPoles(),cntrlDatas.getControllers());
			}
		}
		catch(Exception e)
		{
			
		}
		finally {
			/*try {
				//dbConnection.commitAndClose(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
	}
	
	private void PrepareStationDatas(List<Pole> poles, List<Controller> controllers) {
		// TODO Auto-generated method stub
		
		for (Pole pole : poles)
		{
			String fuseVal = "0";
			String sensorVal= "0";
			String poleVal = "0";
			for (Controller controller : controllers)
			{				
				if( (controller.getControllerType().toLowerCase()).equals("fuse") )
				{
					if( (controller.getControllerBind().toLowerCase()).equals(pole.getPoleFuse().toLowerCase()))
					{
						fuse fuseobj = new fuse();
						fuseobj.Id = pole.getPoleFuse();
						fuseobj.Value = controller.getControllerValue();
						fuseVal = fuseobj.Value;
						
						boolean bAdd = true;
						for (fuse fs : stationFusesList)
						{
							if( fs.Id.equals(fuseobj.Id))
							{
								bAdd = false;							
								if( !fs.Value.equals(fuseobj.Value ) )
								{
									fs.Value =fuseobj.Value;
								}
							}
							
						}
						if(bAdd)
						{
							stationFusesList.add(fuseobj);
						}
					}
					
				}
				if( (controller.getControllerType().toLowerCase()).equals("sensor") )
				{
					if( ( controller.getControllerBind().toLowerCase()).equals(pole.getPoleSensor().toLowerCase()))
					{
						sensor snsrobj = new sensor();
						snsrobj.Id = pole.getPoleSensor();
						snsrobj.Value = controller.getControllerValue();
						sensorVal = snsrobj.Value;
						boolean bAdd = true;
						
						for (sensor sns : stationSensorsList)
						{
							if( sns.Id.equals(snsrobj.Id))
							{
								bAdd = false;							
								if( !sns.Value.equals(snsrobj.Value ) )
								{
									sns.Value =snsrobj.Value;
								}
							}
						}
						if(bAdd)
						{
							stationSensorsList.add(snsrobj);
						}
						
					}
				}
			}
			if( fuseVal.equals("1") && sensorVal.equals("1"))
			{
				poleVal ="1";
			}
			pole poleobj = new pole();
			poleobj.Id = pole.getPoleId();
			poleobj.Value = poleVal;
			boolean bAdd = true;
			for (pole ple : stationPolesList)
			{
				if( ple.Id.equals(poleobj.Id))
				{
					bAdd = false;
					if( !ple.Value.equals(poleobj.Value ) )
					{
						ple.Value =poleobj.Value;
					}
				}
			}
			if(bAdd)
			{
				stationPolesList.add(poleobj);
			}
			
			
		}
	}
	public List<pole> getPoles()
	{
		return stationPolesList;
	}
	
	public List<fuse> getFuses(){
	     return stationFusesList;
	}	
	
	
	public List<sensor> getSensors(){
	     return stationSensorsList;
	}
	
	public void RefreshStationData(String cntrlName, String value) throws NamingException
	{
		PrepareControllers(cntrlName,value);
		InitilizeStationData();
	}
	
	private void PrepareControllers(String cntrlName, String value)
	{		
		List<Controller> controllers = cntrlDatas.getControllers();
		
		for( int nIdx=0;nIdx< controllers.size();nIdx++)
		{			
			if( controllers.get(nIdx).getId().compareToIgnoreCase(cntrlName) == 0 )
			{
				controllers.get(nIdx).SetControllerValue(value);;
			}
		}
	}
}
