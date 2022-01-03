import java.util.Scanner;
import java.io.*;

//perguntar sobre binary search vs. bubble sort
//perguntar sobre comentários de métodos do tipo getThis();

/**
 * Main class. Handles all the input and output of information
 * 
 * @author Lucas Girotto and Pedro Afonso
 */
public class Main {
	// Constants
	// File name
	private static final String FILENAME = "teams.txt";

	// Commands
	private static final String COMMAND_RICHNESS = "riqueza";
	private static final String COMMAND_TERRAIN = "terreno";
	private static final String COMMAND_CLASSIFICATION = "classificacao";
	private static final String COMMAND_STAR = "estrela";
	private static final String COMMAND_EXCAVATION = "escavacao";
	private static final String COMMAND_QUIT = "sair";

	// Output messages
	private static final String RICHNESS_BURIED = "Riqueza enterrada: ";
	private static final String ALL_TEAMS_DISQUALIFIED = "Todas as equipas foram expulsas.";
	private static final String CLASSIFICATION_MESSAGE = "%s: %d pts; %d descl.; %d com lic.\n";
	private static final String INVALID_TEAM = "Equipa invalida";
	private static final String STAR_MESSAGE = "Estrela de %s: %s\n";
	private static final String TEAM_DISQUALIFIED = "%s foi expulsa\n";
	private static final String ALL_DUG_UP = "Todos os tesouros foram descobertos!";
	private static final String TREASURE_LEFT = "Ainda havia tesouros por descobrir...";
	private static final String INVALID_COMMAND = "Comando invalido";
	private static final String INVALID_LEAP = "Salto invalido";
	private static final String TREASURE = "*";
	private static final String NO_TREASURE = "-";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		Scanner file = new Scanner(new FileReader(FILENAME));

		// Create contest with a new terrain
		Contest_Manager manager = new Contest_Manager(readTerrain(in));

		// Adds teams to the contest
		addTeams(in, file, manager);

		// Start the contest loop
		String command;
		do {
			command = in.next();
			interpretCommands(command, in, manager);
		} while (!command.equals(COMMAND_QUIT));

