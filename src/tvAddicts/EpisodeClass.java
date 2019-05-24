package tvAddicts;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EpisodeClass implements Episode {

	private final String name;
	private final int number;
	private List<Event> eventsList;
	
	public EpisodeClass(String name, int number) {
		this.name = name;
		this.number = number;
		eventsList = new ArrayList<Event>();
	}
	
	@Override
	public int number() {
		return number;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void addEvent(String descriptionOfTheEvent, Iterator<Character> involvedCharacters) {
		eventsList.add(new EventClass(descriptionOfTheEvent, involvedCharacters));
	}

	@Override
	public Iterator<Event> eventsList() {
		return eventsList.iterator();
	}

}
