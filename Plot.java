/**
 * 
 * @author
 */
public class Plot {
	// Instance variables
	private int treasure;
	private boolean dugUp;

	/**
	 * Plot Constructor
	 * 
	 * @param treasure: treasure to be assigned to plot
	 * @pre treasure != null
	 */
	public Plot(int treasure) {
		this.treasure = treasure;
		dugUp = false;
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
		return dugUp;
	}

	/**
	 * Make this plot dug up
	 */
	public void excavate() {
		dugUp = true;
		treasure = 0;
	}
}
