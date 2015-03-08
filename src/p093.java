import java.util.ArrayList;
import java.util.List;
/**
 * This problem I did not work out..But it is still a subproblem of DFS
 * 
 * like normal DFS, we need set the boundary case first, 
 * This problem has a trick here, we use a count to record how may dot we have added,
 * when count == 3, it means all remaining substring should be appended to the temp,
 * but we dont know if it valid or not, so our boundary case is 
 * count == 3 && isValid(substring)
 * 
 * For each node, it should have at most 3 variations, i.e. 1-3 digits,
 *  for example : 1 2 3, we will have 1, 12, 123
 *  So we need a loop here to visit all nodes
 *  
 *  Another trick here, when we build the node, the substree actually is the substring of string without current Node's value 
 *  for example: 1 2 3, node 1 has substring(subtree) of 2 3, node 12 has substring(subtree) of 3
 *  
 *  So when we pass the String input S to next DFS, we can just pass the substree which will be convenient (no need to store the index)
 *  
 *  Other steps all similiar to normal DFS
 *  Remark:
 *  1) each time we do DFS, we need to check if current node is valid node, if not, we should break the loop and no need to continue
 *  (for example: substring like 00, 01, which only have length of 2, but is already invalid
 *  2) isValid is important, since its our filter to decide whether add current node
 *  3) dont forget the case "00", "01", which should be invalid, dont forget check the case string == null || string.length()==0
 *  4) when do the loop in the DFS, which are used to visit all three variations, we need to consider the case if left substring.length <3
 *  for example when we are dealing with last digits, we may only have 2 or 1 left, and we cant do three iterations on them, so in addition
 *  to condition i = 0; i < 3; i++, we need more strict condition: i =0; i < 3 &&  i <s.length(); i++
 * 5)dont forget the case if we have used all digits up before the count == 3, then we dont have digits left, so we need add if case that 
 * the input string is null
 * 
 * @author hpPlayer
 * @date Mar 7, 2015 3:19:46 PM
 */
public class p093 {
	public static void main(String[] args) {
		List<String> result = restoreIpAddresses("010010");
		System.out.println(result);
	}

	public static List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<String>();
		if (s == null || s.length() < 4 || s.length()> 12)
			return result;
		DFS(result, "", s, 0);
		return result;
	}

	//we can pass substring to next leaves, so we dont have to remember the index
	public static void DFS(List<String> result, String temp, String s, int count){
		if(count == 3){
			//System.out.println("temp: " + temp + "s: " + s);
			if(isValid(s)){
				result.add(temp+s);
			}
			return;
		}
		//String num = "";
		for(int i = 0; i < 3 && i < s.length(); i++){//in the last leaves, we may have substring.length() < 3 (192.10.12.1, etc.)
			//num = num + s.charAt(i)+"";//add "" to convert from char to string
			String num = s.substring(0, i+1);
			//System.out.println(count+1);
			if(isValid(num)){

				//s.substring(i+1, s.length()), where we want to get the substring from index i+1 to the end 
				//we can simply use s.substring(i+1), which will automatically get substring until the end
				DFS(result, temp + num + "." , s.substring(i+1), count +1);
			}else{
				//System.out.println(num);
				//System.out.println(i);
				//System.out.println("im here");
				break;
			}
		}
		
	}

	public static boolean isValid(String s){
		//if(s.length() == 0 || s== null) return false;// s will not be null...so 
		if(s.length() == 0) return false;
		if(s.charAt(0) == '0') return s.equals("0");//we may have string like 010, 001, they all wrong!
			//return Integer.valueOf(s) <= 255 && Integer.valueOf(s) >= 0;
		return Integer.parseInt(s) <= 255 && Integer.parseInt(s) > 0; //case of "==0" has been checked in the first line of this method
	}
	
	

}
