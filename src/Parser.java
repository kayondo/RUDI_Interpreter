/**
 * 
 */

/**
 * @author Jeanne and Batanda
 *
 */
import java.io.*;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Parser {

	private String line;
	private BufferedReader reader;

	// stack to store brackets and parentheses
	private static Stack<Character> stack = new Stack<Character>();

	//variable object to keep the name, type and value of a variable
	Variable var = new Variable();

	//table to keep the declared variables
	HashMap<String, Variable> symbolTable = new HashMap<String, Variable>();


	//Evaluator object which has functions to evaluate the whole program
	Evaluator evaluator = new Evaluator();

	//conditionEvaluator to take a condition and return a boolean of that condition
	conditionEvaluator condition = new conditionEvaluator();


	public Parser()
	{
		//
	}


	/*
	 * checks if a function is blank
	 */
	private boolean isBlank(String line)
	{
		int strLen;
		if (line == null || (strLen = line.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(line.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * remove comments from a line
	 */
	private String removeComments(String currentLine)
	{
		if(line.contains("/*") & line.contains("*/"))
		{
			int g = line.indexOf("/*");
			line = line.substring(0, g);
		}
		return line;
	}


	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	/*
	 * function to get the string and return its infix
	 */
	private String getOperation(String infix) {

		String[] tempTokens = infix.split(" ");
		String  toReturnInfix = "";

		for(int i = 0; i<tempTokens.length; i++)
		{
			Variable variab =  symbolTable.get(tempTokens[i]);

			if(isNumeric(tempTokens[i]) | tempTokens[i].compareTo("+") == 0  | tempTokens[i].compareTo("*") == 0   | tempTokens[i].compareTo("/") == 0  | tempTokens[i].compareTo("-") == 0)
			{
				toReturnInfix=toReturnInfix+ " " + tempTokens[i];
			}

			else
			{

				if(variab!=null && variab.getVariableValue() != null)
				{
					String newValue = variab.getVariableValue();
					toReturnInfix = toReturnInfix+" " + newValue;
				}

				else
				{
					System.out.println("Invalid operation");
				}
			}
		}
		return toReturnInfix;
	}


	/*
	 * read the file containing the program
	 */
	public void readFile(File inputFile) 
	{

		FileReader file;
		String whilelogicalStatement = null;
		boolean returnedC = false;
		String iflogicalStatement = null;
		boolean returnedCondition = false;
		boolean returnedCWhile = false;

		try {
			file = new FileReader(inputFile);
			reader = new BufferedReader(file);

			int lineNumber = 0;          //keep track of the line number 

			String[] lineTokens;

			String previousLine = null;
			String currentLine = null;
			boolean prevLine = false;

			// to keep track of the decs section
			boolean decsFlag = false;
			// to keep track of the begin section
			boolean beginFlag = false;
			// to keep track of the print keyword
			boolean printFlag = false;
			// to look out for the end keyword
			boolean endFlag = false;

			boolean ifStatementFlag = false;
			boolean ifSection = false;
			Stack<Character> ifStack = new Stack<Character>();

			boolean whileFlag = false;
			boolean whileSection = false;
			Stack<Character> whileStack = new Stack<Character>();

			boolean elseFlag = false;
			boolean elseSection = false;
			Stack<Character> elseStack = new Stack<Character>();
			String logicalStatement = null;


			while((line = reader.readLine()) != null)
			{
				lineNumber ++;   // increment the line number
				line = line.trim();  // taking care of leading and trailing spaces on a line

				Line newLine = new Line(lineNumber, line);   //create a Line object
                
				System.out.println("*-->" + newLine.lineNumber + "  " + newLine.lineContents);
				
				

				/***
				 * 
				 * Handling if statement
				 */
				
				
				if(ifStatementFlag == true)
				{
		
					if(line.startsWith("["))
					{
						ifStack.push('[');
					}	
					
					if(line.startsWith("]"))
					{
						ifStack.pop();
						ifStatementFlag = false;
					}
					continue;
										
				}	
				
				if(elseFlag == true)
				{
					
					if(line.startsWith("["))
					{
					
						elseStack.push('[');
						//System.out.println("else flag...............");
						continue;
						
					}	
					
					if(line.startsWith("]"))
					{
						elseStack.pop();
						ifSection = false;             ///////
						elseSection = false;
						elseFlag = false;
						continue;
						
					}
					
				}
				
				
				if(ifSection == true)
				{
					if(elseFlag & !elseSection)
					{
						continue;
					}
//					else
//					{
//						if(newLine.getLineContents().startsWith("]"))
//						{
//							ifStatementFlag = false;
//						}
//					}
				}
				
				if(elseSection == true)
				{
					System.out.println(":::::::::::::::::::::IN ELSE::");
					if(ifStatementFlag & !ifSection)
					{
						continue;
					}
//					else
//					{
//						if(newLine.getLineContents().startsWith("]"))
//						{
//							elseFlag = false;
//						}
//					}
				}
				
				
				

//				if(ifSection){
//					if(elseFlag & !elseSection){
//						continue;
//					}
//				}
//
//				if(elseSection){
//					
//					System.out.println("#######################$$$$$$$$$$");
//					if(ifStatementFlag & !ifSection){
//						continue;
//					}
//				}
				
				
//				 if(!ifStack.isEmpty() && ifSection == false)
//			        {
//			         /* meeting closing parenthesis that closes if section*/
//			         if(newLine.getLineContents().trim().startsWith("]"))
//			         {
//			          ifStack.pop();
//			          ifStatementFlag = false;
//			         }
//			         continue;
//			        }
//			      
//			      
//			      /* skip processing this line if else statement's block must not be executed*/
//			        if(!elseStack.isEmpty() && elseSection == false)
//			        {
//			         if(newLine.getLineContents().trim().startsWith("]"))
//			         {
//			          elseStack.pop();
//			          elseFlag = false;
//			         }
//			         continue;
//			        }
//				
//				
//				
//
//				if(ifStatementFlag){
//
//					if(line.startsWith("[")){
//						ifStack.push('[');
//						continue;
//					}	
//
//					if(line.startsWith("]")){
//						ifStack.pop();
//						ifStatementFlag = false;
//						continue;
//
//					}
//
//				}	
//
//				if(elseFlag)
//				{
//					if(line.startsWith("["))
//					{
//
//						elseStack.push('[');
//						continue;
//					}	
//
//					if(line.startsWith("]"))
//					{
//						elseStack.pop();
//						ifSection = false;
//						elseSection = false;
//						elseFlag = false;
//						continue;
//
//					}
//				}

				/*****
				 * 
				 * Dealing with this while 
				 * 
				 */

				
				if(whileFlag)
				{
					
					// update status on the returnedC
					
					returnedCWhile = conditionEvaluator.conditionEvaluator(whilelogicalStatement, symbolTable);
					// check if the logical statement returns true or false
					
					if(returnedCWhile)
					{
						whileSection = true;
//						reader.mark(1000);
					}
					else
					{
						//System.out.println("IT isnt true");
						if(!line.startsWith("]"))
						{
							continue;
						}
						
					}
					
					if(whileSection)
					{
						//returnedC = conditionEvaluator.conditionEvaluator(whilelogicalStatement, symbolTable);
						//System.out.println("??????? NTUKKA");
//						reader.mark(1000);
						
						if(line.startsWith("["))
						{
							reader.mark(1000);
							//System.out.println("kikola...............");
							continue;
						}	
						
						if(line.startsWith("]"))
						{
//							reader.reset();
							// check if the logical statement returns true or false
							returnedCWhile = conditionEvaluator.conditionEvaluator(whilelogicalStatement, symbolTable);
							
							if(returnedCWhile)
							{
								reader.reset();
								
							}
							else
							{
								whileSection = false;
								continue;
							}
							// if true, reset 
							
							
						// else set whileSection to false
							
						}
						
					}
					else
					{
						continue;
					}
					// if true, set whileSection to true, else false
										
				}	
				
				
				
				
//				if(whileFlag)
//				{
//
//					returnedC = conditionEvaluator.conditionEvaluator(whilelogicalStatement, symbolTable);
//
//					if(returnedC)
//					{
//						whileSection = true;
//					}
//					else
//					{
//						if(!line.startsWith("]"))
//						{
//							continue;
//						}
//
//					}
//
//					if(whileSection)
//					{
//						if(line.startsWith("["))
//						{
//							reader.mark(1000);
//							continue;
//						}	
//
//						if(line.startsWith("]"))
//						{
//							// check if the logical statement returns true or false
//							if(returnedC)
//							{
//								reader.reset();
//							}
//							else
//							{
//								whileSection = false;
//								continue;
//							}
//						}	
//					}				
//				}	

				/* 
				 * if the line is blank go to the next line
				 */

				if(isBlank(newLine.getLineContents()))
				{
					continue;
				}

				/* if the line is having comments go to the next line 
				 * or only remove the comments if there is something else
				 */

				else if(newLine.getLineContents().contains("/*") | newLine.getLineContents().contains("*/"))
				{
					String commentsRemovedString = removeComments(newLine.getLineContents().trim());

					if(isBlank(commentsRemovedString))
					{
						continue;
					}
					else
					{
						String newL = commentsRemovedString.trim();
						newLine = new Line(lineNumber, newL);   
					}
				}

				
				/**
			     * Line continuation
			     */
			    
			    if(newLine.getLineContents().endsWith("&"))
			    {
			     previousLine = newLine.getLineContents();
			     previousLine = previousLine.substring(0, previousLine.indexOf('&'));
			     prevLine = true;
			     System.out.println("?>>>>"+previousLine);
			     continue;
			    }
			    
			        
			    if(prevLine)
			    {
			     currentLine = newLine.getLineContents();
			     
			     newLine.setLineContents(previousLine.concat(currentLine)); 
			     
			     System.out.println("::LINE CONT::" + newLine.getLineNumber() + "::LINE::" + newLine.getLineContents());
			     
			     prevLine = false;
			    }
			    
				/*
				 *******************************************************************
				 *  Handling the print keyword
				 *******************************************************************
				 */

				if(newLine.getLineContents().startsWith("print"))
				{
					/* before check if the keyword is in the list of keywords
					 * return an error if it is not
					 */

					//SYNTAX ERROR
					//count the occurence of quotes in a string
					int quotesCounter = 0;
					for( int i=0; i<newLine.lineContents.length(); i++ ) {
						if(newLine.lineContents.charAt(i) == '\"' ) {
							quotesCounter++;
						} 
					}

					if(quotesCounter == 1 | quotesCounter > 2)
					{
						System.out.println("::ERROR:: Please check the quotes syntax on"+ lineNumber + ":   " + newLine.getLineContents());
						//System.exit(0);
					}

					//tokenize it and call print() to print
					if(newLine.getLineContents().contains("\""))
					{

						int l = newLine.getLineContents().indexOf("\"");
						int j = newLine.getLineContents().lastIndexOf("\"");
						String line1 = newLine.getLineContents().substring(0, l-1);
						String line2 = newLine.getLineContents().substring(l+1, j);

						/* ******** CALL PRINT FUNCTION ************ */
						evaluator.print(line2);

					}
					else 
					{
						String[] line2 = newLine.getLineContents().split(" ");
						Variable outputVariable =  symbolTable.get(line2[1]);

						/* ******** CALL PRINT FUNCTION ************ */
						//get the value from hash map
						evaluator.print(outputVariable.getVariableValue());

					}	
				}

				/*
				 * ********************************************************************
				 *  Handling the input keyword                                         *
				 * ********************************************************************
				 */

				else if(newLine.getLineContents().startsWith("input"))
				{
					Variable inputVariable = new Variable();

					//split 
					String[] inputTokens = newLine.getLineContents().split(" ");
					String varName = inputTokens[1];    //get the name of the variable to get the input

					/* *********Get the variable from the hashmap ********** */

					//get its Variable (this one has variableName, variableType and variableValue)
					inputVariable =  symbolTable.get(varName);

					/* call input function to get the input the Variable itself
					 * arguments are that varName you want to update and the 
					 */
					evaluator.input(varName, inputVariable);

				}

				/*
				 * ********************************************************************
				 *  Handling the decs keyword                                         *
				 * ********************************************************************
				 */

				else if(newLine.getLineContents().startsWith("decs"))
				{
					decsFlag = true;
					// check if the first [ is on the same line
					if(newLine.getLineContents().contains("["))
					{
						stack.push('[');
					}

					continue;
				}

				if(decsFlag==true)
				{

					if(newLine.getLineContents().equals("["))
					{
						decsFlag = true;
						stack.push('[');
						continue;
					}

					if(!newLine.getLineContents().equals("]"))
					{
						// checking if an opening parenthesis has been found
						if(stack.isEmpty())
						{
							System.out.println("::ERROR:: Opening bracket expected [ on line "+lineNumber + ":  " + newLine.getLineContents());
							//System.exit(0);
						}

						// tokenize the line
						lineTokens = newLine.getLineContents().split(" ");

						if(lineTokens.length==2)
						{
							var = new Variable(lineTokens[1], lineTokens[0], "");
							symbolTable.put(var.getVariableName(), var);
						}

						//checking for the closing bracket
						if(newLine.getLineContents().equals("begin"))
						{
							System.out.println("::ERROR:: A CLOSING BRACKET ']' EXPECTED on line "+lineNumber + ":  "+ newLine.getLineContents());
							//System.exit(0);

						}
					}


					if(newLine.getLineContents().equals("]"))
					{
						decsFlag= false;
						try{
							stack.pop();
						}
						catch (EmptyStackException e)
						{
							//
						}

						continue;
					}

				}   // end of decs is true section


				/* *************************************************************
				 *  Handling the begin keyword
				 * *************************************************************
				 */

				// if the line starts with while
				if(newLine.getLineContents().startsWith("begin"))
				{
					beginFlag = true;
					//previousLine = newLine.getLineContents();
					continue;
				}

				// looking at lines inside the begin segment

				if(beginFlag==true && !newLine.getLineContents().equals("end"))
				{
					// handling conditional statements

					/*
					 * On both cases, if and while, they will always have a parenthesis on the same line as the if & while
					 * So handling these in order to extract the logical statement
					 * This logical statement is the one used to see if executing the loop is necessary or not
					 * 
					 */

					if(newLine.getLineContents().startsWith("if"))
					{
						ifStatementFlag = true;
						if(newLine.getLineContents().contains("(") && newLine.getLineContents().contains(")"))
						{
							int openParenthesis = newLine.getLineContents().indexOf('(');
							int closingParenthesis = newLine.getLineContents().indexOf(')');

							iflogicalStatement = newLine.getLineContents().substring(openParenthesis, closingParenthesis+1);
							//System.out.println(":::STATEMENT:::"+iflogicalStatement);


							/* 
							 * call condition function to return a boolean whether the condition is true or false
							 */
							returnedCondition = condition.conditionEvaluator(iflogicalStatement, symbolTable);
							
							if(returnedCondition)
							{
								ifSection = true;
								elseSection = false;
							}
							else
							{
								ifSection = false;
								elseSection = true;
							}
						}
						else
						{
							System.out.println(":::ERROR:: Invalid syntax. Expected parenthesis on line " + lineNumber + ":   " + newLine.getLineContents());
							//System.exit(0);
						}

						continue;
					}

					if(newLine.getLineContents().startsWith("else"))
					{
						elseFlag = true;
						continue;
					}


					if(newLine.getLineContents().startsWith("while"))
					{
						whileFlag = true;
						if(newLine.getLineContents().contains("(") && newLine.getLineContents().contains(")"))
						{
							int openParenthesis = newLine.getLineContents().indexOf('(');
							int closingParenthesis = newLine.getLineContents().indexOf(')');

							whilelogicalStatement = newLine.getLineContents().substring(openParenthesis, closingParenthesis+1);

							/* 
							 * call condition function to return a boolean whether the condition is true or false
							 */
							returnedCWhile = conditionEvaluator.conditionEvaluator(whilelogicalStatement, symbolTable);
						}
						else
						{
							System.out.println("::ERROR:: Invalid syntax. Expected parenthesis on line " + lineNumber + ":   " + newLine.getLineContents());
							//System.exit(0);

						}

						continue;
					}

					/*
					 * Handling assignment statements under the logical loops
					 */

					// handling the assignment statements in the while loop
					if(newLine.getLineContents().contains("=") & !newLine.getLineContents().startsWith("print"))
					{
						// split by the equals sign
						lineTokens = newLine.getLineContents().split("=");

						if(lineTokens.length == 2)
						{
							//get the second token
							String temp = lineTokens[1];
							temp = temp.trim();	


							/*
							 * get the corresponding infix from an operation and send it to the calculator
							 * */
							String infix = getOperation(temp);

							Double returnedFromCalculator = evaluator.calculator(infix);
							//String newreturnedFromCalculator = "" + returnedFromCalculator;

							//get the variableValue of the token you have and get its value and change it
							String updateVariable = lineTokens[0].trim();

							/*
							 * convert the returned value from Calculator to the correct type
							 */
							String type = symbolTable.get(updateVariable).getVariableType();
							Variable vv = symbolTable.get(updateVariable);

							Integer newInteger = null;
							Float newFloat = null;
							String d = null ;

							if(type.matches("integer"))
							{
								newInteger = returnedFromCalculator.intValue();
								d = ""+newInteger; 
							}

							else if(type.matches("float"))
							{
								newFloat = returnedFromCalculator.floatValue();	
								d = ""+ newFloat; 
							}
							else if(type.matches("double"))
							{
								d = "" + returnedFromCalculator;
							}
							else
							{
								System.out.println("::ERROR:: Invalid data type " + lineNumber + ":   " + newLine.getLineContents());
								//System.exit(0);
							}
							vv.setVariableValue(d);
						}

						continue;

					} 

				}  // end of when begin is true and not ']'

				// end of the while loop
				if(beginFlag == true && newLine.getLineContents().equals("end"))
				{
					beginFlag = false;
				}  // end of while loop and found the ending ']'

//
//				//displaying contents of the hash map
//				System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::");
//				System.out.println("CONTENTS OF THE HASH TABLE");
//				System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::");
//
//
//				for (Map.Entry<String, Variable> entry : symbolTable.entrySet()) {
//					System.out.println(entry.getKey()+" ::::::::::: "+entry.getValue().toPrint());
//				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Line setLineContents(String newL) {
		return null;
	}
}



