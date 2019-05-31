package tvAddicts;

import java.util.Iterator;


public interface Season {
	Iterator<Character> characterListIterator();
	
	void addCharacter (Character character);
	
	
	boolean hasThisCharacter(Character character);
	
	
	/**
	 * @return numero de <code>Episode</code> na <code>Season</code>
	 */
	int episodesNumber();
	
	/**
	 * @return numero identificativo da <code>Season</code>
	 */
	int number();
	
	/**
	 * adiciona um <code>Episode</code>
	 * @param espisodeName nome do <code>Episode a adicionar</code>
	 */
	Episode addEpisode(String espisodeName, Show show);
	
	/**
	 * adiciona um <code>Event</code>
	 * @param descriptionOfTheEvent descricao do <code>Event</code>
	 * @param episodeNum numero identificativo do <code>Episode</code> onde ocorre o <code>Event</code>
	 * @param involvedCharacters lista com todo o <code>Character</code> que intervem nesse <code>Event</code>
	 */
	Event addEvent(String descriptionOfTheEvent, int episodeNum, Iterator<Character> involvedCharacters);
	
	/**
	 * @return lista com todo o <code>Episode</code> da <code>Season</code>
	 */
	Iterator<Episode> episodesList();
}
