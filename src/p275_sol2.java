/**
 * Recursive version of sol1. The main idea is same.
 * The start pointer will finally points to the node next to our target node (citations[mid] == mid)
 * So we just use len - start to convert the index, then return new index
 * @author hpPlayer
 * @date Sep 16, 2015 11:46:28 PM
 */
public class p275_sol2 {
    public int hIndex(int[] citations) {
        return binary(citations, 0, citations.length-1);
    }
    
    public int binary(int[] citations, int start, int end){
        if(start > end) return citations.length - start;
        
        int mid = start + (end - start)/2;
        int len = citations.length;
        if(citations[mid] == len - mid) return len - mid;
        if(citations[mid] > len - mid) return binary(citations, start, mid -1);
        return binary(citations, mid + 1, end);
    }
}
