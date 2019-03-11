import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Jeanne and Batanda
 *
 */
public class Evaluator {

	private Scanner in;
	public void print(String outputString)
	{
		System.out.println(outputString);
	}

	public void input(String varName, Variable varToGetInput)
	{
		//get the input from the user
		in = new Scanner(System.in);
		varName = in.nextLine();
		varToGetInput.setVariableValue(varName);
	}

	public Double calculator (String input)
	{
		String output; 
		
		InfixToPostfix theTrans = new InfixToPostfix(input);
		
		output = theTrans.toPostfix(); 
		/*
		 * separate numbers and operators in a postfix and save it in an array
		 */ 

		String myString= output;
		String[] result = myString.split("(?<=[-+*/])|(?=[-+*/])");

		String postfix = "";
		for (int i=0; i<result.length;i++)
		{
			if(result[i].length() > 1)
			{
				String[] newResult = result[i].split(" ");

				for (int j=0;j<newResult.length;j++)
				{
					postfix += newResult[j] + " ";
				}
			}
			else
			{
				postfix += result[i] + " ";
			}
		}
		/*
		 * postfix 
		 */
	
		String[] postfixTokens = postfix.split(" ");	
		Stack<Double> numbers = new Stack <Double>();
		int zeroDivision = 0;


		for (int i=0; i<postfixTokens.length;i++)
		{
			if(!postfixTokens[i].isEmpty())
			{
				/*
				 * check if the the string is a numeric or not
				 */

				if(theTrans.isNumeric(postfixTokens[i]))   //is numeric
				{
					
					String newNumeric = ""+postfixTokens[i];
					numbers.push(Double.parseDouble(newNumeric));

				}
				else                           //is not numeric
				{

					String operator = postfixTokens[i];
					char operatorChar = operator.charAt(0);

					try{
						Double item1 = numbers.pop();
						Double item2 = numbers.pop();

						Double resultFromOperation;

						/*
						 * do the operations
						 */

						if(operatorChar == '+')
						{
							resultFromOperation = item2 +  item1;
							numbers.push(resultFromOperation);
						}
						else if(operatorChar == '*')
						{
							resultFromOperation = item2 *  item1;
							numbers.push(resultFromOperation);
						}
						else if(operatorChar == '-')
						{
							resultFromOperation = item2 -  item1;
							
							numbers.push(resultFromOperation);
						}
						else if(operatorChar == '/' && item1 == 0)
						{
							zeroDivision = 1;
							break;
						}
						else if(operatorChar == '/')
						{
							resultFromOperation = item2 /  item1;
				
							numbers.push(resultFromOperation);
						}
						else                //is not 
						{
							System.out.println("Invalid operator");
						}

					}
					catch (EmptyStackException e)
					{
						System.out.println(e);
					}

				}

			}
		}

		/*
		 * divide by zero error
		 */
		if(zeroDivision == 1)
		{
			System.out.println("Zero division error, enter the postfix equation, again \n");
		}
		/*
		 * invalid postfix operation
		 */

		if(numbers.size() > 1)
		{
			System.out.println("Invalid operation \n");
		}

		/*
		 * return the result from an operation to the user
		 */
		
		Double finalResult = numbers.pop();
		return finalResult;

	}



}
