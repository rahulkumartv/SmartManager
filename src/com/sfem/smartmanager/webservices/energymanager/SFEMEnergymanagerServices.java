package com.sfem.smartmanager.webservices.energymanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sfem.smartmanager.DataManager.SMDataManager;
import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;

@Path("/energymanager")
public class SFEMEnergymanagerServices {
	
	
	public SFEMEnergymanagerServices(){
		
	}
	
	@GET
 	@Path("/consumedpower")
 	@Produces(MediaType.APPLICATION_XML)
	public String GetConsumedPower(){
		
		return SMDataManager.getInstance().getConsumedPower();
		
	}
	
	@POST
 	@Path("/consumedenergy")
 	@Produces(MediaType.APPLICATION_XML)
 	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response GetConsumedEnergy(@FormParam("device")String devivce, @FormParam("startdate")String startDate, @FormParam("enddate")String endDate) throws ParseException{
		
		EnergyConsumptionSearch search = new EnergyConsumptionSearch();
		search.setStartDate(startDate);
		search.setEndDate(endDate);
		search.setDeviceName(devivce);
		return Response.ok(SMDataManager.getInstance().GetEnergyConsumption(search)).build();		
	}
}
