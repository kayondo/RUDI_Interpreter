import java.io.IOException;
import java.util.Stack;

//import infixpostfix.InToPost;


public class InfixToPostfix {
	//private Stack theStack;
	private String input;
	private String output = "";

	/*
	 * stack storing the characters of an operation
	 */
	Stack<Character> charStack = new Stack<Character>();

	/*
	 * function to get an operation and perform the results
	 */
	public InfixToPostfix(String in) 
	{
		this.input = in;
	}

	/* get the characters of the operation and ------------
	 * only the paranthesis will be pushed to the stack
	 * the operations will be pulled by the order of precedence
	 */
	public String toPostfix() 
	{
		//System.out.println("-------------------GOT THERE KABISA");
		for (int j = 0; j < input.length(); j++) 
		{
			char ch = input.charAt(j);

			if(ch == '+' | ch == '-')
			{
				precedence(ch, 1); 
			}
			else if(ch == '*' | ch == '/')
			{
				precedence(ch, 2); 
			}

			else if(ch == '(')
			{
				charStack.push(ch);
			}
			else if(ch == ')')
			{
				gotParenthesis(ch);
			}
			else
			{
				output = output + ch ; 
			}

		}

		while (!charStack.isEmpty()) {
			output = output + charStack.pop();
		}

		//System.out.println("This is the output: " + output);
		return output; 
	}

	public void precedence(char opThis, int prec1) {
		while (!charStack.isEmpty()) {

			//get the value from the stack
			char opTop = (char) charStack.pop();

			if (opTop == '(') {
				charStack.push(opTop);
				break;
			}
			else 
			{
				int prec2;
				if (opTop == '+' || opTop == '-')
					prec2 = 1;
				else
					prec2 = 2;
				if (prec2 < prec1) { 
					charStack.push(opTop);
					break;
				}
				else
					output = output + opTop  ;   
			}
		}
		charStack.push(opThis);
	}

	/*
	 * do something when got a parenthesis
	 */
	public void gotParenthesis(char ch){ 
		while (!charStack.isEmpty()) {
			char chx = (char) charStack.pop();
			if (chx == '(') 
				break; 
			else
				output = output + chx; 
		}
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

	private static Integer pop(Stack numbers2) {
		// TODO Auto-generated method stub
		return null;
	} 


}