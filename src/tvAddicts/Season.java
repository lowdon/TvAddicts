package tvAddicts;

import java.util.Iterator;

public interface Season {

	int episodesNumber();
	
	int number();
	
	void addEpisode(String espisodeName);
	
	void addEvent(String descriptionOfTheEvent, int episodeNum, Iterator<Character> involvedCharacters);
	
	Iterator<Episode> episodesList();
}
