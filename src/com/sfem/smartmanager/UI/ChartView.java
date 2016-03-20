/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfem.smartmanager.UI;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.MeterGaugeChartModel;

import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;
    private MeterGaugeChartModel meterGaugeModel3;
 
    @PostConstruct
    public void init() {
        createMeterGaugeModels();
    }
 
    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }
     
    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }
    
    public MeterGaugeChartModel getMeterGaugeModel3() {
        return meterGaugeModel3;
    }
 
    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>(){{
            add(20);
            add(50);
            add(120);
            add(220);
        }};
         
        return new MeterGaugeChartModel(140, intervals);
    }
 
    private void createMeterGaugeModels() {
    	
        List<Number> monthlyintervals = new ArrayList<Number>(){{
            add(400);
            add(800);
            add(1200);
            add(1600);
            add(2000);
        }};
        List<Number> yearlyintervals = new ArrayList<Number>(){{
            add(3000);
            add(6000);
            add(9000);
            add(12000);
            add(15000);
        }};
        List<Number> powerintervals = new ArrayList<Number>(){{
            add(1000);
            add(2000);
            add(3000);
            add(4000);
            add(5000);
            add(6000);
            add(7000);
            add(8000);
            add(9000);
            add(10000);
        }};
        
        EnergyConsumptionSearch energyObje = ClientHandler.getInstance().getConsumedEnergy("Total", "2016-01-10", "2016-02-10");
        meterGaugeModel1 = initMeterGaugeModel();
        meterGaugeModel1.setGaugeLabel("Kw/h");
        meterGaugeModel1.setIntervals(monthlyintervals);
        
        meterGaugeModel1.setValue(Double.parseDouble(energyObje.getDeviceValue().get(0)));
         
        energyObje = ClientHandler.getInstance().getConsumedEnergy("Total", "2015-02-10", "2016-02-10");
        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setGaugeLabel("Kw/h");
        meterGaugeModel2.setIntervals(yearlyintervals);
        meterGaugeModel2.setValue(Double.parseDouble(energyObje.getDeviceValue().get(0)));
        
        meterGaugeModel3 = initMeterGaugeModel();       
        meterGaugeModel3.setGaugeLabel("Watt");
        meterGaugeModel3.setIntervals(powerintervals);
        String power= ClientHandler.getInstance().getConsumedPower();
        if(power != null)
        {
        	meterGaugeModel3.setValue(Long.parseLong(power));
        }
        else
        {
        	meterGaugeModel3.setValue(0);
        }
        
        
    }
 
}
