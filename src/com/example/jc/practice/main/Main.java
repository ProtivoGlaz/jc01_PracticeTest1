package com.example.jc.practice.main;

import com.example.jc.practice.temperature.Temperature;

public class Main {

	public static void main(String[] args) {
		String[] dates = { "2024-04-24", "2024-04-25", "2024-04-26", "2024-04-27", "2024-04-28",
				"2024-04-29", "2024-04-30" };
		int[] temperatureMorning = new int[] { 10, 12, 11, 13, 15, 17, 16 };
		int[] temperatureAfternoon = new int[] { 15, 16, 15, 18, 20, 22, 21 };
		int[] temperatureEvening = new int[] { 12, 13, 14, 15, 17, 19, 18 };
		
		double[] averageTemperatures = Temperature.averageTemperatures(temperatureMorning, temperatureAfternoon, temperatureEvening, dates.length);
		
		//Temperature.printAverageTemperatures(dates, averageTemperatures);
		
		int[][] periods =  Temperature.findAllPeriods(averageTemperatures);
		
		//Temperature.printAllPeriods(dates, periods);
		
		int[] longestPeriod = Temperature.findLongestPeriod(periods);
		
		Temperature.printLongestPeriod(dates, longestPeriod);
		
	}

}
