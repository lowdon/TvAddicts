package tvAddicts;

import java.util.List;
import java.util.Iterator;

public interface Season {

	int episodesNumber();
	
	void addEpisode(String espisodeName);
	
	void addEvent(String descriptionOfTheEvent, int episodeNum, Iterator<Character> involvedCharacters);
	
	List<Episode> episodesList();
}
