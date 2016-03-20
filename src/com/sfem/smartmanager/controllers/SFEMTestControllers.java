package com.sfem.smartmanager.controllers;

public class SFEMTestControllers implements SFEMController {
	
	private SFEMSocketServer server;
	
	public SFEMTestControllers()
	{
		server = new SFEMSocketServer();
	}

	@Override
	public Boolean connect() {
		
		
		server.acceptClient();
		return true;
	}

	@Override
	public Boolean disconnect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String read() {
		
		String data = server.readClent();
		return data;

	}

}
