package tvAddicts;

import java.util.Iterator;

public interface Character extends Comparable<Character>{
	
	Show show();
	
	String name();
	
	void addParent(Character parent);
	
	void addChild(Character child);
	
	void addRomance(Character character);
	
	void addQuote(int seasonNum, int episodeNum, String quote);
	
	Iterator<Character> parentsIterator();
	
	Iterator<Character> kidsIterator();
	
	Iterator<Character> siblingsIterator();
	
	Iterator<Character> romanticIterator();
	
	Iterator<Quote> quotesIterator();

	String kidsNumber();

	String parentsNumber();

	void addSiblings(Character character);
}
