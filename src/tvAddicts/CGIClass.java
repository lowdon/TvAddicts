package tvAddicts;

public class CGIClass implements CGI {

	private final String name;
	private int numberOfCharacters;
	private int feesCollected;
	public CGIClass(String name) {
		this.name = name;
		numberOfCharacters = 0;
		feesCollected = 0;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int numberOfCharacters() {
		return numberOfCharacters;
	}

	@Override
	public void addVirtualCharacter() {
		numberOfCharacters++;
	}

	@Override
	public void addFeesCollected(int fees) {
		feesCollected += fees;
	}

	@Override
	public int feesCollected() {
		return feesCollected;
	}

	@Override
	public int compareTo(CGI o) {
		if (o.feesCollected() > this.feesCollected) {
			return 1;
		}
		else if (o.feesCollected() < this.feesCollected){
			return -1;
		} 
		else {
			if (o.numberOfCharacters() > this.numberOfCharacters()) {
				return 1;
			}
			else if (o.numberOfCharacters() < this.numberOfCharacters()){
				return -1;
			} 
			else {
				return this.name().compareTo(o.name());
				} 
			}
		}
	}

