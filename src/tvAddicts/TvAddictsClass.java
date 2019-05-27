package tvAddicts;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import exceptions.*;

import java.util.HashMap;

public class TvAddictsClass implements TvAddicts {

	private Map<String, Show> showList;
	private Map<String, CGI> cGIList;
	private Map<String, Actor> actorsList;
	private Set<CGI> sortedCGIList;
	private Set<Actor> sortedActorsList;
	private Show currentShow;

	public TvAddictsClass() {
		showList = new HashMap<String, Show>();
		cGIList = new HashMap<String, CGI>();
		actorsList = new HashMap<String, Actor>();
		sortedCGIList = new TreeSet<CGI>();
		sortedActorsList = new TreeSet<Actor>();
		currentShow = null;
	}

	@Override
	public Show currentShow() throws NoShowSelectedException {
		noShowSelectedExeption();
		return currentShow;
	}

	@Override
	public void addShow(String showName) throws ShowAlreadyExistsException {
		showAlreadyExistsEx(showName);
		ShowClass show = new ShowClass(showName);
		showList.put(showName, show);
		currentShow = show;
	}

	@Override
	public void switchToShow(String showName) throws UnknownShowException {
		unknownShowEx(showName);
		currentShow = showList.get(showName);
	}

	@Override
	public void addSeason() throws NoShowSelectedException {
		noShowSelectedExeption();
		currentShow.addSeason();
		Iterator<Character> characterList = currentShow.characterListIterator();
		while (characterList.hasNext()) {
			Character character = characterList.next();
			if (character instanceof VirtualCharacter) {
				((VirtualCharacter) character).company()
						.addFeesCollected(((VirtualCharacter) character).costPerSeason());
			}
		}
	}

	@Override
	public Episode addEpisode(int seasonNum, String espisodeName)
			throws NoShowSelectedException, UnknownSeasonException {
		noShowSelectedExeption();
		return currentShow.addEpisode(seasonNum, espisodeName);
	}

	@Override
	public Character addRealCharacter(String characterName, String actorName, int feeByEpisode)
			throws NoShowSelectedException, DuplicateCharacterException, NegativeFeeException {
		noShowSelectedExeption();
		Actor actor;
		if (actorsList.containsKey(actorName)) {
			actor = actorsList.get(actorName);
			sortedActorsList.remove(actor);
		} else {
			actor = new ActorClass(actorName);
			actorsList.put(actorName, actor);
		}
		Character character = currentShow.addRealCharacter(characterName, actor, feeByEpisode);
		actor.addRole(currentShow);
		sortedActorsList.add(actor);
		return character;
	}

	@Override
	public Character addVirtualCharacter(String characterName, String companyName, int costPerSeason)
			throws NoShowSelectedException, DuplicateCharacterException {
		noShowSelectedExeption();
		CGI company;
		if (cGIList.containsKey(companyName)) {
			company = cGIList.get(companyName);
			sortedCGIList.remove(company);
		} else {
			company = new CGIClass(companyName);
			cGIList.put(companyName, company);
		}
		Character character = currentShow.addVirtualCharacter(characterName, company, costPerSeason);
		company.addVirtualCharacter();
		company.addFeesCollected(costPerSeason);
		sortedCGIList.add(company);
		return character;
	}

	@Override
	public void addRelationship(String character1Name, String character2Name) throws NoShowSelectedException,
			SameCharacterException, UnknownCharacterException, RepeatedRelationshipException {
		noShowSelectedExeption();
		currentShow.addRelationship(character1Name, character2Name);
	}

	@Override
	public void addRomance(String character1Name, String character2Name) throws NoShowSelectedException,
			SameCharacterException, UnknownCharacterException, RepeatedRomanceException {
		noShowSelectedExeption();
		Actor actor1 = actorsList.get(character1Name);
		Actor actor2 = actorsList.get(character2Name);

		currentShow.addRomance(character1Name, character2Name);

		sortedActorsList.remove(actor1);
		sortedActorsList.remove(actor2);

		actor1 = actorsList.get(character1Name);
		actor2 = actorsList.get(character2Name);

		sortedActorsList.add(actor1);
		sortedActorsList.add(actor2);
	}

