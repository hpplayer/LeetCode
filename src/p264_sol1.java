import java.util.LinkedList;
/*
Ugly Number II 

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
*/

/**
 * This is a very brilliant solution that with the help of double linked list structure
 * What we do is creating the newest ugly number by taking the largest potential number in linkedlist.
 * What does that mean? It means we will scan the list and find the largest potential number that can produce the new ugly number.
 * We have three primes 2,3,5 to compose a new ugly number, so we will have three potential numbers, each of them is the largest number that
 * is smaller then last number in list divided by this prime. By doing this, we will always produce the newest number with current prime number
 * The next step is comparing those three new ugly numbers and inserting the smallest one into the list.
 * By repeating this n loops, the last element in linkedlist is the nth ugly number
 * 
 * Example:
 * Initial case, first element in list: 1
 * First loop: 
 * we found the largest potential candidates for those three primes are  1, 1, 1
 * So we can get three new ugly numbers: 1*2, 1*3, 1*5 
 * inserting the smallest one into the linkedlist
 * 
 * The idea of building three new ugly numbers from 3 primes and using the smallest one actually can be parsed as a modified merge sort
 * sol2 explain this algorithm in a better one, and sol2 can also be more easily understood, so sol2 is the best solution!
 * @author hpPlayer
 * @date Aug 19, 2015 8:23:01 PM
 */

public class p264_sol1 {
	public static void main(String[] args){
		System.out.println(new p264_sol1().nthUglyNumber(7));
	}
    public int nthUglyNumber(int n) {
    	LinkedList<Integer> ugly = new LinkedList<Integer>();
        ugly.push(1);//first ugly number
        int i2 = 0, i3 = 0, i5=0;//index for prime 2, 3, 5
        while(ugly.size() < n){
        	//get the largest number that will produce a new ugly number with this prime
        	while (ugly.getLast() >= ugly.get(i2)*2) i2++;
        	while (ugly.getLast() >= ugly.get(i3)*3) i3++;
        	while (ugly.getLast() >= ugly.get(i5)*5) i5++;
        	//produce the new ugly number from 3 primes and pick the smallest new ugly number
        	ugly.addLast((Math.min(ugly.get(i2)*2, Math.min(ugly.get(i5)*5, ugly.get(i3)*3))));
        }
        return ugly.getLast();      

    }
}
