package com.sfem.smartmanger.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sfem.smartmanager.controllers.SFEMControllerThread;
import com.sfem.smartmanager.db.dao.DBUtils;

public class SFEMServletContext implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		(new Thread(new SFEMControllerThread())).start();
		
	}

}
