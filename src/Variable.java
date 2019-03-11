public class Variable 
{

	String variableName;
	String variableType;
	String variableValue;
	
	public Variable(String variablename, String variableType, String variableValue)
	{
		this.variableName = variablename;
		this.variableType = variableType;
		this.variableValue = variableValue;
	}
	
	public Variable()
	{
		
	}
	
	public Variable(Object value) {
		// TODO Auto-generated constructor stub
	}

	public void setVariable(String variableName, String variableType, String variableValue) {
		this.variableName = variableName;
		this.variableType = variableType;
		this.variableValue = variableValue;
		
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public String toPrint()
	 {
	  return variableName+" "+variableType+ " "+ variableValue;
	 }
	public String toString()
	 {
	  return variableName+" "+variableType+ " "+ variableValue;
	 }
	
	
}
