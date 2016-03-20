package com.sfem.smartmanager.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sfem.smartmanager.DataManager.SMDataManager;
import com.sfem.smartmanager.DataManager.SMStationDatas;
import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;
import com.sfem.smartmanager.webservices.userservices.SFEMUserAuthenitication;

public final class ClientHandler {
	 private static ClientHandler clientHandler = null;
	 // A user storage which stores <token and , user id>
	private SFEMUserAuthenitication crntUserObjet;
	 private ClientHandler(){
		 
	 }
	 public static ClientHandler getInstance() {
	        if ( clientHandler == null ) {
	        	clientHandler = new ClientHandler();
	        }

	        return clientHandler;
	    }
	 public SFEMUserAuthenitication getCurrentUser() {
	      return crntUserObjet;
	    }
	 public void setCurrentUser(SFEMUserAuthenitication user) {
		 crntUserObjet = user;
	    }
	 public String getConsumedPower()
	 {
		 Client client = ClientBuilder.newClient();
		 WebTarget clientTraget = client.target(Resources.HOST_ADRESS).path(Resources.SERVICE_ENERGY_CONS_POWER);		
		 Response  response =  clientTraget.request(MediaType.APPLICATION_XML).get();
		 String strPower = response.readEntity(String.class);
		 return strPower;
		// SFEMUserAuthenitication crntUser= response.readEntity(SFEMUserAuthenitication.class);		 
	 }
	 public EnergyConsumptionSearch getConsumedEnergy( String devName, String startDate,String endDate )
	 {
		 Client client = ClientBuilder.newClient();
		 WebTarget clientTraget = client.target(Resources.HOST_ADRESS).path(Resources.SERVICE_ENERGY_CONS_ENERGY);
		 Form userForm = new Form();
		 userForm.param("device", devName);
		 userForm.param("startdate", startDate);
		 userForm.param("enddate", endDate);
		 Response  response =  clientTraget.request().post(Entity.form(userForm));
		 EnergyConsumptionSearch consEnergyObj= response.readEntity(EnergyConsumptionSearch.class);
		 return consEnergyObj;
	 }
}
