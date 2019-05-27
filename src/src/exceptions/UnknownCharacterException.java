package exceptions;

@SuppressWarnings("serial")
public class UnknownCharacterException extends Exception {

	public UnknownCharacterException() {
		super();
	}
	public UnknownCharacterException(String characterName) {
		super(characterName);
	}
}