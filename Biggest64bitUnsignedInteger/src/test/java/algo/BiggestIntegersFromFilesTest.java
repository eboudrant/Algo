package algo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Random;
import java.util.StringTokenizer;

import org.junit.Test;

public class BiggestIntegersFromFilesTest {

	@Test
	public void testGetBiggestUnsignedIntegerFromFilesError3() {

		System.out.println("Launching test...");
		
		try {
			BiggestIntegersFromFiles fromFiles = new BiggestIntegersFromFiles();
			fromFiles.getBiggestUnsignedIntegerFromFiles("/inexisting_file", 1);
			fail("test should fail because no file");
		} catch (FileNotFoundException e) {
			// expected
			System.out.println("Test done : " + e.getMessage());
		} catch (Throwable e) {
			System.out.println("Test fail");
			e.printStackTrace();
			fail(e.toString());
		}
	}
	@Test
	public void testGetBiggestUnsignedIntegerFromFilesError2() {
		String testFilename = System.getProperty("java.io.tmpdir")
				+ "/sample.txt";
		try {
			System.out.println("Writing test file : " + testFilename + "...");

			FileWriter writer = null;
			try {
				writer = new FileWriter(testFilename);
				writer.write("18446326131104610000");
				writer.write("184463261x1104610000");
				writer.write("18446326131104610000");
			} catch (IOException e) {
				e.printStackTrace();
				fail(e.toString());
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
						fail(e.toString());
					}
				}
			}
			System.out.println("Launching test...");
			
			try {
				BiggestIntegersFromFiles fromFiles = new BiggestIntegersFromFiles();
				fromFiles.getBiggestUnsignedIntegerFromFiles(testFilename, 1);
				fail("test should fail because invalid file");
			} catch (IOException e) {
				// expected
				System.out.println("Test done : " + e.getMessage());
			} catch (Throwable e) {
				System.out.println("Test fail");
				e.printStackTrace();
				fail(e.toString());
			}
		} finally {
			assertTrue("Cannot delete file " + testFilename, new File(
					testFilename).delete());
		}
	}
	@Test
	public void testGetBiggestUnsignedIntegerFromFilesError1() {
		String testFilename = System.getProperty("java.io.tmpdir")
				+ "/sample.txt";
		try {
			System.out.println("Writing test file : " + testFilename + "...");

			FileWriter writer = null;
			try {
				writer = new FileWriter(testFilename);
				writer.write("18446326131104610000");
				writer.write("18446326131104610000");
				writer.write("1844632613110461000");
			} catch (IOException e) {
				e.printStackTrace();
				fail(e.toString());
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
						fail(e.toString());
					}
				}
			}
			System.out.println("Launching test...");
			
			try {
				BiggestIntegersFromFiles fromFiles = new BiggestIntegersFromFiles();
				fromFiles.getBiggestUnsignedIntegerFromFiles(testFilename, 1);
				fail("test should fail because invalid file");
			} catch (IOException e) {
				// expected
				System.out.println("Test done : " + e.getMessage());
			} catch (Throwable e) {
				System.out.println("Test fail");
				e.printStackTrace();
				fail(e.toString());
			}
		} finally {
			assertTrue("Cannot delete file " + testFilename, new File(
					testFilename).delete());
		}
	}

	@Test
	public void testGetBiggestUnsignedIntegerFromFiles() {

		String testFilename = System.getProperty("java.io.tmpdir")
				+ "/sample.txt";
		try {
			System.out.println("Writing test file : " + testFilename + "...");

			FileWriter writer = null;
			DecimalFormat decimalFormat = new DecimalFormat(
					"00000000000000000000");
			try {

				writer = new FileWriter(testFilename);
				// Generate a test file with random 64 bit unsigned int, use
				// random in java int
				// range (0 to MAX_VALUE) for that. Seed used so we can do
				// assertion
				// on resulted collection
				Random random = new Random(10);
				// Limit the test to the 1000000 random numbers
				for (int i = 0; i < 1000000; i++) {
					// generate a random number in range
					double number = random.nextLong();
					// set unsigned 64bit integer
					number = Long.MAX_VALUE + number;
					writer.write(decimalFormat.format(number));
				}
			} catch (IOException e) {
				e.printStackTrace();
				fail(e.toString());
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
						fail(e.toString());
					}
				}
			}
			System.out.println("Launching test...");

			BiggestIntegersFromFiles fromFiles = new BiggestIntegersFromFiles();

			try {
				long start = System.currentTimeMillis();
				// Limit the test to the 10 max numbers
				Collection<Double> result = fromFiles
						.getBiggestUnsignedIntegerFromFiles(testFilename, 10);
				System.out.println("Test done in : "
						+ (System.currentTimeMillis() - start) + " msec");

				assertEquals("Resulted colection should contain 10 elements",
						10, result.size());

				String expectedResult = "18446326131104610000,18446334817560338000,18446411251133755000,18446443658605283000,18446430760163353000,18446521290790330000,18446688206455910000,18446525527170695000,18446512447355933000,18446738096053789000";
				StringTokenizer tokenizer = new StringTokenizer(expectedResult,
						",");
				while (tokenizer.hasMoreElements()) {
					String number = (String) tokenizer.nextElement();
					assertTrue("Resulted colection should contain " + number,
							result.contains(Double.valueOf(number.trim())));
				}
			} catch (Throwable e) {
				System.out.println("Test fail");
				e.printStackTrace();
				fail(e.toString());
			}
		} finally {
			assertTrue("Cannot delete file " + testFilename, new File(
					testFilename).delete());
		}

	}

}
