package com.sfem.smartmanager.UI;

public class Resources {	
		public static final String HOST_ADRESS  = "http://192.168.56.1:8080";
		public static final String USER_TOKEN  = "123456789101112";
		//user
		public static final String SERVICE_USER_AUTH = "/SmartManager/sfem/user/authentication";
		public static final String SERVICE_USER_LOGOUT = "/SmartManager/sfem/user/logout";
		public static final String SERVICE_USER_SIGNUP = "/SmartManager/sfem/user/signup";
		public static final String SERVICE_USER_CREATE_STATION = "/SmartManager/sfem/user/createstationuser";
		public static final String SERVICE_USER_CREATE_ENERGY= "/SmartManager/sfem/user/createenergyuser";
		//station
		public static final String SERVICE_STATION_POLES = "/SmartManager/sfem/station/poles";
		public static final String SERVICE_STATION_FUSES = "/SmartManager/sfem/station/fuses";
		public static final String SERVICE_STATION_SENSORS = "/SmartManager/sfem/station/sensors";
		public static final String URL_STATION = "SmartManager/faces/poles.xhtml";
		public static final String URL_ENERGYMNGR = "SmartManager/faces/feeder.xhtml";
		//energy
		public static final String SERVICE_ENERGY_CONS_POWER= "SmartManager/sfem/energymanager/consumedpower";
		public static final String SERVICE_ENERGY_CONS_ENERGY= "SmartManager/sfem/energymanager/consumedenergy";
		
		//Dashboard
		public static final String DSH_GAUGE_ENGY_TITLE = "Consumed Energy";
		public static final String DSH_GAUGE_PWR_TITLE = "Consumed Power";
		public static final String DSH_CHART_TITLE= "Energy History";
		
		//public static final String DSH_CHART_YEAR = "Yearly Consumed Energy";
	
}
