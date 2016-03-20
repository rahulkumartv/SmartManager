package com.sfem.smartmanager.xml.StationDisplay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root(name="Pole")
public class Pole {

	@Element(name="id",type= String.class,required=false)
	private String poleId;
	@Element(name="fuse",type= String.class,required=false)
	private String poleFuse;
	@Element(name="sensor",type= String.class,required=false)
	private String poleSensor;
	public Pole() {
	      
	   }

	   public String getPoleId() {
	      return poleId;
	   }
	   public void  SetPoleId(String id) {
		       poleId = id;
		   }


	   public String getPoleFuse() {
		      return poleFuse;
		   }
	   public void  SetPoleFuse(String fuse) {
		   poleFuse = fuse;
	   }
	   public String getPoleSensor() {
		      return poleSensor;
		   }
	   public void  SetPoleSensor(String sensor) {
		   poleFuse = sensor;
	   }
}
