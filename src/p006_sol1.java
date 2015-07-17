/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
/**
 * This is a pure hard-core question without short cuts.
 * I didn't solve it.
 * This is the first way to solve the problem.
 * Use a String array to record each row.
 * Each element in the array is the string of each row.
 * We use the step counter to indicate the row we reach.
 * When move forward(reach the first row), the step counter is +1
 * when move backward(reach the last row), the step counter is -1
 * Finally, we need to append each row's string together to form our output
 * @author hpPlayer
 * @date Jul 17, 2015 2:53:07 PM
 */
public class p006_sol1 {
	public static void main(String[] args){
		p006_sol1 test = new p006_sol1();
		System.out.println(test.convert("ABC", 2));
	}
    public String convert(String s, int numRows) {
        if (numRows <= 1 || numRows >= s.length()) return s; //boundary case
        
        String[] r = new String[numRows];
        for(int i = 0; i < r.length; i++){
        	r[i] = "";
        }
        int row = 0;
        int step =1;
        for(int i = 0; i < s.length(); i++){
        	if (row == numRows -1) step = -1;//reach last row, move backward
        	if (row == 0) step = 1;//reach first row, move forward
        	r[row] += s.charAt(i);
        	row += step;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < numRows; i++){
        	//System.out.println(r[i]);
        	sb.append(r[i]);
        }
        
        return sb.toString();
    }	
}
