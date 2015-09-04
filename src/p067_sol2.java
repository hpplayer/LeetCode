/**
 * Here is the code that applies optimizations I mentioned in sol1
 * The main idea is same.
 * But we combine three loops into one by checking the index of pointer in the combined loop
 * Since we will check index inside the while loop, we can even combine the extreme case into the loop, where
 * we have finished two strings, but still have carry left
 * 
 * Also, we use sum/2 to get carry and sum%2 to get appropriate remainder
 * EX:
 * we will have followings sum cases:
 * 		0, 1, 2, 3  (extreme case: a: 0, b: 0, carry: 0 and a: 1, b; 1, carry: 1)
 * %2   0, 1, 0, 1  <= remainder
 * /2	0, 0, 1, 1  <= carry
 * 
 * Besides, I use stringBuilder here to build string more beautifully
 * 
 * Although the main idea is same, but this code is shorter
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 11:33:55 PM
 */
public class p067_sol2 {
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i>= 0 || j >= 0 || carry > 0){
            //i -- and j-- to short the code
            int p = i>=0? a.charAt(i--) - '0' : 0;//get value if existed
            int q = j>=0? b.charAt(j--) - '0' : 0;//get value if existed
            int sum = p + q + carry;
            carry = sum/2;
            sb.insert(0, sum%2);
        }
        /*
        if(carry > 0){//if we still have carry after both strings end
            sb.insert(0, carry);//carry is 1 actually
        }
        */
        return sb.toString();
    }
}
