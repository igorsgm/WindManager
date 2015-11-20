package exception;

public class CharacterNotFoundException extends Exception {

	private String message;
	
	public CharacterNotFoundException() {
		this.message = "The Character with the specified Account ID could not be found.";
	}
	
	public CharacterNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}