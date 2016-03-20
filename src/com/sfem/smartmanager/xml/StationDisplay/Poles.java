package com.sfem.smartmanager.xml.StationDisplay;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

//@Root(name="poles")
@javax.xml.bind.annotation.XmlRootElement
public class Poles {
	 @ElementList(name="pole",inline=true,required=false)
	 private List<Pole> poles;
	 public Poles() {
	      super();
	   }
	 public List<Pole> getPoles() {
	      return poles;
	   }
	 public void SetPoles(List<Pole> data) {
	       poles = data;
	   }
}
