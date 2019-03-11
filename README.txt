Capstone Final Project: RUDI Interpreter 

 

Assumptions 

 

. Assume that all syntaxes are separated by a space 
. Assume each variable is declared on a separate line 
. One user, one run (no concurrency in the program) 
. The program will stop when an error is encountered. 
. Assume an operation will be written in this way (((11 + 22) * 3) + 66) / (22 + 22), assume there 
will be a space between numbers and operators but no space between the latter two and the 
parenthesis. 
. Assume that the user enters positive numbers as an operation in the program e.g: fact = fact - 20 
or x = x + 1. Entering x = x + (-1) is not allowed. 
. Assume that the condition’s elements will be separated by space ( i.e. between parentheses and 
numbers. E.g. if ( n :eq: 2 ) ) 
. The user can write comments across multiple lines by appending a line continuation operator ‘&’ 
and writes the end of comment character “*/” on the last line with the comments. 


 

Design of the Interpreter 

 

Programs written in the RUDI language are saved in files with extension “.rudi”. The user will be asked 
to give the file name if it is stored in the same folder as the application or the link if they are in different 
locations, by pressing enter, the interpreter will take the program in that file and execute it. The file does 
not have to be in the same folder as the application, the user just has to provide the path to the file. 

 

The interpreter will execute the program line by line and without storing each line in the memory, it 
processes it. The interpreter displays and stops the program when it encounters an error. 

 


Classes in the interpreter 

 

Line class 

 

It is made of the line number (of type Integer) and the line contents (of type String). This is created when 
a line is read from the rudi file into the interpreter. It has getters and setters and toString() to print the line 
number and contents. Lines are read one by one from the file and they are not stored in the memory. 

 

Variable class 

 

It is made of the variable name (of type String), the variable type (of type String) and the variable value 
(of type String). RUDI supports 3 data types (variable types): string, integer and float. The variable value 


is set to null as the default value. The items that make up this class are declared under the decs section. It 
has getters and setters and toPrint() to print its members. 

 

We set all these items to type String because: - 

. Java’s BufferedReader reads in a line as a String. We tokenize the line to get the name, the 
datatype and the value. These tokens are of type String as well. So it’s easier to save and handle 
them instantly. 
. It is easy to convert from data type String to the either float or integer while keeping the 
information intact. 


 

RunRUDI class 

 

This is the main class of the whole program. It takes the input as the file name or the link of the file from 
the user and calls the parser to read the file, parse and perform other operations. By pressing “enter”, our 
interpreter will execute the program and start interpreting it. 

 

RunRUDI has isBlank(), a function to check if the user did not enter an empty file name. An error will be 
return if the filename entered is blank or if the filename do not exist.. 

 

Parser class 

 

This class reads the file line by line, parses each line, tokenizes it and call a function to evaluate that line 
whenever it meets a special keyword like print, input, equal sign … 

It has Parser() as its default constructor. 

 

It implements the methods below: 

 

readFile(): gets the filename, reads each line by skipping empty lines and removing comments in the 
current line. It checks if the current line is starting with a keyword, if yes, it gets what it needs and call the 
corresponding function of that keyword. 

For example: if the line starts with “print”, it takes the string between the quotes only and call print() from 
evaluator class to display it. If the line start with “input”, it takes the variable name only and call input() 
from evaluator class which asks the user to enter the value and then updates that Variable. 

 

Using flags to keep track of the conditional statement blocks i.e. if, else and while. These are ifSection, 
ifExecution, elseSection, elseExecution and whileSection. These are set to false at the beginning of 
execution. ifSection, elseSection and whileSection are set to true when keywords if, else and while are 
found at the start of a line. The ifExecution flag is set to true only when the conditional statement 
evaluates to true, else the elseExecution flag is set to true. We chose to use flags for these states can either 
be true or false and this is what the boolean signify. Additionally, they dont consume a lot of memory. 

 

Additional methods called by readFile() in parser class: 


 

isBlank(line): It takes in a object of data type String as an argument, which in this case is the line read 
from the .rudi file. This method checks to see if the line that has been read in is blank or not. It returns a 
boolean. If true, that line is skipped. 

 

removeComments(line): takes the current line and checks if it has comments, removes them and returns 
the edited line. The returned line can be blank if was only commented or not blank if it was having other 
string(s). 

 

isNumeric(str): takes the string, checks if it is numeric and returns a boolean as true or false. 

 

getOperation(infix): takes an operation in infix notation, cleans it to get the exact infix to use later. It 
returns an infix string. E.g. infix (( 2 +5)/8) was cleaned to ((2 + 5) / 8). 

 

Evaluator class 

 

It contains methods that are implemented when a keyword if found on the current line that is being 
parsed. Those keywords are: print, input, decs, operations (i.e lines having equal signs). 

 

Methods 

 

print(outputString): takes a string to display and display it to the user. 

 

input(variable name, Variable): takes the variable name to store the input from the user and the 
Variable type to be able to set the value of that variable name. input() asks the user to enter a value for a 
specified variable name and sets that variable name to that value entered. 

 

