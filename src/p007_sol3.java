/**|
 * Official solution.
 * The key part is how to check overflow
 * To avoid the use of double, it check the value before reaching Intger.MAX_VALUE /10
 * If the front part (Integer.MAX_VALUE /10 part) already larger than it, then no matter what later digits are,
 * they will always overflow.
 * 
 * Integer.MAX_VALUE / 10 = 214748364
 * 
 * How about "==" case?
 * If ret == Integer.MAX_VALUE /10, then it means the original input should in the form of xxxxxx463847412.
 * Since input is int, it means xxxxxx can only be 1, and value 2147483641 is in good range.
 * 
 * @author hpPlayer
 * @date Jul 20, 2015 5:48:11 PM
 */
public class p007_sol3 {
    public int reverse(int x) {
        int ret = 0;
        while(x != 0){
        	//no need check "= Integer.MAX_VALUE/10" 
            if (Math.abs(ret) > Integer.MAX_VALUE /10) return 0;
            ret = ret * 10 + x%10;
            x /= 10;
        }
        return ret;
    }
}
