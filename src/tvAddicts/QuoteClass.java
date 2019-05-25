package tvAddicts;

public class QuoteClass implements Quote {

	private final int seasonNum;
	private final int episodeNum;
	private final String quote;
	
	public QuoteClass(int seasonNum, int episodeNum, String quote) {
		this.seasonNum = seasonNum;
		this.episodeNum = episodeNum;
		this.quote = quote;
	}

	@Override
	public String quote() {
		return quote;
	}

	@Override
	public int seasonNum() {
		return seasonNum;
	}

	@Override
	public int episodeNum() {
		return episodeNum;
	}

}
