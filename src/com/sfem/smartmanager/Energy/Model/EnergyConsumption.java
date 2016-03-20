package com.sfem.smartmanager.Energy.Model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


public class EnergyConsumption {
	public EnergyConsumption(){
	
	}
	
	//private String energyID;
	private String deviceName;
	private String deviceValue;
	private String date;
	
    /*public String getEnergyID() {
		return energyID;
	}
	public void setEnergyID(String energyID) {
		this.energyID = energyID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}*/

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceValue() {
		return deviceValue;
	}

	public void setDeviceValue(String deviceValue) {
		this.deviceValue = deviceValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

}
