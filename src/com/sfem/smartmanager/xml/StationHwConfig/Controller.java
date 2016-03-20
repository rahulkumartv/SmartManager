package com.sfem.smartmanager.xml.StationHwConfig;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Controller {
	@Attribute(name="id")
	private String Id;
	@Attribute(name="type")
	private String contType;
	@Attribute(name="bind")
	private String contBind;
	@Text
	private String pointValue;
	public Controller() {
	      
	   }

	   public String getId() {
	      return Id;
	   }

	   public String getControllerType() {
		      return contType;
		   }
	   public String getControllerBind() {
		      return contBind;
	   }
	   public String getControllerValue() {
		      return pointValue;
		   }	  
	   public void SetControllerValue( String value) {
		      pointValue = value;
		   }
}
