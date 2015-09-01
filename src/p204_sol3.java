/**
 * This solution combines the trick mentioned in sol1, in which we can skip all numbers that larger than number^0.5
 * The given hint has described this algorithm very clearly.
 * 
 * We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 
 * must not be primes, so we mark them off as non-primes. Then we look at the next number, 3. Similarly, 
 * all multiples of 3 such as 3 ¡Á 2 = 6, 3 ¡Á 3 = 9, ... must not be primes, so we mark them off as well. 
 * Now we look at the next number, 4, which was already marked off. What does this tell you? Should you 
 * mark off all multiples of 4 as well?
 *   
 * 4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible 
 * by 2 and were already marked off. So we can skip 4 immediately and go to the next number, 5. Now, 
 * all multiples of 5 such as 5 ¡Á 2 = 10, 5 ¡Á 3 = 15, 5 ¡Á 4 = 20, 5 ¡Á 5 = 25, ... can be marked off. 
 * There is a slight optimization here, we do not need to start from 5 ¡Á 2 = 10. Where should we start marking off?
 *   
 * In fact, we can mark off multiples of 5 starting at 5 ¡Á 5 = 25, because 5 ¡Á 2 = 10 was already marked off 
 * by multiple of 2, similarly 5 ¡Á 3 = 15 was already marked off by multiple of 3. Therefore, if the current 
 * number is p, we can always mark off multiples of p starting at p2, then in increments of p: p^2 + p, p^2 + 2p, ... 
 * Now what should be the terminating loop condition?
 *   
 * It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. 
 * Do you still remember Hint #3?
 *   
 * Yes, the terminating loop condition can be p < ¡Ìn, as all non-primes ¡Ý ¡Ìn must have already been marked off. 
 * When the loop terminates, all the numbers in the table that are non-marked are prime.
 * 
 * The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n). 
 * 
 * Remark:
 * 
 * @author hpPlayer
 * @date Sep 1, 2015 4:39:51 PM
 */
public class p204_sol3 {
    public int countPrimes(int n) {
        boolean[] isPrimes = new boolean[n];
        //this loop can help us deal with boundary case that n = 0 and n = 1
        for(int i = 2; i < n; i++){
            isPrimes[i] = true;
        }
        
        //we only need to visit all numbers i^2 < n, as described before.
        for(int i = 2; i * i < n; i++){
            if(!isPrimes[i]) continue;//has marked before
            //our search will begin after i*i
            //since if current number is new factor, then all numbers < i*i should already been visited
            // all numbers smaller than i*i have been marked off, so we can start i*i, 
            //we are checking the multiples of i, so the increment should be i, i.e. j += i
            for(int j = i *i; j < n; j+=i){//!!!!!!!!important, j = i*i is important to skip previously visited numbers
                isPrimes[j] = false;
            }
        }
        
        int count = 0;
        for(boolean bool : isPrimes){
            if(bool) count ++;
        }
        return count;
    }
    
    public int countPrimes2(int n) {
        if(n == 0 || n == 1) return 0;
        boolean[] notPrimes = new boolean[n];
        
        notPrimes[0] = notPrimes[1] = true; // 0 and 1 are not prime
        
        //we only need to visit all numbers i^2 < n
        for(int i = 2; i * i < n; i++){
            if(notPrimes[i]) continue;//has marked before
            //our search will begin after i*i
            //since if current number is new factor, then all numbers < i*i should already been visited 
            for(int j = i *i; j < n; j+=i){
                notPrimes[j] = true;
            }
        }
        
        int count = 0;
        for(int i = 0; i < notPrimes.length; i++){
            if(!notPrimes[i]) count ++;
        }
        
        return count;
        
    }
    
}
