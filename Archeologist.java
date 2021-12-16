/**
 * 
 * @author
 */
public class Archeologist {
	// Constants
	private static final int STARTING_POSX = -1; // "-1" because plots begin at "0"
	private static final int STARTING_POSY = -1; // "-1" because plots begin at "0"
	private static final int STARTING_MERIT = 0;

	// Instance variables
	private String name;
	private int posX;
	private int posY;
	private int merit;
	private boolean license;
	private int numPenalties;

	/**
	 * Archeologist Constructor
	 * 
	 * @param name: name to be assigned to archeologist
	 * @pre name != null
	 */
	public Archeologist(String name) {
		this.name = name;
		posX = STARTING_POSX;
		posY = STARTING_POSY;
		merit = STARTING_MERIT;
		license = true;
		numPenalties = 0;
	}

	/**
	 * Updates archeologist's position in the x-axis
	 * 
	 * @param leap: leap archeologist makes
	 * @leap != null
	 */
	public void updateXPos(int leap) {
		posX += leap;
	}

	/**
	 * Updates the archeologist's position in the y-axis
	 * 
	 * @param leap: leap archeologist makes
	 * @pre leap != null
	 */
	public void updateYPos(int leap) {
		posY += leap;
	}

	/**
	 * @return position on x-axis
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return position on y-axis
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Adds merit to archeologist
	 * 
	 * @param value: value to be added to archeologist
	 * @pre value != null
	 */
	public void addMerit(int value) {
		merit += value;
	}

	/**
	 * Removes merit from Archeologist
	 * 
	 * @param meritLoss to be subtracted from archeologists merit
	 * @pre meritLoss != null
	 */
	public void removeMerit(int meritLoss) {
		merit -= meritLoss;
		numPenalties++;
	}

	/**
	 * @return Archeologist's merit
	 */
	public int getMerit() {
		return merit;
	}

	/**
	 * @return true if Archeologist has a license, false if he doesn't
	 */
	public boolean hasLicense() {
		return license;
	}

	/**
	 * Removes Archeologists license and sets his merit to 0
	 */
	public void removeLicense() {
		license = false;
		merit = 0;
	}

	/**
	 * @return the number of penalties this archeologist commited
	 */
	public int getNumPenalties() {
		return numPenalties;
	}

	/**
	 * @return Archeologist's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Compares two names to see which is lexicographically bigger
	 * 
	 * @param other: other archeologist to compare to
	 * @pre other != null
	 * @return a positive int if this archeologist's name is lexicographically bigger than the other
	 */
	public int compareTo(Archeologist other) {
		return name.compareTo(other.getName());
	}

}
