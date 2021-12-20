/**
 * 
 * @author
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
	 *gets the value of the treasure in this plot
	 *
	 * @return this plot's treasure
	 */
	public int getTreasure() {
		return treasure;
	}

	/**
	 *Checks if this plot has been dug up
	 *
	 * @return true if plot has been dug up
	 */
	public boolean isDugUp() {
		return timesDugUp > 0;
	}

	/**
	 *Checks how many times this plot has been dug up
	 *
	 * @return the amount of times this plot has been dug up
	 */
	public int getTimesDugUp() {
		return timesDugUp;
	}

	/**
	 * Marks this plot as dug up
	 */
	public void excavate() {
		timesDugUp++;
		treasure = 0;
	}
}
