/**
 * Class regarding a single Team. Holds all its' information, like the team name, all the members
 * and the next archeologist to excavate
 * 
 * @author Lucas Girotto and Pedro
 */
public class Team {

	// Constants
	private static final int FIRST_ARCH = 0;

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
		currentArchIndex = FIRST_ARCH;
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

			if (currentArchIndex < members.length - 1)
				currentArchIndex++;
			else
				currentArchIndex = FIRST_ARCH;
		} while (!arch.hasLicense());

		return arch;
	}

	/**
	 * @return the team's name
	 */
	public String getName() {
		return teamName;
	}

	/**
	 * @return the number of archeologists with a valid license
	 */
	public int getNumArchLicensed() {
		return members.length - getNumArchNotLicensed();
	}

	/**
	 * @return the total of archeologists without a license on this team
	 */
	public int getNumArchNotLicensed() {
		int total = 0;

		for (int member = 0; member < members.length; member++) {
			if (!members[member].hasLicense())
				total++;
		}

		return total;
	}

	/**
	 * Checks who is this team's star, base on merit, penalties and name
	 * 
	 * @return the name of this team's star
	 */
	public String getTeamStar() {

		Archeologist[] sortedMembers = new Archeologist[members.length];

		for (int member = 0; member < members.length; member++) {
			sortedMembers[member] = members[member];
		}

		sortMembers(sortedMembers); // Sort members from best to worst

		return sortedMembers[FIRST_ARCH].getName();
	}

	/**
	 * Sorts the team members based on merit, penalties and name
	 * 
	 * @param sortedMembers: team members to be sorted
	 * @pre sortedMembers != null
	 */
	private void sortMembers(Archeologist[] sortedMembers) {
		for (int i = 1; i < members.length; i++) {
			for (int j = members.length - 1; j >= i; j--) {
				// If and arch is worse than the one in front, switch their places
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
	 * Compares this team's score, number of licensed/not licensed archeologists and name to another
	 * team
	 * 
	 * @param other: other team to compare to
	 * @pre other != null
	 * @return true if this team needs to be sorted lower than the other
	 */
	public boolean isBehind(Team other) {
		// This team is lower than the other
		if (getScore() < other.getScore())
			return true;
		// This team is higher than the other
		else if (getScore() > other.getScore())
			return false;
		// This team is lower than the other
		else if (getNumArchNotLicensed() > other.getNumArchNotLicensed())
			return true;
		// This team is higher than the other
		else if (getNumArchNotLicensed() < other.getNumArchNotLicensed())
			return false;
		// This team is lower than the other
		else if (getNumArchLicensed() > other.getNumArchLicensed())
			return true;
		// This team is higher than the other
		else if (getNumArchLicensed() < other.getNumArchLicensed())
			return false;
		// Whoever comes first on the alphabet higher
		else
			return getName().compareTo(other.getName()) > 0;
	}
}
