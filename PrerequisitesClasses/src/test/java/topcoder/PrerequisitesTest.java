package topcoder;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrerequisitesTest {

	private static String[] test1 = new String[] { "CSE110:", "CSE121: CSE110",
			"MATH122:" };
	private static String[] test1Result = new String[] {
		"CSE110","CSE121","MATH122" };
	
	private static String[] test2 = new String[] { "ENGL111: ENGL110" };

	private static String[] test3 = new String[] { "ENGL111: ENGL110",
			"ENGL110: ENGL111" };

	private static String[] test4 = new String[] {
			"CSE258: CSE244 CSE243 INTR100", "CSE221: CSE254 INTR100",
			"CSE254: CSE111 MATH210 INTR100", "CSE244: CSE243 MATH210 INTR100",
			"MATH210: INTR100", "CSE101: INTR100", "CSE111: INTR100",
			"ECE201: CSE111 INTR100", "ECE111: INTR100", "CSE243: CSE254",
			"INTR100:" };
	
	private static String[] test4Result = new String[] {
		"INTR100","CSE101","CSE111","ECE111","ECE201","MATH210","CSE254","CSE221","CSE243","CSE244" };

	private static String[] error1 = new String[] { "CS117:" };
	private static String[] error2 = new String[] { "cs117:" };
	private static String[] error3 = new String[] { "CS9E11:" };
	private static String[] error4 = new String[] { "CSE110: " };
	private static String[] error5 = new String[] { "CSE110: CSE101 " };
	private static String[] error6 = new String[] { "MATH211: MAM2222" };
	private static String[] error7 = new String[] { "MATH211: MAM22" };
	private static String[] error8 = new String[] { "ENGIN517: MATH211" };

	@Test
	public void testOrderClassesTest1() {
		String[] ordered = new Prerequisites().orderClasses(test1);
		System.out.print("[");
		for (int i = 0; i < ordered.length; i++) {
			System.out.print(ordered[i] + " ");
			assertEquals(test1Result[i], ordered[i]);
		}
		System.out.println("]");
	}

	@Test
	public void testOrderClassesTest2() {
		assertEquals(0, new Prerequisites().orderClasses(test2).length);
	}

	@Test
	public void testOrderClassesTest3() {
		assertEquals(0, new Prerequisites().orderClasses(test3).length);
	}

	@Test
	public void testOrderClassestest4() {

		String[] ordered = new Prerequisites().orderClasses(test4);
		System.out.print("[");
		for (int i = 0; i < ordered.length; i++) {
			System.out.print(ordered[i] + " ");
			assertEquals(test4Result[i], ordered[i]);
		}
		System.out.println("]");
		
	}

	@Test
	public void testOrderClassesError1() {
		assertEquals(0, new Prerequisites().orderClasses(error1).length);
	}

	@Test
	public void testOrderClassesError2() {
		assertEquals(0, new Prerequisites().orderClasses(error2).length);
	}

	@Test
	public void testOrderClassesError3() {
		assertEquals(0, new Prerequisites().orderClasses(error3).length);
	}

	@Test
	public void testOrderClassesError4() {
		assertEquals(0, new Prerequisites().orderClasses(error4).length);
	}

	@Test
	public void testOrderClassesError5() {
		assertEquals(0, new Prerequisites().orderClasses(error5).length);
	}

	@Test
	public void testOrderClassesError6() {
		assertEquals(0, new Prerequisites().orderClasses(error6).length);
	}

	@Test
	public void testOrderClassesError7() {
		assertEquals(0, new Prerequisites().orderClasses(error7).length);
	}

	@Test
	public void testOrderClassesError8() {
		assertEquals(0, new Prerequisites().orderClasses(error8).length);
	}

}
