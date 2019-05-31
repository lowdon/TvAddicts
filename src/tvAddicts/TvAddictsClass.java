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
	private Set<Actor> sortedActorsList;
	private Show currentShow;

	public TvAddictsClass() {
		showList = new HashMap<String, Show>();
		cGIList = new HashMap<String, CGI>();
		actorsList = new HashMap<String, Actor>();
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
		} else {
			actor = new ActorClass(actorName);
			actorsList.put(actorName, actor);
		}
		Character character = currentShow.addRealCharacter(characterName, actor, feeByEpisode);
		return character;
	}

	@Override
	public Character addVirtualCharacter(String characterName, String companyName, int costPerSeason)
			throws NoShowSelectedException, DuplicateCharacterException {
		noShowSelectedExeption();
		CGI company;
		if (cGIList.containsKey(companyName)) {
			company = cGIList.get(companyName);
		} else {
			company = new CGIClass(companyName);
			cGIList.put(companyName, company);
		}
		Character character = currentShow.addVirtualCharacter(characterName, company, costPerSeason);
		company.addVirtualCharacter();
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
		currentShow.addRomance(character1Name, character2Name);
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
		for (Actor actor : actorsList.values())
			sortedActorsList.add(actor);
		noRomanticCharactersException();
		Actor actor = actorsList.get(actorName);
		Set<Actor> set = new TreeSet<Actor>();
		Iterator<Actor> iterator = ((TreeSet<Actor>) sortedActorsList).headSet(actor).iterator();
		while (iterator.hasNext()) {
			set.add(iterator.next());
		}
		set.add(actor);
		return set.iterator();
	}

	@Override
	public CGI kingOfCGI() throws NoVirtualCharactersException {
		noVirtualCharactersException();

		Iterator<CGI> cGIs = cGIList.values().iterator();
		while (cGIs.hasNext()) {
			cGIs.next().initFees();
		}
		Iterator<Show> shows = showList.values().iterator();
		while (shows.hasNext()) {
			Iterator<Season> seasonIterator = shows.next().seasonsIterator();
			while (seasonIterator.hasNext()) {
				Iterator<Character> characterListIterator = seasonIterator.next().characterListIterator();
				while (characterListIterator.hasNext()) {
					Character character = characterListIterator.next();
					if (character instanceof VirtualCharacter) {
						VirtualCharacter virtualCaracter = (VirtualCharacter) character;
						virtualCaracter.company().addFeesCollected(virtualCaracter.costPerSeason());
					}
				}
			}
		}
		TreeSet<CGI> sortedCGIList = new TreeSet<CGI>();
		Iterator<CGI> cGIList = this.cGIList.values().iterator();
		while (cGIList.hasNext()) {
			sortedCGIList.add(cGIList.next());

		}

		return sortedCGIList.first();
	}

	private void noVirtualCharactersException() throws NoVirtualCharactersException {
		if (cGIList.isEmpty())
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