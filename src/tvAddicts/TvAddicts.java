package tvAddicts;

import java.util.Iterator;

import exceptions.*;

public interface TvAddicts {

	/**
	 * @return <code>Show</code> atual
	 */
	Show currentShow () throws NoShowSelectedException;
	
	/**
	 * adiciona um <code>Show</code>
	 * @param showName nome identificativo do <code>Show</code>
	 */
	void addShow(String showName) throws ShowAlreadyExistsException;
	
	/**
	 * altera o <code>Show</code> atual
	 * @param showName nome identificativo do <code>Show</code>
	 */
	void switchToShow(String showName) throws UnknownShowException;
	
	/**
	 * adiciona um <code>Season</code> a um <code>Show</code>
	 */
	void addSeason() throws NoShowSelectedException;
	
	/**
	 * adicioana um novo <code>Episode</code> ao <code>Show</code>
	 * @param seasonNum numero identificativo da <code>Season</code> 
	 * @param espisodeName nome do <code>Episode</code>
	 */
	Episode addEpisode(int seasonNum, String espisodeName) throws NoShowSelectedException,UnknownSeasonException;
	
	/**
	 * adiciona um novo <code>RealCharacter</code>
	 * @param characterName nome do <code>Character</code>
	 * @param actor <code>Actor</code> que faz de <code>Character</code>
	 * @param feeByEpisode dinehro recebido por <code>Episode</code> pelo <code>Actor</code>
	 */
	Character addRealCharacter(String characterName, String actorName, int feeByEpisode) throws NoShowSelectedException, DuplicateCharacterException, NegativeFeeException;
	
	/**
	 * adiciona um novo <code>VirtualCharacter</code>
	 * @param characterName nome do <code>Character</code>
	 * @param company <code>CGI</code> que criou o <code>Character</code>
	 * @param costPerSeason dinheiro recebido pela <code>CGI</code> por cada <code>Season</code> em que o <code>Character</code> partecipa
	 */
	Character addVirtualCharacter(String characterName, String companyName, int costPerSeason) throws NoShowSelectedException, DuplicateCharacterException;
	
	/**
	 * adiciona uma relacao entre dois <code>Character</code>
	 * @param character1Name nome identificativo do primeiro <code>Character</code>
	 * @param character2Name nome identificativo do segundo <code>Character</code>
	 */
	void addRelationship(String character1Name, String character2Name) throws NoShowSelectedException, SameCharacterException, UnknownCharacterException, RepeatedRelationshipException;
	
	/**
	 * adiciona um romance entre dois <code>Character</code>
	 * @param character1Name nome identificativo do primeiro <code>Character</code>
	 * @param character2Name nome identificativo do segundo <code>Character</code>
	 */
	void addRomance(String character1Name, String character2Name) throws NoShowSelectedException, SameCharacterException, UnknownCharacterException, RepeatedRomanceException;
	
	/**
	 * adiciona um <code>Event</code>
	 * @param descriptionOfTheEvent descricao do <code>Event</code>
	 * @param seasonNum numero identificativo da <code>Season</code> onde ocorre o <code>Event</code>
	 * @param episodeNum numero identificativo do <code>Episode</code> onde ocorre o <code>Event</code>
	 * @param involvedCharacters lista com todo o <code>Character</code> que intervem nesse <code>Event</code>
	 */
	void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<String> involvedCharacters) throws NoShowSelectedException, UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException, SameCharacterException;
	
	/**
	 * adicona uma <code>quote</code> a um <code>Character</code>
	 * @param seasonNum numero identificativo da <code>Season</code> onde o <code>Character</code> disse a <code>Quote</code>
	 * @param episodeNum numero identificativo do <code>Episode</code> onde o <code>Character</code> disse a <code>Quote</code>
	 * @param characterName nome do <code>Character</code>
	 * @param quote <code>Quote</code>
	 */
	void addQuote(int seasonNum, int episodeNum, String characterName, String quote) throws NoShowSelectedException, UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException;
	
	/**
	 * resumo de partes do <code>Show</code>
	 * @param startingSeason <code>Season</code> onde comeca o resumo
	 * @param endingSeason <code>Season</code> onde termina o resumo
	 * @return lista dos episodios do resumo
	 */
	Iterator<Episode> seasonsOutline (int startingSeason, int endingSeason) throws NoShowSelectedException, InvalidSeasonsIntervalException;
	
	/**
	 * @param characterName nome do <code>Character</code>
	 * @return <code>Character</code>
	 */
	Character character(String characterName) throws NoShowSelectedException, UnknownCharacterException;
	
	/**
	 * @param character1Name nome do primeiro <code>Character</code>
	 * @param character2Name nome do  segundo <code>Character</code>
	 * @return retoma a lista de <code>Character</code> comecando no primeiro ascendete e termina no ultimo descendente
	 */
	Iterator<Character> howAreTheseTwoRelated (String character1Name, String character2Name) throws NoShowSelectedException, UnknownCharacterException, SameCharacterException,NoRealatedCharactersException;
	
	/**
	 * @param quote <code>Quote</code>
	 * @return lista de <code>Character</code> que disseram a <code>Quote</code>
	 */
	Iterator<Character> famousQuotes (String quote) throws NoShowSelectedException, UnknownQuoteException;
	
	/**
	 * @param characterName nome do <code>Character</code>
	 * @return lista de <code>Show</code> onde o <code>Actor</code> do <code>Character</code> partecipa
	 */
	Iterator<Show> alsoAppearsOn (String characterName) throws NoShowSelectedException, UnknownCharacterException, VirtualActorException;
	
	/**
	 * @param actorName nome do <code>Actor</code>
	 * @return lista de <code>Actor</code> que sao mais romanticos que o <code>Actor dado</code> (por ordem crescente de romancismo)
	 */
	Iterator<Actor> mostRomantic (String actorName) throws UnknownActorException, NoRomanticCharactersException;
	
	/**
	 * @return <code>CGI</code> que mais lucro vez com os seus <code>VirtualCharacter</code>
	 */
	CGI kingOfGDI() throws NoVirtualCharactersException;
	
	/**
	 * resumo do <code>Show</code>
	 * @return lista dos episodios
	 */
	Iterator<Episode> showOutline () throws NoShowSelectedException;
	
}
