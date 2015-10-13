/**
 * This is a very clear and clever way to solve the problem
 * The key idea is to use the pointer and destination variable
 * we will go through strings with pointer, and check if pointer == destination in the end
 * we also have an indicator isNumeric that can tell us whether we have digits in current part.
 * When we say part it may mean the part before ".", or the part after "." but before "e", or the part after "e"
 * With this indicator, we can safely check if the use of "e" is legal.
 * 
 * More details can be found below
 * @author hpPlayer
 * @date Jul 22, 2015 5:35:55 PM
 */
public class p065_sol2 {
	public static void main(String[] args) {
		System.out.println(new p065_sol2().isNumber("e9"));
	}
    public boolean isNumber(String s) {
        int pointer = 0, dest = s.length();
        //move pointer through all heading whitespaces
        while(pointer < dest && Character.isWhitespace(s.charAt(pointer))) pointer++;
        
        //handle the case sign(+/-) case
        if(pointer < dest && (s.charAt(pointer) == '+' || s.charAt(pointer) == '-')) pointer++;
        
        boolean isNumeric = false;
        
        //move pointer through digits until we reach some unexpected non-digit char
        while( pointer < dest && Character.isDigit(s.charAt(pointer))){
        	pointer ++;
        	isNumeric = true;//indicate whether we have at least one digit now
        }
        
        //if this unexpected non-digit char is '.', we dont need to check isNumeric, since our number can start with "."
        if(pointer < dest && s.charAt(pointer) == '.'){
        	pointer ++;
        }
        
        //we may have digits after "." but before "e", so we need go through all of them
        while( pointer < dest && Character.isDigit(s.charAt(pointer))){
        	pointer ++;
        	isNumeric = true;//indicate whether we have at least one digit now
        }
        
        //if this unexpected non-digit char is 'e', we need to check isNumeric, since we must have digit before e
        if(isNumeric && pointer < dest && s.charAt(pointer) == 'e'){
        	isNumeric = false;
        	pointer++;
        	//we allow + or - after e
        	if(pointer < dest && (s.charAt(pointer) == '+' || s.charAt(pointer) == '-')) pointer++; 
        } 
       
        while( pointer < dest && Character.isDigit(s.charAt(pointer))){
        	pointer ++;
        	//we may have special case like ".5", where isNumeric is false before ".". so we still need set isNumeric
        	//also we reset isNumeric if "e" exists, so we need check again if we have digits after e.
        	isNumeric = true;
        }
        //move pointer through all tailing whitespaces
        while(pointer < dest && Character.isWhitespace(s.charAt(pointer))) pointer++;
        
        //if we have at least one digit and our pointer reach the dest, then we are done
        System.out.println(pointer);
        System.out.println(dest);
        return isNumeric && pointer == dest;
    }
}
