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
    private long fuelCapacity;
    
	/**
     * A benzinkutak sz�ma
     */
    private int numberOfStation;
    
    /**
     * A benzinkutak, els� dimenzi� elhelyezked�s, m�sodik dimenzi� az �zemanyag �ra.
     */
    private long[][] gasStations;
    
    /**
     * Minden adott t�lt��llom�shoz itt t�rol�dik a k�vetkez� olyan t�lt��llom�s azonosit�ja, melyen olcs�bban lehet tankolni, mint aza dott k�ton,
     * ha az �t sor�n m�r nincs olcs�bb �llom�s akkor -1 ker�l az  
     */
    private int[] nextCheaperGasStation; 
     
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
	 * Visszaadja azt a t�mb�t, mely tartalmazza minden egyes �llom�shoz, az ut�na k�vetkez� olcs�bb �llom�st.
	 * 
	 * @return nextCheaperGasStation t�lt�ll�om�sok ut�n k�vetkez� olcs�bb �llom�s
	 */
	 public int[] getNextCheaperGasStation() {
		return nextCheaperGasStation;
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
		this.nextCheaperGasStation = new int[numberOfStation];
	}

    
	/**
     * Megvizsg�lja, hogy az aktu�lis benzink�tr�l el�rhet� e Baynor v�ros tankol�s n�lk�l.
     * 
     * @param actGasStationId a benzink�t amelyen legutolj�ra tankolt
     * 
     * @return <code>true</code> ha el�rhet� Baynor v�ros, <code>false</code> ha nem
     */
    boolean reachedDestination(int actGasStationId, long actFuel ) {
	    return gasStations[actGasStationId][0] + actFuel >= distance;
    }
    
     
    /**
     * Az utols� t�lt��llom�sr�l indulva elindul visszafele a t�lt��llom�sokon, �s megkeresi mindegyik �llom�shoz, hogy a melyik a k�vetkez�
     * olyan k�t, amelyeiken olcs�b az �zemanyag az �t sor�n. Egy vermet haszn�l azoknak a kutak id-j�nak a t�rol�s�ra amelyek potenci�lis jel�ltek 
     * lehetnek  az olcs� benzinkutnak, ha tal�l olcs�bbat, akkor az kiker�l a veremb�l �s k�s�bbiekben nem foglalkozik vele a program, ezzel 
     * gyorsitja a programot a nagy inputokn�l, mivel nem kell mindegyik kut eset�n v�gign�zni az �sszes ut�na k�vetkez� kutat.
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
     * Kisz�molja a minim�lis tankol�si k�lts�get. 
     * @return a minim�lis tankol�si k�lts�g Baynor v�ros el�r�s�hez
     */
    public long solveProblem() {   
    	long totalFuelCost = 0; //tankol�si k�lts�g
    	long actFuel = fuelCapacity; //az aut�ban tal�lhat� �zemanyag mennyis�ge
    	long fuelNeed; //ennyi �zemanyagra van sz�ks�g, hogy el�rje a k�vetkez� c�lt
    	
    	Arrays.sort(gasStations, (a, b) -> Long.compare(a[0], b[0]));    	
    	actFuel = fuelCapacity - gasStations[0][0];	
    	findNextCheaperGasStations();

    	for (int actGasStationId = 0; actGasStationId < numberOfStation; actGasStationId++) {	
    		//Tankoland� �zemanyag kisz�mit�sa
    	    if (nextCheaperGasStation[actGasStationId] == -1) {
    	    	fuelNeed = distance - gasStations[actGasStationId][0];
    	    } else {
    	    	fuelNeed = gasStations[nextCheaperGasStation[actGasStationId]][0] - gasStations[actGasStationId][0];
    	    }
    	    
    	    //Tankol�s
    	    if (fuelNeed > fuelCapacity) {
    	    	fuelNeed = fuelCapacity;
    	    }
    	    if (fuelNeed > actFuel) {
				totalFuelCost += (fuelNeed - actFuel) * gasStations[actGasStationId][1];
				actFuel = fuelNeed;
			}
    	    
    	    //Ha m�r el�rhet� ezzel a c�l�llom�s akkor meg�ll a ciklus
    	    if (reachedDestination(actGasStationId, actFuel)) {
				break;
			}
    	    
    	    //Utaz�s
    		if(actGasStationId != numberOfStation -1 ) {
    			actFuel -= gasStations[actGasStationId + 1][0] - gasStations[actGasStationId][0];			 			
    		}     		
    	}
    	return totalFuelCost;
    }
    
    /**
     * Beolvassa az adatokat a standard inputr�l �s be�llitja a p�ld�ny mez�it.
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
