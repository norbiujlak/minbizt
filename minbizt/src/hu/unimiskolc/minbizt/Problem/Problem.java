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
 * Az osztály EC08E Fuel Consciousness programozási feladat megoldásához szükséges mezõket és metódusokat tartalmazza.
 * 
 * @version 1.0 17 Apr 2020
 * @author Stéfán Norbert
 *
 */
public class Problem {
	
	/**
	 * A távolság Alham és Baynor város között.
	 */
	private long distance;
	
	/**
	 * Az üzemanyagtartály kapacitása.
	 */
    private long  fuelCapacity;
    
	/**
     * A benzinkutak száma
     */
    private int numberOfStation;
    
    /**
     * A benzinkutak, elsõ dimenzió elhelyezkedés, második dimenzió az üzemanyag ára.
     */
    private long[][] gasStations;
    
    /**
	 * Visszaadja a távolságot Alham és Baynor város között.
	 * 
	 * @return a távolság Alham és Baynor város között
	 */
    public long getDistance() {
		return distance;
	}

    /**
	 * Beállitja a távolságot Alham és Baynor város között. 
	 * 
	 * @param distance a távolság Alham és Baynor város között
	 */
	public void setDistance(long distance) {
		this.distance = distance;
	}

	/**
	 * Visszaadja az üzemanyagtartály kapacitását.
	 * 
	 * @return fuelCapacity az üzemanyagtartály kapacitása
	 */
	public long getFuelCapacity() {
		return fuelCapacity;
	}

	/**
	 * Beállitja az üzemanyagtartály kapacitását.
	 * 
	 * @param fuelCapacity az üzemanyagtartály kapacitása
	 */
	public void setFuelCapacity(long fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	/**
	 * Visszaadja az töltõállomások számát.
	 * 
	 * @return numberOfStation a töltõállomások száma
	 */
	public int getNumberOfStation() {
		return numberOfStation;
	}

	/**
	 * Beállitja a töltõállomások számát.
	 * 
	 * @param numberOfStation a töltõállomások száma
	 */
	public void setNumberOfStation(int numberOfStation) {
		this.numberOfStation = numberOfStation;
	}

	/**
	 * Visszaadja az töltõállomásokat, elsõ dimenzió elhelyezkedés, második dimenzió az üzemanyag ára.
	 * 
	 * @return gasStations töltõállomások tömb
	 */
	public long[][] getGasStations() {
		return gasStations;
	}

	/**
	 * Beállitja a töltõállomások tömb értékei.
	 * 
	 * @param gasStations töltõállomások tömb
	 */
	public void setGasStations(long[][] gasStations) {
		this.gasStations = gasStations;
	}

    /**
     * Üres konstruktor
     */
    public Problem() {
		super();
	}

	/**
     * A Problem osztály kontruktora mely beállitja az összes mezõt. 
	 *
     * @param distance a két város közötti távolság
     * @param fuelCapacity az üzemanyagtartály kapacitása
     * @param numberOfStation a töltõállomások száma
     * @param gasStations a töltõállomások adatait tartalmazó tömb
     */
    public Problem(long distance, long fuelCapacity, int numberOfStation, long[][] gasStations) {
		super();
		this.distance = distance;
		this.fuelCapacity = fuelCapacity;
		this.numberOfStation = numberOfStation;
		this.gasStations = gasStations;
	}

    /**
     * Megvizsgálja, hogy az utoljára tankolt benzinkútról elérhetõ e Baynor város tankolás nélkül.
     * 
     * @param actGasStationId a benzinkút amelyen legutoljára tankolt
     * 
     * @return <code>true</code> ha elérhetõ Baynor város, <code>false</code> ha nem
     */
    boolean reachedDestination(int actGasStationId) {
	    return gasStations[actGasStationId][0] + fuelCapacity >= distance;
    }
    
    
    /**
     * Kiszámolja, hogy elérhetõ e paraméterként átadott aktuális benzinkútról a cél beninkút tankolás nélkül.
     * 
     * @param actGasStationId az aktuális benzinkút sorszáma
     * @param targetGasStationdId a cél benzinkút sorszáma
     * 
     * @return <code>true</code> ha elérhetõ a cél benzinkút, <code>false</code> ha nem
     */
    boolean enoughTheFuel(int actGasStationId, int targetGasStationdId) {  	
    	return fuelCapacity >= (gasStations[targetGasStationdId][0] - gasStations[actGasStationId][0]);
    }
    
	/**
     * Kiszámolja az aktuális tankolás költségét.
     * 
     * @param lastGasStationId a benzinkút sorszáma, amelyen utoljára tankolt
     * 
     * @param actGasStationId a benzinkút sorszáma, amelyen tankolni fog
     * 
     * @return a tankolás költsége
     */
    long costCalculation(int lastGasStationId, int actGasStationId) {
    	return (gasStations[actGasStationId][0] - gasStations[lastGasStationId][0]) * gasStations[actGasStationId][1];  	
    }
    
    /**
     * Megkeresi a tankolás nélkül elérhetõ legolcsóbb benzinkutat.
     * 
     * @param actGasStationId a benzinkút sorszáma, amelyen utoljára tankolt
     * 
     * @return a legolcsóbb benzinkút sorszáma
     */
    int searchBestGasStationd(int actGasStationId) {
    	long minFuelCost = 2147483647; //minimum tankolási költség
    	int	bestGasStationId = 0; //legolcsóbb elérhetõ benzinkút azonositója
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
     * Kiszámolja a minimális tankolási költséget.
     * 
     * @return a minimális tankolási költség Baynor város eléréséhez
     */
    public long solveProblem() {
    	long fuelPrice = 0; //tankolási költség
    	int actGasStationId = 0; //aktuálisan tartozkodó benzinkút azonositója 
    	int bestGasStationId = 0; //legolcsóbb elérhetõ benzinkút azonositója
    	Arrays.sort(gasStations, (a, b) -> Long.compare(a[0], b[0]));
    	while (!reachedDestination(actGasStationId)) {	
    		bestGasStationId = searchBestGasStationd(actGasStationId);
    		fuelPrice  += costCalculation(actGasStationId, bestGasStationId);
    		actGasStationId = bestGasStationId;
    	}
    	return fuelPrice;
    }
}
