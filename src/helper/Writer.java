package helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private BufferedWriter writer;
	
	public Writer(String filePath) {
			try {
				this.writer = new BufferedWriter(new FileWriter(filePath, true));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void write(String text) {
		try {
			this.writer.append(text);
			this.writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.writer.flush();
			this.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}