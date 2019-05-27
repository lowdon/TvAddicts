package tvAddicts;

public class RealCharacterClass extends CharacterAbstract implements RealCharacter {
	
	private Actor actor;
	
	
	public RealCharacterClass(String characterName, Actor actor, int feeByEpisode, Show show) {
		this.name = characterName;
		this.actor = actor;
		this.cost = feeByEpisode;
		this.show = show;
	}

	@Override
	public Actor actor() {
		return this.actor;
	}

	@Override
	public String characterName() {
		return name();
	}


	@Override
	public int feeByEpisode() {
		return cost();
	}

}
