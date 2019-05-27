package exceptions;

@SuppressWarnings("serial")
public class UnknownEpisodeException extends Exception {

	public UnknownEpisodeException() {
		super();
	}
	public UnknownEpisodeException(String showName) {
		super(showName);
	}
}