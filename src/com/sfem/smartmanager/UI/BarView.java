/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfem.smartmanager.UI;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

import com.sfem.smartmanager.Energy.Model.EnergyConsumptionSearch;

/**
 *
 * @author Favio
 */
@ManagedBean
@ViewScoped
public class BarView implements Serializable {
    
    private LineChartModel animatedModel1;
    private LineChartModel animatedModel2;
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    public LineChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    private void createAnimatedModels() {
    	
    	EnergyConsumptionSearch energyObje = ClientHandler.getInstance().getConsumedEnergy("Total", "2016-01-10", "2016-02-10");
    	//Date date = new Date();
    	DateAxis x = new DateAxis("Date");
        animatedModel1 = initLinearModel1(energyObje);
        //animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        
        energyObje = ClientHandler.getInstance().getConsumedEnergy("Total", "2015-02-10", "2016-02-10");
        animatedModel2 = initBarModel2(energyObje);
       // animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
       
    }
     
    private LineChartModel initBarModel2(EnergyConsumptionSearch energyObje) {
    	 LineChartModel model = new LineChartModel();
    	 
         LineChartSeries monthSeries = new LineChartSeries();
         monthSeries.setLabel(energyObje.getDeviceName());
         for( int nIndx =1; nIndx< energyObje.getDeviceValue().size(); nIndx++)
         {
         	monthSeries.set(energyObje.getDateList().get(nIndx), Double.parseDouble(energyObje.getDeviceValue().get(nIndx)));
         } 
         model.addSeries(monthSeries);
         model.getAxis(AxisType.Y).setLabel("Values");
         DateAxis axis = new DateAxis("Dates");
         axis.setTickAngle(-50);
         axis.setMax(energyObje.getStartDate());
         axis.setMin(energyObje.getEndDate());
        // axis.setTickFormat("%H:%#M:%S");
         model.getAxes().put(AxisType.X, axis);

          
         return model;
    }
     
    private LineChartModel initLinearModel1(EnergyConsumptionSearch energyObje) {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries monthSeries = new LineChartSeries();
        monthSeries.setLabel(energyObje.getDeviceName());
        for( int nIndx =1; nIndx< energyObje.getDeviceValue().size(); nIndx++)
        {
        	monthSeries.set(energyObje.getDateList().get(nIndx), Double.parseDouble(energyObje.getDeviceValue().get(nIndx)));
        } 
        model.addSeries(monthSeries);
        model.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax(energyObje.getStartDate());
        axis.setMin(energyObje.getEndDate());
       // axis.setTickFormat("%H:%#M:%S");
        model.getAxes().put(AxisType.X, axis);
         
        return model;
    }    
}
