package com.utils;

import java.util.ArrayList;

public class Statistics {
	
	int min, max;
	double average, variance;

	public Statistics(ArrayList<Integer> pressure) { 
		
		average = 0;
		variance = 0;
		min = pressure.get(0);
		max = pressure.get(0);
		
		for(int elem:pressure) {
			
			if (elem < min) {
				min = elem;
			}
			
			else if (elem > max) {
				max = elem;
			}
			
			average += elem;
		}
		
		average /= pressure.size();
		
		for(int elem:pressure) {
			variance += Math.pow((elem - average), 2);
		}
		
		variance /= pressure.size();
		
	}
	
	
	public int getMin() {
		return min;
	}
	
	
	public int getMax() {
		return max;
	}
	
	
	public double getAverage() {
		return average;
	}
	
	
	public double getVariance() {
		return variance;
	}
	
}