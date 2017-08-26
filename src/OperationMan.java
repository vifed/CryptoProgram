import java.util.*;
import java.lang.*;

/**
*
* @author Viola Federico
* 
* @category Academic Project / Computer Security
* @version 1.0
*/

public class OperationMan {
	
	private static Substitution subs;
	private static Transposition trans;
	private static String operation;
	private static final String message = "\n\t\t\t°°°Now select what you want to do!°°°\n\n\t\t\tInsert: \n\t\t\t\t*1* if you want to perform Substitution method; "
										+ "\n\t\t\t\t*2* if you want to perform Transposition method;"
										+ "\n\t\t\t\t*3* if you want to Stop the execution of the program!\n";
	
public OperationMan() {
		subs = new Substitution();
		trans = new Transposition();
		operation="";
	}
	
	public void start() {
		System.out.println(message);
		run();
	}
	
	public void run() {
		operation = checkInput();
		selectMethod(operation);
	}
	
	private void selectMethod(String selection) {
		
		if(selection.equals("1")) {
			System.out.println("\n\t\t\t°*°Substitution encryption selected!°*°");
			showMessage();
			selection = checkInput();
			if(selection.equals("1")) {
				subs.encode();
			}
			else {
				subs.decode();
			}
			start();
		}
		if(selection.equals("2")) {
			System.out.println("\n\t\t\t°*°Transposition encryption selected!°*°");
			showMessage();
			selection = checkInput();
			if(selection.equals("1")) {
				trans.encode();
			}
			else {
				trans.decode();
			}
			start();
		}
		if(selection.equalsIgnoreCase("3")) {
			System.out.println("\n\tExiting...\n");
			System.exit(0);
		}
	}
	
	private static String checkInput() { 
		Scanner in = new Scanner(System.in);
		String op = in.nextLine();
       
        while ( !(op.equals("1") || op.equals("2") || op.equals("3"))  ) {
            
            System.out.println("\n\t\tWrong selection! Try again...\n");
            
            op = in.nextLine();
        }
        return op;
    }
	
	public void showMessage() {
		System.out.println("\n\t\t\tNow insert:\n\t\t\t\t\t*1* if you want to Encrypt a file;\n\t\t\t\t\t"
				+ "*2* if you want to Decrypt a file; \n\n");
	}
	
}
