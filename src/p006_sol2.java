/**
 * This is the second approach without using extra space
 * When I was doing this problem, I found the gap between whole column (char on "|") is 2n-2.
 * This solution make it further. It found the gap between whole column will fall into 2 parts since 2nd row until n-1 row.
 * Only the first and row has a whole gap, other rows have 2 separated gaps.
 * The length of first separated gap is shrinking, and can be found as 2(n-1-i)
 * The length of second separated gap is growing, and can be found as 2*i
 * 
 * After found such rule, it is easy to get the correct result.
 * Just build a loop for each row, in each row, we get chars based on these two gaps.
 * 
 * 1     7 (no gap2, gap1 is 2*(4-1-0) = 6
 * 2   6 8 (gap1: 2*(4-1-1) = 4, gap2: 2*1 = 2)
 * 3 5   9 (gap1: 2*(4-1-2) = 2, gap2: 2*2 = 4)
 * 4     10(no gap1, gap2: 2*3 = 6)
 * 
 * @author hpPlayer
 * @date Jul 17, 2015 3:53:23 PM
 */
public class p006_sol2 {
	public static void main(String[] args){
		System.out.println(new p006_sol2().convert("ABC", 2));
	}
    public String convert(String s, int numRows) {
        if (numRows >= s.length() || numRows <= 1){//two special cases that keeps original order of string s
        	return s;
        }
        
        String result = "";
        
        for(int i = 0; i < numRows; i++){
        	/*
        	 * notice:
        	 * first row does not have gap2
        	 * last row does not have gap1
        	 * since one is shrinking and the other one is growing
        	 */
        	int gap1 = 2 * (numRows - 1 - i);//the gap1 is moving closer with the row number increase
        	int gap2 = 2 * i;//gap2 is moving further with the row number increase, gap1 + gap2 = 2*(n-2) which is fix
        	
        	int j = i;//create duplicate i, since we will change it later
        	result += s.charAt(i);//i is the row number but also the index of char in first column
        	while(j < s.length()){
        		j += gap1;
        		if(i != numRows-1 && j< s.length()){
        			result += s.charAt(j);
        		}
        		j += gap2;
        		if (i != 0 && j < s.length()){
        			result += s.charAt(j);
        		}
        	}
        }
        return result;
    }
}
