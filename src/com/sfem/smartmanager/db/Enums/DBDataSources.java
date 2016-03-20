package com.sfem.smartmanager.db.Enums;

public enum DBDataSources {
	DERBY_DATASOURCE("jdbc/SmartManagerDB"),
	DERBY_CONNECTION_URL("jdbc:derby:SmartManagerDB");
	private final String dataSource;
	DBDataSources(String dataSource) {
		this.dataSource = dataSource;
	}
	
	public String toString() {
		return this.dataSource;
	}
}