package com.sfem.smartmanager.webservices.station.XMLDS;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class user {
	@XmlElement(name="username",required=true) 
	public String  userName;
	@XmlElement(name="password",required=true) 
	public String  passWord;
	@XmlElement(name="firstname",required=true) 
	public String  firstName;
	@XmlElement(name="lastname",required=false) 
	public String  LastName;
	@XmlElement(name="email",required=false) 
	public String userEmail;
	@XmlElement(name="mobile",required=false) 
	public String userMobile;
	@XmlElement(name="group",required=true) 
	public String groupId;
	@XmlElement(name="permission",required=true) 
	public String permId;
}