calculator(input): takes the infix input (e.g. fact + 1 ) and returns a result as a double data type of that 
postfix. We first converted the infix to postfix because it easy to perform the calculations having the latter 
than the other one. 

It produces that postfix by calling infixToPostfix class to convert the infix to the postfix string. Then, it 
performs the calculations (additions, divisions, multiplications, subtractions) of that postfix and returns 
the result which will be returned in parser class as a double data type. 

 

Condition evaluator class 

 

This class contains methods that are implemented to evaluate the condition inside the brackets after if, 
else statements and while loop. Methods inside this class are called when the current line starts with 
keywords including if, else, while. 

 

Methods of this class: 

 


conditionEvaluator(condition, Variables ): takes the condition inside the brackets as a type String and 
the map of variables. Stacks to store operands, responses and parenthesis are used to be able to get the 
boolean result of the condition operation. It returns True if the condition evaluates to True and False 
otherwise. 

It calls findVariable(), comparisonOperation(), isBooleanOperator() and other methods that will be 
detailed below. 

 

booleanOperation (operand1, operand2, operator) : gets two operands of type string, one operator of 
type String and return the result from the boolean operator function. This method handles operations 
involving logical characters ‘|’, ‘^’ and ‘~’. 

 

isBooleanOperator(str) : gets an operator as a string, checks if an operator is a logical (‘|’, ‘^’ and ‘~’. ) 
and return a boolean as True or False. 

 

comparisonOperation (operand1, operand2, oper) : gets two operands of type string, one operator of 
type String, do the computation depending on each operator (:eq:, :ne:, ...) and return the result of type 
String. 

 

findVariable (String variableName, Map<String, Variable> variables): gets the variable name and 
the map of Variables, checks if that variable exists in that map and returns true if yes, otherwise false. 

 

isComparisonOperator(str): gets a comparison operator as a String and checks if it is true. This function 
returns a boolean as True or False. 

 

InfixToPostfix class 

 

This class gets the input as an infix (of a string data type) and returns a postfix as a string data type. It has 
members, input to store the infix string and output to store the postfix to return. 

 

Methods of this class: 

 

InfixToPostfix(String in) : constructor of InfixToPostfix class 

 

toPostfix(): checks the infix and make a postfix made of numbers, brackets and operators, then, it returns 
the postfix output. 

 

precedence(char ch, int precedence): gets a character in the infix and operator and helps in getting the 
postfix by order of precedence by storing in the stack. 

 

gotParenthesis(char ch): gets a character in an infix string, checks if it is a parenthesis and returns a 
boolean as true or false. 

 


isNumeric(String str): gets a string from the infix and checks if a string is numeric and return a boolean 
as true or false. 

 

Design of the ADTs 

 

. Stack 
o Stack is used in keeping count of the parentheses and brackets in different sections of the 
program. We choose to use a stack given its Last In, First Out nature. It gives the 
program a lookup complexity of O(1). 




 

. Hash Map 
o A hash map is used to store the variables declared in the program. We chose to use a hash 
map since the time complexity for search is O(1). This is achieved by hashing to a 
variable’s name, which is the key in our case. The value is of type Variable which has a 
variable type, a variable name and a variable value. 




 


Algorithm 

 

1. Read from the file containing the program line by line. 
2. For each line, we check if the line is blank/empty. If it is, skip it else parse the line. 
3. Check for comments on the line. If there are any, remove them, else parse the line. 
4. Check if the line contains any RUDI keywords for example decs, program among others. 
5. Check whether to execute lines of RUDI code under the if section or the else section of an if..else 
statement. 
6. Check whether to execute the lines of code under the while section. 
7. Check if the line is blank/empty. If it is, skip it else parse the line. 
8. Check for comments on the line. If there are any, remove them, else parse the line. 
9. Parses each line in the program 
10. Checks for the correct syntax 
11. Evaluates the line 
12. When the line evaluates to an error, execution is stopped and the error is printed along with the 
line number and line contents. 


 


Performance and memory complexity 

 

A line is read from the .rudi file and parsed immediately. All the lines except those with a line 
continuation character ‘&’ are not stored into memory. Therefore the memory complexity is O(n). 


 

The variables are stored in a hash map named “symbol table”. Search, Insertion and deletion from the 
symbol table is of time complexity O(1) in best case scenario. On the other hand, space complexity is 
O(n). 

 

The postfix calculator that handles the arithmetic problems to be interpreted carries out its operations with 
a complexity of O(n), with respect to operator precedence. The user provides input in infix notation which 
is converted to postfix notation 

 

 

Description of tradeoffs 

 

. We were planning to handle nested if statements but we have been limited by time. We were 
planning on employing a counter to keep count of how many loops are nested inside of one 
another. 
. We have not checked if all the errors were handled and for some of the handled errors, we were 
not able to print the line number and the line itself, because some functions were in different 
classes. We will try to handle them if time allows. 


 


 


