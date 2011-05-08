import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.PriorityQueue;

public class BiggestIntegersFromFiles {
	
	// 64 bit unsigned char
	private static final int UNSIGNED_INT_SIZE_CHAR_SIZE = 20;
	
	public Collection<Double> getBiggestUnsignedIntegerFromFiles(String filename, int n)
			throws IOException {

		File file = new File(filename);
		
		if(file.length() % UNSIGNED_INT_SIZE_CHAR_SIZE != 0) {
			throw new IOException("Invalid file, should containt only 64 bit unsigned char (formatted on 20 digits)");
		}
		
		PriorityQueue<Double> biggest = new PriorityQueue<Double>(n);
		FileReader reader = new FileReader(filename);
		
		try {
			
			double current = -1;
			char[] buffer = new char[UNSIGNED_INT_SIZE_CHAR_SIZE]; 
			
			while ((current = getNextInteger(reader, buffer)) != -1) {

				biggest.add(current);
				
				if(biggest.size() > n) {
					biggest.remove();
				}
				
			}
			
		} finally {
			reader.close();
		}

		return biggest;
	}

	private double getNextInteger(FileReader reader, char[] buffer) throws IOException {

		if(reader.read(buffer)==-1){
			return -1;
		}
		
		double dec = 1;
		double result = 0;
		for (int i = buffer.length-1; i >= 0; i--) {
			if(buffer[i] < '0' || buffer[i] > '9') {
				throw new IOException("Invalid file, should containt only 64 bit unsigned char, found " + String.valueOf(buffer));
			}
			result += (buffer[i] - '0') * dec;
			dec = dec*10;
		}
		
		return result;
	}

}