	@Override
	public void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum,
			Iterator<String> involvedCharacters) throws NoShowSelectedException, UnknownSeasonException,
			UnknownEpisodeException, UnknownCharacterException, SameCharacterException {
		noShowSelectedExeption();
		currentShow.addEvent(descriptionOfTheEvent, seasonNum, episodeNum, involvedCharacters);
	}

	@Override
	public void addQuote(int seasonNum, int episodeNum, String characterName, String quote)
			throws NoShowSelectedException, UnknownSeasonException, UnknownEpisodeException, UnknownCharacterException {
		noShowSelectedExeption();
		currentShow.addQuote(seasonNum, episodeNum, characterName, quote);
	}

	@Override
	public Iterator<Episode> seasonsOutline(int startingSeason, int endingSeason)
			throws NoShowSelectedException, InvalidSeasonsIntervalException {
		noShowSelectedExeption();
		return currentShow.seasonsOutline(startingSeason, endingSeason);
	}

	@Override
	public Character character(String characterName) throws NoShowSelectedException, UnknownCharacterException {
		noShowSelectedExeption();
		return currentShow.character(characterName);
	}

	@Override
	public Iterator<Character> howAreTheseTwoRelated(String character1Name, String character2Name)
			throws NoShowSelectedException, UnknownCharacterException, SameCharacterException,
			NoRealatedCharactersException {
		noShowSelectedExeption();
		return currentShow.howAreTheseTwoRelated(character1Name, character2Name);
	}

	@Override
	public Iterator<Character> famousQuotes(String quote) throws NoShowSelectedException, UnknownQuoteException {
		noShowSelectedExeption();
		return currentShow.famousQuotes(quote);
	}

	@Override
	public Iterator<Show> alsoAppearsOn(String characterName)
			throws NoShowSelectedException, UnknownCharacterException, VirtualActorException {
		noShowSelectedExeption();
		Character character = character(characterName);
		virtualActorEx(characterName);
		return ((RealCharacter) character).actor().rolesListIterator();
	}

	private void virtualActorEx(String characterName) throws UnknownCharacterException, VirtualActorException {
		if (!(currentShow.character(characterName) instanceof RealCharacter))
			throw new VirtualActorException();
	}

	@Override
	public Iterator<Actor> mostRomantic(String actorName) throws UnknownActorException, NoRomanticCharactersException {
		unknownActorException(actorName);
		noRomanticCharactersException();
		return ((TreeSet<Actor>) sortedActorsList).headSet(actorsList.get(actorName)).iterator();
	}

	@Override
	public CGI kingOfGDI() throws NoVirtualCharactersException {
		noVirtualCharactersException();
		return ((TreeSet<CGI>) sortedCGIList).first();
	}


	@Override
	public Iterator<Episode> characterOutline(Character character) throws NoShowSelectedException {
		noShowSelectedExeption();
		return currentShow.characterOutline(character);
	}
	
	private void noVirtualCharactersException() throws NoVirtualCharactersException {
		if (((TreeSet<CGI>) sortedCGIList).first().equals(null))
			throw new NoVirtualCharactersException();
	}

	private void noRomanticCharactersException() throws NoRomanticCharactersException {
		if (((TreeSet<Actor>) sortedActorsList).first().romanticNumber() == 0)
			throw new NoRomanticCharactersException();
	}

	private void unknownActorException(String actorName) throws UnknownActorException {
		Iterator<String> actorListIterator = actorsList.keySet().iterator();
		boolean finded = false;
		while (actorListIterator.hasNext())
			if (actorListIterator.next().equals(actorName))
				finded = true;
		if (!finded)
			throw new UnknownActorException();
	}

	private void noShowSelectedExeption() throws NoShowSelectedException {
		if (currentShow == null)
			throw new NoShowSelectedException();
	}

	private void showAlreadyExistsEx(String showName) throws ShowAlreadyExistsException {
		Iterator<String> showListIterator = showList.keySet().iterator();
		while (showListIterator.hasNext())
			if (showListIterator.next().equals(showName))
				throw new ShowAlreadyExistsException();
	}

	private void unknownShowEx(String showName) throws UnknownShowException {
		Iterator<String> showListIterator = showList.keySet().iterator();
		boolean finded = false;
		while (showListIterator.hasNext())
			if (showListIterator.next().equals(showName))
				finded = true;
		if (!finded)
			throw new UnknownShowException();
	}

}