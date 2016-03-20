package com.sfem.smartmanager.webservices.station.XMLDS;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="fuse")
public class fuse {
	@XmlElement(name="id",required=true) 
    public String  Id;
	@XmlElement(name="data",required=true) 
	public String  Value;
}
