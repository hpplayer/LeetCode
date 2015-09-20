/*
Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.
*/


/**
 * My AC solution without help
 * It is similar to p27_sol2, one pass algorithm
 * Nothing special.
 * 
 * Remark:
 * Care about the start of virtual arary, since 
 * we may have arary like [1,1]. So the virtual ary
 * should at least have length > 1 (except for empty ary) 
 * 
 * We have another simple approach see sol2
 * @author hpPlayer
 * @date Mar 26, 2015 3:06:14 PM
 */
public class p026_sol1 {
    public int removeDuplicates(int[] A) {
        int len = A.length;
        if(len == 0 || len == 1) return len;
        int index = 1;
        for(int i = 1; i < len; i++){
            if(A[i] != A[index-1]){
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}
