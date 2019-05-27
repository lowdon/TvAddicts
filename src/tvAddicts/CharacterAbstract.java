package tvAddicts;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class CharacterAbstract implements Character {

	private ArrayList<Character> parents;
	private ArrayList<Character> children;
	private ArrayList<Character> romances;
	private ArrayList<Character> siblings;
	private	ArrayList<Quote> quotes;
	protected String name;
	protected int cost;
	protected Show show;
	
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
	
	public int cost(){
		return this.cost;
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
		Iterator<Character> itr = this.show.characterListIterator();
		while(itr.hasNext()){
			Character current = itr.next();
			if(current.name().equals(parent.name())){
				this.parents.add(current);
			}
		}
	}
	
	@Override
	public void addChild(Character child) {
		Iterator<Character> itr = this.show.characterListIterator();
		while(itr.hasNext()){
			Character current = itr.next();
			if(current.name().equals(child.name())){
				this.children.add(current);
			}
		}
	}
	
	@Override
	public void addRomance(Character character) {
		this.romances.add(character);
	}

	private ArrayList<Quote> getQuotes(){
		return this.quotes;
	}
	
	@Override
	public void addQuote(int seasonNum, int episodeNum, String quote) {
		Quote newQuote = new QuoteClass(seasonNum, episodeNum, quote);
		this.getQuotes().add(newQuote);
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

}
