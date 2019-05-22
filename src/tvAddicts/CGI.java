package tvAddicts;

public interface CGI extends Comparable<CGI> {

	String name();
	
	int numberOfCharacters();
	
	void addVirtualCharacter();
	
	void addFeesCollected(int fees);
	
	int feesCollected();
}
