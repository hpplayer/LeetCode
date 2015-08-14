/*
Number of Digit One

Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
*/

/**
 * This solution works in following way:
 * each number can be divided into three parts:
 * sub integers in (base - 1) * k
 * sub integers in integer starts with 1
 * sub integers in n - (base - 1)*k
 * Example 335:
 * 335: base 100
 * 1)we have k=3, three (base - 1), i.e. 3 * countDigitOne(99)
 * 0-99, 
 * 100-199
 * 200-299
 * 2)extra one from integer start with 1, i.e. 100
 * 100-199
 * 3)remaining integer 335- (base - 1)*3, i.e.  countDigitOne(35)
 * 0-35
 * 
 * For part 2, it is easy to calculate the number, if first digit > 1, then we got 1 * base extra 1, if first digit = 1, then we 
 * got n-base + 1 extra 1, ex: n= 13, base = 10, we got 13 - 10 + 1 = 4 extra 1 they are 10, 11, 12, 13, for 11, the second one will
 * be counted when we scan the remainders
 * 
 * Basically, this algorithm processes current first digit by doing recursion on n-1 scope, then add extra ones with that first digits, 
 * and last add 1 from remainder through recursion too.
 * 
 * sol2 is a solution with short code but hard to understand, it can be generalized to other digits like 2,3,4,...
 * sol3 is a mathematical solution but CANNOT be generalized to other digits like 2,3,4,...
 * sol4 is python version of sol1
 * sol5 is python version of sol2
 * sol6 is python version of sol3
 * 
 * Among those solutions:
 * sol2 is more recommended
 * @author hpPlayer
 * @date Aug 13, 2015 4:09:26 PM
 */
public class p233_sol1 {
	public static void main(String[] args){
		int n = 20;
		System.out.println(new p233_sol1().countDigitOne(n));
	}

    public int countDigitOne(int n) {
        //case input is non positive integers
        if(n <= 0) return 0;
        //no way to split 1 
        if(n == 1) return 1;
        //pow return double, so we need case it to integer
        //base is like 10 for 13, 100 for 130
        int base = (int) Math.pow(10, (n+"").length()-1);
        //get first digit, which help us find extra zeros come from 10, 100, 1000, etc
        int FirstDigit = n/base;
        int extraOnes = 0;
        //we will not get full load of extraOnes
        if(FirstDigit == 1){
            extraOnes = n - base + 1;//1 for 0th in 10, 100, 100, etc...
        }else{
            //full load
            extraOnes = base;
        }
        //total one is count(10^n-1) + extraOnes + (n-10^n*FirstDigit)
        return countDigitOne(base - 1) * FirstDigit + extraOnes + countDigitOne(n - base * FirstDigit);
    }
}
