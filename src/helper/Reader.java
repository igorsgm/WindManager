package helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

	private BufferedReader reader;
	
	public Reader(String path) {
		try {
			this.reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public String readLine() {
		try {
			return this.reader.readLine();
		} catch (IOException e) {
			return "IOException";
		}
	}
	
	public ArrayList<String> readFile() {
		ArrayList<String> lines = new ArrayList<String>();
		String line = this.readLine();
		while (line != null) {
			lines.add(line);
			line = this.readLine();
		}
		return lines;
	}
	
}