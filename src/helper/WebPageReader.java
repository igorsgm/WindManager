package helper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebPageReader {
	
	private String urlRecieved;
	private String fieldRequired;
		
	public WebPageReader(String urlRecieved, String fieldRequired) throws IOException {
		this.urlRecieved = urlRecieved;
		this.fieldRequired = fieldRequired;
		this.getInfo(urlRecieved, fieldRequired);
	}

	public String getInfo(String urlRecieved, String fieldRequired) throws IOException{
		//Connecting to de website
		Document doc = Jsoup.connect(urlRecieved).get();
		//Searching for some specific Field, eg "Level:"
		Elements result = doc.select("*:containsOwn(" + fieldRequired + ":)" );
		//Getting results and returning the next elemen, that is the information
		for( Element e : result )
		{
		    String value = e.nextElementSibling().text(); /* text of next element */
		    return value;
		}
		throw new IOException();
	}

	public String getUrlRecieved() {
		return urlRecieved;
	}

	public void setUrlRecieved(String urlRecieved) {
		this.urlRecieved = urlRecieved;
	}

	public String getFieldRequired() {
		return fieldRequired;
	}

	public void setFieldRequired(String fieldRequired) {
		this.fieldRequired = fieldRequired;
	}
	
	
}




