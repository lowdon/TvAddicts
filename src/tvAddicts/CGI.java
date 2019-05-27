package tvAddicts;

public interface CGI extends Comparable<CGI> {

	/**
	 * @return nome da <code>CGI</code>
	 */
	String name();
	
	/**
	 * @return numero de carateres da <code>CGI</code>
	 */
	int numberOfCharacters();
	
	/**
	 * adiciona um <code>VirtualCharacter</code> a <code>CGI</code>
	 */
	void addVirtualCharacter();
	
	/**
	 * adiciona dinheiro ganho por cada <code>VirtualCharacter</code> da <code>CGI</code>
	 * @param fees dinheiro coletado com as personagens
	 */
	void addFeesCollected(int fees);
	
	/**
	 * @return dinheiro coletado pela <code>CGI</code>
	 */
	int feesCollected();
}
