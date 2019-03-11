import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;




public class conditionEvaluator
{
	public static String booleanOperation(String operand1, String operand2, String operator){
		String result = "";
		boolean op1=false,op2=false;
		
		if(operand1.equals("false"))
			op1 = false;
		if(operand1.equals("true"))
			op1 = true;
		if(operand2.equals("false"))
			op2 = false;
		if(operand2.equals("true"))
			op2 = true;
	
		if(operator.equals("|")){
			//applying OR operator
			result = ""+(op1 || op2);
			System.out.println("result from boolean operator function: "+result);
		}else if(operator.equals("^")){
			//applying AND operator
			result = ""+(op1 && op2);
			System.out.println("result from boolean operator function: "+result);
			
		}else if(operator.equals("~")){
			//applying NOT operator
			result = ""+(!(op1) || (!op2));//TODO:handling NOT operator after
			System.out.println("result from boolean operator function: "+result);
		}

		return result;
	}

	public static boolean isBooleanOperator(String s){
		if(s.equals("^") || s.equals("|") || s.equals("~" )){
			return true;
		}else{
			return false;
		}
	}

	
	//TODO: compare letters and numbers!! is it true???!! what to do?
	public static String comparisonOperation(String operand1, String operand2, String oper){
		String result = "";
		String operator = oper.trim();
		if(operator.equals(":eq:")){
			//operand1 is equal to operand2
			result = ""+operand1.equals(operand2);
			
		}else if(operator.equals(":ne:")){
			//operand1 is not equal to operand2
			result= ""+!operand1.equals(operand2);
			
		}else if(operator.equals(":gt:")){
			//operand1 is greater than operand2
			int comp = operand1.compareTo(operand2);
			result = ""+ (comp > 0);

		}else if(operator.equals(":lt:")){
			//operand1 is less than operand2
			int comp = operand1.compareTo(operand2);
			result = ""+ (comp < 0);
			
		}else if(operator.equals(":ge:")){
			//operand1 is greater than or equal to operand2
			int comp = operand1.compareTo(operand2);
			result = ""+ ((comp > 0) || operand1.equals(operand2));
			
		}else if(operator.equals(":le:")){
			//operand1 is less than or equal to operand2
			int comp = operand1.compareTo(operand2);
			result = ""+ ((comp < 0) || operand1.equals(operand2));
		}
		else{
			//unknown comparison operator
			result= "";
		}
		
		return result;
	}

	
	public static Variable findVariable(String variableName, Map<String, Variable> variables){
		//Variable class instance to store variable once checked in variables list
		Variable var = variables.get(variableName);
		
		if(var != null){
			//variable found
			return var;
		}
		else{
			//variable not found
			//System.out.println("variable not found");
			return null;
		}	//TODO: have to think of implication if the value returned is null (to be done in the functions that call this one)
		
	}//end evaluate variable function

	public static boolean isComparisonOperator(String s){
		if(s.equals(":eq:")  || s.equals(":ne:") || s.equals(":gt:") || s.equals(":lt:") || s.equals(":ge:") || s.equals(":le:") ){
			return true;
		}else{
			return false;
		}
	}

	public static boolean conditionEvaluator(String condition, Map<String, Variable> variables ){

		Stack<String> operandStack = new Stack<String>();
		Stack<String> responseStack = new Stack<String>();
		Stack<String> parenthesisStack = new Stack<String>();
		boolean returnValue = false;
      

		String result="";

		//splitting condition
		StringTokenizer conditionTokens = new StringTokenizer(condition," ");

		//looping through the condition tokens
		while(conditionTokens.hasMoreTokens()){
			//there are still tokens

			String token = conditionTokens.nextToken();

			if(token.equals("(")){
				parenthesisStack.push(token);
			}
			else if(token.equals(")")){
				if(parenthesisStack.size()!=0){
					parenthesisStack.pop();


					while(operandStack.size() == 3){
						String operand2 = operandStack.pop(); //second operand
						String operator = operandStack.pop(); //operator
						String operand1 = operandStack.pop(); //first operand

						if(isComparisonOperator(operator)){
							//the operator is comparison one
							

							//check if the operands are variables
							Variable varReturned = findVariable(operand1, variables); //TODO: if necessary, first check if it is not null before assigning it to a variable
							Variable varReturned2 = findVariable(operand2, variables);

							//System.out.println(" var returned: "+varReturned);
							if(varReturned!= null){
								//the first operand is a variable

								String variableType = varReturned.getVariableType();
								String value = varReturned.getVariableValue();


								operand1 = value; //assigning the variable value to the operand1

							}
							if(varReturned2 != null){
								//the second operand is a variable
								String value = varReturned2.getVariableValue();

								operand2 = value; //assigning the variable value to the operand2

								//check if the variable data type and value match, by converting to the proper type
								//varTypeConverted(variableType,value); //TODO:deal with it later if necessary
							}


							//call function to evaluate the comparison operation
							result = comparisonOperation(operand1, operand2, operator);//TODO: why making this string and not boolean??
							responseStack.push(result);

						}
						else{
							//it must be an operator
							System.out.println("invalid condition!");
							System.exit(0);
						}

					}

				}
				else{
					System.out.println("opening and closing parenthesis are not matching");
					System.exit(0);
				}
			}
			else if(isBooleanOperator(token)){
				//the token is a boolean operator
				responseStack.push(token);			
			}
			else{

				operandStack.push(token); //push to operand stack
			}
		}

		//	while(responseStack.size() !=0){
		//		System.out.println(responseStack.pop());
		//	}

		while(responseStack.size() >= 3){
			String op2 = responseStack.pop();
			String oper = responseStack.pop();
			String op1 = responseStack.pop();

			result = booleanOperation(op1, op2, oper);//TODO: why making this string and not boolean??

			responseStack.push(result);


		}
		if(responseStack.size() == 1){
			result = responseStack.pop();
			//System.out.println("result after applying the boolean operator: "+result);
		}
		else{
			System.out.println("response stack must have one element at this stage");
		}


		if(parenthesisStack.size() != 0 || operandStack.size() != 0 || responseStack.size() != 0){
			System.out.println("invalid condition");
			System.exit(0);
		}


		if(result.equals("false")){
			//the comparison result is false
			//return false;
			returnValue=false;
		}else if(result.equals("true")){
			//the comparison result is true
			//return true;
			returnValue=true;
		}
		return returnValue;

	} //end condition evaluator function

}