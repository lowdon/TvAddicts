package tvAddicts;


public class VirtualCharacterClass extends CharacterAbstractClass implements VirtualCharacter {
	
	private CGI company;

	public VirtualCharacterClass(String characterName, CGI company, int costPerSeason, Show show) {
		this.name = characterName;
		this.company = company;
		this.cost = costPerSeason;
		this.show = show;
		company.addVirtualCharacter();
		company.addFeesCollected(costPerSeason);
	}

	@Override
	public CGI company() {
		return this.company;
	}

	@Override
	public int costPerSeason() {
		return this.cost;
	}

}
