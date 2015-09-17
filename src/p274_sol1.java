import java.util.Arrays;
/*
H-Index

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
and the other N - h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
*/

/**
 * This is my original AC solution with help
 * First, let's get understand what is h value:
 * 
 * A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N - h papers have no more than h citations each.
 * In other words, a scholar with an index of h has published h papers each of which has been cited in other papers at least h times.
 * 
 * citations(ascending)
 * |../
 * | / ..
 * |/_45__ papers (ascending)
 * 
 * H index is actually a point at or above the diagonal of above graph, which has a degree of 45.
 * Then any papers smaller than this H index, will have at least H citations. Of course our those papers can have more citations than H.
 * 
 * I firstly sort the array. The later the cell is, the higher citations it has.
 * According to the description above, if our sort is descending, then we just need to find the last index of papers, where its citation
 * is larger than its paper index.
 * But since now we are visiting the array forward with ascending order, while our expect index should be counting backward. So
 * we need to use len - i to check the index, The first index i, where our remaining papers are less than its citations, then 
 * len - i (convert forward index i to its backward index len - i) will be such H-index
 * 
 * Simple approach, but may not the problem really want to test.
 * 
 * Sol1 is my sorting solution, O(logN) time with O(1) space
 * Sol2 is another brilliant solution, O(N) time with O(N) space
 * 
 * Sol2 is more recommended, since it uses trick and faster!
 * @author hpPlayer
 * @date Sep 16, 2015 9:00:26 PM
 */
public class p274_sol1 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for(int i = 0; i < citations.length; i++){
            if(citations[i] >= citations.length - i){
                return citations.length - i;
            }   
        }
        
        return -1;
    }
}
