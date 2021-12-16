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
		System.out.println(name);
		for (int i = 0; i < members.length; i++) {
			System.out.println(members[i].getName());
		}
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
		Archeologist[] sortedMembers = sortMembers();
		return sortedMembers[FIRST].getName();
	}

	/**
	 * Sorts the team members based on merit, penalties and name
	 * 
	 * @return a sorted archeologist array
	 */
	private Archeologist[] sortMembers() {
		Archeologist[] tmpMembers = new Archeologist[members.length];
		tmpMembers = members;
		for (int arch = 0; arch < members.length; arch++) {
			if (tmpMembers[arch].getMerit() < tmpMembers[arch + 1].getMerit()) {
				Archeologist tmp = tmpMembers[arch];
				tmpMembers[arch] = tmpMembers[arch + 1];
				tmpMembers[arch + 1] = tmp;
			} else if (tmpMembers[arch].getMerit() == tmpMembers[arch + 1].getMerit()) {
				if (tmpMembers[arch].getNumPenalties() < tmpMembers[arch + 1].getNumPenalties()) {
					Archeologist tmp = tmpMembers[arch];
					tmpMembers[arch] = tmpMembers[arch + 1];
					tmpMembers[arch + 1] = tmp;
				} else if (tmpMembers[arch].getNumPenalties() == tmpMembers[arch + 1]
						.getNumPenalties()) {
					if (tmpMembers[arch].compareTo(tmpMembers[arch + 1]) > 0) {
						Archeologist tmp = tmpMembers[arch];
						tmpMembers[arch] = tmpMembers[arch + 1];
						tmpMembers[arch + 1] = tmp;
					}
				}
			}
		}
		return tmpMembers;
	}

	/**
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
