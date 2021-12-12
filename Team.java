/**
 * 
 * @author
 */
public class Team {
	// Instance variables
	private Archeologist[] members;
	private int points;

	/**
	 * Team Constructor
	 * 
	 * @param members: archeologist's from this team
	 * @pre members != null
	 */
	public Team(Archeologist[] members) {
		this.members = members;
		points = 0;
	}

	/**
	 * @return the number of archeologists with a valid license
	 */
	public int getNumArchLicense() {
		return 1;
	}

	/**
	 * Checks who is this team's star, base on his merit, his penalties and his name
	 * 
	 * @return the name of this team's star
	 */
	public String getTeamStar() {
		return "";
	}
}
