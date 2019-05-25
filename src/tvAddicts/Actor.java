package tvAddicts;

import java.util.Iterator;

public interface Actor {

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
}
