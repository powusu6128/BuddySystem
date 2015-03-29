/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Functions Class JUnit Tests
 * 
 * @author Justin Hyland
 */
public class FunctionsTest {

	public FunctionsTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of isPowerOfTwo method, of class Functions.
	 */
	@Test
	public void testIsPowerOfTwo() {
		System.out.println("isPowerOfTwo");
		long number = 32;
		long number1 = 255;
		long number2 = 53;
		long number3 = 64;

		Functions instance = new Functions();
		Functions instance1 = new Functions();
		Functions instance2 = new Functions();
		Functions instance3 = new Functions();

		boolean expResult = true;
		boolean result = instance.isPowerOfTwo(number);
		assertEquals(expResult, result);

		boolean expResult1 = false;
		boolean result1 = instance1.isPowerOfTwo(number1);
		assertEquals(expResult1, result1);

		boolean expResult2 = false;
		boolean result2 = instance2.isPowerOfTwo(number2);
		assertEquals(expResult2, result2);

		boolean expResult3 = true;
		boolean result3 = instance3.isPowerOfTwo(number3);
		assertEquals(expResult3, result3);

	}

	/**
	 * Test of log2 method, of class Functions.
	 */
	@Test
	public void testLog2() {
		System.out.println("log2");
		long num = 1024;
		long num1 = 64;
		long num2 = 256;

		Functions instance = new Functions();
		Functions instance1 = new Functions();
		Functions instance2 = new Functions();

		int expResult = 10;
		int result = instance.log2(num);
		assertEquals(expResult, result);

		int expResult1 = 6;
		int result1 = instance1.log2(num1);
		assertEquals(expResult1, result1);

		int expResult2 = 8;
		int result2 = instance2.log2(num2);
		assertEquals(expResult2, result2);

	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/**
	 * Test exception on IsPowerOf2 method, of class Functions
	 */
	@Test
	public void testExceptionOnIsPowerOf2() {
		System.out.println("Exception");
		exception.expect(IllegalArgumentException.class);
		long num3 = -1;
		Functions instance3 = new Functions();
		instance3.isPowerOfTwo(num3);

	}

}
