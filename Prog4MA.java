/**
 * SVC-V3 Parser
 * Description: Full Java implementation of a recursive descent parser for the SVC-V3 language using the grammar rules shown
 * below.
 * Programmer: Michael Astfalk
 * Date Created: 4/22/25
 * Last Modified: 5/1/25
 * 
 * Grammar:
 * START -> {COMMANDS}
 * COMMANDS -> MOVE|MATH|IMMEDIATE
 * MOVE -> 'MOVZ'REGISTER','IMM_VALUE
 * MATH -> ('ADD'|'SUB')REGISTER','REGISTER','REGISTER
 * IMMEDIATE -> ('ADDI'|'SUBI')REGISTER','REGISTER','IMM_VALUE
 * REGISTER -> 'X0'|'X1'|'X2'
 * IMM_VALUE -> '#'DIGITS
 * DIGITS -> DIGIT{DIGIT}
 * DIGIT -> '0'|..|'9'
 * 
 * Variables:
 * prog4.txt - The input file variable containing SVC-V3 tokens
 * prog4MAout.txt - The output file variable which has token messages and a valid/invalid message printed out to it
 * 
 * start:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * token - a String value that holds the current line/token from the input stream
 * 
 * commands:
 * Description: Implements the COMMANDS grammar rule
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * token - a String value that holds the current line/token from the input stream
 * 
 * move:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * token - a String value that holds the current line/token from the input stream
 * 
 * math:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * token - a String value that holds the current line/token from the input stream
 * 
 * immediate:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * token - a String value that holds the current line/token from the input stream
 * 
 * register:
 * reg - a String value that holds the register being checked
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * 
 * immValue:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * token - a String value that holds the current line/token from the input stream
 * subLine - a substring of line containing the digits after the '#'
 * 
 * digits:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * line - a String value that holds the line of characters after the '#'
 * 
 * digit:
 * in - a Scanner object for the input stream
 * out - a PrintWriter object for the output stream
 * call - a Prog4MA object used for method calls
 * term - the Character value of the current terminal being examined
 * 
 * getNextToken:
 * in - a Scanner object for the input stream
 * 
 * main:
 * inStream - the input stream for Prog4.txt
 * outStream - the output stream for Prog4MA.txt
 * call - a Prog4MA object used for method calls
 * fileName - a string value for the name of the file containing the program tokens
 */

