package com.sfem.smartmanager.UI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.primefaces.context.RequestContext;

import com.sfem.smartmanager.webservices.userservices.SFEMUserAuthenitication;
 
@ManagedBean
public class UserLoginView {
     
    private String username;
     
    private String password;
    
    private boolean loggedIn = false;
    
    private String page;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        String page = "";
        
      /*  if(username != null && username.equals("feeder") && password != null && password.equals("feeder")) {
            page = "http://localhost:8080/SmartManager/faces/poles.xhtml";
            loggedIn = true;            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else if(username != null && username.equals("	") && password != null && password.equals("dashboard")) { 
            page = "http://localhost:8080/SmartManager/faces/feeder.xhtml";
            loggedIn = true;            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "feeder", username);
        } else {        
            loggedIn = false;            
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }*/
        Client client = ClientBuilder.newClient();
        WebTarget clientTraget = client.target(Resources.HOST_ADRESS).path(Resources.SERVICE_USER_AUTH);
        Form userForm = new Form();
        userForm.param("username", username);
        userForm.param("password", password);
        Response  response =  clientTraget.request().post(Entity.form(userForm));
        SFEMUserAuthenitication crntUser= response.readEntity(SFEMUserAuthenitication.class);
        if( crntUser.groupId.equals("1") )
        {
        	 page = "http://localhost:8080/SmartManager/faces/station.xhtml";
             loggedIn = true;            
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", crntUser.userName);
        }
        else if(crntUser.groupId.equals("2"))
        {
        	page = "http://localhost:8080/SmartManager/faces/dashboard.xhtml";
            loggedIn = true;            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", crntUser.userName);
        }
        ClientHandler.getInstance().setCurrentUser(crntUser);
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("page", page);
    }
    
    public void logOut(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        loggedIn = false;
    }
}