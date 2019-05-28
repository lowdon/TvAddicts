package tvAddicts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CharacterAbstractClass implements Character {

	
	private List<Character> parents;
	private List<Character> children;
	private List<Character> romances;
	private List<Character> siblings;
	private	List<Quote> quotes;
	private final String name;
	private final Show show;
	
	public CharacterAbstractClass(String name,Show show) {
		this.name = name;
		this.show = show;
		parents = new ArrayList<Character>();
		children = new ArrayList<Character>();
		romances = new ArrayList<Character>();
		siblings = new ArrayList<Character>();
		quotes = new ArrayList<Quote>();
	}
	public String kidsNumber(){
		return Integer.toString(children.size());
	}
	
	public String parentsNumber(){
		return Integer.toString(parents.size());
	}
	
	public Show show(){
		return show;
	}
	
	public String name(){
		return this.name;
	}
	
	@Override
	public int compareTo(Character arg0) { // return 1 if source is after comparing (a>b), 0 if equal, -1 if source is before (a<b)
		int sourceLen = this.name().length();
		int compLen = arg0.name().length();
		int smallestLen = Math.min(sourceLen, compLen);
		int result = 0;
		for(int i = 0; i < smallestLen; i++){
			char sourceChar = this.name().charAt(i);
			char compChar = arg0.name().charAt(i);
			if(sourceChar > compChar){
				result = 1;
				i = smallestLen;
			}
			else if(sourceChar < compChar){
				result = -1;
				i = smallestLen;
			}
			else {}
		}
		return result;
	}

	
	@Override
	public void addParent(Character parent) {	
		this.parents.add(parent);
		Iterator<Character> kidsIterator = parent.kidsIterator();
		while(kidsIterator.hasNext()) {
			Character kid = kidsIterator.next();
			if(!siblings.contains(kid) &&  !kid.equals(this)) {
				siblings.add(kid);
			    kid.addSiblings(this);
			}
		}
	}
	
	@Override
	public void addChild(Character child) {
		children.add(child);
		
	}
	
	@Override
	public void addRomance(Character character) {
		this.romances.add(character);
	}
	
	@Override
	public void addQuote(int seasonNum, int episodeNum, String quote) {
		Quote newQuote = new QuoteClass(seasonNum, episodeNum, quote);
		this.quotes.add(newQuote);
	}

	@Override
	public Iterator<Character> parentsIterator() {
		return parents.iterator();
	}

	@Override
	public Iterator<Character> kidsIterator() {
		return children.iterator();
	}

	@Override
	public Iterator<Character> siblingsIterator() {
		return siblings.iterator();
	}

	@Override
	public Iterator<Character> romanticIterator() {
		return romances.iterator();
	}

	@Override
	public Iterator<Quote> quotesIterator() {
		return quotes.iterator();
	}
	
	@Override
	public void addSiblings(Character character) {
		if(!siblings.contains(character))
		siblings.add(character);
	}

}
