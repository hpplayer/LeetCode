import java.util.Arrays;

/**
 * Sorting solution takes O(nlogn) time, actually we can solve this problem in O(n) time.
 * How? we firstly scan the array and assign number of papers to each citation. This step actually convert the original x Axis to y Axis
 * and convert original y Axis to x Axis. But we need to notice that citation is not cumulative but the paper is. Which means if we want to 
 * build (or called rotate) the graph, for each index i we need to take the accumulative papers before index i and plus papers has exactly citation i
 * So it implies that later we need to scan the array backward so that lower citations will include papers from higher citations. 
 * Another question is we may have some papers have a very large citations, do we need to build an array based on this citation? The solution is no
 * Since our H index is the last index where citations > papers we will stop the scan when we found the first index that has paper >= citations.
 * Why not we return the index i instead of i + 1, i.e. why we dont't move the pointer back to the last index? Please see explanation below
 * 
 * 
 * @author hpPlayer
 * @date Sep 16, 2015 10:38:22 PM
 */

public class p274_sol2 {
	public static void main(String[] args){
		int a[] = {1, 1};
		System.out.println(new p274_sol2().hIndex(a));
	}
	
    public int hIndex(int[] citations) {
        int len = citations.length;
        //assume n is the len of citations
        //the length is built based on num of citations, the range can start from 0 to n
        //we can treat citations >n to be n, since our H-index will not exceed n
        int[] papers = new int[len+1];
        
        for(int i = 0; i < len; i++){
            if(citations[i] > len) papers[len] ++;//cut citations > n
            else papers[citations[i]]++;
        }
    //System.out.println(Arrays.toString(papers));
    //our current array is end at len, which is supposed to be len -1 in input citations
    //so if look from y axis(citations) actually we are top shift our index(acutal higher), now we want to down shift (lower) index to original index
    //we stop at the first index papers[i] >= i, the H-index is supposed to be this index plus 1 (if looks at citation axis)
    //but since we already higher the index, so actually the returned index is the index + 1 in origial axis, no need to convert
    
        if(papers[len] >= len) return len;//if highest citation already has more than n papers, then stop here and the H-index will be n 
        for(int i = len-1; i >= 0; i--){
        	papers[i] += papers[i+1]; 
            if(papers[i] >= i) return i; 
        }
        
        return -1;
    }
}
