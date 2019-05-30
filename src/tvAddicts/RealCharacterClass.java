package tvAddicts;

public class RealCharacterClass extends CharacterAbstractClass implements RealCharacter {
	
	private final Actor actor;
	private final int feeByEpisode;
	
	public RealCharacterClass(String characterName, Actor actor, int feeByEpisode, Show show) {
		super(characterName, show);
		this.actor = actor;
		this.feeByEpisode = feeByEpisode;
		actor.addRole(show);
	}

	@Override
	public Actor actor() {
		return this.actor;
	}


	@Override
	public int feeByEpisode() {
		return feeByEpisode;
	}
	
	@Override
	public void addRomance(Character character) {
		if(!this.romanticIterator().hasNext())
			actor.addFirstRomanceInShow();
		super.addRomance(character);
		actor.addRomance();
	}

}
