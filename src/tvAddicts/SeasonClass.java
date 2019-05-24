package tvAddicts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeasonClass implements Season {

	private final int seasonNumber;
	private List<Episode> episodesList;
	
	public SeasonClass(int seasonNumber) {
		this.seasonNumber = seasonNumber;
		episodesList = new ArrayList<Episode>();
	}

	@Override
	public int episodesNumber() {
		return episodesList.size();
	}

	@Override
	public int number() {
		return seasonNumber;
	}

	@Override
	public void addEpisode(String espisodeName) {
		int episodeNumber = episodesList.size();
		episodesList.add(new EpisodeClass(espisodeName,episodeNumber));
	}

	@Override
	public void addEvent(String descriptionOfTheEvent, int episodeNum, Iterator<Character> involvedCharacters) {
		Episode episode =episodesList.get(episodeNum-1);
		episode.addEvent(descriptionOfTheEvent, involvedCharacters);
	}

	@Override
	public Iterator<Episode> episodesList() {
		return episodesList.iterator();
	}

}
