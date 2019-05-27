package tvAddicts;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import exceptions.*;

public class ShowClass implements Show {

	private final String name;
	private List<Season> SeasonList;
	private Map<String,Character> characterList;
	
	public ShowClass(String name) {
		this.name = name;
		SeasonList = new ArrayList<Season>();
		characterList = new HashMap<String,Character>();
	    addSeason();
	}

	@Override
	public int compareTo(Show o) {
		return this.name().compareTo(o.name());
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int seasonsNumber() {
		return SeasonList.size();
	}

	@Override
	public int episodesNumber() {
		int episodesNumber = 0;
		Iterator<Season> iterator = SeasonList.iterator();
		while(iterator.hasNext()) {
			episodesNumber += iterator.next().episodesNumber();
		}
		return episodesNumber;
	}

	@Override
	public void addSeason() {
		int seasonNumber = SeasonList.size();
		seasonNumber++;
		SeasonList.add(new SeasonClass(seasonNumber));
		
		Iterator<String> iterator = characterList.keySet().iterator();
		while(iterator.hasNext()) {
			Character character = characterList.get(iterator.next());
			if (character instanceof VirtualCharacter)
				((VirtualCharacter)character).company().addFeesCollected(((VirtualCharacter)character).cost());
		}
	}

	@Override
	public Episode addEpisode(int seasonNum, String episodeName) throws UnknownSeasonException{
		unknownSeasonExeption(seasonNum);
		return SeasonList.get(seasonNum - 1).addEpisode(episodeName, this);
	}

	@Override
	public Character addRealCharacter(String characterName, Actor actor, int feeByEpisode) throws DuplicateCharacterException, NegativeFeeException {
		duplicateCharacterExeption(characterName);
		negativeFeeExeption(feeByEpisode);
		RealCharacter character = new RealCharacterClass (characterName, actor, feeByEpisode,this);
		characterList.put(characterName, character);
		return character;
	}

	@Override
	public Character addVirtualCharacter(String characterName, CGI company, int costPerSeason) throws DuplicateCharacterException {
		duplicateCharacterExeption(characterName);
		VirtualCharacter character = new VirtualCharacterClass (characterName, company, costPerSeason,this);
		characterList.put(characterName, character);
		return character;
	}

	@Override
	public void addRelationship(String character1Name, String character2Name) throws SameCharacterException, UnknownCharacterException, RepeatedRelationshipException {
		sameCharacterExeption(character1Name, character2Name);
		unknownCharacterExeption(character1Name);
		unknownCharacterExeption(character2Name);
		Character parent = characterList.get(character1Name);
		Character child = characterList.get(character2Name);
		repeatedRelationshipExeption(parent, child);
		parent.addChild(child);
		child.addParent(parent);
	}

	@Override
	public void addRomance(String character1Name, String character2Name) throws SameCharacterException, UnknownCharacterException, RepeatedRomanceException {
		sameCharacterExeption(character1Name, character2Name);
		unknownCharacterExeption(character1Name);
		unknownCharacterExeption(character2Name);
		Character character1 = characterList.get(character1Name);
		Character character2 = characterList.get(character2Name);
		repeatedRomanceExeption(character1, character2);
		character1.addRomance(character2);
		character2.addRomance(character1);
	}

	@Override
	public void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<String> involvedCharacters) throws UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException, SameCharacterException {
		unknownSeasonExeption(seasonNum);
		unknownEpisodeExeption(seasonNum,episodeNum);
		Iterator<String> involvedCharactersIterator = involvedCharacters;
		while(involvedCharactersIterator.hasNext()) {
			unknownCharacterExeption(involvedCharactersIterator.next());
		}
		ArrayList<String> involvedCharactersList = new ArrayList<String>();
		involvedCharactersIterator = involvedCharacters;
		while(involvedCharactersIterator.hasNext()) {
			involvedCharactersList.add(involvedCharactersIterator.next());
		}
		for (int i = 0; i < involvedCharactersList.size(); i++)
			for (int j = 1; j < involvedCharactersList.size(); j++)
				sameCharacterExeption(involvedCharactersList.get(i), involvedCharactersList.get(j));
		
		ArrayList<Character> auxList = new ArrayList<Character>();
		while (involvedCharacters.hasNext())
			auxList.add(characterList.get(involvedCharacters.next()));
		Iterator<Character> auxIterator = auxList.iterator();
		
		SeasonList.get(seasonNum - 1).addEvent(descriptionOfTheEvent, episodeNum, auxIterator);
	}

	@Override
	public void addQuote(int seasonNum, int episodeNum, String characterName, String quote) throws UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException {
		unknownSeasonExeption(seasonNum);
		unknownEpisodeExeption(seasonNum,episodeNum);
		unknownCharacterExeption(characterName);
		characterList.get(characterName).addQuote(seasonNum, episodeNum, quote);
	}

	@Override
	public Iterator<Episode> seasonsOutline(int startingSeason, int endingSeason) throws InvalidSeasonsIntervalException {
		invalidSeasonsIntervalExeption(startingSeason, endingSeason);
		List<Episode> seasonsOutline = new ArrayList<Episode>();
		for (int i = startingSeason - 1; i < endingSeason ; i++) {
			Iterator<Episode> episodesList = SeasonList.get(i).episodesList();
			while (episodesList.hasNext()) {
				seasonsOutline.add(episodesList.next());
			}
		}
		return seasonsOutline.iterator();
	}

