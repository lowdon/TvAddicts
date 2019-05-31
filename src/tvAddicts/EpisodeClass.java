package tvAddicts;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EpisodeClass implements Episode {

	private final String name;
	private final Show show;
	private final Season season;
	private final int number;
	private List<Event> eventsList;

	public EpisodeClass(String name, int number, Show show, Season season) {
		this.name = name;
		this.number = number;
		this.show = show;
		this.season = season;
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
	public Show show() {
		return show;
	}

	@Override
	public Season season() {
		return season;
	}

	@Override
	public Event addEvent(String descriptionOfTheEvent, Iterator<Character> involvedCharacters) {
		Iterator<Event> iterator = eventsList.iterator();
		while (iterator.hasNext()) {
			Event event = iterator.next();
			if (event.descriptionOfTheEvent().equals(descriptionOfTheEvent)) {
				return event;
				}
		}
			EventClass event = new EventClass(descriptionOfTheEvent, involvedCharacters, this);
			eventsList.add(event);
			Iterator<Character> involvedCharacters2 = event.involvedCharacters();
			while (involvedCharacters2.hasNext()) {
				involvedCharacters2.next().addEvent(event);
			}
		
		return event;
	}

	@Override
	public Iterator<Event> eventsList() {
		return eventsList.iterator();
	}

}
