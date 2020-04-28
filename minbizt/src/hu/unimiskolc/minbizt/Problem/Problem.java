/*
 * @Problem.java
 * 
 * 1.0 
 * 
 * 17/04/21
 * 
 * Copyright (c) 2020 University of Miskolc
 */

package hu.unimiskolc.minbizt.Problem;

import java.util.Arrays;


/**
 * Az oszt�ly EC08E Fuel Consciousness programoz�si feladat megold�s�hoz sz�ks�ges mez�ket �s met�dusokat tartalmazza.
 * 
 * @version 1.0 17 Apr 2020
 * @author St�f�n Norbert
 *
 */
public class Problem {
	
	/**
	 * A t�vols�g Alham �s Baynor v�ros k�z�tt.
	 */
	private long distance;
	
	/**
	 * Az �zemanyagtart�ly kapacit�sa.
	 */
    private long  fuelCapacity;
    
	/**
     * A benzinkutak sz�ma
     */
    private int numberOfStation;
    
    /**
     * A benzinkutak, els� dimenzi� elhelyezked�s, m�sodik dimenzi� az �zemanyag �ra.
     */
    private long[][] gasStations;
    
    /**
	 * Visszaadja a t�vols�got Alham �s Baynor v�ros k�z�tt.
	 * 
	 * @return a t�vols�g Alham �s Baynor v�ros k�z�tt
	 */
    public long getDistance() {
		return distance;
	}

    /**
	 * Be�llitja a t�vols�got Alham �s Baynor v�ros k�z�tt. 
	 * 
	 * @param distance a t�vols�g Alham �s Baynor v�ros k�z�tt
	 */
	public void setDistance(long distance) {
		this.distance = distance;
	}

	/**
	 * Visszaadja az �zemanyagtart�ly kapacit�s�t.
	 * 
	 * @return fuelCapacity az �zemanyagtart�ly kapacit�sa
	 */
	public long getFuelCapacity() {
		return fuelCapacity;
	}

	/**
	 * Be�llitja az �zemanyagtart�ly kapacit�s�t.
	 * 
	 * @param fuelCapacity az �zemanyagtart�ly kapacit�sa
	 */
	public void setFuelCapacity(long fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	/**
	 * Visszaadja az t�lt��llom�sok sz�m�t.
	 * 
	 * @return numberOfStation a t�lt��llom�sok sz�ma
	 */
	public int getNumberOfStation() {
		return numberOfStation;
	}

	/**
	 * Be�llitja a t�lt��llom�sok sz�m�t.
	 * 
	 * @param numberOfStation a t�lt��llom�sok sz�ma
	 */
	public void setNumberOfStation(int numberOfStation) {
		this.numberOfStation = numberOfStation;
	}

	/**
	 * Visszaadja az t�lt��llom�sokat, els� dimenzi� elhelyezked�s, m�sodik dimenzi� az �zemanyag �ra.
	 * 
	 * @return gasStations t�lt��llom�sok t�mb
	 */
	public long[][] getGasStations() {
		return gasStations;
	}

	/**
	 * Be�llitja a t�lt��llom�sok t�mb �rt�kei.
	 * 
	 * @param gasStations t�lt��llom�sok t�mb
	 */
	public void setGasStations(long[][] gasStations) {
		this.gasStations = gasStations;
	}

    /**
     * �res konstruktor
     */
    public Problem() {
		super();
	}

	/**
     * A Problem oszt�ly kontruktora mely be�llitja az �sszes mez�t. 
	 *
     * @param distance a k�t v�ros k�z�tti t�vols�g
     * @param fuelCapacity az �zemanyagtart�ly kapacit�sa
     * @param numberOfStation a t�lt��llom�sok sz�ma
     * @param gasStations a t�lt��llom�sok adatait tartalmaz� t�mb
     */
    public Problem(long distance, long fuelCapacity, int numberOfStation, long[][] gasStations) {
		super();
		this.distance = distance;
		this.fuelCapacity = fuelCapacity;
		this.numberOfStation = numberOfStation;
		this.gasStations = gasStations;
	}

    /**
     * Megvizsg�lja, hogy az utolj�ra tankolt benzink�tr�l el�rhet� e Baynor v�ros tankol�s n�lk�l.
     * 
     * @param actGasStationId a benzink�t amelyen legutolj�ra tankolt
     * 
     * @return <code>true</code> ha el�rhet� Baynor v�ros, <code>false</code> ha nem
     */
    boolean reachedDestination(int actGasStationId) {
	    return gasStations[actGasStationId][0] + fuelCapacity >= distance;
    }
    
    
    /**
     * Kisz�molja, hogy el�rhet� e param�terk�nt �tadott aktu�lis benzink�tr�l a c�l benink�t tankol�s n�lk�l.
     * 
     * @param actGasStationId az aktu�lis benzink�t sorsz�ma
     * @param targetGasStationdId a c�l benzink�t sorsz�ma
     * 
     * @return <code>true</code> ha el�rhet� a c�l benzink�t, <code>false</code> ha nem
     */
    boolean enoughTheFuel(int actGasStationId, int targetGasStationdId) {  	
    	return fuelCapacity >= (gasStations[targetGasStationdId][0] - gasStations[actGasStationId][0]);
    }
    
	/**
     * Kisz�molja az aktu�lis tankol�s k�lts�g�t.
     * 
     * @param lastGasStationId a benzink�t sorsz�ma, amelyen utolj�ra tankolt
     * 
     * @param actGasStationId a benzink�t sorsz�ma, amelyen tankolni fog
     * 
     * @return a tankol�s k�lts�ge
     */
    long costCalculation(int lastGasStationId, int actGasStationId) {
    	return (gasStations[actGasStationId][0] - gasStations[lastGasStationId][0]) * gasStations[actGasStationId][1];  	
    }
    
    /**
     * Megkeresi a tankol�s n�lk�l el�rhet� legolcs�bb benzinkutat.
     * 
     * @param actGasStationId a benzink�t sorsz�ma, amelyen utolj�ra tankolt
     * 
     * @return a legolcs�bb benzink�t sorsz�ma
     */
    int searchBestGasStationd(int actGasStationId) {
    	long minFuelCost = 2147483647; //minimum tankol�si k�lts�g
    	int	bestGasStationId = 0; //legolcs�bb el�rhet� benzink�t azonosit�ja
    	for (int i = actGasStationId + 1; i < numberOfStation; i++) {
	       	if (enoughTheFuel(actGasStationId,i)){    
	       		if (minFuelCost >= gasStations[i][1]){ 
	       			minFuelCost = gasStations[i][1];
	       			bestGasStationId = i;
	       		}
	        }else{       
	        	break;
	        }
    	}
       	return bestGasStationId;
    }
    
    /**
     * Kisz�molja a minim�lis tankol�si k�lts�get.
     * 
     * @return a minim�lis tankol�si k�lts�g Baynor v�ros el�r�s�hez
     */
    public long solveProblem() {
    	long fuelPrice = 0; //tankol�si k�lts�g
    	int actGasStationId = 0; //aktu�lisan tartozkod� benzink�t azonosit�ja 
    	int bestGasStationId = 0; //legolcs�bb el�rhet� benzink�t azonosit�ja
    	Arrays.sort(gasStations, (a, b) -> Long.compare(a[0], b[0]));
    	while (!reachedDestination(actGasStationId)) {	
    		bestGasStationId = searchBestGasStationd(actGasStationId);
    		fuelPrice  += costCalculation(actGasStationId, bestGasStationId);
    		actGasStationId = bestGasStationId;
    	}
    	return fuelPrice;
    }
}
