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
		System.out.println("X: " + posX);
	}

	/**
	 * Updates the archeologist's position in the y-axis
	 * 
	 * @param leap: leap archeologist makes
	 * @pre leap != null
	 */
	public void updateYPos(int leap) {
		posY += leap;
		System.out.println("Y: " + posY);
	}

	/**
	 *updates the X position on the terrain
	 *
	 * @return position on x-axis
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 *updates the Y position on the terrain
	 *
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
	 *gets the archeologist's merit'
	 *
	 * @return Archeologist's merit
	 */
	public int getMerit() {
		return merit;
	}

	/**
	 *Checks if the archeologist has license
	 *
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
	 *Gtes how many times this archeologist has been penalized
	 *
	 * @return the number of penalties this archeologist commited
	 */
	public int getNumPenalties() {
		return numPenalties;
	}

	/**
	 *Gets the archeologist's name
	 *
	 * @return Archeologist's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Compares two archeologists to see which one is doing better in the contest
	 * 
	 * @param other: other archeologist to compare to
	 * @pre other != null
	 * @return true if the other one is better, otherwise it is false
	 */
	public boolean isBehind(Archeologist other){
		if(merit < other.getMerit()){
			return true;
		}
		else if(merit > other.getMerit()){
			return false;
		}
		else{
			if(numPenalties > other.getNumPenalties()){
				return true;
			}
			else if(numPenalties < other.getNumPenalties()){
				return false;
			}
			else{
				return name.compareTo(other.getName()) > 0;
			}
		}
	}

}
