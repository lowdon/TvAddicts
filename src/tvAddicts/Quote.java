package tvAddicts;

public interface Quote {

	/**
	 * @return <code>Quote</code>
	 */
	String quote();
	
	/**
	 * @return <code>Season</code> onde a <code>Quote</code> foi dita
	 */
	int seasonNum();
	
	/**
	 * @return <code>Episode</code> onde a <code>Quote</code> foi dita
	 */
	int episodeNum();
}
