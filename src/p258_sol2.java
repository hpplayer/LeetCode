/**
 * This is iterative solution.
 * Same thing, during each iteration, I simply convert current int into string
 * then build new int, then convert new int back to string and do next iteration
 * When string.length() == 1, I will simply stop the loop and return result
 * @author hpPlayer
 * @date Aug 16, 2015 12:18:22 AM
 */
public class p258_sol2 {
    public int addDigits(int num) {
        if(num <= 0) return num;
        String s = num + "";
        while(s.length() != 1){
            int sum = 0;
            for(int i = 0; i < s.length(); i++){
                sum += s.charAt(i) - '0';
            }     
            s = sum + "";
        }

        return Integer.valueOf(s);
    }
}
