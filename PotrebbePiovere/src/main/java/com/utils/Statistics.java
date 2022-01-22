package com.utils;

import java.util.List;

/**
 * Classe che effettua statistiche a partire da una List Integer
 */

public class Statistics {
	
	int min, max;
	double average, variance;

	public Statistics(List<Integer> list) { 
		
		average = 0;
		variance = 0;
		min = list.get(0);
		max = list.get(0);
		
		for(int elem:list) {
			
			if (elem < min) {
				min = elem;
			}
			
			else if (elem > max) {
				max = elem;
			}
			
			average += elem;
		}
		
		average /= list.size();
		
		for(int elem:list) {
			variance += Math.pow((elem - average), 2);
		}
		
		variance /= list.size();
		
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