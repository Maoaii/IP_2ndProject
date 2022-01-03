/**
 * Class regarding a single Plot. Holds all its' information, like its' current treasure and how
 * many times it has been dug up
 * 
 * @author Lucas Girotto and Pedro Afonso
 */
public class Plot {
	
	// Instance variables
	private int treasure;
	private int timesDugUp;

	/**
	 * Plot Constructor
	 * 
	 * @param treasure: treasure to be assigned to plot
	 * @pre treasure != null
	 */
	public Plot(int treasure) {
		this.treasure = treasure;
		timesDugUp = 0;
	}

	/**
	 * @return this plot's treasure
	 */
	public int getTreasure() {
		return treasure;
	}

	/**
	 * @return true if plot has been dug up
	 */
	public boolean isDugUp() {
		return timesDugUp > 0;
	}

	/**
	 * @return the amount of times this plot has been dug up
	 */
	public int getTimesDugUp() {
		return timesDugUp;
	}

	/**
	 * Marks this plot as dug up and removes its' treasure
	 */
	public void excavate() {
		timesDugUp++;
		treasure = 0;
	}
}
