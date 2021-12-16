/**
 * 
 * @author
 */
public class Contest_Manager {
	// Constants
	private static final int MAX_NUM_OF_TEAMS = 10;

	// Instance variables
	private Plot[][] plots;
	private Team[] teams;
	private int size;

	/**
	 * Contest Manager Constructor
	 * 
	 * @param terrain: value buried inside each plot
	 * @pre terrain != null
	 */
	public Contest_Manager(int[][] terrain) {
		int lines = terrain.length;
		int cols = terrain[0].length;
		plots = new Plot[lines][cols];

		System.out.println("Terrain: ");
		for (int row = 0; row < lines; row++) {
			for (int col = 0; col < cols; col++) {
				plots[row][col] = new Plot(terrain[row][col]);
				System.out.print(plots[row][col].getTreasure());
			}
			System.out.println();
		}

		teams = new Team[MAX_NUM_OF_TEAMS];
		size = 0;
	}

	/**
	 * Creates a team and adds it to the contest
	 * 
	 * @param teamName:    the name that describes the team
	 * @param memberNames: an array that displays each member's name
	 * @pre teamName != null && memberName != null
	 */
	public void addTeam(String teamName, String[] memberNames) {
		Archeologist[] members = new Archeologist[memberNames.length];
		for (int i = 0; i < members.length; i++) {
			members[i] = new Archeologist(memberNames[i]);
		}
		teams[size] = new Team(members, teamName);
		size++;
	}

	/**
	 * Computes the richness buried in the plots
	 * 
	 * @return the sum of all the treasures buried
	 */
	public int computeRichness() {
		int rows = plots.length;
		int cols = plots[0].length;
		int sumTreasures = 0;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				sumTreasures += plots[row][col].getTreasure();
			}
		}
		return sumTreasures;
	}

	/**
	 * Computes the logic behind printing the current state of the terrain
	 * 
	 * @return a boolean matriz with the current state of the terrain
	 */
	public boolean[][] getTerrain() {
		int rows = plots.length;
		int cols = plots[0].length;

		boolean[][] tmpTerrain = new boolean[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (plots[row][col].getTreasure() == 0)
					tmpTerrain[row][col] = false;
				else
					tmpTerrain[row][col] = true;
			}
		}

		return tmpTerrain;
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
	 * @param name: team name to search in
	 * @pre name != null
	 * @return the name of a certain team's star
	 */
	public String computeTeamStar(String name) {
		String starName = "";
		int team = 0;
		boolean found = false;

		while (!found) {
			if (teams[team].getName().equals(name)) {
				found = true;
				starName = teams[team].getTeamStar();
			}
		}
		return starName;
	}

	/**
	 * Computes the excavation of an archeologist
	 */
	public void computeExcavation() {

	}

	/**
	 * Checks if a given team name matches any of the teams in the contest
	 * 
	 * @param name: name to compare with
	 * @pre name != nulls
	 * @return true if name matches a team name
	 */
	public boolean doesTeamExist(String name) {
		for (int team = 0; team < teams.length; team++) {
			if (teams[team].getName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * Checks if all the teams are disqualified
	 * 
	 * @return true if all the teams are disqualified
	 */
	public boolean areTeamsDisqualified() {
		return false;
	}

	/**
	 * Sorts the teams based on their score
	 */
	private void updateScore() {
		for (int team = 0; team < size - 1; team++) {
			for (int other = 1; other < size; other++) {
				if (teams[team].goesAfter(teams[other])) {
					Team tmp = teams[team];
					teams[team] = teams[other];
					teams[other] = tmp;
				}
			}
		}
	}

	/**
	 * Sort the teams based on their score and return an iterator
	 * 
	 * @return a scoreIterator
	 */
	public Classification_Iterator scoreIterator() {
		updateScore();
		return new Classification_Iterator(teams, size);
	}

}
