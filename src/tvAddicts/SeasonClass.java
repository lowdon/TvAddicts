package tvAddicts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class SeasonClass implements Season {

	private final int seasonNumber;
	private List<Episode> episodesList;
	private Map<String, Character> characterList;
	
	public SeasonClass(int seasonNumber) {
		this.seasonNumber = seasonNumber;
		episodesList = new ArrayList<Episode>();
		characterList = new HashMap<String, Character>();
	}

	@Override
	public Iterator<Character> characterListIterator() {
		return characterList.values().iterator();
	}
	
	@Override
	public void addCharacter (Character character) {
		characterList.put(character.name(), character);
	}
	
	@Override
	public boolean hasThisCharacter(Character character) {
		return characterList.containsValue(character);
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
	public Episode addEpisode(String espisodeName, Show show) {
		int episodeNumber = episodesList.size() + 1;
		EpisodeClass episode = new EpisodeClass(espisodeName,episodeNumber, show, this);
		episodesList.add(episode);
		return episode;
	}

	@Override
	public Event addEvent(String descriptionOfTheEvent, int episodeNum, Iterator<Character> involvedCharacters){
		Episode episode = episodesList.get(episodeNum-1);
		episode.addEvent(descriptionOfTheEvent, involvedCharacters);
		Event event = episode.addEvent(descriptionOfTheEvent, involvedCharacters);
		return event;
	}

	@Override
	public Iterator<Episode> episodesList() {
		return episodesList.iterator();
	}
	


}
