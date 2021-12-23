/**
 * 
 * @author
 */
public class Contest_Manager {
	// Constants
	private static final int MAX_NUM_OF_TEAMS = 10;
	private static final int PENALTY = 10;

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
				if (plots[row][col].getTreasure() > 0)
					tmpTerrain[row][col] = true;
				else
					tmpTerrain[row][col] = false;
			}
		}

		return tmpTerrain;
	}

	/**
	 * Computes the logic behind checking who's the team star
	 * 
	 * @param name: team name to search in
	 * @pre name != null
	 * @return the name of a certain team's star
	 */
	public String computeTeamStar(String name) {
		return teams[getTeamIndex(name)].getTeamStar();
	}

	/**
	 * * Computes the excavation of an archeologist
	 * 
	 * @param leapY:    archeologist movement on Y-axis
	 * @param leapX:    archeologist movement on X-axis
	 * @param teamName: name of the team that'll excavate
	 * @pre leapY != null && leapX != null && teamName != null
	 */
	public void computeExcavation(int leapY, int leapX, String teamName) {

		// Check which archeologist will make the excavation
		// Based on order in team and license
		Archeologist arch = teams[getTeamIndex(teamName)].getCurrentArch();

		// Compute the excavation

		// If arch gets out of the terrain
			// Lose license
		if (isOutOfBounds(leapY, leapX, arch)) {
			arch.removeLicense();
			if(teams[getTeamIndex(teamName)].getNumArchLicensed() == 0) {
				removeTeam(teamName);
			}
		}
		else {
			// Update position
			arch.updateXPos(leapX);
			arch.updateYPos(leapY);
			
			// Get plot the arch landed on
			Plot landedPlot = plots[arch.getPosY()][arch.getPosX()];
			
			// If is inside terrain and has been excavated
				// Lose merit based on how many times plot has been excavated
			if (landedPlot.isDugUp()) {
				arch.removeMerit(PENALTY * landedPlot.getTimesDugUp());
				landedPlot.excavate();
			}
				
			// If is inside terrain and has not been excavated
				// Gain merit based on treasure on plot
			else {
				arch.addMerit(landedPlot.getTreasure());
				landedPlot.excavate();
			}
		}	
	}
	
	private void removeTeam(String teamName) {
		Team[] newTeams = new Team[MAX_NUM_OF_TEAMS];
		int removedIndex = getTeamIndex(teamName);
		for(int index = 0; index < removedIndex; index++) {
			newTeams[index] = teams[index];
		}
		size--;
		for(int index = removedIndex; index < size; index++) {
			newTeams[index] = teams[index + 1];
		}
		teams = newTeams;
	}

	private boolean isOutOfBounds(int leapY, int leapX, Archeologist arch) {
		int cols = plots[0].length;
		int rows = plots.length;
		
		if (arch.getPosX() + leapX >= cols || arch.getPosX() + leapX < 0
				|| arch.getPosY() + leapY >= rows || arch.getPosY() + leapY < 0)
			return true;
		
		return false;
	}

	/**
	 * @param teamName: name of the team to look for
	 * @pre doesTeamExist()
	 * @return the index of the team
	 */
	private int getTeamIndex(String teamName) {
		int teamIndex = 0;
		boolean found = false;
		while (!found && teamIndex < size) {
			if (teams[teamIndex].getName().equals(teamName)) {
				found = true;
			}
			else
				teamIndex++;
		}
		return teamIndex;
	}

	/**
	 * Checks if a given team name matches any of the teams in the contest
	 * 
	 * @param name: name to compare with
	 * @pre name != nulls
	 * @return true if name matches a team name
	 */
	public boolean doesTeamExist(String name) {
		for (int team = 0; team < size; team++) {
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
		return size == 0;
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
