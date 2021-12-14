/**
 * 
 * @author
 */
public class Contest_Manager {
	//Constants
	private final int MAX_NUM_OF_TEAMS = 10;
	// Instance variables
	private Plot[][] plots;
	private Team[] teams;
	private int size;

	/**
	 * Contest Manager Constructor
	 */
	public Contest_Manager(int[][] terrain) {
		int lines = terrain.length;
		int cols = terrain[0].length;
		plots = new Plot[lines][cols];
		for(int i = 0; i < lines; i++) {
			for(int j = 0; j < cols; j++) {
				plots[i][j] = new Plot(terrain[i][j]);
			}
		}
		teams = new Team[MAX_NUM_OF_TEAMS];
		size = 0;
	}
	
	/**
	 * Creates a team and adds it to the contest
	 * @param teamName: the name that describes the team
	 * @param memberNames: an array that displays each member's name
	 */
	public void addTeam(String teamName, String[] memberNames) {
		Archeologist[] members = new Archeologist[memberNames.length];
		for(int i = 0; i < members.length; i++) {
			members[i] = new Archeologist(memberNames[i]);
		}
		teams[size] = new Team(members, teamName);
		size++;
	}

	/**
	 * Computes the logic behind printing the current state of the terrain
	 * 
	 * @return a String with the current state of the terrain
	 */
	public String printTerrain() {
		return "";
	}

	/**
	 * Computes the richness buried in the plots
	 * 
	 * @return the sum of all the treasures buried
	 */
	public int computeRichness() {
		return 1;
	}

	/**
	 * Computes the logic behind displaying the contest classification
	 * 
	 * @return a string with the current classification of the contest
	 */
	public String computeClassification() {
		return "";
	}

	/**
	 * Computes the logic behind checking who's the team star
	 * 
	 * @return the name of a certain team's star
	 */
	public String computeTeamStar() {
		return "";
	}

	/**
	 * Computes the excavation of an archeologist
	 */
	public void computeExcavation() {

	}
}
