package com.sfem.smartmanager.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.sfem.smartmanager.DataManager.SMDataManager;
import com.sfem.smartmanager.Energy.Model.EnergyConsumption;
import com.sfem.smartmanager.db.dao.DBUtils;;

public class SFEMDataProcessor {
	
public void processData(String data) throws ParseException, IOException{
	
	
	if( data.contains("{SM}"))
	{
		String controllerName = data.substring(4, data.length()-3);
		String controllerValue = data.substring(data.length()-2, data.length()-1);
		SMDataManager.getInstance().RefreshStationData(controllerName,controllerValue);
	}
	else{
		
		if(data.contains("{EM_P}")){
			
			String dataPower = data.substring(6);
			
			SMDataManager.getInstance().UpdateConsumedPower(dataPower);
			
			
		}
		else if(data.contains("{EM_C}"))
		{
			String dateString = data.substring(7,17);			
			String filePath = data.substring(18);
			ArrayList<String > deviceList = readEnergyFile(filePath);
			for(String st: deviceList)
			{
				System.out.println("CSV"+st);
			}
			ArrayList<EnergyConsumption >  energyConsumptiionList = new ArrayList<EnergyConsumption >();		
			double consumption = 0;
			if(deviceList  != null ){
				for(int index = 0; index < deviceList.size();++ index )
				{
					//EnergyConsumption cons = new EnergyConsumption();
					//cons.setDate(dateString);
					
					String row = deviceList.get(index);
					String splitBy = ",";
					String[] values  = row.split(splitBy);
					//int find = row.indexOf(",");
					String device = values[0];//row.substring(0, find-1);
					String value = values[1];//row.substring(find+1);
					//cons.setDeviceName(device);
					System.out.println(value);
					System.out.println(String.valueOf(Double.parseDouble(value)));
					consumption = consumption + Double.parseDouble(value);
					//cons.setDeviceValue(value);
					//energyConsumptiionList.add(cons);
					
				}				
				System.out.println(String.valueOf(consumption));
				EnergyConsumption cons = new EnergyConsumption();
				cons.setDate(dateString);
				cons.setDeviceName("Total");
				cons.setDeviceValue(String.valueOf(consumption));
				energyConsumptiionList.add(cons);
				SMDataManager.getInstance().StoreEnergyConsumption(energyConsumptiionList);
				
			}
			
			
		}
	}
	
	
}

public ArrayList<String > readEnergyFile(String path) throws IOException
{
	ArrayList<String > deviceList = new ArrayList<String >();
	 try {
		/*Scanner scanner = new Scanner(new File(path));
		scanner.useDelimiter("/n");
		
		while (scanner.hasNext())
        {
			String text = scanner.next();
			if( text.contains("Appliance") ||  text.contains("Consumption (kWh)")) 
			{
				continue;
			}
			deviceList.add(text);
			
        }      
        scanner.close();
        return deviceList;*/		
		BufferedReader buffReader = new BufferedReader(new FileReader(path));
		String splitBy = ",";
		String buffLine;
		while ((buffLine = buffReader.readLine()) !=null){
            String[] headers  = buffLine.split(splitBy);
            if( headers[0].contains("Appliance") ||  headers[1].contains("Consumption (kWh)"))
            {
            	continue;
            }
            deviceList.add(buffLine);
       }
		buffReader.close();
	   return deviceList;
		
        
	} catch (FileNotFoundException e) {

		e.printStackTrace();
		return null;
	}
	
}
	
}
