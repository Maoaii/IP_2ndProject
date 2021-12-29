/**
 * 
 * @author
 */
public class Team {
	// Constants
	private static final int FIRST = 0;

	// Instance variables
	private Archeologist[] members;
	private String teamName;
	private int currentArchIndex;

	/**
	 * Team Constructor
	 * 
	 * @param members: archeologist's from this team
	 * @param name:    name to be assigned to this team
	 * @pre members != null && name != null
	 */
	public Team(Archeologist[] members, String name) {
		this.members = members;
		teamName = name;
		for (int i = 0; i < members.length; i++) {
		}
		currentArchIndex = FIRST;
	}

	/**
	 * Gets the next archeologist who is going to dig, skips any that doesn't have a license
	 * 
	 * @pre at least one member has license
	 * @return the archeologist whose turn to dig is next
	 */
	public Archeologist getCurrentArch() {
		Archeologist arch;
		do {
			arch = members[currentArchIndex];
			if (currentArchIndex < members.length - 1) {
				currentArchIndex++;
			} else {
				currentArchIndex = FIRST;
			}
		} while (!arch.hasLicense());
		return arch;
	}

	/**
	 * gets the team's name
	 *
	 * @return the team's name
	 */
	public String getName() {
		return teamName;
	}

	/**
	 * gets the number of archeologists that still have a license
	 *
	 * @return the number of archeologists with a valid license
	 */
	public int getNumArchLicensed() {
		return members.length - getNumArchNotLicensed();
	}

	/**
	 * gets the number of archeologists that have lost their license
	 *
	 * @return the total of archeologists without a license on this team
	 */
	public int getNumArchNotLicensed() {
		int total = 0;
		for (int member = 0; member < members.length; member++) {
			if (!members[member].hasLicense()) {
				total++;
			}
		}
		return total;
	}

	/**
	 * Checks who is this team's star, base on his merit, his penalties and his name
	 * 
	 * @return the name of this team's star
	 */
	public String getTeamStar() {
		
		Archeologist[] sortedMembers = new Archeologist[members.length];
		for (int member = 0; member < members.length; member++) {
			sortedMembers[member] = members[member];
		}
		sortMembers(sortedMembers);
		return sortedMembers[FIRST].getName();
	}

	/**
	 * Sorts the team members based on merit, penalties and name
	 * 
	 * @return a sorted archeologist array
	 */
	private void sortMembers(Archeologist[] sortedMembers) {
		for (int i = 1; i < members.length; i++) {
			for (int j = members.length - 1; j >= i; j--) {
				if (sortedMembers[j - 1].isBehind(sortedMembers[j])) {
					Archeologist tmp = sortedMembers[j - 1];
					sortedMembers[j - 1] = sortedMembers[j];
					sortedMembers[j] = tmp;
				}
			}
		}
	}

	/**
	 * Calculates the team score by adding the merit of each member together
	 *
	 * @return the team's score
	 */
	public int getScore() {
		int score = 0;
		for (int member = 0; member < members.length; member++) {
			score += members[member].getMerit();
		}
		return score;
	}

	/**
	 * Compares this team's score, number of licensed/not licensed archeologists
	 * 
	 * @param other: other team to compare to
	 * @pre other != null
	 * @return true if this team needs to be sorted lower than the other
	 */
	public boolean goesAfter(Team other) {
		if (getScore() < other.getScore()) {
			return true;
		} else if (getScore() > other.getScore()) {
			return false;
		} else {
			if (getNumArchNotLicensed() > other.getNumArchNotLicensed()) {
				return true;
			} else if (getNumArchNotLicensed() < other.getNumArchNotLicensed()) {
				return false;
			} else {
				if (getNumArchLicensed() > other.getNumArchLicensed()) {
					return true;
				} else if (getNumArchLicensed() < other.getNumArchLicensed()) {
					return false;
				} else {
					return getName().compareTo(other.getName()) > 0;
				}
			}
		}
	}
}
