package tvAddicts;

import java.util.Iterator;

public interface Character  extends Comparable<Character>{
	
	Show show();
	
	String characterName();
	
	void addParent(Character child);
	
	void addChild(Character parent);
	
	void addRomance(Character character);
	
	void addQuote(int seasonNum, int episodeNum, String quote);
	
	Iterator<Character> parentsIterator();
	
	int parentsNumber();
	
	Iterator<Character> kidsIterator();
	
	int kidsNumber();
	
	Iterator<Character> siblingsIterator();
	
	Iterator<Character> romanticIterator();
	
	Iterator<Quote> quotesIterator();
}
