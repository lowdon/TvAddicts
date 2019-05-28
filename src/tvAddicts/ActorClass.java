package tvAddicts;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ActorClass implements Actor{

	private int romanticNumber;
	private int romanticShowsNumber;
        private int rolesNumber;
	private final String name;
	private Set<Show> rolesList;
	
	
	public ActorClass(String name) {
		this.name = name;
		rolesList = new TreeSet<Show>();
		rolesNumber = 0;
		romanticNumber = 0;
		romanticShowsNumber = 0;
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
		if(!rolesList.contains(show))
			rolesList.add(show);
		rolesNumber++;
	}

	@Override
	public int rolesNumber() {
		return rolesNumber;
	}

	
	
	@Override
	public int romanticNumber() {
		return romanticNumber;
	}

	

	@Override
	public void addRomance() {
		romanticNumber++;
	}

	@Override
	public int romanticShowsNumber() {
		return romanticShowsNumber;
	}

	@Override
	public void addFirstRomanceInShow() {
		romanticShowsNumber++;
	}
	
	@Override
	public int compareTo(Actor arg0) {
		if (romanticNumber > arg0.romanticNumber())
			return -1;
		else if (romanticNumber < arg0.romanticNumber())
			return 1;
		else {
			if (rolesNumber() < arg0.rolesNumber())
				return -1;
			else if (rolesNumber() > arg0.rolesNumber())
				return 1;
			else {
				if (romanticShowsNumber < arg0.romanticShowsNumber())
					return -1;
				else if (romanticShowsNumber > arg0.romanticShowsNumber())
					return 1;
				else {
					return  name.compareTo(arg0.name());
				}
			}
		}
	}

	
}
