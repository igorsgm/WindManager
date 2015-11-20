package exception;

public class CharacterNotFoundException extends Exception {

	private String message;
	
	public CharacterNotFoundException() {
		this.message = "The Character could not be found.";
	}
	
	public CharacterNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}