package lcomCalculator;
import java.util.ArrayList;

public class Category {
	
	private String name;
	private ArrayList<Attribute> variables;
	private ArrayList<String> methods;
	
	public Category(String name) {
		this.name = name;
		variables = new ArrayList<Attribute>();
		methods = new ArrayList<String>();
	}
	// class methods
	
	/*Lcom is calculated by getting total usage for each variable, summing them together
	 * then dividing it by number of vars * number of functions
	 * then finally subtracting that number from 1
	 */
	
	public void addToVariables(String varLine) {
		Attribute newVar = new Attribute(varLine);
		variables.add(newVar);
	}
	
	public void addToMethods(String methodLine) {
		methods.add(methodLine);
	}
	
	public void incrementAttribute(int index) {
		Attribute var = variables.get(index);
		var.increaseUsage();
	}
	
	/*Displays all variables/methods that category holds
	 * 
	 */
	public String[] getMethods() {
		String[] methodStrings = new String[methods.size()];
		for(int i = 0; i < methods.size();i++) {
			methodStrings[i] = methods.get(i);
		}
		return methodStrings;
	}
	// printing stuff
	
	/*
	public void printVars() {
		for(Attribute var: variables) {
			System.out.println(var.getName());
		}
	}
	*/
	
	public void printMethods() {
		for(String method: methods) {
			System.out.println(method);
		}
	}
	
	// getters
	 
	public String getName() {
		return name;
	}
	
	/* variable name is saved in its entirety including visibility level etc
	 * This returns variable names to be searched. when returning names, the names
	 * are split to get the actual name of variable without types and visibility levels
	 */
	public String[] getVariables(){
		String[] varArray = new String[variables.size()];
		String[] arraySplit;
		int index = 0;
		for(Attribute var: variables) {
			arraySplit = var.getName().split(" ");
			if(arraySplit.length == 2) {
				varArray[index] = arraySplit[1].trim();
			}
			else if(arraySplit.length > 2 && arraySplit.length < 4) {
				varArray[index] = arraySplit[2].trim();
			}
			else if(arraySplit.length == 4) {
				varArray[index] = arraySplit[3].trim();
			}
			else {
				varArray[index] = arraySplit[0].trim();
			}
			index++;
		}
		return varArray;
	}
	
	public int  getUsage(int index) {
		for(int i = 0; i < variables.size();i++) {
			if(variables.get(index) != null) {
				return variables.get(index).getUsage();
			}
		}
		return 0;
	}

	public void getUsage2() {
		for(Attribute var: variables) {
			System.out.println(var.getName() + " usage: " + var.getUsage());
		}
	}

}
