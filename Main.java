import java.util.Scanner;
import java.io.*;

/**
 * 
 * @author
 */
public class Main {
	// Constants
		//File name
	private static final String FILENAME = "teams.txt";
	
		// Commands
	private static final String COMMAND_RICHNESS = "riqueza";
	private static final String COMMAND_TERRAIN = "terreno";
	private static final String COMMAND_MERIT = "merito";
	private static final String COMMAND_STAR = "estrela";
	private static final String COMMAND_EXCAVATION = "escavacao";
	private static final String COMMAND_QUIT = "sair";

	// Output messages
	private static final String RICHNESS_BURIED = "Riqueza enterrada: ";
	private static final String BOTH_DISQUALIFIED = "Correu mal! Foram ambos desclassificados.";
	private static final String ALL_DUG_UP = "Todos os tesouros foram descobertos!";
	private static final String TREASURE_LEFT = "Ainda havia tesouros por descobrir...";
	private static final String INVALID_COMMAND = "Comando invalido";
	private static final String ARQ_DISQUALIFIED = "Arqueologo desclassificado";
	private static final String ARQ_DOESNT_EXIST = "Arqueologo inexistente";
	private static final String INVALID_LEAP = "Salto invalido";
	private static final String LOST_LICENSE = " perdeu a licenca de escavacao";
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		Scanner file = new Scanner(new FileReader(FILENAME));
		
		// Read two int's, L and C (lines and columns for terrain)
		// Read the terrain and store it
		
		
		// Open up the file
		// Read an int, E (how many teams enter the contest)

		// Read a line of ints (number of the teams that'll join)

		// Start the contest loop

		in.close();
		file.close();
	}
}
