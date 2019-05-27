package tvAddicts;


public class VirtualCharacterClass extends CharacterAbstract implements VirtualCharacter {
	
	private CGI company;

	public VirtualCharacterClass(String characterName, CGI company, int costPerSeason, Show show) {
		this.name = characterName;
		this.company = company;
		this.cost = costPerSeason;
		this.show = show;
	}

	@Override
	public CGI company() {
		return this.company;
	}

	@Override
	public int costPerSeason() {
		return this.cost;
	}

	@Override
	public Actor actor() {
		return null;
	}

}
