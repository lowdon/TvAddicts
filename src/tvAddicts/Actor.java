package tvAddicts;

import java.util.Iterator;

public interface Actor {

	String name();
	
	Iterator<Show> rolesListIterator();
	
	void addRole(Show show);
}
