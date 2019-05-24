package tvAddicts;

import java.util.Iterator;

public interface Event {

	String descriptionOfTheEvent();
	
	Iterator<Character> involvedCharacters();
}