import java.util.Scanner;
import java.lang.Character;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Prog4MA
{
    
    /**
     * start
     * Description: Implements the START grammar rule by retrieving the token and calling immValue if it is not empty.
     * START -> {COMMANDS}
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 5/1/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * token - a String value that holds the current line/token from the input stream
     */

    public boolean start(Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: start");
        out.println("Entering function: start");
        
        //*****************************************
        // Declare and initialize token to the
        // next token
        //*****************************************
        
        String token = call.getNextToken(in);
            
        //*****************************************
        // There can be zero or many commands, so
        // an empty program is valid
        //*****************************************
            
        if (token.equals(""))
            return true;
        
        //*****************************************
        // Keep retrieving tokens while there are
        // still tokens left in the input stream.
        // Call commands and pass it the token.
        //*****************************************
        
        while (!token.equals("")) {
            
            //*****************************************
            // Call the commands method and if it returns
            // false, quit the method and return false.
            //*****************************************
                
            if (!call.commands(token, in, out, call)) {
                System.out.println("Leaving function: start Unsuccessful");
                out.println("Leaving function: start Unsuccessful");
                
                return false;
            }
            
            token = call.getNextToken(in);
        }
        
        //*****************************************
        // The syntax for START is valid, so return true.
        //*****************************************
        
        System.out.println("Leaving function: start Successful");
        out.println("Leaving function: start Successful");
        return true;
    }
    
    /**
     * commands
     * Description: Implements the COMMANDS grammar rule
     * COMMANDS -> MOVE|MATH|IMMEDIATE
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 4/24/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * token - a String value that holds the current line/token from the input stream
     */
    
    public boolean commands(String token, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: commands, token = " + token);
        out.println("Entering function: commands, token = " + token);
        
        //*****************************************
        // Check for valid syntax of MOVE, MATH, or
        // IMMEDIATE
        //*****************************************
        
        if (call.move(token, in, out, call)) {
            System.out.println("Leaving function: commands Successful");
            out.println("Leaving function: commands Successful");
            
            return true;
        }
        else if (call.math(token, in, out, call)) {
            System.out.println("Leaving function: commands Successful");
            out.println("Leaving function: commands Successful");
            
            return true;
        }
        else if (call.immediate(token, in, out, call)) {
            System.out.println("Leaving function: commands Successful");
            out.println("Leaving function: commands Successful");
            
            return true;
        }
        
        //*****************************************
        // No valid syntax found for MOVE, MATH, or IMMEDIATE,
        // so the method returns false.
        //*****************************************
        
        System.out.println("Leaving function: commands Unsuccessful");
        out.println("Leaving function: commands Unsuccessful");
        
        return false;
    }
    
    /**
     * move
     * Description: implements the MOVE grammar rule
     * MOVE -> 'MOVZ'REGISTER','IMM_VALUE
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 5/1/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * token - a String value that holds the current line/token from the input stream
     */
    
    public boolean move(String token, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: move, token = " + token);
        out.println("Entering function: move, token = " + token);
        
        //*****************************************
        // Check to make sure the first token is 'MOVZ',
        // then check that lexemes are of the form
        // 'MOVZ'REGISTER','IMM_VALUE.
        //*****************************************
        
        if(token.equals("MOVZ")) {
            token = getNextToken(in);
            
            if (call.register(token, in, out)) {
                token = getNextToken(in);
                
                if (token.equals(",")) {
                    token = getNextToken(in);
                    System.out.println("Found ','");
                    out.println("Found ','");
                    
                    if (immValue(token, in, out, call)) {
                        System.out.println("Leaving funciton: move Successful");
                        out.println("Leaving function: move Successful");
                    
                        return true;
                    }
                }
            }
        }
        
        //*****************************************
        // Checking the lexemes did not return true,
        // so the syntax is incorrect.
        //*****************************************
        
        System.out.println("Leaving function: move Unsuccessful");
        out.println("Leaving function: move Unsuccessful");
        
        return false;
    }
    
    /**
     * math
     * Description: Implements the MATH grammar rule
     * MATH -> ('ADD'|'SUB')REGISTER','REGISTER','REGISTER
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 5/1/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * token - a String value that holds the current line/token from the input stream
     */
    
    public boolean math(String token, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: math, token = " + token);
        out.println("Entering function: math, token = " + token);
        
        //*****************************************
        // Check to make sure the first token is 'ADD'
        // or 'SUB'. Then check to make sure the rest
        // of the command is of the form
        // REGISTER','REGISTER','REGISTER.
        //*****************************************
        
        if (token.equals("ADD") || token.equals("SUB")) {
            token = getNextToken(in);
            
            if (call.register(token, in, out)) {
                token = getNextToken(in);
                
                if (token.equals(",")) {
                    token = getNextToken(in);
                    System.out.println("Found ','");
                    out.println("Found ','");
                    
                    if (call.register(token, in, out)) {
                        token = getNextToken(in);
                        
                        if (token.equals(",")) {
                            token = getNextToken(in);
                            System.out.println("Found ','");
                            out.println("Found ','");
                            
                            if (call.register(token, in, out)) {
                                System.out.println("Leaving function: math Successful");
                                out.println("Leaving function: math Successful");
                                
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        //*****************************************
        // Checking the lexemes did not return true
        // so the syntax is incorrect.
        //*****************************************
        
        System.out.println("Leaving funciton: math Unsuccessful");
        out.println("Leaving function: math Unsuccessful");
        
        return false;
    }
    
    /**
     * immediate
     * Description: implements the IMMEDIATE grammar rule
     * IMMEDIATE -> ('ADDI'|'SUBI')REGISTER','REGISTER','IMM_VALUE
     * Programmer: Michael Astfalk
     * Created: 4/22/25
     * Last Modified: 5/1/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * token - a String value that holds the current line/token from the input stream
     */
    
    public boolean immediate(String token, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: immediate, token = " + token);
        out.println("Entering function: immediate, token = " + token);
        
        //*****************************************
        // Check to make sure the first token is
        // equal to 'ADDI' or 'SUBI'. If it is,
        // then keep parsing to check that the rest of
        // the command is of the form
        // REGISTER','REGISTER','IMM_VALUE.
        //*****************************************
        
        if (token.equals("ADDI") || token.equals("SUBI")) {
            token = getNextToken(in);
            
            if (call.register(token, in, out)) {
                token = getNextToken(in);
                
                if (token.equals(",")) {
                    token = getNextToken(in);
                    System.out.println("Found ','");
                    out.println("Found ','");
                    
                    if (call.register(token, in, out)) {
                        token = getNextToken(in);
                        
                        if(token.equals(",")) {
                            token = getNextToken(in);
                            System.out.println("Found ','");
                            out.println("Found ','");
                            
                            if (call.immValue(token, in, out, call)) {
                                System.out.println("Leaving function: immediate Successful");
                                out.println("Leaving function: immediate Successful");
                                        
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        //*****************************************
        // Checking the lexemes did not return true so
        // the syntax is invalid and false is returned.
        //*****************************************
        
        System.out.println("Leaving function: immediate Unsuccessful");
        out.println("Leaving function: immediate Unsuccessful");
        
        return false;
    }
    
    /**
     * register
     * Description: Implements the REGISTER grammar rule
     * REGISTER -> 'X0'|'X1'|'X2'
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 4/22/25
     * 
     * Variables:
     * reg - a String value that holds the register being checked
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     */
    
    public boolean register(String reg, Scanner in, PrintWriter out) {
        System.out.println("Entering function: register, reg = " + reg);
        out.println("Entering function: register, reg = " + reg);
        
        //*****************************************
        // Check to see that the register is equal to
        // the strings "X0", "X1", or "X2". If it is,
        // then the function is successful and returns
        // true. If this fails, return false.
        //*****************************************
        
        if (reg.equals("X0") || reg.equals("X1") || reg.equals("X2")) {
            System.out.println("Leaving function: register Successful");
            out.println("Leaving function: register Successful");
            
            return true;
        }
        
        System.out.println("Leaving function: register Unsuccessful");
        out.println("Leaving function: register Unsuccessful");
        
        return false;
    }
    
    /**
     * immValue
     * Description: Implements the IMM_VALUE grammar rule
     * IMM_VALUE -> '#'DIGITS
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 4/24/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * token - a String value that holds the current line/token from the input stream
     * subLine - a substring of line containing only the characters after the '#'
     */
    
    public boolean immValue(String token, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: immValue, token = " + token);
        out.println("Entering function: immValue, token = " + token);
        
        //*****************************************
        // Check to make sure the first lexeme is
        // a '#'
        //*****************************************

        if (token.charAt(0) != '#') {
            System.out.println("Leaving function: immValue Unsuccessful");
            out.println("Leaving function: immValue Unsuccessful");
            
            return false;
        }
        
        //*****************************************
        // Set subLine to the characters after the '#'
        //*****************************************
        
        String subLine = token.substring(1, token.length());
        
        //*****************************************
        // Call the digits functions and if it
        // returns false, quit the method, returning
        // false.
        //*****************************************
        if (!call.digits(subLine, in, out, call)) {
            System.out.println("Leaving function: immValue Unsuccessful");
            out.println("Leaving function: immValue Unsuccessful");
            
            return false;
        }
        
        //*****************************************
        // The syntax for IMM_VALUE is valid so return true.
        //*****************************************
        
        System.out.println("Leaving function: immValue Successful");
        out.println("Leaving function: immValue Successful");
        
        return true;
    }
    
    /**
     * digits
     * Description: Implements the DIGITS grammar rule
     * DIGITS -> DIGIT{DIGIT}
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 4/24/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * line - a String value that holds the line of digits after the '#'
     */
    
    public boolean digits(String line, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: digits, line = " + line);
        out.println("Entering function: digits, line = " + line);
        
        //*****************************************
        // Check to make sure line is not empty
        // since there must be at least one digit
        // according to the grammar rule. If line
        // is empty, quit the method.
        //*****************************************
        
        if (line.equals("")) {
            System.out.println("Leaving function: digits Unsuccessful");
            out.println("Leaving function: digits Unsuccessful");
            
            return false;
        }
        
        //*****************************************
        // Loop through the characters and call the digit method.
        // If digit returns false, return false and quit the method.
        //*****************************************
        
        for (int i = 0; i < line.length(); i++) {
            if (!call.digit(line.charAt(i), in, out, call)) {
                System.out.println("Leaving function: digits Unsuccessful");
                out.println("Leaving function: digits Unsuccessful");
                
                return false;
            }
        }
        
        //*****************************************
        // The sytax for DIGITS is valid, so return true.
        //*****************************************
        
        System.out.println("Leaving function: digits Successful");
        out.println("Leaving function: digits Successful");
        
        return true;
    }
    
    /**
     * digit
     * Description: Implements the DIGIT grammar rule by evaluating whether the lexeme is a digit.
     * DIGIT -> '0'|..|'9'
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 4/24/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     * out - a PrintWriter object for the output stream
     * call - a Prog4MA object used for method calls
     * term - a Character value of the current terminal being examined
     */
    
    public boolean digit(Character term, Scanner in, PrintWriter out, Prog4MA call) {
        System.out.println("Entering function: digit, term = " + term);
        out.println("Entering function: digit, term = " + term);
        
        //*****************************************
        // Verify that token is a digit. If it is
        // not a digit, then the syntax is invalid
        // and the method returns false and outputs
        // an error statement.
        //*****************************************
        
        if (!java.lang.Character.isDigit(term)) {
            System.out.println("Leaving function: digit Unsuccessful");
            out.println("Leaving function: digit Unsuccessful");
            
            return false;
        }
        
        //*****************************************
        // The syntax for DIGIT is valid, so return true.
        //*****************************************
        
        System.out.println("Leaving function: digit Successful");
        out.println("Leaving function: digit Successful");
        
        return true;
    }
    
    /**
     * getNextToken
     * Description: Gets the next token in the file, returning a String of the token or an empty String for no token.
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 4/22/25
     * 
     * Variables:
     * in - a Scanner object for the input stream
     */
    
    public String getNextToken(Scanner in) {
        if (in.hasNextLine())
            return in.nextLine().strip();
        
        return "";
    }
    
    /**
     * main
     * Description: Initializes input and output streams and begins the parsing process by calling the start method.
     * Programmer: Michael Astfalk
     * Date Created: 4/22/25
     * Last Modified: 5/1/25
     * 
     * Variables:
     * inStream - the input stream for prog4.txt
     * outStream - the output stream for prog4MA.txt
     * call - a Prog4MA object used for method calls
     * fileName - a string value for the name of the file containing the program tokens
     */
    
    public static void main() {
        
        //*****************************************
        // Variable declarations
        //*****************************************
        
        Scanner inStream = null;
        PrintWriter outStream = null;
        Prog4MA call = new Prog4MA();
        String fileName = "prog4.txt";
        
        //*****************************************
        // Set inStream and outStream and quit if
        // there is an error
        //*****************************************
        
        try {
            inStream = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening ," + fileName + "quitting...");
            System.exit(0);
        }
        
        try {
            outStream = new PrintWriter("prog4MAout.txt");
        }
        catch(FileNotFoundException e) {
            System.out.println("Error opening prog4MAout.txt, quitting...");
            System.exit(0);
        }
        
        //*****************************************
        // Call the start method to begin
        // recursive descent parsing. Output an
        // appropriate message for legal and illegal
        // syntax.
        //*****************************************
        
        System.out.println("Checking the syntax of " + fileName);
        outStream.println("Checking the syntax of " + fileName);
        
        if (call.start(inStream, outStream, call)) {
            System.out.println("Program parsed successfully, syntax is legal");
            outStream.println("Program parsed successfully, syntax is legal");
        }
        else {
            System.out.println("Program parsed unsuccessfully, syntax is illegal");
            outStream.println("Program parsed unsuccessfully, syntax is illegal");
        }
        
        //*****************************************
        // Close the input and output streams
        //*****************************************
        
        inStream.close();
        outStream.close();
    }
}