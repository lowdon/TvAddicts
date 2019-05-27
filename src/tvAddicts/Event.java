package tvAddicts;

import java.util.Iterator;

public interface Event {

	/**
	 * @return descricao do <code>Event</code>
	 */
	String descriptionOfTheEvent();
	
	/**
	 * @return lista com todo o <code>Character</code> envolvido no <code>Event</code>
	 */
	Iterator<Character> involvedCharacters();
}
