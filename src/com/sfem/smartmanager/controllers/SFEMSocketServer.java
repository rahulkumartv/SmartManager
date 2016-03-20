package com.sfem.smartmanager.controllers;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class SFEMSocketServer {
	
	private Socket socket;
	private ServerSocket serverSock;
	public SFEMSocketServer() {
		
		Initialise();
	}
	private void Initialise() {
		
		
		try {
			serverSock = new ServerSocket(2000);
			
		} catch (IOException e) {
			System.out.println("Failed to connect to socket");
			e.printStackTrace();
		}		
	}
	
	public void acceptClient()
	{
		try {
			socket = serverSock.accept();
		} catch (IOException e) {
			System.out.println("Failed to accept client");
			e.printStackTrace();
		}
	
	}
	
	
	public String readClent(){
		
		String message;
		
	try {
		
		InputStreamReader IR;
		IR = new InputStreamReader(socket.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
	    message = BR.readLine();
	    return message;
		
	} catch (IOException e) {
		
		e.printStackTrace();
		return null;
	}	

	}

}