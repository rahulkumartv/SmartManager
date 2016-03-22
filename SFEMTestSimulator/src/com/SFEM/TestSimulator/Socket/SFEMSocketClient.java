package com.SFEM.TestSimulator.Socket;
import java.net.*;
import java.io.*;

public class SFEMSocketClient {

	Socket socketClient;
	
	public SFEMSocketClient(String host, int port){
		
		initialise(host, port);
		
	}

	private void initialise(String host, int port) {
		
		try {
			
			socketClient = new Socket(host, port);
				
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void send(String value)
	{
		PrintStream PS;
		try {
			PS = new PrintStream(socketClient.getOutputStream());
			PS.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void close()
	{
		try {
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
