package tvAddicts;

public class RealCharacterClass extends CharacterAbstractClass implements RealCharacter {
	
	private Actor actor;
	
	
	public RealCharacterClass(String characterName, Actor actor, int feeByEpisode, Show show) {
		this.name = characterName;
		this.actor = actor;
		this.cost = feeByEpisode;
		this.show = show;
		actor.addRole(show);
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
	
	@Override
	public void addRomance(Character character) {
		if(!this.romanticIterator().hasNext())
			actor.addFirstRomanceInShow();
		super.addRomance(character);
		actor.addRomance();
	}

}
