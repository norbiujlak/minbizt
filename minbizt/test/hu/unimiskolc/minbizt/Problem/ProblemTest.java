package hu.unimiskolc.minbizt.Problem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Test;

public class ProblemTest {
	
	@Test
	public void testGetDistance() {	
		Problem problem = new Problem();
		
		problem.setDistance(4);
		assertEquals(4, problem.getDistance());
		
		problem.setDistance(0);
		assertEquals(0, problem.getDistance());
		
		problem.setDistance(-32567);
		assertEquals(-32567, problem.getDistance());
	}

	@Test
	public void testSetDistance() {	
		Problem problem = new Problem();
		
		problem.setDistance(4);
		assertEquals(4, problem.getDistance());
		
		problem.setDistance(0);
		assertEquals(0, problem.getDistance());
		
		problem.setDistance(-32567);
		assertEquals(-32567, problem.getDistance());
	}

	@Test
	public void testGetFuelCapacity() {
		Problem problem = new Problem();
		
		problem.setFuelCapacity(127);
		assertEquals(127, problem.getFuelCapacity());
		
		problem.setFuelCapacity(0);
		assertEquals(0, problem.getFuelCapacity());
		
		problem.setFuelCapacity(-127);
		assertEquals(-127, problem.getFuelCapacity());
	}

	@Test
	public void testSetFuelCapacity() {
		Problem problem = new Problem();
		
		problem.setFuelCapacity(127);
		assertEquals(127, problem.getFuelCapacity());
		
		problem.setFuelCapacity(0);
		assertEquals(0, problem.getFuelCapacity());
		
		problem.setFuelCapacity(-127);
		assertEquals(-127, problem.getFuelCapacity());
	}

	@Test
	public void testGetNumberOfStation() {
		Problem problem = new Problem();
		
		problem.setNumberOfStation(1247);
		assertEquals(1247, problem.getNumberOfStation());
		
		problem.setNumberOfStation(0);
		assertEquals(0, problem.getNumberOfStation());
		
		problem.setNumberOfStation(-1);
		assertEquals(-1, problem.getNumberOfStation());
	}

	@Test
	public void testSetNumberOfStation() {
		Problem problem = new Problem();
		
		problem.setNumberOfStation(1247);
		assertEquals(1247, problem.getNumberOfStation());
		
		problem.setNumberOfStation(0);
		assertEquals(0, problem.getNumberOfStation());
		
		problem.setNumberOfStation(-1);
		assertEquals(-1, problem.getNumberOfStation());
	}

	@Test
	public void testGetGasStations() {
		Problem problem = new Problem();
		
		long[][] arr = { { 1, 2 }, { 3, 4 } };
		problem.setGasStations(arr);
		assertArrayEquals(arr, problem.getGasStations());
		
		long[][] arr2 = { { 1, 2 } };
		problem.setGasStations(arr2);
		assertArrayEquals(arr2, problem.getGasStations());
		
		long[][] arr3 = { { 1, 2 }, { 3, 4 }, { 999, 484515 } };
		problem.setGasStations(arr3);
		assertArrayEquals(arr3, problem.getGasStations());
	}

	@Test
	public void testSetGasStations() {
		Problem problem = new Problem();
		
		long[][] arr = { { 1, 2 }, { 3, 4 } };
		problem.setGasStations(arr);
		assertArrayEquals(arr, problem.getGasStations());
		
		long[][] arr2 = { { 1, 2 } };
		problem.setGasStations(arr2);
		assertArrayEquals(arr2, problem.getGasStations());
		
		long[][] arr3 = { { 1, 2 }, { 3, 4 } , { 999, 484515 } };
		problem.setGasStations(arr3);
		assertArrayEquals(arr3, problem.getGasStations());
	}

	@Test
	public void testProblem() {
		Problem problem = new Problem();
		
		long[][] arr = { { 1, 2 }, { 3, 4 } };
		problem = new Problem(1, 2, 3, arr);
		
		assertEquals(1, problem.getDistance());
		assertEquals(2, problem.getFuelCapacity());
		assertEquals(3, problem.getNumberOfStation());
		assertArrayEquals(arr, problem.getGasStations());
	}
	
	@Test
	public void testReachedDestination() {
		Problem problem = new Problem();
		
		long[][] arr = { { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 } };
		problem = new Problem(100, 50, 4, arr);
		
		assertFalse(problem.reachedDestination(0, 20));
		assertFalse(problem.reachedDestination(3, 10));
		assertTrue(problem.reachedDestination(3, 50));
		assertTrue(problem.reachedDestination(3, 30));
	}
	
	
	@Test
	public void findNextCheaperGasStations() {
		Problem problem = new Problem();
		
		long[][] arr = { { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 } };
		int [] cheaperStations = { 1, -1, -1, -1 };
		problem = new Problem(100, 50, 4, arr);
		
		problem.findNextCheaperGasStations();
		assertArrayEquals(cheaperStations, problem.getNextCheaperGasStation());
	}

	
	@Test
	public void testSolveProblem() {
		Problem problem = new Problem();
		
		long[][] arr = { { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 } };
		problem = new Problem(100, 50, 4, arr);
		
		assertEquals(1500, problem.solveProblem());
	}
	
	@Test
	public void testReadFromConsole() throws NumberFormatException, IOException {
		Problem problem = new Problem();		
		long[][] arr = { { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 } };
		
		String sysIn = "100\n50\n4\n20 100\n30 10\n50 60\n70 70"; 
		System.setIn(new ByteArrayInputStream(sysIn.getBytes()));

		problem.readFromConsole();
		assertEquals(100, problem.getDistance());
		assertEquals(50, problem.getFuelCapacity());
		assertEquals(4, problem.getNumberOfStation());
		assertArrayEquals(arr, problem.getGasStations());
	}
}
