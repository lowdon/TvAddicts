package tvAddicts;

import java.util.Iterator;

public interface Episode {

	public int number();
	
	public String name();
	
	public void addEvent(String descriptionOfTheEvent, Iterator<Character> involvedCharacters);
	
	public Iterator<Event> eventsList();
}
