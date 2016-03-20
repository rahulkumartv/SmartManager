package com.sfem.smartmanager.db.dao.interfaces;

import java.sql.Connection;

import com.sfem.smartmanager.Energy.Model.EnergyConsumption;
import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;
import com.sfem.smartmanager.db.Datas.SMStation;

public interface EnergyMngrDAO {

	boolean insertEnergyTable(Connection conn, EnergyConsumption enrgyCon);

	EnergyConsumptionSearch getEnergyConsumption(Connection conn, EnergyConsumptionSearch energyConSearch);

}
