package tvAddicts;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShowClass implements Show {

	private final String name;
	private List<Season> SeasonList;
	private Map<String,Character> CharacterList;
	
	public ShowClass(String name) {
		this.name = name;
		SeasonList = new ArrayList<Season>();
		CharacterList = new HashMap<String,Character>();
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
		SeasonList.add(new SeasonClass());
	}

	@Override
	public void addEpisode(int seasonNum, String espisodeName) {
		SeasonList.get(seasonNum).addEpisode(espisodeName);
	}

	@Override
	public void addRealCharacter(String characterName, Actor actor, int feeByEpisode) {
		CharacterList.put(characterName, new RealCharacterClass (characterName, actor, feeByEpisode));
	}

	@Override
	public void addVirtualCharacter(String characterName, CGI company, int costPerSeason) {
		CharacterList.put(characterName, new VirtualCharacterClass (characterName, company, costPerSeason));
	}

	@Override
	public void addRelationship(String character1Name, String character2Name) {
		Character parent = CharacterList.get(character1Name);
		Character child = CharacterList.get(character2Name);
		parent.addChild(child);
		child.addParent(parent);
	}

	@Override
	public void addRomance(String character1Name, String character2Name) {
		Character character1 = CharacterList.get(character1Name);
		Character character2 = CharacterList.get(character2Name);
		character1.addRomance(character2);
		character2.addRomance(character1);
	}

	@Override
	public void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<Character> involvedCharacters) {
		SeasonList.get(seasonNum).addEvent(descriptionOfTheEvent, episodeNum, involvedCharacters);
	}

	@Override
	public void addQuote(int seasonNum, int episodeNum, String characterName, String quote) {
		CharacterList.get(characterName).addQuote(seasonNum, episodeNum, quote);
	}

	@Override
	public Iterator<Episode> seasonsOutline(int startingSeason, int endingSeason) {
		List<Episode> seasonsOutline = new ArrayList<Episode>();
		for (int i = startingSeason - 1; i < endingSeason ; i++) {
			seasonsOutline.addAll(SeasonList.get(i).episodesList());
		}
		return seasonsOutline.iterator();
	}

	@Override
	public Character character(String characterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Character> howAreTheseTwoRelated(String character1Name, String character2Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Character> famousQuotes(String quote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<String> characterList() {
		// TODO Auto-generated method stub
		return null;
	}

}
