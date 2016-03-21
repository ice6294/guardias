/**
 *                ███
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███                     ██
 *                ███   █  █  █  █        ██
 *                ███    ▀▀ ▀▀ ▀▀█        ██
 * ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄███▄▄▄▄▄▄▄▄▄▄▄▄█▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *     ▀▀▄▄       ███            █        ██
 *        ▀▀▄▄   ███▀            █        ██
 *           ▀▀███▀              █        ██
 *                               █▄▄▄▄▄▄▄▄██▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ ▄
 *      Copyright (c) 2016       █  ▀▀▄▄  ██
 *      All right reserved       █     ▀▀██▀
 *                               ▀
 */
package guardias;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @version v1.0
 * @author luis
 */
public class CalendarioTest {

	/**
	 * Test of getId method, of class Calendario.
	 */
	@Test
	public void testGetId() {
		System.out.println("Test getId");
		Calendario instance = new Calendario(2016, 2, 360);
		String expResult = "2016_2_360";
		String result = instance.getId();
		assertEquals(expResult, result);
	}

	/**
	 * Test of setId method, of class Calendario.
	 */
	@Test
	public void testSetId() {
		System.out.println("Test setId");
		Calendario instance = new Calendario(2016, 2, 360);
		instance.setId();
	}

	/**
	 * Test of getYear method, of class Calendario.
	 */
	@Test
	public void testGetYear() {
		System.out.println("Test getYear");
		Calendario instance = new Calendario(2016, 2, 360);
		Integer expResult = 2016;
		Integer result = instance.getYear();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getMonth method, of class Calendario.
	 */
	@Test
	public void testGetMonth() {
		System.out.println("Test getMonth");
		Calendario instance = new Calendario(2016, 2, 360);
		Integer expResult = 2;
		Integer result = instance.getMonth();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getSeed method, of class Calendario.
	 */
	@Test
	public void testGetSeed() {
		System.out.println("Test getSeed");
		Calendario instance = new Calendario(2016, 2, 360);
		Integer expResult = 360;
		Integer result = instance.getSeed();
		assertEquals(expResult, result);
	}

	/**
	 * Test of setSeed method, of class Calendario.
	 */
	@Test
	public void testSetSeed() {
		System.out.println("Test setSeed");
		Integer seed = 240;
		Calendario instance = new Calendario(2016, 2, 360);
		instance.setSeed(seed);
	}

	/**
	 * Test of getDia method, of class Calendario.
	 */
	@Test
	public void testGetDia() {
		System.out.println("Test getDia");
		Integer d = 0;
		Calendario instance = new Calendario(2016, 2, 360);
		Dia expResult = new Dia(0, 1);
		Dia result = instance.getDia(d);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isDay method, of class Calendario.
	 */
	@Test
	public void testIsDay() {
		System.out.println("Test isDay");
		Integer day1 = -1;
		Integer day2 = 20;
		Integer day3 = 30;
		Integer day4 = 36;
		Calendario instance = new Calendario(2016, 2, 360);
		boolean expResult1 = false;
		boolean expResult2 = true;
		boolean expResult3 = true;
		boolean expResult4 = false;
		boolean result1 = instance.isDay(day1);
		boolean result2 = instance.isDay(day2);
		boolean result3 = instance.isDay(day3);
		boolean result4 = instance.isDay(day4);
		assertEquals(expResult1, result1);
		assertEquals(expResult2, result2);
		assertEquals(expResult3, result3);
		assertEquals(expResult4, result4);
	}

	/**
	 * Test of hasNext method, of class Calendario.
	 */
	@Test
	public void testHasNext() {
		System.out.println("Test hasNext");
		Integer day1 = -1;
		Integer day2 = 20;
		Integer day3 = 30;
		Integer day4 = 36;
		Calendario instance = new Calendario(2016, 2, 360);
		boolean expResult1 = false;
		boolean expResult2 = true;
		boolean expResult3 = false;
		boolean expResult4 = false;
		boolean result1 = instance.hasNext(day1);
		boolean result2 = instance.hasNext(day2);
		boolean result3 = instance.hasNext(day3);
		boolean result4 = instance.hasNext(day4);
		assertEquals(expResult1, result1);
		assertEquals(expResult2, result2);
		assertEquals(expResult3, result3);
		assertEquals(expResult4, result4);
	}

	/**
	 * Test of next method, of class Calendario.
	 */
	@Test
	public void testNext() {
		System.out.println("Test next");
		Integer day = 1;
		Calendario instance = new Calendario(2016,2,360);
		Dia expResult = new Dia(2,3);
		Dia result = instance.next(day);
		assertEquals(expResult, result);
	}

	/**
	 * Test of isFriday method, of class Calendario.
	 */
	@Test
	public void testIsFriday() {
		System.out.println("Test isFriday");
		Integer day1 = 0;
		Integer day2 = 3;
		Calendario instance = new Calendario(2016, 2, 360);
		boolean expResult1 = false;
		boolean expResult2 = true;
		boolean result1 = instance.isFriday(day1);
		boolean result2 = instance.isFriday(day2);
		assertEquals(expResult1, result1);
		assertEquals(expResult2, result2);
	}

	/**
	 * Test of hasNextFriday method, of class Calendario.
	 */
	@Test
	public void testHasNextFriday() {
		System.out.println("Test hasNextFriday");
		Integer day1 = 3;
		Integer day2 = 24;
		Calendario instance = new Calendario(2016, 2, 360);
		boolean expResult1 = true;
		boolean expResult2 = false;
		boolean result1 = instance.hasNextFriday(day1);
		boolean result2 = instance.hasNextFriday(day2);
		assertEquals(expResult1, result1);
		assertEquals(expResult2, result2);
	}

	/**
	 * Test of monthName method, of class Calendario.
	 */
	@Test
	public void testMonthName() {
		System.out.println("Test monthName");
		Calendario instance = new Calendario(2016, 2, 360);
		String expResult = "Marzo";
		String result = instance.monthName();
		assertEquals(expResult, result);
	}

	/**
	 * Test of calendarName method, of class Calendario.
	 */
	@Test
	public void testCalendarName() {
		System.out.println("Test calendarName");
		Calendario instance = new Calendario(2016, 2, 360);
		String expResult = "2016 - Marzo";
		String result = instance.calendarName();
		assertEquals(expResult, result);
	}
	
}
