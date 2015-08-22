/**
 * This is the modified merge sort algorithm, which can also be treated as Dynamic programming since it uses the value from previous computation
 * Ugly numbers can only be produced by previous ugly numbers, otherwise it will not be treated as ugly (think about p263, how we define the ugly number)
 * So, based on the basic prime number, we can split the complete ugly number into three sequences:
 * Total list: 1, 2, 3, 4, 5, 6, 8, ....
 * Sequence 1: 1*2, 2*2, 3*2, 4*2, 5*2, 6*2, 8*2
 * Sequence 2: 1*3, 2*3, 3*3, 4*3, 5*3, 6*3, 8*3
 * Sequence 3: 1*5, 2*5, 3*5, 4*5, 5*5, 6*5, 8*5
 * 1, 2, 3, 4, 5, 6,8 are first seven numbers in the sequence.
 * 1 in total list in the initial case, which we already define 1 can be treated as ugly number
 * In each sequence, we will simply create a new ugly number by following the created total list and multiplying the corresponding prime number
 * So this is like, we are creating the total list by splitting the previous total list into three sequences(updating three subsequences),
 * then merging them and creating the new element in total list
 * Ex:
 * Inital case in total list: 1
 * we splitting 1 into three sequences, and getting new ugly number 2,3,5
 * seq1: 1*2
 * seq2: 1*3
 * seq5: 1*5
 * Then we merge 2 into total list, now the total list: 1,2
 * We splitting 2 into three sequences:
 * seq1 2*2
 * seq2 1*3, 2*3
 * seq3 1*5, 2*5
 * Then we merge 3 into total list, now the total list: 1,2,3
 * We splitting 3 into three sequences:
 * seq1 2*2, 2*3
 * seq2 2*3, 3*3
 * seq3 1*5, 2*5, 3*3
 * and so on....
 * 
 * Remark:
 * 1) this algorithm is at least very similar to merge sort, but with a little modification
 * 2) Each time we will pick the smallest head elements in three sequences into our total list
 * 3) Sometimes, head elements maybe identical for two or more sequences, in this case, we will pick all of them, so all sequences will be 
 * updated with new head elements
 * 4) Time complexity: O(n), Space complexity: O(n)
 * 5) This algorithm uses a very smart technique to save space. We are supposed to use three lists to keep track of three sequences.
 * But we observed that, each element in each sequence is composed of left part that is a created ugly number in ugly list and right part 
 * the prime number of the sequence, so we can simply use three index pointers to keep track the change of left part to save spaces
 * So, each time, when we insert the heading element in one sequence into the final ugly number list, we can just move index one step further 
 * which means we will points to the next number in the ugly list, in other words points to the next left part, thus prime number * [ ++index]
 * will give us the next heading element in that sequnce. Very smart technique!
 * 
 * So based on the clear idea, fast algorithm and concise code of sol2, sol2 is the best solution towards this problem!
 * @author hpPlayer
 * @date Aug 20, 2015 6:38:27 PM
 */
public class p264_sol2 {
    public int nthUglyNumber(int n) {
    	int[] ugly = new int[n];
    	int num2 = 2, num3= 3, num5 = 5;//current number got from multiplying primes
    	int index2 = 0, index3 = 0, index5 = 0;
    	ugly[0] = 1;
    	for(int i = 1; i < n; i ++){
    		//pick the smallest element in three sequences and insert into the merged sequence
    		int newUgly = Math.min(num2, Math.min(num3, num5));
    		ugly[i] = newUgly;
    		//We use one element in num2 sequence so we need move index2 one step further and points to the next num2 element
    		if (newUgly == num2){
    			num2 = 2 * ugly[++index2];
    		}
    		//Notice: here we use three ifs not if-else if, why?
    		//Because we may have same elements in several sequences, and we will take all of them from sequences, so we will check each sequence one by one
    		if (newUgly == num3){
    			//We use one element in num3 sequence so we need move index3 one step further and points to the next num3 element
    			num3 = 3*ugly[++index3];
    		}
    		if (newUgly == num5){
    			//We use one element in num5 sequence so we need move index5 one step further and points to the next num5 element
    			num5 = 5*ugly[++index5];
    		}
    	}
    	
    	return ugly[n-1];
    	}
}
