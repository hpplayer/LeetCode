/*
 *  Count Primes 
 * Description:
 * 
 * Count the number of prime numbers less than a non-negative number, n.
 * 
 * 
 * 
 * Hint:
 * 
 *   Let's start with a isPrime function. To determine if a number is prime, we need to check if 
 *   it is not divisible by any number less than n. The runtime complexity of isPrime function 
 *   would be O(n) and hence counting the total prime numbers up to n would be O(n2). Could we do better?
 *   
 *   As we know the number must not be divisible by any number > n / 2, we can immediately cut the total 
 *   iterations half by dividing only up to n / 2. Could we still do better?
 *   
 *   Let's write down all of 12's factors:
 * 
 * 	2 ¡Á 6 = 12
 * 	3 ¡Á 4 = 12
 * 	4 ¡Á 3 = 12
 * 	6 ¡Á 2 = 12
 * 
 * As you can see, calculations of 4 ¡Á 3 and 6 ¡Á 2 are not necessary. Therefore, we only need to consider 
 * factors up to ¡Ìn because, if n is divisible by some number p, then n = p ¡Á q and since p ¡Ü q, we could derive that p ¡Ü ¡Ìn.
 * 
 * Our total runtime has now improved to O(n1.5), which is slightly better. Is there a faster approach?
 * 
 *	public int countPrimes(int n) {
 *	   int count = 0;
 *	   for (int i = 1; i < n; i++) {
 *	      if (isPrime(i)) count++;
 *	   }
 *	   return count;
 *	}
 *	
 *	private boolean isPrime(int num) {
 *	   if (num <= 1) return false;
 *	   // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
 *	   // to avoid repeatedly calling an expensive function sqrt().
 *	   for (int i = 2; i * i <= num; i++) {
 *	      if (num % i == 0) return false;
 *	   }
 *	   return true;
 *	}
 *   
 *   The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n. 
 *   But don't let that name scare you, I promise that the concept is surprisingly simple.
 *
 *	[Sieve of Eratosthenes](http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
 * 
 *	[https://leetcode.com/static/images/solutions/Sieve_of_Eratosthenes_animation.gif]
 *	[http://commons.wikimedia.org/wiki/File:Sieve_of_Eratosthenes_animation.gif]
 *
 *   Sieve of Eratosthenes: algorithm steps for primes below 121. "Sieve of Eratosthenes Animation"() 
 *   by SKopp is licensed under CC BY 2.0.
 *
 *      * [Skoop](http://de.wikipedia.org/wiki/Benutzer:SKopp)
 *	* [CC BY 2.0](http://creativecommons.org/licenses/by/2.0/)
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
 * number is p, we can always mark off multiples of p starting at p^2, then in increments of p: p^2 + p, p^2 + 2p, ... 
 * Now what should be the terminating loop condition?
 *   
 * It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. 
 * Do you still remember Hint #3?
 *   
 * Yes, the terminating loop condition can be p < ¡Ìn, as all non-primes ¡Ý ¡Ìn must have already been marked off. 
 * When the loop terminates, all the numbers in the table that are non-marked are prime.
 * 
 * The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n). 
 * For the more mathematically inclined readers, you can read more about its algorithm complexity on 
 * [Wikipedia](http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_complexity).
 * 
 *	public int countPrimes(int n) {
 *	   boolean[] isPrime = new boolean[n];
 *	   for (int i = 2; i < n; i++) {
 *	      isPrime[i] = true;
 *	   }
 *	   // Loop's ending condition is i * i < n instead of i < sqrt(n)
 *	   // to avoid repeatedly calling an expensive function sqrt().
 *	   for (int i = 2; i * i < n; i++) {
 *	      if (!isPrime[i]) continue;
 *	      for (int j = i * i; j < n; j += i) {
 *	         isPrime[j] = false;
 *	      }
 *	   }
 *	   int count = 0;
 *	   for (int i = 2; i < n; i++) {
 *	      if (isPrime[i]) count++;
 *	   }
 *	   return count;
 *	}
 *   
 *               
 */



/**
 * Sol1 should work but is too slow on large data test
 * 
 * This is a pure math problem.
 * The raw idea is checking each number < n,
 * then dividing current number by all numbers smaller than it, if remainder of any division is 0, then it is not a prime
 * 
 * We can use multiply tricks to speedup the procedure to check Prime
 * 
 * Trick 1: We don't need to check any number larger than n/2, i.e skip n/2 numbers. Why?
 * Like n = 6,
 * we will check:
 * 2, 3, 4, 5
 * we found for 4,5, the remainder must not be zero, as the quotient will smaller than 2
 * 
 * Trick 2: Actually, we can not only skip n/2, we can also skip any number larger than n^0.5 numbers, why?
 * Each non prime number can be divided into the product of two numbers, in which both numbers < given number
 * Assume the smaller number is c1, the larger number is c2, then when we check c1, we already check c2 at the same time,
 * so we don't need to look at c2. The boundary of larger and smaller numbers is n ^ 0.5. So we only need to check the number from 2 to 
 * number <= n^0.5, which will cover all products and numbers < given number
 * 
 * By using Trick2, we have n numbers that need to be verified, and each verification costs O(n^0.5), so the total time is O(n^1.5)
 * 
 * Below code provides such solution using trick 2, but still cannot pass large test, still too slow
 * 
 * Sol2 provides a solution with faster speed called Sieve of Eratosthenes
 * Sol3 is the original code given by hint, it uses Sieve of Eratosthenes plus the trick2 we talked above, i.e we don't trick numbers 
 * larger than n^0.5
 * 
 * Sol2 and Sol3 are both good solutions and have similar speed, but sol2 is more neat and clean.
 * So sol2 is more recommended though sol3 uses more tricks
 * 
 * Remark:
 * 1) Remember 0 and 1 are not primes!!!!
 * 2) Sol2 and Sol3 both applied Sieve of Eratosthenes algorithm, whose running complextiy is O(nlognlogn) and space complexity is O(n)
 * @author hpPlayer
 * @date Sep 1, 2015 3:41:27 PM
 */
public class p204_sol1 {
	   public int countPrimes(int n) {
	        int count = 0;
	        for(int i = 1; i < n; i++){
	            if(isPrimes(i)){
	                count ++;
	            }
	        }
	        
	        return count;
	    }
	    
	    public boolean isPrimes(int n){
	        if(n <= 1) return true;
	        for(int i = 2; i*i <= n; i++){
	            if(n % i == 0) return false;
	        }
	        
	        return true;
	    }
}
