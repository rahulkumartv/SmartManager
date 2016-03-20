package com.sfem.smartmanager.DataManager;

public class SFEMEnergyDataMgr {
	
	SFEMEnergyDataMgr()
	{
		consumedPower = "0";
	}
	private String consumedPower;

	public String getConsumedPower() {
		
		return consumedPower;
	}

	public void setConsumedPower(String consumedPower) {
		this.consumedPower = consumedPower;
	}
	

}
