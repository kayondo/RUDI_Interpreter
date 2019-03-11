
public class Line 
{
	int lineNumber;
	String lineContents;

	public Line()
	{
		//
	}
	public Line(int lineNumber, String lineContents )
	{
		this.lineNumber = lineNumber;
		this.lineContents = lineContents;
	}
	
	public int getLineNumber()
	{
		return lineNumber;
	}
	
	public String getLineContents()
	{
		return lineContents;
	}
	
	public void setLineNumber(int lineNumber)
	{
		this.lineNumber = lineNumber;
	}
	
	public void setLineContents(String lineContents)
	{
		this.lineContents = lineContents;
	}
	
	@Override
	public String toString() {
		return "Line [lineNumber=" + lineNumber + ", lineContents=" + lineContents + "]";
	}
	
}
