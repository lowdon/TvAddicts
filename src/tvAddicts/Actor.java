package tvAddicts;

import java.util.Iterator;

public interface Actor extends Comparable<Actor> {

	/**
	 * @return nome do <code>Actor</code>
	 */
	String name();
	
	/**
	 * @return a lista de personagens do <code>Ator</code>
	 */
	Iterator<Show> rolesListIterator();
	
	/**
	 * @param show adiciona uma personagem ao <code>Actor</code>
	 */
	void addRole(Show show);
	
	/**
	 * @return numero de <code>Character</code> do <code>Ator</code>
	 */
	int rolesNumber();
	
	/**
	 * @return numero de relacoes romanticas do <code>Ator</code>
	 */
	int romanticNumber();
	
	/**
	 * adiciona um romance
	 */
	void addRomance();
	
	/**
	 * @return numero de <code>Show</code> com relacoes romanticas do <code>Ator</code>
	 */
	int romanticShowsNumber();
	
	/**
	 * adicona o primeiro roamnce dum dado <code>Show</code>
	 */
	void addFirstRomanceInShow();
}
