package tvAddicts;

import java.util.Iterator;

public interface Character  extends Comparable<Character>{
	Actor actor();
	
	void addParent(Character child);
	
	void addChild(Character parent);
	
	void addRomance(Character character);
	
	void addQuote(int seasonNum, int episodeNum, String quote);
	
	Iterator<Quotes> quotesIterator();
}
