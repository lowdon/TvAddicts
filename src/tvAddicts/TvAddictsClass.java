package tvAddicts;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class TvAddictsClass implements TvAddicts {

	private Map<String,Show> showList;
	private Map<String,CGI> cGIList;
	private Map<String,Actor> actorsList;  
	private Set<CGI> sortedCGIList;
	private Set<Actor> sortedActorsList; 
	private Show currentShow;
	
	public TvAddictsClass() {
		showList = new HashMap<String,Show>();
		cGIList = new HashMap<String,CGI>();
		actorsList = new HashMap<String,Actor>();
		sortedCGIList = new TreeSet<CGI>();
		sortedActorsList = new TreeSet<Actor>();
		currentShow = null;
	}

	@Override
	public Show currentShow() {
		return currentShow;
	}

	@Override
	public void addShow(String showName) {
		showList.put(showName, new ShowClass(showName));
	}

	@Override
	public void switchToShow(String showName) {
		currentShow = showList.get(showName);
	}

	@Override
	public void addSeason() {
		currentShow.addSeason();
		Iterator<Character> characterList = currentShow.characterListIterator();
		while (characterList.hasNext()) {
			Character character = characterList.next();
			if(character instanceof VirtualCharacter) {
				((VirtualCharacter) character).company().addFeesCollected(((VirtualCharacter) character).costPerSeason());
			}
		}
	}

	@Override
	public void addEpisode(int seasonNum, String espisodeName) {
		currentShow.addEpisode(seasonNum, espisodeName);
	}
	
	@Override
	public void addRealCharacter(String characterName, String actorName, int feeByEpisode) {
		Actor actor;
		if (actorsList.containsKey(actorName)) {
			actor = actorsList.get(actorName);
			sortedActorsList.remove(actor);
		}
		else {
			actor = new ActorClass(actorName);
			actorsList.put(actorName, actor);
		}
		currentShow.addRealCharacter(characterName, actor, feeByEpisode);
		actor.addRole(currentShow);
		sortedActorsList.add(actor);
	}

	@Override
	public void addVirtualCharacter(String characterName, String companyName, int costPerSeason) {
		CGI company;
		if (cGIList.containsKey(companyName)) {
			company = cGIList.get(companyName);
			sortedCGIList.remove(company);
		}
		else {
			company = new CGIClass(companyName);
			cGIList.put(companyName, company);
		}
		currentShow.addVirtualCharacter(characterName, company, costPerSeason);
		company.addVirtualCharacter();
		company.addFeesCollected(costPerSeason);
		sortedCGIList.add(company);
	}

	@Override
	public void addRelationship(String character1Name, String character2Name) {
		currentShow.addRelationship(character1Name, character2Name);
	}

	@Override
	public void addRomance(String character1Name, String character2Name) {
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
	public void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<Character> involvedCharacters) {
		currentShow.addEvent(descriptionOfTheEvent, seasonNum, episodeNum, involvedCharacters);
	}

	@Override
	public void addQuote(int seasonNum, int episodeNum, String characterName, String quote) {
		currentShow.addQuote(seasonNum, episodeNum, characterName, quote);
	}

	@Override
	public Iterator<Episode> seasonsOutline(int startingSeason, int endingSeason) {
		return currentShow.seasonsOutline(startingSeason, endingSeason);
	}

	@Override
	public Character characterResume(String characterName) {
		return currentShow.character(characterName);
	}

	@Override
	public Iterator<Character> howAreTheseTwoRelated(String character1Name, String character2Name) {
		return currentShow.howAreTheseTwoRelated(character1Name, character2Name);
	}

	@Override
	public Iterator<Character> famousQuotes(String quote) {
		return currentShow.famousQuotes(quote);
	}

	@Override
	public Iterator<Show> alsoAppearsOn(String characterName) {
		return currentShow.character(characterName).actor().rolesListIterator();
	}

	@Override
	public Iterator<Actor> mostRomantic(String actorName) {
		return ((TreeSet<Actor>)sortedActorsList).headSet(actorsList.get(actorName)).iterator();
	}

	@Override
	public CGI kingOfGDI() {
		
		return ((TreeSet<CGI>)sortedCGIList).first();
	}

}
