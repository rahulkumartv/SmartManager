package com.sfem.smartmanager.webservices.station;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sfem.smartmanager.DataManager.SMDataManager;
import com.sfem.smartmanager.UI.Resources;
import com.sfem.smartmanager.webservices.station.XMLDS.fuse;
import com.sfem.smartmanager.webservices.station.XMLDS.pole;
import com.sfem.smartmanager.webservices.station.XMLDS.sensor;

@Path("/station")
public class StationService {
	/*@GET
	@Path("/models")
	@Produces(MediaType.APPLICATION_XML)
	public List<> getStationModels(){
	     return ;
	}*/	
	@GET
	@Path("/poles")
	@Produces(MediaType.APPLICATION_XML)
	public List<pole>  getPoles(){
	     try {
	    	 String userToken = Resources.USER_TOKEN;
	    	 List<pole> poles = SMDataManager.getInstance().getPoles(userToken);
	    	 //GenericEntity<List<pole>> genPoleList = new GenericEntity<List<pole>>(poles){};
	    	// return Response.ok(genPoleList).build(); 
	    	 return poles;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@GET
	@Path("/fuses")
	@Produces(MediaType.APPLICATION_XML)
	public List<fuse> getFuses(){
		 try {
			 String userToken = Resources.USER_TOKEN;
			 List<fuse> fuses = SMDataManager.getInstance().getFuses(userToken);
			 //GenericEntity<List<fuse>> genFuseList = new GenericEntity<List<fuse>>(fuses){};
	    	 //return Response.ok(genFuseList).build(); 
			 return fuses;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	@GET
	@Path("/sensors")
	@Produces(MediaType.APPLICATION_XML)
	public List<sensor> getSensors(){
		 try {
			 String userToken = Resources.USER_TOKEN;
			 List<sensor> sensors =  SMDataManager.getInstance().getSensor(userToken);
			// GenericEntity<List<sensor>> genSensorList = new GenericEntity<List<sensor>>(sensors){};
	    	// return Response.ok(genSensorList).build(); 
			 return sensors;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
