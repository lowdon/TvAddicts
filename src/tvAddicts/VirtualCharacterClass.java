package tvAddicts;


public class VirtualCharacterClass extends CharacterAbstractClass implements VirtualCharacter {
	
	private final CGI company;
    private final int costPerSeason;
	public VirtualCharacterClass(String characterName, CGI company, int costPerSeason, Show show) {
		super(characterName, show);
		this.company = company;
		this.costPerSeason = costPerSeason;
		company.addVirtualCharacter();
	}

	@Override
	public CGI company() {
		return this.company;
	}

	@Override
	public int costPerSeason() {
		return this.costPerSeason;
	}

}