	@Override
	public Character character(String characterName) throws UnknownCharacterException{
		unknownCharacterExeption(characterName);
		return characterList.get(characterName);
	}

	
	@Override
	public Iterator<Character> howAreTheseTwoRelated(String character1Name, String character2Name) throws UnknownCharacterException, SameCharacterException,NoRealatedCharactersException {
		unknownCharacterExeption(character1Name);
		unknownCharacterExeption(character2Name);
		sameCharacterExeption(character1Name, character2Name);
		Character character1 = characterList.get(character1Name);
		Character character2 = characterList.get(character2Name);
		List<Character> related = new ArrayList<Character>();
		howAreTheseTwoRelatedAux(character1, character2, related);
		noRealatedCharactersException(character1, character2, related);			
		return related.iterator();
		
	}
	
	private void howAreTheseTwoRelatedAux(Character character1, Character character2, List<Character> related) {
		//este metodo vai testar se o <code>character1Name</code> e descendente de <code>character2Name</code>
		Iterator<Character> kidsIterator = character1.kidsIterator();
		related.add(character1);
		int counter = 1;
		boolean finded = false;
		while (kidsIterator.hasNext()) {
			Character kid = kidsIterator.next();
			related.add(kid);
			counter++;
			if(kid.equals(character2)) {
				related.add(character2);
				counter = -1; // -1 é um valor que um size nunca pode tomar por isso foi escolhido aqui
				finded=true;
			}
			else {
				howAreTheseTwoRelatedAux(kid,character2,related);
			}
			if(related.size() == counter && !finded) {
				related.remove(kid);
				counter--;
			}
		}
	if (!finded) 	
              related.clear();
	}

	@Override
	public Iterator<Character> famousQuotes(String quote) throws  UnknownQuoteException{
		Set<Character> famousQuotes = new TreeSet<Character>();
		Iterator<Character> characterList = characterListIterator();
		while(characterList.hasNext()) {
			Character character = characterList.next();
			Iterator<Quote> quotesList = character.quotesIterator();
			boolean finded = false;
			while(quotesList.hasNext()) {
				if(quotesList.next().quote().equals(quote) && !finded) {
					famousQuotes.add(character);
					finded = true;
				}	
			}
		}
		unknownQuoteException(famousQuotes);
		return famousQuotes.iterator();
	}

	@Override
	public Iterator<Character> characterListIterator() {
		return characterList.values().iterator();
	}
	
	@Override
	public Iterator<Episode> characterOutline(Character character) {
		List<Episode> characterOutline = new ArrayList<Episode>();
		Iterator<Season> iterator = SeasonList.iterator();
		while(iterator.hasNext()) {
			Iterator<Episode> episodesList = iterator.next().episodesList();
			while (episodesList.hasNext()) {
				Episode episode = episodesList.next();
				Iterator<Event> eventsList = episode.eventsList();
				boolean finded = false;
				while (eventsList.hasNext() && !finded) {
					Iterator<Character> involvedCharacters = eventsList.next().involvedCharacters();
					while(involvedCharacters.hasNext() && !finded)
						if (involvedCharacters.next().equals(character)) {
							characterOutline.add(episode);
							finded = true;
						}
							
				
				}
			}
		}
		return characterOutline.iterator();
	}
	
	
	private void unknownQuoteException(Set<Character> famousQuotes) throws UnknownQuoteException {
		if (famousQuotes.size() == 0)
			throw new UnknownQuoteException();
	}
	
	private void noRealatedCharactersException(Character character1, Character character2, List<Character> related)
			throws NoRealatedCharactersException {
		if (related.size() == 0) {
			howAreTheseTwoRelatedAux(character2, character1, related);
			if (related.size() == 0) {
				throw new NoRealatedCharactersException();
			}
		}
	}
	
	private void unknownSeasonExeption(int seasonNum) throws UnknownSeasonException {
		if(seasonNum<=0 || seasonNum > SeasonList.size())
				throw new UnknownSeasonException(name());
	}
	
	private void invalidSeasonsIntervalExeption(int startingSeason, int endingSeason) throws InvalidSeasonsIntervalException {
		if(startingSeason<0 || endingSeason >= SeasonList.size() || startingSeason < endingSeason)
			throw new InvalidSeasonsIntervalException();
	}

	private void sameCharacterExeption(String character1Name, String character2Name) throws SameCharacterException {
		if (character1Name.equals(character2Name))
			throw new SameCharacterException();
	}
	
	private void unknownCharacterExeption(String characterName) throws UnknownCharacterException {
		if (!characterList.containsKey(characterName))
			throw new UnknownCharacterException(characterName);
	}
	
	private void repeatedRomanceExeption(Character character1, Character character2) throws RepeatedRomanceException {
		Iterator<Character> romanticIterator = character1.romanticIterator();
		while (romanticIterator.hasNext())
			if(romanticIterator.next().equals(character2))
				throw new RepeatedRomanceException();
	}
	
	private void repeatedRelationshipExeption(Character parent, Character child) throws RepeatedRelationshipException {
		Iterator<Character> kidsIterator = parent.kidsIterator();
		while(kidsIterator.hasNext())
			if (kidsIterator.next().equals(child))
				throw new RepeatedRelationshipException();
	}
	
	private void negativeFeeExeption(int feeByEpisode) throws NegativeFeeException {
		if (feeByEpisode < 0)
				throw new NegativeFeeException();
	}
	
	private void unknownEpisodeExeption(int seasonNum,int episodeNum) throws UnknownEpisodeException {
		if(episodeNum<0 || episodeNum >= SeasonList.get(seasonNum - 1).episodesNumber())
				throw new UnknownEpisodeException(name());
	}
	
	private void duplicateCharacterExeption(String characterName) throws DuplicateCharacterException {
		Iterator<String> characterListIterator = characterList.keySet().iterator();
		while (characterListIterator.hasNext())
			if(characterListIterator.next().equals(characterName))
				throw new DuplicateCharacterException();
	}

}
