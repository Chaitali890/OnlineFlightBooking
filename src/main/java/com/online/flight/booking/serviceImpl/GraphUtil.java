package com.online.flight.booking.serviceImpl;
import java.util.List;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import com.online.flight.booking.entity.Airport;
public class GraphUtil {

	public static JFreeChart generateGraph(List<Airport> airports) {
	
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
		for(Airport airport : airports)
		{
			dataSet.addValue(airport.getPassengerCount(), "Details", airport.getCountry());
		}
		
		return ChartFactory.createBarChart("Employee Details Chart", "Country", "Passenger Count", dataSet,PlotOrientation.VERTICAL,false,true,false);
	}
}