		in.close();
		file.close();
	}

	/**
	 * Inteprets the user commands
	 * 
	 * @param command: user command
	 * @param in:      console input reader
	 * @param manager: system that handles the logic
	 * @pre command != null && in != null && manager != null
	 */
	private static void interpretCommands(String command, Scanner in, Contest_Manager manager) {
		switch (command) {
		case COMMAND_RICHNESS:
			handleRichnessCommand(manager);
			break;
		case COMMAND_TERRAIN:
			handleTerrainCommand(manager);
			break;
		case COMMAND_CLASSIFICATION:
			handleClassificationCommand(manager);
			break;
		case COMMAND_STAR:
			handleStarCommand(manager, in);
			break;
		case COMMAND_EXCAVATION:
			handleExcavationCommand(manager, in);
			break;
		case COMMAND_QUIT:
			handleQuitCommand(manager);
			break;
		default:
			System.out.println(INVALID_COMMAND);
			in.nextLine();
			break;
		}
	}

	/**
	 * Prints out the amount of richness buried in the terrain
	 * 
	 * @param manager: system that handles the logic
	 * @pre manager != null
	 */
	private static void handleRichnessCommand(Contest_Manager manager) {
		System.out.println(RICHNESS_BURIED + manager.computeRichness());
	}

	/**
	 * Prints out a makeshift of the current state of the terrain If a certain plot has treasure,
	 * it's represented by "*", otherwise, by "-"
	 * 
	 * @param manager: system that handles the logic
	 * @pre manager != null
	 */
	private static void handleTerrainCommand(Contest_Manager manager) {
		boolean[][] tmpTerrain = manager.getTerrain();
		int rows = tmpTerrain.length;
		int cols = tmpTerrain[0].length;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (tmpTerrain[row][col])
					System.out.print(TREASURE);
				else
					System.out.print(NO_TREASURE);
			}
			System.out.println();
		}
	}

	/**
	 * Prints out the name, score, licensed members and not licensed members of each team
	 * 
	 * @param manager: system that handles the logic
	 * @pre manager != null
	 */
	private static void handleClassificationCommand(Contest_Manager manager) {
		if (manager.areTeamsDisqualified()) {
			System.out.println(ALL_TEAMS_DISQUALIFIED);
			return;
		}

		Classification_Iterator it = manager.scoreIterator();

		while (it.hasNext()) {
			Team team = it.next();
			String name = team.getName();
			int score = team.getScore();
			int nNotLic = team.getNumArchNotLicensed();
			int nLic = team.getNumArchLicensed();
			System.out.printf(CLASSIFICATION_MESSAGE, name, score, nNotLic, nLic);

		}
	}

	/**
	 * Prints out the star of a certain team
	 * 
	 * @param manager: system that handles the logic
	 * @param in:      console input reader
	 * @pre manager != null && in != null
	 */
	private static void handleStarCommand(Contest_Manager manager, Scanner in) {
		String teamName = in.nextLine().trim();

		if (manager.doesTeamExist(teamName))
			System.out.printf(STAR_MESSAGE, teamName, manager.computeTeamStar(teamName));
		else
			System.out.println(INVALID_TEAM);
	}

	/**
	 * Passes the information of a excavation request to the system. Prints out two different error
	 * messages, depending if the leap or the team name is invalid
	 * 
	 * @param manager: system that handles the logic
	 * @param in:      console input reader
	 * @pre manager != null && in != null
	 */
	private static void handleExcavationCommand(Contest_Manager manager, Scanner in) {
		int leapY = in.nextInt();
		int leapX = in.nextInt();
		String teamName = in.nextLine().trim();

		if (leapY == 0 && leapX == 0)
			System.out.println(INVALID_LEAP);
		else if (!manager.doesTeamExist(teamName))
			System.out.println(INVALID_TEAM);

		else {
			manager.computeExcavation(leapY, leapX, teamName);

			if (!manager.doesTeamExist(teamName))
				System.out.printf(TEAM_DISQUALIFIED, teamName);
		}

	}

	/**
	 * Prints out three different messages based on the state of the contest as it ended
	 * 
	 * @param manager: system that handles the logic
	 * @pre manager != null
	 */
	private static void handleQuitCommand(Contest_Manager manager) {
		if (manager.areTeamsDisqualified())
			System.out.println(ALL_TEAMS_DISQUALIFIED);
		else if (manager.computeRichness() == 0)
			System.out.println(ALL_DUG_UP);
		else
			System.out.println(TREASURE_LEFT);

	}

	/**
	 * Reads and stores the treasure buried in the terrain
	 * 
	 * @param in: console input reader
	 * @pre in != null
	 * @return a temporary terrain with treasures buried in each plot
	 */
	private static int[][] readTerrain(Scanner in) {
		int rows = in.nextInt();
		int cols = in.nextInt();
		int[][] tmpTerrain = new int[rows][cols];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				tmpTerrain[row][col] = in.nextInt();
			}
			in.nextLine();
		}

		return tmpTerrain;
	}

	/**
	 * Reads the teams the user wants to add to the contest and adds them
	 *
	 * @param in:     console input reader
	 * @param file:   file reader
	 * @param manager system that handles the logic
	 * @pre in != null && file != null && manager != null
	 */
	private static void addTeams(Scanner in, Scanner file, Contest_Manager manager) {
		int numTeams = readInt(in);
		int[] teams = new int[numTeams];
		for (int index = 0; index < numTeams; index++) {
			teams[index] = in.nextInt();
		}
		in.nextLine();
		int limit = teams[numTeams - 1];
		int arrayIndex = 0;
		for (int numTeam = 1; numTeam <= limit; numTeam++) {
			int numMembers = readInt(file);
			if (numTeam == teams[arrayIndex]) {
				String teamName = file.nextLine().trim();
				String[] memberNames = new String[numMembers];
				for (int member = 0; member < numMembers; member++) {
					memberNames[member] = file.nextLine().trim();
				}
				manager.addTeam(teamName, memberNames);
				arrayIndex++;
			} else {
				for (int index = 0; index < numMembers + 1; index++) {
					file.nextLine();
				}
			}
		}
	}

	/**
	 * Reads and returns an int from the console
	 * 
	 * @param in: console input reader
	 * @pre in != null
	 * @return an int read from console
	 */
	private static int readInt(Scanner in) {
		int tmp = in.nextInt();
		in.nextLine();
		return tmp;
	}

}
