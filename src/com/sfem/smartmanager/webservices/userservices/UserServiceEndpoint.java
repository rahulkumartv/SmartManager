package com.sfem.smartmanager.webservices.userservices;

import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sfem.smartmanager.DataManager.SMDataManager;
import com.sfem.smartmanager.db.dao.DBUtils;
import com.sfem.smartmanager.db.Datas.Interfaces.User;
import com.sfem.smartmanager.webservices.station.XMLDS.user;
import com.sfem.smartmanager.webservices.utils.TokenGenerator;

@Path("/user")
public class UserServiceEndpoint {
	 	@POST
	 	@Path("/authentication")
	 	@Produces(MediaType.APPLICATION_XML)
	 	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response authenticateUser(@FormParam("username") String username, 
                     					 @FormParam("password") String password )
	 	{
	 		//JOptionPane.showMessageDialog(null, "authenticateUser");
	 		try
	 		{
		
	 		// Issue a token for the user
 				String token = IssueToken();
	 			// Authenticate the user using the credentials provided
	 			
 				User user = AuthenticateUser(token, username, password);
 				SFEMUserAuthenitication userAuthenticationResponse = new SFEMUserAuthenitication();
 				if( user != null){
 					userAuthenticationResponse.userName = user.GetUserName();
 					userAuthenticationResponse.groupId = String.valueOf(user.GetGroupDetails().GetGroupValue());
 					userAuthenticationResponse.token = token;
 					userAuthenticationResponse.response = (Response.Status.OK).toString();
	 			}
	 			else
	 			{
	 				userAuthenticationResponse.userName = "";
 					userAuthenticationResponse.groupId = "";
 					userAuthenticationResponse.token = "";
 					userAuthenticationResponse.response = (Response.Status.UNAUTHORIZED).toString();;
	 			}
 				return Response.ok(userAuthenticationResponse).build();

	 		}
	 		catch (Exception e)
	 		{
	 			return Response.status(Response.Status.UNAUTHORIZED).build();
	 		}
	 		
	 	}
	 	@POST
	 	@Path("/logout")
	 	@Produces(MediaType.APPLICATION_XML)
	 	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public Response LogOutUser( @FormParam("userToken")String userToken )
	 	{
	 		if(DisconnectUser( userToken ))
	 		{
	 			return Response.status(Response.Status.OK).build();
	 		}
	 		return Response.status(Response.Status.UNAUTHORIZED).build();
	 	}
	 	
	 	@POST
	 	@Path("/signup")
	 	@Produces(MediaType.APPLICATION_XML)
	 	@Consumes(MediaType.APPLICATION_XML)
		public Response SignUp( user userobj) throws NamingException, SQLException, ParseException
		{
	 		//JOptionPane.showMessageDialog(null, "SignUp");
	 		return CreateUser(userobj);
		}
		
	 	@GET
	 	@Path("/createstationuser")
	 	@Produces(MediaType.APPLICATION_XML)
		public Response CreateStationUser() throws NamingException, SQLException, ParseException
		{
	 		//JOptionPane.showMessageDialog(null, "SignUp");
	 		user userobj =  new user();
	 		userobj.userName ="sfemstation";
	 		userobj.passWord = "Epita123";
	 		userobj.firstName ="station";
	 		userobj.LastName ="User";
	 		userobj.userEmail ="sfemstation@gmail.com";
	 		userobj.userMobile ="0783456789";
	 		userobj.groupId ="1";
	 		userobj.permId = "2";
	 		return CreateUser(userobj);
		}
	 	
	 	
	 	@GET
	 	@Path("/createenergyuser")
	 	@Produces(MediaType.APPLICATION_XML)
		public Response CreateEnergyMgrUser() throws NamingException, SQLException, ParseException
		{
	 		//JOptionPane.showMessageDialog(null, "SignUp");
	 		user userobj =  new user();
	 		userobj.userName ="sfememgr";
	 		userobj.passWord = "Epita123";
	 		userobj.firstName ="energy";
	 		userobj.LastName ="User";
	 		userobj.userEmail ="sfemenergy@gmail.com";
	 		userobj.userMobile ="0783456689";
	 		userobj.groupId ="2";
	 		userobj.permId = "2";
	 		return CreateUser(userobj);
		}

		private Response CreateUser(user userobj) throws NamingException, SQLException, ParseException {
			// TODO Auto-generated method stub
			if( SMDataManager.getInstance().CreateUser(userobj))
			{
				return Response.status(Response.Status.CREATED).build();
			}
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		private boolean DisconnectUser( String userToken) {
			// TODO Auto-generated method stub
			return SMDataManager.getInstance().DisconnectUser(userToken);
		}
		private String IssueToken() {
			// TODO Auto-generated method stub
			TokenGenerator tknGen =  new TokenGenerator();
			return tknGen.nextTokenValue();
		}

		private User AuthenticateUser(String token,String username, String password) throws LoginException, NamingException, SQLException {
			// TODO Auto-generated method stub
			return SMDataManager.getInstance().AuthenticateandLoginUser(token, username, password);
		}
}
