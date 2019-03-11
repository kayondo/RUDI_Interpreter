//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * 
// */
//
///**
// * @author Jeanne and Batanda
// *
// */
//public class Tokenize {
//	
//	//Evaluator object
//	Evaluator evaluator = new Evaluator();
//
//	public Tokenize() {
//		// TODO Auto-generated constructor stub
//	}
//	
//
//	//method that takes a line and call an evaluator corresponding to that line
//	public void getLines(String lines)
//	{
//		//receive the queue of lines from mainRUDI class
//		Queue queue = new LinkedList(linesQueue);
//		String dequeuedLine;
//		
//		int lineNumber = 0;            //get the line number 
//	
//		//loop through the elements of a queue and dequeue one by one
//		while (queue.size() > 0) {
//				
//			//System.out.println("<<<<<"+queue.element());
//			
//			dequeuedLine = (String) queue.element();          //get the queue element which is a line
//			
//			Line line = new Line(lineNumber, dequeuedLine);   //create a Line object
//		
//			
//			//check the line keyword which is the starting one and call its corresponding evaluator
//			
//			/* **************** PRINT KEYWORD ********************* */
//			
//			if(line.getLineContents().startsWith("print"))
//			{
//				System.out.println("That element is this one --- ");
//				
//				//SYNTAX ERROR
//				//count the occurence of quotes in a string
//				int quotesCounter = 0;
//				for( int i=0; i<line.lineContents.length(); i++ ) {
//				    if(line.lineContents.charAt(i) == '\"' ) {
//				        quotesCounter++;
//				    } 
//				}
//				System.out.println("quotes --- " + quotesCounter);
//				
//				if(quotesCounter == 1 | quotesCounter > 2)
//				{
//					System.err.println("Error at line "+ lineNumber + ", " + "Please check the quotes syntax");
//					break;
//				}
//
//				
//				//tokenize it and call print() to print
//				if(line.getLineContents().contains("\", \""))
//			    {
//			     int l = line.getLineContents().indexOf("\"");
//			     int j = line.getLineContents().lastIndexOf("\"");
//			     
//			     //check if the line do not contain quotes, one or both
//			     String line1 = line.getLineContents().substring(0, l-1);
//			     System.out.println("first part = "+ line1);
//			     String line2 = line.getLineContents().substring(l+1, j);
//			     System.out.println("second part = "+ line2);
//			     
//			     /* ******** CALL PRINT FUNCTION ************ */
//			     evaluator.print(line2);
//			    }
//				else 
//				{
//					
//					String[] line2 = line.getLineContents().split(" ");
//					
//					/* ******** CALL PRINT FUNCTION ************ */
//					evaluator.print(line2[1]);
//				}	
//			}
//		    lineNumber++;   
//			//after passing the element, remove it	
//		    queue.remove(queue.element());
//		}
//		
//	}
//	
//	
//	
//	// This method is to extract the strings found on a line
//	// Strings are between double quotes
//	public void handleStrings(String currentLine)
//	{
//		System.out.println("_______________________handleStrings()");
//		Pattern p = Pattern.compile("\"([^\"]*)\"");
//		Matcher m = p.matcher(currentLine);
//		while (m.find()) 
//		{
//		  System.out.println(m.group(1));
//		  System.err.println("HAndled the strings here");
//		}
//	}
//	
//	
//
//}
