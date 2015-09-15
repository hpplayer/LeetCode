/**
 * This is one of the best solution, with two-pass algorithm.
 * The tricky part is we don't how to assign candy to index i, unless we have got the information before and after it.
 * I have come up with a similar solution as sol1, but didn't finish it..
 * Here is the algorithm of sol1:
 * we will scan the input array two times, forward and backward
 * forward will update all indexes based on the information before it
 * backward will update all indexes based on the information after it
 * Then our final output array will pick the max value from forward and backward scan for each index i.
 * Obviously, we need at least one array to store the temp information from one scan
 * But here I use two arrays to make the logic more clear, one for forward scan and one for backward scan
 * 
 * Candy2() implements the same algorithm but with one array
 * 
 * Sol2 provides a one-pass algorithm, which is not so straightforward as sol1
 * 
 * Sol1 is more clear and easy-understanding, sol2 is faster, both are recommended
 * @author hpPlayer
 * @date Sep 15, 2015 1:30:56 PM
 */

public class p135_sol1 {
	public static void main(String[] args){
		int[] ratings = {2,3,2};
		System.out.println(new p135_sol1().candy(ratings));
	}
    public int candy(int[] ratings) {
        int len = ratings.length;
        int left[] = new int[len];
        int right[] = new int[len];
        left[0] = 1;
        right[len-1] = 1;
        for(int i = 1; i < len; i++){
            if(ratings[i] > ratings[i-1]){
                left[i] = left[i-1] + 1;
            }else{
                left[i] = 1;
            }
        }
        
        for(int i = len -2; i>=0; i--){
            if(ratings[i] > ratings[i+1]){
                right[i] = right[i+1] + 1;
            }else{
                right[i] = 1;
            }
        }
        
        int total = 0;
        for(int i = 0; i < len; i++){
            total += Math.max(left[i], right[i]);
        }
        
        return total;
    }
    
    public int candy2(int[] ratings) {
        int len = ratings.length;
        int[] forward = new int[len];
        forward[0] = 1;
        for(int i = 1; i < len; i++){
            if(ratings[i] > ratings[i-1]){
                forward[i] = forward[i-1] + 1;
            }else{
                forward[i] = 1;
            }
        }
        
        int result = forward[len-1];//boundary case
        int temp = 1;//record the candy 
        for(int i = len -2; i >= 0; i--){
            if(ratings[i] > ratings[i+1]){
                temp ++;
                result += Math.max(temp, forward[i]);
            }else{
                temp = 1;
                result += Math.max(1, forward[i]);
            }
        }
        
        return result;
    }
}
