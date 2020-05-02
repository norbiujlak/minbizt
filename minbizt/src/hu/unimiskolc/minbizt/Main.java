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

import java.io.IOException;

import hu.unimiskolc.minbizt.Problem.Problem;

/**
 * Main osztály. Ez tartalmazza a program belépési és kilépési pontját.
 * 
 * @version 1.0 28 April 2020
 * @author Stéfán Norbert. 2020.04.28.
 */
public class Main {
	
	public static void main(String[] args) throws IOException { 
       Problem alhamToBaynor = new Problem();
       alhamToBaynor.readFromConsole();
      
       System.out.println(alhamToBaynor.solveProblem());     
	}

}
