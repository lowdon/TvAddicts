package tvAddicts;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ActorClass implements Actor {

	private final String name;
	private Set<Show> rolesList;
	
	
	public ActorClass(String name) {
		this.name = name;
		rolesList = new TreeSet<Show>();
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Iterator<Show> rolesListIterator() {
		return ((TreeSet<Show>) rolesList).iterator();
	}

	@Override
	public void addRole(Show show) {
		rolesList.add(show);
	}

}
