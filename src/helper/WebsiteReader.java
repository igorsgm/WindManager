package helper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteReader {
	
	private String characterToSearch;
	private String informationRequired;
	
	
	public WebsiteReader(String characterToSearch, String informationRequired) {
		this.characterToSearch = characterToSearch;
		this.informationRequired = informationRequired;
	}
	
	//Constructor used when checking character status (on/off)
	public WebsiteReader(String characterToSearch) {
		this.characterToSearch = characterToSearch;
	}

	public String characterInfoReader() throws IOException{
		//Connecting to the website
		Document doc = Jsoup.connect("http://www.tibia.com/community/?subtopic=characters&name=" + this.characterToSearch).userAgent("Mozilla").get();
		
		//Searching for some specific Field, eg "Level:"
		Elements result = doc.select("*:containsOwn(" + this.informationRequired +":)");
		
		//Getting results and returning the next element, that is the information
		for( Element e : result )
		{
		    String value = e.nextElementSibling().text(); /* text of next element */
		    return value;
		}
		throw new IOException();
	}
	
	public String characterStatusReader() throws IOException{
		
		/*Setting informationRequired to World in order the characterInfoReader method retrieves the World,
		 * which is necessary to check character status
		 */
		this.informationRequired = "World";
		Document doc = Jsoup.connect("https://secure.tibia.com/community/?subtopic=worlds&world=" + this.characterInfoReader()).userAgent("Mozilla").get();
		
		//Checking if in the specific page, has some element containing character's name as text
		if (doc.getElementsContainingOwnText(this.characterToSearch).hasText()){
			return "online";
		}else{
			return "offline";
		}
	}
	

}
