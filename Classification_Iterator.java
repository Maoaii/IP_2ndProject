
public class Classification_Iterator {
	private Team[] teams;
	private int size;
	private int index;
	
	public Classification_Iterator(Team[] classification, int size){
		teams = classification;
		this.size = size;
		index = 0;
	}
	
	public boolean hasNext(){
		return index < size;
	}
	
	public Team next(){
		index++;
		return teams[index - 1];		
	}
}
