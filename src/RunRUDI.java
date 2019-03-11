/**
 * 
 */

/**
 * @author Jeanne and Batanda
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.util.Scanner;

public class RunRUDI {

	private static Scanner in=new Scanner(System.in);

	/*
	 * check if the input is blank or empty
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}


	public static void main(String[] args) throws FileNotFoundException {

		/* 
		 * get the filename or the full link of the file from the user to be interpreted
		 */
		String inputFilename;
		System.out.println("Please, the file having the program you want to execute in rudi (extension .rudi): ");
		System.out.println("To create a rudi file, save your document as document.rudi in between quotes");

		inputFilename = in.nextLine();

		//check to see if the entered filename is empty
		while(isBlank(inputFilename))
		{
			System.err.println(":::ERROR:::"+"You entered a whitespace");
			System.out.println("Please, the file having the program you want to execute in rudi (extension .rudi): ");
			System.out.println("To create a rudi file, save your document as document.rudi in between quotes");
			inputFilename = in.nextLine();
		}
		File file =new File(inputFilename);

		/*
		 * call the parser to read and interpret the program in that file
		 */
		Parser parser = new Parser();
		parser.readFile(file);
	}

}
