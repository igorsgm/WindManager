package helper;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {

	private PrintWriter writer;
	private String fileName;
	
	public Writer(String fileName) {
		this.fileName = fileName;
		
	}
	
	public void write(String text) {
		try {
			this.writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.writer.println(text);
		this.writer.close();	
	}
	
}
