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
	private List<Season> seasonList;
	private Map<String, Character> characterList;

	public Iterator<Season> seasonsIterator() {
		return seasonList.iterator();
	}
	
	public ShowClass(String name) {
		this.name = name;
		seasonList = new ArrayList<Season>();
		characterList = new HashMap<String, Character>();
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
		return seasonList.size();
	}

	@Override
	public int episodesNumber() {
		int episodesNumber = 0;
		Iterator<Season> iterator = seasonList.iterator();
		while (iterator.hasNext()) {
			episodesNumber += iterator.next().episodesNumber();
		}
		return episodesNumber;
	}

	@Override
	public void addSeason() {
		int seasonNumber = seasonList.size();
		seasonNumber++;
		seasonList.add(new SeasonClass(seasonNumber));
	}

	@Override
	public Episode addEpisode(int seasonNum, String episodeName) throws UnknownSeasonException {
		unknownSeasonExeption(seasonNum);
		return seasonList.get(seasonNum - 1).addEpisode(episodeName, this);
	}

	@Override
	public Character addRealCharacter(String characterName, Actor actor, int feeByEpisode)
			throws DuplicateCharacterException, NegativeFeeException {
		duplicateCharacterExeption(characterName);
		negativeFeeExeption(feeByEpisode);
		RealCharacter character = new RealCharacterClass(characterName, actor, feeByEpisode, this);
		characterList.put(characterName, character);
		return character;
	}

	@Override
	public Character addVirtualCharacter(String characterName, CGI company, int costPerSeason)
			throws DuplicateCharacterException {
		duplicateCharacterExeption(characterName);
		VirtualCharacter character = new VirtualCharacterClass(characterName, company, costPerSeason, this);
		characterList.put(characterName, character);
		return character;
	}

	@Override
	public void addRelationship(String character1Name, String character2Name)
			throws SameCharacterException, UnknownCharacterException, RepeatedRelationshipException {
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
	public void addRomance(String character1Name, String character2Name)
			throws SameCharacterException, UnknownCharacterException, RepeatedRomanceException {
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
	public void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum,
			Iterator<String> involvedCharacters)
			throws UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException, SameCharacterException {
		unknownSeasonExeption(seasonNum);
		unknownEpisodeExeption(seasonNum, episodeNum);
		Iterator<String> involvedCharactersIterator = involvedCharacters;

		ArrayList<String> involvedCharactersList = new ArrayList<String>();
		involvedCharactersIterator = involvedCharacters;
		while (involvedCharactersIterator.hasNext()) {
			involvedCharactersList.add(involvedCharactersIterator.next());
		}

		for (int i = 0; i < involvedCharactersList.size(); i++)
			unknownCharacterExeption(involvedCharactersList.get(i));

		for (int i = 0; i < involvedCharactersList.size(); i++)
			for (int j = 1; j < involvedCharactersList.size(); j++) {
				if (i != j)
					sameCharacterExeption(involvedCharactersList.get(i), involvedCharactersList.get(j));
			}

		List<Character> auxList = new ArrayList<Character>();

		for (int i = 0; i < involvedCharactersList.size(); i++)
			auxList.add(i, character(involvedCharactersList.get(i)));

		Iterator<Character> auxIterator = auxList.iterator();

		Season season = seasonList.get(seasonNum - 1);
		Event event = season.addEvent(descriptionOfTheEvent, episodeNum, auxIterator);

		Iterator<Character> involvedCharacters2 = event.involvedCharacters();
		while (involvedCharacters2.hasNext()) {
			Character character = involvedCharacters2.next();
			if (!season.hasThisCharacter(character)) {
				season.addCharacter(character);
			}
		}
	}


	@Override
	public void addQuote(int seasonNum, int episodeNum, String characterName, String quote)
			throws UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException {
		unknownSeasonExeption(seasonNum);
		unknownEpisodeExeption(seasonNum, episodeNum);
		unknownCharacterExeption(characterName);
		Character character = characterList.get(characterName);
		character.addQuote(seasonNum, episodeNum, quote);
		Season season = seasonList.get(seasonNum - 1);;
		if (!season.hasThisCharacter(character)) {
			season.addCharacter(character);
		}
	}

	@Override
	public Iterator<Episode> seasonsOutline(int startingSeason, int endingSeason)
			throws InvalidSeasonsIntervalException {
		invalidSeasonsIntervalExeption(startingSeason, endingSeason);
		List<Episode> seasonsOutline = new ArrayList<Episode>();
		for (int i = startingSeason - 1; i < endingSeason; i++) {
			seasonOutline(seasonsOutline, i);
		}
		return seasonsOutline.iterator();
	}

	private void seasonOutline(List<Episode> seasonsOutline, int i) {
		Iterator<Episode> episodesList = seasonList.get(i).episodesList();
		while (episodesList.hasNext()) {
			seasonsOutline.add(episodesList.next());
		}
	}

	@Override
	public Character character(String characterName) throws UnknownCharacterException {
		unknownCharacterExeption(characterName);
		return characterList.get(characterName);
	}

	@Override
	public Iterator<Character> howAreTheseTwoRelated(String character1Name, String character2Name)
			throws UnknownCharacterException, SameCharacterException, NoRealatedCharactersException {
		unknownCharacterExeption(character1Name);
		unknownCharacterExeption(character2Name);
		sameCharacterExeption(character1Name, character2Name);
		Character character1 = characterList.get(character1Name);
		Character character2 = characterList.get(character2Name);
		List<Character> related = new ArrayList<Character>();
		boolean finded = false;
		related = howAreTheseTwoRelatedAux(character1, character2, related, finded);
		if (related.isEmpty())
			related = howAreTheseTwoRelatedAux(character2, character1, related, finded);
		noRealatedCharactersException(related);

		return related.iterator();

	}

	private List<Character> howAreTheseTwoRelatedAux(Character character1, Character character2,
			List<Character> related, boolean finded) {
		// este metodo vai testar se o <code>character1Name</code> e descendente de
		// <code>character2Name</code>
		List<Character> tmp;
		Iterator<Character> kidsIterator = character1.kidsIterator();
		while (kidsIterator.hasNext() && !finded) {
			Character kid = kidsIterator.next();
			if (kid.equals(character2)) {
				finded = true;
				tmp = new ArrayList<Character>();
				tmp.add(character1);
				tmp.add(character2);
				return tmp;
			} else {
				tmp = howAreTheseTwoRelatedAux(kid, character2, related, finded);
				if (!tmp.isEmpty()) {
					finded = true;
					tmp.add(0, character1);
					return tmp;
				}

			}
		}
		return new ArrayList<Character>();

	}

	@Override
	public Iterator<Character> famousQuotes(String quote) throws UnknownQuoteException {
		Set<Character> famousQuotes = new TreeSet<Character>();
		Iterator<Character> characterList = characterListIterator();
		while (characterList.hasNext()) {
			Character character = characterList.next();
			Iterator<Quote> quotesList = character.quotesIterator();
			boolean finded = false;
			while (quotesList.hasNext()) {
				if (quotesList.next().quote().equals(quote) && !finded) {
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


	private void unknownQuoteException(Set<Character> famousQuotes) throws UnknownQuoteException {
		if (famousQuotes.size() == 0)
			throw new UnknownQuoteException();
	}

	private void noRealatedCharactersException(List<Character> related) throws NoRealatedCharactersException {

		if (related.isEmpty()) {
			throw new NoRealatedCharactersException();
		}
	}

	private void unknownSeasonExeption(int seasonNum) throws UnknownSeasonException {
		if (seasonNum <= 0 || seasonNum > seasonList.size())
			throw new UnknownSeasonException(name());
	}

	private void invalidSeasonsIntervalExeption(int startingSeason, int endingSeason)
			throws InvalidSeasonsIntervalException {
		if (startingSeason < 1 || endingSeason > seasonList.size() || startingSeason > endingSeason)
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
			if (romanticIterator.next().equals(character2))
				throw new RepeatedRomanceException();
	}

	private void repeatedRelationshipExeption(Character parent, Character child) throws RepeatedRelationshipException {
		Iterator<Character> kidsIterator = parent.kidsIterator();
		while (kidsIterator.hasNext())
			if (kidsIterator.next().equals(child))
				throw new RepeatedRelationshipException();
	}

	private void negativeFeeExeption(int feeByEpisode) throws NegativeFeeException {
		if (feeByEpisode < 0)
			throw new NegativeFeeException();
	}

	private void unknownEpisodeExeption(int seasonNum, int episodeNum) throws UnknownEpisodeException {
		if (episodeNum < 0 || episodeNum >= seasonList.get(seasonNum - 1).episodesNumber())
			throw new UnknownEpisodeException(name());
	}

	private void duplicateCharacterExeption(String characterName) throws DuplicateCharacterException {
		Iterator<String> characterListIterator = characterList.keySet().iterator();
		while (characterListIterator.hasNext())
			if (characterListIterator.next().equals(characterName))
				throw new DuplicateCharacterException();
	}

	@Override
	public boolean isInRelationWith(String source, String comp) throws UnknownCharacterException {
		// TODO Auto-generated method stub
		Iterator<Character> romanticIterator = character(source).romanticIterator();
		while (romanticIterator.hasNext()) {
			if (romanticIterator.next().name().equals(comp))
				return true;
		}
		return false;

	}

}
