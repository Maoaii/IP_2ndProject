/**
 * Iterator designed to cycle through the teams on the contest
 * 
 * @author Lucas Girotto and Pedro Afonso
 */
public class Classification_Iterator {

	// Instance variables
	private Team[] teams;
	private int size;
	private int index;

	/**
	 * Iterator constructor
	 * 
	 * @param teams: array of teams in contest
	 * @param size:  number of teams in contest
	 * @pre teams != null && size >= 0
	 */
	public Classification_Iterator(Team[] teams, int size) {
		this.teams = teams;
		this.size = size;
		index = 0;
	}

	/**
	 * Checks if there is there are other teams left on the array
	 *
	 * @return true if there is more teams to iterate over
	 */
	public boolean hasNext() {
		return index < size;
	}

	/**
	 * Gets the next team on the array
	 *
	 * @return the next team to be iterated over
	 * @pre hasNext()
	 */
	public Team next() {
		return teams[index++];
	}
}
