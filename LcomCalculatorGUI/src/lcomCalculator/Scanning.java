package lcomCalculator;

import java.util.ArrayList;

final class Scanning {

	/*Notes: cannot ignore multi line comments without acting like a bitch atm
	 * Also cannot detect variables if they are inside brackets
	 */

	public static Category scan(String content, Category entity) {
		ArrayList<String> incrementedVars = new ArrayList<>();
		int index = 0;
		char ch = content.charAt(index);
		int contentLength = content.length();
		String line = "";
		boolean hasVisibility = false;//recognises method/variable if it has visibility
		// used for saving lines of strings as methods/ variables
	
		int lineNumber = 1;
		int startLine = lineNumber;
		
		String[] variables = null;
				
		do {// increases index to find first line with code
			startLine++;
			index++;
		} while (content.charAt(index) == '\n');
		index = 0;
	
		// ignores packages/imports
		
		while (index < content.length()) {
			ch = content.charAt(index);
			boolean alphabet = isLetter(ch);
			boolean space = isWhiteSpace(ch);
			boolean newline = isLineBreak(ch);
			TokenType sym = getSymbol(ch);

			if (space) {
				index++;
				continue;
			}
			
			//Adds line to class as variable if it is not "\n" or is not a method
			else if (newline) {
				if(!line.contains("(") && lineNumber > startLine && line.length() > 1 && !line.contains("class") && !line.contains("for")
						&& !line.contains("class")) { 
					//checks if line contains letters
					entity.addToVariables(line);
					
				}
				if (entity != null && hasVisibility && lineNumber != startLine && (line.contains("()") || 
				line.contains("(") && line.contains(")") )  ) {
					entity.addToMethods(line);
					incrementedVars.clear();
				}		
				lineNumber++;
				hasVisibility = false;
				index++;
				line = "";
				continue;
			}

			else if (ch == '/' && content.charAt(index + 1) == '/') {// checks for single line comment, ignores it
				index += 2;
				ch = content.charAt(index);
				while (content.charAt(index+1) != '\n') {
					index++;
					ch = content.charAt(index);
				}
				continue;
			}

			//comment this out if you want accurate lineNumbers, else uncomment it so it skips stuff lmao
			
			else if (ch == '/' && content.charAt(index + 1) == '*') {// checks for multiple line comment, ignores it
				index += 2;
				ch = content.charAt(index);
				while (ch != '/') {
					
					index++;
					ch = content.charAt(index);
					if (ch == '/') {
						index++;
						ch = content.charAt(index);
						break;
					} 
					
					else if(ch == '\n') {
						lineNumber++;
						index++;
						ch = content.charAt(index);
					}
					
					else {
						index++;
						ch = content.charAt(index);
					}
				}
				
				index++;
				continue;
			}
			

			else if (alphabet) {
				TokenType checkWord;
				String word = "";// if ch is alphabet, adds char to word string
				word += ch;
				index++;
				while (index < contentLength) {
					ch = content.charAt(index);
					alphabet = isLetter(ch);
					sym = getSymbol(ch);
					if (alphabet) {
						word += ch;
						index++;
					} 
					/*
					else if (sym != null) {
						if(ch == '.' || ch == ',') {
							break;
						}
						else {
							word += ch;
							index++;	
									
						}
					}
					*/
					
					else if (sym != null) {

						word += ch;
						index++;

					}
					
					else {
						break;
					}
				}
				checkWord = getKeyword(word);// checks if word is keyword then adds to line
				if (checkWord != null && (checkWord == TokenType.KEYWORD_PRIVATE || checkWord == TokenType.KEYWORD_PUBLIC)) {
					hasVisibility = true;
					line += word;
					continue;
				} 
				
			
				
				else {
					/*The word class is also added so it can be recognised in a line and not be 
					 * saved as a variable
					 */
					if ((hasVisibility && getKeyword(word) == null) || word.compareTo("class") == 0) {
						line+= " ";
						word = word.trim();
						line += word; 
					}
					if(entity != null && word.compareTo("return") != 0 ) {
						variables = entity.getVariables();
						for(int i = 0; i < variables.length; i++) {
							if(word.compareTo(variables[i]) == 0 || word.contains(variables[i])) {
								
								/* detectedVariable is incremented if word and the variable are the same
								 * the detectedVariable is then added to arrayList of strings to show it was already incremented
								 * the Attribute object of the detected variable if the 
								 */
								
								if(!isInCollection(variables[i],incrementedVars)) {
									incrementedVars.add(variables[i]);
									//System.out.println(word + " Matched at: " + lineNumber);
									entity.incrementAttribute(i);
									break;
								}
							}
							
						}
						variables = null;
					}
				}
			}
			else {
				index++;
				continue;
			}
		}
		return entity;
	}
	
	/*
	 * Content checkers
	 */
	public static boolean isLetter(char ch) {
		return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
	}

	public static boolean isWhiteSpace(char ch) {
		return (ch == ' ' || ch == '\t');
	}

	public static boolean isLineBreak(char ch) {
		return (ch == '\n');
	}

	public static TokenType getKeyword(String s) {
		TokenType TokenKey = null;
		switch (s) {
		case "if":
			TokenKey = TokenType.KEYWORD_IF;
			break;
		case "else":
			TokenKey = TokenType.KEYWORD_ELSE;
			break;
		case "int":
			TokenKey = TokenType.KEYWORD_INT;
			break;
		case "String":
			TokenKey = TokenType.KEYWORD_STRING;
			break;
		case "public":
			TokenKey = TokenType.KEYWORD_PUBLIC;
			break;
		case "private":
			TokenKey = TokenType.KEYWORD_PRIVATE;
			break;
		case "protected":
			TokenKey = TokenType.KEYWORD_PROTECTED;
			break;
		case "class":
			TokenKey = TokenType.KEYWORD_CLASS;
			break;
		case "void":
			TokenKey = TokenType.KEYWORD_VOID;
			break;
		case "static":
			TokenKey = TokenType.KEYWORD_STATIC;
			break;
		case "for":
			TokenKey = TokenType.KEYWORD_FOR;
			break;
		case "abstract":
			TokenKey = TokenType.KEYWORD_ABSTRACT;
			break;
		case "interface":
			TokenKey = TokenType.KEYWORD_INTERFACE;
			break;
		case "new":
			TokenKey = TokenType.KEYWORD_NEW;
			break;
		case "null":
			TokenKey = TokenType.KEYWORD_NULL;
			break;
		}
		return TokenKey;
	}

	public static TokenType getSymbol(char ch) {
		TokenType TokenSym = null;
		switch (ch) {
		case '(':
			TokenSym = TokenType.LEFT_PAREN;
			break;
		case ')':
			TokenSym = TokenType.RIGHT_PAREN;
			break;
		case '[':
			TokenSym = TokenType.LEFT_BRACKET;
			break;
		case ']':
			TokenSym = TokenType.RIGHT_BRACKET;
			break;
		case ',':
			TokenSym = TokenType.COMMA;
			break;
		case '=':
			TokenSym = TokenType.EQUALS;
			break;
		case '.':
			TokenSym = TokenType.FULL_STOP;
			break;
		}
		return TokenSym;
	}
	
	public static boolean isInCollection(String word, ArrayList<String> collection) {
		
		for(String i : collection) {
			if(i.compareTo(word) == 0) {
				return true;
			}
		}
		return false;
	}
}
