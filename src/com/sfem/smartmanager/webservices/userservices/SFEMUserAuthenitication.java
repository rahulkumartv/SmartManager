package com.sfem.smartmanager.webservices.userservices;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class SFEMUserAuthenitication {
	
	@XmlElement(name="username",required=true) 
	public String userName;
	@XmlElement(name="groupid",required=true) 
	public String groupId;
	@XmlElement(name="token",required=true) 
	public String token;
	@XmlElement(name="token",required=true) 
	public String response;

}
