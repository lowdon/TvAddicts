package tvAddicts;

import java.util.Iterator;

public interface Episode {

	/**
	 * @return numero identificativo do <code>Episode</code>
	 */
	public int number();
	
	/**
	 * @return nome identificativo do <code>Episode</code>
	 */
	public String name();
	
	/**
	 * cira um novo <code>Event</code>
	 * @param descriptionOfTheEvent descricao do <code>Event</code>
	 * @param involvedCharacters lista com todo o <code>Character</code> que intervem nesse <code>Event</code>
	 */
	public void addEvent(String descriptionOfTheEvent, Iterator<Character> involvedCharacters);
	
	/**
	 * @return lista com todo o <code>Event</code> do <code>Episode</code>
	 */
	public Iterator<Event> eventsList();
}
