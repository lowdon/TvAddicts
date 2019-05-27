package exceptions;

@SuppressWarnings("serial")
public class UnknownSeasonException extends Exception {

	public UnknownSeasonException() {
		super();
	}
	public UnknownSeasonException(String showName) {
		super(showName);
	}
}