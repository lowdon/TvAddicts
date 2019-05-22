package tvAddicts;

import java.util.Iterator;

public interface Show extends Comparable<Show>{

	String name();
	
	int seasonsNumber();
	
	int episodesNumber();
	
	void addSeason();
	
	void addEpisode(int seasonNum, String espisodeName);
	
	void addRealCharacter(String characterName, Actor actor, int feeByEpisode);
	
	void addVirtualCharacter(String characterName, CGI company, int costPerSeason);
	
	void addRelationship(String character1Name, String character2Name);
	
	void addRomance(String character1Name, String character2Name);
	
	void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<Character> involvedCharacters);
	
	void addQuote(int seasonNum, int episodeNum, String characterName, String quote);
	
	Iterator<Episode> seasonsOutline (int startingSeason, int endingSeason);
	
	Character character(String characterName);
	
	Iterator<Character> howAreTheseTwoRelated (String character1Name, String character2Name);
	
	Iterator<Character> famousQuotes (String quote);
	
	Iterator<String> characterList();  //Iterator<String> characterList = currentShow.characterList().keySet().iterator();
	
}
