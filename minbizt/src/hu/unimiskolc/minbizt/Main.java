/*
 * @Main.java
 * 
 * 1.0 
 * 
 * 20/04/28
 * 
 * Copyright (c) 2020 University of Miskolc
 */

package hu.unimiskolc.minbizt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hu.unimiskolc.minbizt.Problem.Problem;


/**
 * Main class. The base of this program which provides entry and exit point.
 * 
 * @version 1.0 28 April 2020
 * @author Stéfán Norbert. 2020.04.21.
 */
public class Main {

	public static void main(String[] args) throws IOException { 
			
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		Problem alhamToBaynor;
        String split[];
        long distance;
        long fuelCapacity;
        int numberOfStation;
        long[][] gasStations;
        long minCost;
        
        distance =  Long.parseLong(reader.readLine());
        fuelCapacity =  Long.parseLong(reader.readLine());
        numberOfStation =  Integer.parseInt(reader.readLine()) + 1;
        
        gasStations = new long [numberOfStation][2];
        gasStations[0][0] = 0;
   	    gasStations[0][1] = 999999999;
        
        for (int i = 1; i < numberOfStation  ; i++) {
        	 split = reader.readLine().split(" ");
        	 gasStations[i][0] = Long.parseLong(split[0]);
        	 gasStations[i][1] = Long.parseLong(split[1]);
		}
        
       alhamToBaynor = new Problem(distance, fuelCapacity, numberOfStation, gasStations);
       minCost = alhamToBaynor.solveProblem(); 
       
       System.out.println(minCost);
       
	}

}
