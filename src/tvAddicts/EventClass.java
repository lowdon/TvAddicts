package tvAddicts;

import java.util.Iterator;

public class EventClass implements Event {

	private final String descriptionOfTheEvent;
	private final Iterator<Character> involvedCharacters;
	private final Episode episode;
	
	public EventClass(String descriptionOfTheEvent, Iterator<Character> involvedCharacters, Episode episode) {
		this.descriptionOfTheEvent = descriptionOfTheEvent;
		this.involvedCharacters = involvedCharacters;
		this.episode = episode;
	}

	@Override
	public String descriptionOfTheEvent() {
		return descriptionOfTheEvent;
	}

	@Override
	public Iterator<Character> involvedCharacters() {
		return involvedCharacters;
	}

	@Override
	public Episode episode() {
		return this.episode;
	}
}
