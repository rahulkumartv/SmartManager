package com.sfem.smartmanager.Energy.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="energyconsumption")
public class EnergyConsumptionSearch {
	private String deviceName;
	private List<String> deviceValue = new ArrayList<String>();
	private List<String> dateList = new ArrayList<String>();
	private String startDate;
	private String endDate;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public List<String> getDeviceValue() {
		return deviceValue;
	}
	public void setDeviceValue(List<String> deviceValue) {
		this.deviceValue = deviceValue;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	
}
