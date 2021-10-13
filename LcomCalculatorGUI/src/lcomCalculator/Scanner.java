package lcomCalculator;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Scanner {
	
	/*
	 * 
	 */
	public String[]  scanFolder(String location) {
		File newFolder = new File(location);
		
		File[] files;
		ArrayList<Category> detectedClasses = new ArrayList<Category>();

		files = newFolder.listFiles();

		// creates and adds class object to arraylist of classes
		for (File file : files) {
		
			File foundFile = new File(file.getAbsoluteFile().toString());
			Category detectedClass = new Category(foundFile.getName());
			detectedClass = Scanning.scan(readFile2String(foundFile.getAbsolutePath()), detectedClass);
			detectedClasses.add(detectedClass);
		}

		String[] calculatedClasses = new String[detectedClasses.size()];
	
		
		for(int i = 0; i < detectedClasses.size();i++){
			calculatedClasses[i] = calculateLcom(detectedClasses.get(i),0.8);
		}
		System.out.println( calculatedClasses.length);
		for(String i: calculatedClasses) {
			System.out.print(i);
		}
		return calculatedClasses;
	}

	private  static String readFile2String(String fname) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(fname)));
		} catch (IOException e) {
			System.out.println("Fail to read a file");
		}
		return content;
	}

	public String calculateLcom(Category cat, double cutOffValue) {
		String returnString = "";
		String[] attributes = cat.getVariables();
		int[] attributeUsage = new int[attributes.length];
		int sumUsage = 0;
		double totalLcom = 0;

		for (int i = 0; i < attributes.length; i++) {
			attributeUsage[i] = cat.getUsage(i);
		}
		for (int i = 0; i < attributeUsage.length; i++) {
			sumUsage += attributeUsage[i];
		}
		returnString += "\n" + cat.getName();
		returnString += "\ntotal variables: " + attributes.length;
		returnString += "\ntotal methods: " + cat.getMethods().length;
		totalLcom = 1 - ((double) sumUsage / (cat.getMethods().length * attributes.length));
		returnString += String.format("\nTotal Lcom: " + "%.2f \n", totalLcom);
		if(cutOffValue > 0) {
			returnString +="\nCutoff value: " + cutOffValue;
			
			if (totalLcom < cutOffValue) {
				returnString += "\nClass meets lcom requirements\n";
				
			} else {
				returnString += "\nClass doesn't meets lcom requirements\n";
			}
		}
		
		return returnString;
	}
}
