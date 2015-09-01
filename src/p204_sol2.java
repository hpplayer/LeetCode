/**
 * This algorithm is called Sieve of Eratosthenes
 * Directly speaking, we can create a boolean array of length n,
 * each cell stands for whether current index(number) is prime
 * This array is the key part, and it help us skip a lot of repeated calculation
 * Like using this array we will skip candidate 4 in the main loop, as we have looked at the candidate 2, during which we have scanned candidate 4
 * 
 * The basic idea:
 * We check each number smaller than n:
 * 	if this number has not been visited before, we start our scan
 *   Instead of looking number smaller than current number, we will look all numbers that are multiples of current number
 *   	we mark each multiple as visited or as prime here. It is truly non prime number, since we have found factors that can compose it
 * That's it
 * 
 * Below code does the exact same thing as described above.
 * 
 * By comparison the hint of problem provides a similar code but different work flow. Sol3 gives such solution
 * 
 * That's ok, thanks to our array, either work flow will not scan visited number, so the speed should be similar
 * 
 * Update:
 * 1) the code provides here may still visit some visited cells in the inner loop. But it already skip a lot of repeated calculation
 * so the speed is still improved a lot compared with sol1
 * 2) Also the solution given by hint is not good as well, as it has to scan the whole array after the main loop is done. 
 * 
 * @author hpPlayer
 * @date Sep 1, 2015 4:10:46 PM
 */
public class p204_sol2 {
	public static void main(String[] args){
		System.out.println(new p204_sol2().countPrimes(50));
	}
    public int countPrimes(int n) {
        //we firstly assume all values are primes
        boolean NotPrimes[] = new boolean[n];
        int count = 0;
        
        //main loop will go through each unvisited cells
        for(int i = 2; i < n; i++){
            if(!NotPrimes[i]){
                count ++;//we can safly let count ++ if NotPrimes[i] is false, since the inital case 2 is prime
                //each cell visited in this inner loop will be notPrimes
                for(int j = 2; j*i < n; j++){
                	if(NotPrimes[i*j]) System.out.println("here");
                    NotPrimes[i*j] = true;
                }
            }
        }
        
        return count;
    }
    
    
}
