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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


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
    private long fuelCapacity;
    
	/**
     * A benzinkutak száma
     */
    private int numberOfStation;
    
    /**
     * A benzinkutak, elsõ dimenzió elhelyezkedés, második dimenzió az üzemanyag ára.
     */
    private long[][] gasStations;
    
    /**
     * Minden adott töltõállomáshoz itt tárolódik a következõ olyan töltõállomás azonositója, melyen olcsóbban lehet tankolni, mint aza dott kúton,
     * ha az út során már nincs olcsóbb állomás akkor -1 kerül az  
     */
    private int[] nextCheaperGasStation; 
     
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
	 * Visszaadja azt a tömböt, mely tartalmazza minden egyes állomáshoz, az utána következõ olcsóbb állomást.
	 * 
	 * @return nextCheaperGasStation töltõlláomások után következõ olcsóbb állomás
	 */
	 public int[] getNextCheaperGasStation() {
		return nextCheaperGasStation;
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
		this.nextCheaperGasStation = new int[numberOfStation];
	}

    
	/**
     * Megvizsgálja, hogy az aktuális benzinkútról elérhetõ e Baynor város tankolás nélkül.
     * 
     * @param actGasStationId a benzinkút amelyen legutoljára tankolt
     * 
     * @return <code>true</code> ha elérhetõ Baynor város, <code>false</code> ha nem
     */
    boolean reachedDestination(int actGasStationId, long actFuel ) {
	    return gasStations[actGasStationId][0] + actFuel >= distance;
    }
    
     
    /**
     * Az utolsó töltõállomásról indulva elindul visszafele a töltõállomásokon, és megkeresi mindegyik állomáshoz, hogy a melyik a következõ
     * olyan kút, amelyeiken olcsób az üzemanyag az út során. Egy vermet használ azoknak a kutak id-jának a tárolására amelyek potenciális jelöltek 
     * lehetnek  az olcsó benzinkutnak, ha talál olcsóbbat, akkor az kikerül a verembõl és késõbbiekben nem foglalkozik vele a program, ezzel 
     * gyorsitja a programot a nagy inputoknál, mivel nem kell mindegyik kut esetén végignézni az összes utána következõ kutat.
     */ 
     void findNextCheaperGasStations() {
    	 Stack<Integer> stack = new Stack<Integer>();
    	 for (int i = numberOfStation - 1 ; i >= 0; i--) {
			while (!stack.empty() &&  gasStations[i][1] <= gasStations[stack.peek()][1] ) {
				stack.pop();
			}
			if (stack.empty()) {
				nextCheaperGasStation[i] = -1;
			} else {
				nextCheaperGasStation[i] = stack.peek();
			}
			stack.push(i);
		}
    }
    
    /**
     * Kiszámolja a minimális tankolási költséget. 
     * @return a minimális tankolási költség Baynor város eléréséhez
     */
    public long solveProblem() {   
    	long totalFuelCost = 0; //tankolási költség
    	long actFuel = fuelCapacity; //az autóban található üzemanyag mennyisége
    	long fuelNeed; //ennyi üzemanyagra van szükség, hogy elérje a következõ célt
    	
    	Arrays.sort(gasStations, (a, b) -> Long.compare(a[0], b[0]));    	
    	actFuel = fuelCapacity - gasStations[0][0];	
    	findNextCheaperGasStations();

    	for (int actGasStationId = 0; actGasStationId < numberOfStation; actGasStationId++) {	
    		//Tankolandó üzemanyag kiszámitása
    	    if (nextCheaperGasStation[actGasStationId] == -1) {
    	    	fuelNeed = distance - gasStations[actGasStationId][0];
    	    } else {
    	    	fuelNeed = gasStations[nextCheaperGasStation[actGasStationId]][0] - gasStations[actGasStationId][0];
    	    }
    	    
    	    //Tankolás
    	    if (fuelNeed > fuelCapacity) {
    	    	fuelNeed = fuelCapacity;
    	    }
    	    if (fuelNeed > actFuel) {
				totalFuelCost += (fuelNeed - actFuel) * gasStations[actGasStationId][1];
				actFuel = fuelNeed;
			}
    	    
    	    //Ha már elérhetõ ezzel a célállomás akkor megáll a ciklus
    	    if (reachedDestination(actGasStationId, actFuel)) {
				break;
			}
    	    
    	    //Utazás
    		if(actGasStationId != numberOfStation -1 ) {
    			actFuel -= gasStations[actGasStationId + 1][0] - gasStations[actGasStationId][0];			 			
    		}     		
    	}
    	return totalFuelCost;
    }
    
    /**
     * Beolvassa az adatokat a standard inputról és beállitja a példány mezõit.
     * 
     * @throws NumberFormatException
     * @throws IOException
     */
    public void readFromConsole() throws NumberFormatException, IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        String split[];
        
        distance =  Long.parseLong(reader.readLine());
        fuelCapacity =  Long.parseLong(reader.readLine());
        numberOfStation =  Integer.parseInt(reader.readLine());      
        gasStations = new long [numberOfStation][2];
        nextCheaperGasStation = new int [numberOfStation];
        
        for (int i = 0; i < numberOfStation; i++) {
        	 split = reader.readLine().split(" ");
        	 gasStations[i][0] = Long.parseLong(split[0]);
        	 gasStations[i][1] = Long.parseLong(split[1]);
		}
    }

   
    
    
}
