package tvAddicts;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ShowClass implements Show {

	private final String name;
	private List<Season> SeasonList;
	private Map<String,Character> characterList;
	
	public ShowClass(String name) {
		this.name = name;
		SeasonList = new ArrayList<Season>();
		characterList = new HashMap<String,Character>();
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
	}

	@Override
	public void addEpisode(int seasonNum, String espisodeName) {
		SeasonList.get(seasonNum).addEpisode(espisodeName);
	}

	@Override
	public void addRealCharacter(String characterName, Actor actor, int feeByEpisode) {
		characterList.put(characterName, new RealCharacterClass (characterName, actor, feeByEpisode));
	}

	@Override
	public void addVirtualCharacter(String characterName, CGI company, int costPerSeason) {
		characterList.put(characterName, new VirtualCharacterClass (characterName, company, costPerSeason));
	}

	@Override
	public void addRelationship(String character1Name, String character2Name) {
		Character parent = characterList.get(character1Name);
		Character child = characterList.get(character2Name);
		parent.addChild(child);
		child.addParent(parent);
	}

	@Override
	public void addRomance(String character1Name, String character2Name) {
		Character character1 = characterList.get(character1Name);
		Character character2 = characterList.get(character2Name);
		character1.addRomance(character2);
		character2.addRomance(character1);
	}

	@Override
	public void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<Character> involvedCharacters) {
		SeasonList.get(seasonNum).addEvent(descriptionOfTheEvent, episodeNum, involvedCharacters);
	}

	@Override
	public void addQuote(int seasonNum, int episodeNum, String characterName, String quote) {
		characterList.get(characterName).addQuote(seasonNum, episodeNum, quote);
	}

	@Override
	public Iterator<Episode> seasonsOutline(int startingSeason, int endingSeason) {
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
	public Character character(String characterName) {
		return characterList.get(characterName);
	}

	
	@Override
	public Iterator<Character> howAreTheseTwoRelated(String character1Name, String character2Name) {
		Character character1 = characterList.get(character1Name);
		Character character2 = characterList.get(character2Name);
		List<Character> related = new ArrayList<Character>();
		howAreTheseTwoRelatedAux(character1, character2, related);
		
		if (related.size() == 0) {
			howAreTheseTwoRelatedAux(character2, character1, related);
			if (related.size() == 0) {
				return null;
			}
		}			
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
		related.clear();
	}

	@Override
	public Iterator<Character> famousQuotes(String quote) {
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
		return famousQuotes.iterator();
	}

	@Override
	public Iterator<Character> characterListIterator() {
		return characterList.values().iterator();
	}

}
