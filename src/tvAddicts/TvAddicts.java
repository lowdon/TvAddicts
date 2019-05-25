package tvAddicts;

import java.util.Iterator;

public interface TvAddicts {

	/**
	 * @return <code>Show</code> atual
	 */
	Show currentShow ();
	
	/**
	 * adiciona um <code>Show</code>
	 * @param showName nome identificativo do <code>Show</code>
	 */
	void addShow(String showName);
	
	/**
	 * altera o <code>Show</code> atual
	 * @param showName nome identificativo do <code>Show</code>
	 */
	void switchToShow(String showName);
	
	/**
	 * adiciona um <code>Season</code> a um <code>Show</code>
	 */
	void addSeason();
	
	/**
	 * adicioana um novo <code>Episode</code> ao <code>Show</code>
	 * @param seasonNum numero identificativo da <code>Season</code> 
	 * @param espisodeName nome do <code>Episode</code>
	 */
	void addEpisode(int seasonNum, String espisodeName);
	
	/**
	 * adiciona um novo <code>RealCharacter</code>
	 * @param characterName nome do <code>Character</code>
	 * @param actor <code>Actor</code> que faz de <code>Character</code>
	 * @param feeByEpisode dinehro recebido por <code>Episode</code> pelo <code>Actor</code>
	 */
	void addRealCharacter(String characterName, String actorName, int feeByEpisode);
	
	/**
	 * adiciona um novo <code>VirtualCharacter</code>
	 * @param characterName nome do <code>Character</code>
	 * @param company <code>CGI</code> que criou o <code>Character</code>
	 * @param costPerSeason dinheiro recebido pela <code>CGI</code> por cada <code>Season</code> em que o <code>Character</code> partecipa
	 */
	void addVirtualCharacter(String characterName, String companyName, int costPerSeason);
	
	/**
	 * adiciona uma relacao entre dois <code>Character</code>
	 * @param character1Name nome identificativo do primeiro <code>Character</code>
	 * @param character2Name nome identificativo do segundo <code>Character</code>
	 */
	void addRelationship(String character1Name, String character2Name);
	
	/**
	 * adiciona um romance entre dois <code>Character</code>
	 * @param character1Name nome identificativo do primeiro <code>Character</code>
	 * @param character2Name nome identificativo do segundo <code>Character</code>
	 */
	void addRomance(String character1Name, String character2Name);
	
	/**
	 * adiciona um <code>Event</code>
	 * @param descriptionOfTheEvent descricao do <code>Event</code>
	 * @param seasonNum numero identificativo da <code>Season</code> onde ocorre o <code>Event</code>
	 * @param episodeNum numero identificativo do <code>Episode</code> onde ocorre o <code>Event</code>
	 * @param involvedCharacters lista com todo o <code>Character</code> que intervem nesse <code>Event</code>
	 */
	void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<Character> involvedCharacters);
	
	/**
	 * adicona uma <code>quote</code> a um <code>Character</code>
	 * @param seasonNum numero identificativo da <code>Season</code> onde o <code>Character</code> disse a <code>Quote</code>
	 * @param episodeNum numero identificativo do <code>Episode</code> onde o <code>Character</code> disse a <code>Quote</code>
	 * @param characterName nome do <code>Character</code>
	 * @param quote <code>Quote</code>
	 */
	void addQuote(int seasonNum, int episodeNum, String characterName, String quote);
	
	/**
	 * resumo de partes do <code>Show</code>
	 * @param startingSeason <code>Season</code> onde comeca o resumo
	 * @param endingSeason <code>Season</code> onde termina o resumo
	 * @return lista dos episodios do resumo
	 */
	Iterator<Episode> seasonsOutline (int startingSeason, int endingSeason);
	
	/**
	 * @param characterName nome do <code>Character</code>
	 * @return <code>Character</code>
	 */
	Character characterResume(String characterName);
	
	/**
	 * @param character1Name nome do primeiro <code>Character</code>
	 * @param character2Name nome do  segundo <code>Character</code>
	 * @return retoma a lista de <code>Character</code> comecando no primeiro ascendete e termina no ultimo descendente
	 */
	Iterator<Character> howAreTheseTwoRelated (String character1Name, String character2Name);
	
	/**
	 * @param quote <code>Quote</code>
	 * @return lista de <code>Character</code> que disseram a <code>Quote</code>
	 */
	Iterator<Character> famousQuotes (String quote);
	
	/**
	 * @param characterName nome do <code>Character</code>
	 * @return lista de <code>Show</code> onde o <code>Actor</code> do <code>Character</code> partecipa
	 */
	Iterator<Show> alsoAppearsOn (String characterName);
	
	/**
	 * @param actorName nome do <code>Actor</code>
	 * @return lista de <code>Actor</code> que sao mais romanticos que o <code>Actor dado</code> (por ordem crescente de romancismo)
	 */
	Iterator<Actor> mostRomantic (String actorName);
	
	/**
	 * @return <code>CGI</code> que mais lucro vez com os seus <code>VirtualCharacter</code>
	 */
	CGI kingOfGDI();
	
}
