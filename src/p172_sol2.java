/**
 * Ok, here is a very short and smart solution
 * How can we get tailing 0s?
 * Only after we produce a 10 during the factorial calculation
 * How can we produce a 10 during the factorial calculation?
 * Only after we got a 5 and a 2
 * We have bunches of 2s during the calculation, which can come from any of even numbers
 * So we only care about the number of 5s would be included in our input n
 * Then we just need to calculate how many multiplies of 5 are included in input n
 * 
 * For example:
 * 10: we got 2 5s from 10/5 =2, then we will have 2 tailing 0s
 * 20: we got 4 5s from 20/5 = 4, then we will have 4 tailing 0s
 * 
 * However some specific numbers may provide more than one. Those numbers are the power of 5.
 * Of course the multiplies of those numbers will also provide more 5s
 * 
 * For example:
 * 25: we got 5 5s from 25/5 = 5, and we get an additinal 5 from 25 itself, so we will have 6 tailing 0s
 * 50: we got 10 5s from 50/5 = 10, and we get two additinal 5 from the fact 50 is the multiply of 25, so we will have 12 tailing 0s
 * 
 * Thus the baisc idea is that we firstly how many general 5s current input have, then check if current input is larger than some power of 5
 * namely: 25, 125, .....
 * 
 * Below is the recursive solution
 * In each recursion, we firstly calculate how many 5s current input have, then do recursive call on input/5, which will remove all 5s 
 * and if the input number contains some numbers that are power of 5, it will peel one 5 away from those nunbers and left remaining 5s there
 * In other words, each recursion will calculate how many 5s current layer have, then remove all those 5s and calculating remaining 5s in the 
 * updated input
 * 
 * 
 * @author hpPlayer
 * @date Sep 7, 2015 12:22:52 AM
 */
public class p172_sol2 {
    public int trailingZeroes(int n) {
        //boundary case
        if(n == 0) return 0;
        
        //count how many 5s currnt n have, and check if current n can provide additional 5s
        return n/5 + trailingZeroes(n/5);
    }
}
