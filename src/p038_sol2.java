/**
 * Iterative way to solve this problem.
 * We just need an extra outer loop of n to get the value of each value in sequence
 * Other part is almost same with sol1
 * more details can be found below.
 * @author hpPlayer
 * @date Aug 4, 2015 11:36:29 PM
 */
public class p038_sol2 {
	public static void main(String[] args){
		int a = 2;
		System.out.println(new p038_sol2().countAndSay(a));
	}
    public String countAndSay(int n) {
        if (n == 0) return "";
        StringBuilder temp = new StringBuilder();
        temp.append(1);
        for(int i = 1; i < n; i++){
            String s = temp.toString();//get prev string
            temp = new StringBuilder();//clear sb
            char prev = s.charAt(0);//get first char
            int count = 1;//we already get one char so count inital value should be 1
            for(int j = 1; j < s.length(); j++){
                if(s.charAt(j) == prev){
                    count ++;
                }else{
                    temp.append(count);
                    temp.append(prev);
                    count = 1;//even we found a mismatch, we still get one char so count should be reset to 1
                }
                prev = s.charAt(j);
            }
            
            //don't forget last set of chars
            temp.append(count);
            temp.append(prev);            
        }
        
        return temp.toString();
    }
}
