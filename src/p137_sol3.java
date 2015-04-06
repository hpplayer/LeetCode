/**
* when update value, we actually can create a state machine:
* like 00 -> 01 -> 10 ->00, no fourth state (we also can create other state machine, as long as it will only have 3 states)
* we can also have 00 -> 10 -> 01 -> 00, as long as reset state = 0 after encounter 3 times
* Note: 3 states can be presented by 2 digits (4 states at most)
* we just need update the digit based on the state machine 
* we firstly update first bit, first bit depends on two things
* First bit is going like this 0->0->1
* the trend is based on two things, (1) what is current value of input, (2) what is the second value
* we get 10 is because previous second bit is 1,
* Second trend is going like this 0->1->0->0
* Similarly, the trend is based on two things, (1) what is current value of input, (2) what is the first value
* we get 01 is because previous first bit is 0,
* 
* By calling one/two ^A[i], we are doing 1 0 1 0
* By calling & ~two/one, we are force it follow our rule
* 
* At first I used two = (two^A[i]) ^ one, but it is wrong.
* for example we need update 00->01
* if one = (one^A[i]) ^ two and two = (two^A[i]) ^ one; then it will give us 11
* however if we use the correct one   one = (one^A[i]) & ~two and two = (two^A[i]) & ~one, then it will give us 01, which follow
* the rule.
* 
*  I know this is hard to comprehend, we can uses an example:
*  (4,4,4,1) or in binary representation (100, 100, 100, 001)
*  ones =0, twos = 0
*  index = 0, one = 100^000 & 111 = 100 two = 100^000 & 011 = 000
*  index = 1, one = 100^100 & 111 = 000 two = 000^100 & 111 = 100
*  index = 2 one = 000^100 & 011 = 000 two = 100^100 & 111 = 000
*  index = 3 one = 000^001 & 111 = 001 two = 001 & 100 = 000
*  
*  we can see from above that, for those index that has bit = 1, we will have 00->10->01->00
  
 * @author hpPlayer
 * @date Apr 5, 2015 2:38:53 AM
 */
public class p137_sol3 {
    public int singleNumber(int[] A) {
        int one = 0, two = 0;//one first digit, two second digit
        for(int i = 0; i < A.length; i++){
            one = (one^A[i]) & ~two;
            two = (two^A[i]) & ~one;
        }
        return one;
    }
}
