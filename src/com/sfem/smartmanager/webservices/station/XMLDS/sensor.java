package com.sfem.smartmanager.webservices.station.XMLDS;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sensor")
public class sensor {
	@XmlElement(name="id",required=true) 
	public String  Id;
	@XmlElement(name="data",required=true) 
	public String  Value;
}
