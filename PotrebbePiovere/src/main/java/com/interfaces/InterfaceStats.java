package com.interfaces;

/**
 * Interfacce per l'implementazione della statistiche
 * come da progetto
 *
 */
public interface InterfaceStats {
	
	/**
	 * 
	 * @return massimo 
	 */
	public double getMax();
	
	/**
	 * 
	 * @return minimo
	 */
	public double getMin();
	
	/**
	 * 
	 * @retur media
	 */
	public double getAverage();
	
	/**
	 * 
	 * @retur varianza
	 */
	public double getVariance();
	
}
