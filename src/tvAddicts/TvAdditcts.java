package tvAddicts;

import java.util.Iterator;

public interface TvAdditcts {

	Show currentShow ();
	
	void addShow(String showName);
	
	void switchToShow(String showName);
	
	void addSeason();
	
	void addEpisode(int seasonNum, String espisodeName);
	
	void addRealCharacter(String characterName, String actorName, int feeByEpisode);
	
	void addVirtualCharacter(String characterName, String companyName, int costPerSeason);
	
	void addRelationship(String character1Name, String character2Name);
	
	void addRomance(String character1Name, String character2Name);
	
	void addEvent(String descriptionOfTheEvent, int seasonNum, int episodeNum, Iterator<Character> involvedCharacters);
	
	void addQuote(int seasonNum, int episodeNum, String characterName, String quote);
	
	Iterator<Episode> seasonsOutline (int startingSeason, int endingSeason);
	
	Character characterResume(String characterName);
	
	Iterator<Character> howAreTheseTwoRelated (String character1Name, String character2Name);
	
	Iterator<Character> famousQuotes (String quote);
	
	Iterator<Show> alsoAppearsOn (String characterName);
	
	Iterator<Actor> mostRomantic (String actorName);
	
	CGI kingOfGDI();
	
}
