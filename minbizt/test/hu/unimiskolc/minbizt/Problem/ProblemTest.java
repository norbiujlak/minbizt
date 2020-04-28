package hu.unimiskolc.minbizt.Problem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProblemTest {

	@Test
	public void testGetDistance() {
		Problem problem = new Problem();
		
		problem.setDistance(4);
		assertEquals(problem.getDistance(), 4);
		
		problem.setDistance(0);
		assertEquals(problem.getDistance(), 0);
		
		problem.setDistance(-32567);
		assertEquals(problem.getDistance(), -32567);
	}

	@Test
	public void testSetDistance() {
		Problem problem = new Problem();
		
		problem.setDistance(4);
		assertEquals(problem.getDistance(), 4);
		
		problem.setDistance(0);
		assertEquals(problem.getDistance(), 0);
		
		problem.setDistance(-32567);
		assertEquals(problem.getDistance(), -32567);
	}

	@Test
	public void testGetFuelCapacity() {
		Problem problem = new Problem();
		
		problem.setFuelCapacity(127);
		assertEquals(problem.getFuelCapacity(), 127);
		
		problem.setFuelCapacity(0);
		assertEquals(problem.getFuelCapacity(), 0);
		
		problem.setFuelCapacity(-127);
		assertEquals(problem.getFuelCapacity(), -127);
	}

	@Test
	public void testSetFuelCapacity() {
		Problem problem = new Problem();
		
		problem.setFuelCapacity(127);
		assertEquals(problem.getFuelCapacity(), 127);
		
		problem.setFuelCapacity(0);
		assertEquals(problem.getFuelCapacity(), 0);
		
		problem.setFuelCapacity(-127);
		assertEquals(problem.getFuelCapacity(), -127);
	}

	@Test
	public void testGetNumberOfStation() {
		Problem problem = new Problem();
		
		problem.setNumberOfStation(1247);
		assertEquals(problem.getNumberOfStation(), 1247);
		
		problem.setNumberOfStation(0);
		assertEquals(problem.getNumberOfStation(), 0);
		
		problem.setNumberOfStation(-1);
		assertEquals(problem.getNumberOfStation(), -1);
	}

	@Test
	public void testSetNumberOfStation() {
		Problem problem = new Problem();
		
		problem.setNumberOfStation(1247);
		assertEquals(problem.getNumberOfStation(), 1247);
		
		problem.setNumberOfStation(0);
		assertEquals(problem.getNumberOfStation(), 0);
		
		problem.setNumberOfStation(-1);
		assertEquals(problem.getNumberOfStation(), -1);
	}

	@Test
	public void testGetGasStations() {
		Problem problem = new Problem();
		
		long[][] arr = { { 1, 2 }, { 3, 4 } };
		problem.setGasStations(arr);
		assertArrayEquals(problem.getGasStations(), arr);
		
		long[][] arr2 = { { 1, 2 } };
		problem.setGasStations(arr2);
		assertArrayEquals(problem.getGasStations(), arr2);
		
		long[][] arr3 = { { 1, 2 }, { 3, 4 } , { 999, 484515 } };
		problem.setGasStations(arr3);
		assertArrayEquals(problem.getGasStations(), arr3);
	}

	@Test
	public void testSetGasStations() {
		Problem problem = new Problem();
		
		long[][] arr = { { 1, 2 }, { 3, 4 } };
		problem.setGasStations(arr);
		assertArrayEquals(problem.getGasStations(), arr);
		
		long[][] arr2 = { { 1, 2 } };
		problem.setGasStations(arr2);
		assertArrayEquals(problem.getGasStations(), arr2);
		
		long[][] arr3 = { { 1, 2 }, { 3, 4 } , { 999, 484515 } };
		problem.setGasStations(arr3);
		assertArrayEquals(problem.getGasStations(), arr3);
	}

	@Test
	public void testProblem() {
		long[][] arr = { { 1, 2 }, { 3, 4 } };
		Problem problem = new Problem(1, 2, 3, arr);
		assertEquals(problem.getDistance(), 1);
		assertEquals(problem.getFuelCapacity(), 2);
		assertEquals(problem.getNumberOfStation(), 3);
		assertArrayEquals(problem.getGasStations(), arr);
	}
	
	@Test
	public void testEnoughTheFuel() {
		long[][] arr = { { 0, 999 }, { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 }   };
		Problem problem = new Problem(100, 50, 5, arr);
		assertTrue(problem.enoughTheFuel(0, 1));
		assertTrue(problem.enoughTheFuel(0, 2));
		assertTrue(problem.enoughTheFuel(0, 3));
		assertFalse(problem.enoughTheFuel(0, 4));
		assertTrue(problem.enoughTheFuel(3, 4));
	}
	
	@Test
	public void testReachedDestination() {
		long[][] arr = { { 0, 999 }, { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 }   };
		Problem problem = new Problem(100, 50, 5, arr);
		assertTrue(problem.reachedDestination(4));
		assertTrue(problem.reachedDestination(3));
		assertFalse(problem.reachedDestination(2));
		assertFalse(problem.reachedDestination(1));
		assertFalse(problem.reachedDestination(0));		
	}
	
	@Test
	public void testCostCalculation() {
		long[][] arr = { { 0, 999 }, { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 }   };
		Problem problem = new Problem(100, 50, 5, arr);
		assertEquals(problem.costCalculation(0, 1), 2000);
		assertEquals(problem.costCalculation(0, 2), 300);
		assertEquals(problem.costCalculation(1, 2), 100);
	}
	
	@Test
	public void testSearchBestGasStationd() {
		long[][] arr = { { 0, 999 }, { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 }   };
		Problem problem = new Problem(100, 50, 5, arr);
		assertEquals(problem.searchBestGasStationd(0), 2);
		assertEquals(problem.searchBestGasStationd(1), 2);
	}

	@Test
	public void testSolveProblem() {
		long[][] arr = { { 0, 999 }, { 20, 100 }, { 30, 10 }, { 50, 60 }, { 70, 70 }   };
		Problem problem = new Problem(100, 50, 5, arr);
		assertEquals(problem.solveProblem(), 1500);
		
		long[][] arr2 = { { 0, 999999999 }, { 1157, 41 }, { 497, 95 }, { 124, 14 }, { 2020, 46 }, { 1608, 65 },
				{ 1346, 100 }, { 2764, 47 }, { 2247, 56 }, { 481, 63 }};
		problem = new Problem(2801, 672, 10, arr2);
		assertEquals(problem.solveProblem(), 113786);
	}

}
