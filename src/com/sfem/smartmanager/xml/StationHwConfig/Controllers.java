package com.sfem.smartmanager.xml.StationHwConfig;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.sfem.smartmanager.xml.StationDisplay.Pole;

@Root(name="controllers")
public class Controllers {
	 @ElementList(name="controller",inline=true,required=false)
	 private List<Controller> controllers;
	 public Controllers() {
	      super();
	   }
	 public List<Controller> getControllers() {
	      return controllers;
	   }
	 public void SetControllers(List<Controller> data) {
		 controllers = data;
	   }
}
