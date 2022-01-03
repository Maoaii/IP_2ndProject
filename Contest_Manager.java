/**
 * System class. Handles all the logic regarding this program
 * 
 * @author Lucas Girotto and Pedro Afonso
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
		int rows = terrain.length;
		int cols = terrain[0].length;
		plots = new Plot[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				plots[row][col] = new Plot(terrain[row][col]);
			}
		}

		teams = new Team[MAX_NUM_OF_TEAMS];
		size = 0;
	}

	/**
	 * Creates a team and adds it to the contest
	 * 
	 * @param teamName:    the name that describes the team
	 * @param memberNames: an array that displays each member's name
	 * @pre teamName != null && memberNames != null
	 */
	public void addTeam(String teamName, String[] memberNames) {
		Archeologist[] members = new Archeologist[memberNames.length];

		for (int member = 0; member < size; member++) {
			members[member] = new Archeologist(memberNames[member]);
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
	 * @return a boolean matrix with the current state of the terrain
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
	 * Computes the excavation of an archeologist
	 * 
	 * @param leapY:    archeologist movement on Y-axis
	 * @param leapX:    archeologist movement on X-axis
	 * @param teamName: name of the team that'll excavate
	 * @pre leapY != 0 && leapX != 0 && teamName != null
	 */
	public void computeExcavation(int leapY, int leapX, String teamName) {
		// Check which archeologist will make the excavation
		Archeologist arch = teams[getTeamIndex(teamName)].getCurrentArch();

		if (isOutOfBounds(leapY, leapX, arch))
			computeOutOfBounds(arch, teamName);
		else
			excavate(arch, leapX, leapY);
	}

	/**
	 * Removes archeologist's license and removes team from contest if it doesn't have licensed
	 * archs
	 * 
	 * @param arch:     archeologist to lose license
	 * @param teamName: team to check for licensed archs
	 * @pre arch != null && teamName != null
	 */
	private void computeOutOfBounds(Archeologist arch, String teamName) {
		arch.removeLicense();

		// If team doesn't have any licensed arch, remove team from contest
		if (teams[getTeamIndex(teamName)].getNumArchLicensed() == 0)
			removeTeam(teamName);
	}

	/**
	 * Handles the logic behind an excavation
	 * 
	 * @param arch:  archeologist that'll excavate
	 * @param leapX: arch movement on x-axis
	 * @param leapY: arch movement on y-axis
	 * @pre arch != null
	 */
	private void excavate(Archeologist arch, int leapX, int leapY) {
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

	/**
	 * Removes a team from the contest
	 * 
	 * @param teamName: name of the team to be removed
	 * @pre teamName != null
	 */
	private void removeTeam(String teamName) {
		int removedIndex = getTeamIndex(teamName);

		for (int index = removedIndex; index < size - 1; index++) {
			teams[index] = teams[index + 1];
		}

		size--;
	}

	/**
	 * Checks if the arch will be out of bound after the movement
	 * 
	 * @param leapY: leap on y-axis
	 * @param leapX: leap on x-axis
	 * @param arch:  archeologist that'll make the leap
	 * @pre arch != null
	 * @return true if archeologist is out of bounds
	 */
	private boolean isOutOfBounds(int leapY, int leapX, Archeologist arch) {
		int cols = plots[0].length;
		int rows = plots.length;

		boolean outOnRight = arch.getPosX() + leapX >= cols;
		boolean outOnLeft = arch.getPosX() + leapX < 0;
		boolean outOnTop = arch.getPosY() + leapY < 0;
		boolean outOnBottom = arch.getPosY() + leapY >= rows;

		return outOnRight || outOnLeft || outOnBottom || outOnTop;
	}

	/**
	 * Gets the index of the team with "teamName"
	 * 
	 * @param teamName: name of the team to look for
	 * @pre doesTeamExist() && teamName != null
	 * @return the index of the team
	 */
	private int getTeamIndex(String teamName) {
		int teamIndex = 0;
		boolean found = false;

		while (!found && teamIndex < size) {
			if (teams[teamIndex].getName().equals(teamName))
				found = true;
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
	 * Sorts the teams based on their score, licensed/not licensed archeologists and name
	 * 
	 * @param tmpTeams: team to be sorted
	 * @pre tmpTeams != null
	 */
	private void sortTeams(Team[] tmpTeams) {
		for (int i = 1; i < size; i++) {
			for (int j = size - 1; j >= i; j--) {
				if (tmpTeams[j - 1].isBehind(tmpTeams[j])) {
					Team tmp = tmpTeams[j - 1];
					tmpTeams[j - 1] = tmpTeams[j];
					tmpTeams[j] = tmp;
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
		Team[] tmpTeams = new Team[MAX_NUM_OF_TEAMS];

		for (int team = 0; team < size; team++) {
			tmpTeams[team] = teams[team];
		}

		sortTeams(tmpTeams);
		return new Classification_Iterator(tmpTeams, size);
	}
}