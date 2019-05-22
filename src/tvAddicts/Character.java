package tvAddicts;

public interface Character {
	Actor actor();
	
	void addParent(Character child);
	
	void addChild(Character parent);
	
	void addRomance(Character character);
	
	void addQuote(int seasonNum, int episodeNum, String quote);
}
