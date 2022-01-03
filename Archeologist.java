/**
 * Class regarding a single Archeologist. Holds all its' information, like current position, merit,
 * number of penalties, license and name
 * 
 * @author Lucas Girotto and Pedro Afonso
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
	 */
	public void updateXPos(int leap) {
		posX += leap;
	}

	/**
	 * Updates the archeologist's position in the y-axis
	 * 
	 * @param leap: leap archeologist makes
	 */
	public void updateYPos(int leap) {
		posY += leap;
	}

	/**
	 * @return arch position on x-axis
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return arch position on y-axis
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Adds merit to archeologist
	 * 
	 * @param value: value to be added to archeologist
	 * @pre value > 0
	 */
	public void addMerit(int value) {
		merit += value;
	}

	/**
	 * Removes merit from Archeologist
	 * 
	 * @param meritLoss to be subtracted from archeologists merit
	 * @pre meritLoss > 0
	 */
	public void removeMerit(int meritLoss) {
		merit -= meritLoss;
		numPenalties++;
	}

	/**
	 * @return arch's merit
	 */
	public int getMerit() {
		return merit;
	}

	/**
	 * Checks if the archeologist has license
	 *
	 * @return true if Archeologist has a license, false if he doesn't
	 */
	public boolean hasLicense() {
		return license;
	}

	/**
	 * Removes Archeologists license and sets his merit and number of penalties to 0
	 */
	public void removeLicense() {
		license = false;
		merit = 0;
		numPenalties = 0;
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
	 * Compares two archeologists to see which one is doing better in the contest, based on
	 * licenses, merit and number of penalties. If all the same, whoever comes first on the alphabet
	 * is better
	 * 
	 * @param other: other archeologist to compare to
	 * @pre other != null
	 * @return true if the other one is better, otherwise it is false
	 */
	public boolean isBehind(Archeologist other) {
		// This arch is worse than the other
		if (!this.hasLicense())
			return true;

		// This arch is better than the other
		if (!other.hasLicense())
			return false;

		// This arch is worse than the other
		if (merit < other.getMerit())
			return true;
		// This arch is better than the other
		else if (merit > other.getMerit())
			return false;
		// This arch is worse than the other
		else if (numPenalties > other.getNumPenalties())
			return true;
		// This arch is better than the other
		else if (numPenalties < other.getNumPenalties())
			return false;
		// Whoever comes first on the alphabet is better
		else
			return name.compareTo(other.getName()) > 0;
	}
}