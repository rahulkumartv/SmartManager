package com.sfem.smartmanager.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.sfem.smartmanager.db.dao.DBUtils;
import com.sfem.smartmanager.webservices.station.XMLDS.user;

public class SFEMControllerThread implements Runnable{

	
	private SFEMController controller;
	public SFEMControllerThread(){
		
		controller = new SFEMTestControllers();
	}
	
	@Override
	public void run() {
		
		System.out.println("Thread Started");
		controller.connect();
		System.out.println("Thread Connected");
		SFEMDataProcessor dataProcessor = new SFEMDataProcessor();
		
		while(true){
					
			String data = controller.read();
			if( null != data )
			{
				try {
					try {
						dataProcessor.processData(data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("client returns null");
				controller.connect();
			}
		
		try {
			
				Thread.sleep(1000);
			
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		
		}
				
	}

}

