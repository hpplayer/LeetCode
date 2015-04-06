/**
 * This solution is very smart, it go through all chars in the input string(include the long and short one)
 * we get the int value from each digit.
 * for short one, if we have reach its length, then we simply feed 0, if remaining long string contains all 0, then it means they 
 * are equal. If their return values are equal then we need continue the loop
 * otherwise their return value is not equal, we can simply judge which one is larger.
 * @author hpPlayer
 * @date Apr 5, 2015 11:07:03 PM
 */
public class p165_sol2 {
	public static void main(String[] args){
		System.out.println("\\");
		System.out.println(compareVersion("0", "0.0.1"));
		System.out.println(compareVersion("1", "1.0"));
	}
	public static int compareVersion(String version1, String version2) {
		String[] s1 = version1.split("\\.");
		String[] s2 = version2.split("\\.");
		
		for(int i = 0; i < Math.max(s1.length, s2.length); i++){
			int v1 = i >= s1.length?0 : Integer.parseInt(s1[i]);
			int v2 = i >= s2.length?0 : Integer.parseInt(s2[i]);
			if(v1 == v2){//still equal, we need loop continue
				continue;
			}
			else{//not equal, time to return which one is larger
				return v1 > v2? 1 : -1;
			}
		}
		return 0;//we cant find which one is larger or smaller even we have run all chars, then it means they are equal
	}
}
