package com.SFEM.TestSimulator;

import com.SFEM.TestSimulator.Socket.SFEMSocketClient;

public class SFEMController {
	
	private SFEMSocketClient scocketClient;
	public SFEMController() {
		
		scocketClient = new SFEMSocketClient("Rahul",2000);
	}
	
	public void SendFaultyController(String value){
		
		value = "{SM}" + value+"{0}";
		scocketClient.send(value);
	
	}
	
	public void SendResolvedController(String value){
		
		value = "{SM}" + value+"{1}";
		scocketClient.send(value);
		
	}
	
	public void SendConsumedPower(String value){
		
		value = "{EM_P}" + value;
		scocketClient.send(value);
		
	}
	
	public void SendConsumedEnergy(String date, String filePath){
		
		String value = "{EM_C}" + "#" + date + "#" + filePath;
		scocketClient.send(value);
		
	}
	
	public void close(){
		scocketClient.close();
	}
}
