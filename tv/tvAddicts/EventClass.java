package tvAddicts;

import java.util.Iterator;

public class EventClass implements Event {

	private final String descriptionOfTheEvent;
	private final Iterator<Character> involvedCharacters;
	
	public EventClass(String descriptionOfTheEvent, Iterator<Character> involvedCharacters) {
		this.descriptionOfTheEvent = descriptionOfTheEvent;
		this.involvedCharacters = involvedCharacters;
	}

	@Override
	public String descriptionOfTheEvent() {
		return descriptionOfTheEvent;
	}

	@Override
	public Iterator<Character> involvedCharacters() {
		return involvedCharacters;
	}

}
