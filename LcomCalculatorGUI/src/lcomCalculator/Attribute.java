package lcomCalculator;

public class Attribute {

	String name;
	int numOfTimesUsed;
	
	public Attribute(String name){
		this.name = name;
		numOfTimesUsed = 0;
	}
	
	public void increaseUsage() {
		numOfTimesUsed++;
	}
	
	public void resetUsage() {
		numOfTimesUsed = 0;
	}
	public String getName() {
		return name;
	}
	
	public int getUsage() {
		return numOfTimesUsed;
	}
}
