/**
 * This is my original AC solution without help
 * I actually mis-click the tag, then found it is actually a binary search problem
 * Then the problem becomes simple, each time we check the mid value, if it is a bad version, then go to
 * left part means we have included too much bad version, otherwise we go to right part.
 * 
 * Remark:
 * 1) Be careful with boundary case like start == end. If now start == end and its value is bad, then current version is the first bad value
 * if its value is clean, we move start++, and next value is guaranteed to be bad
 * Draw a graph would help us get rid of error
 * 2) Be careful with overflow, I firstly set (start + end )/2, but got error, after change the format my solution got AC!
 * 
 * I did not find any better solution, so my solution is best!
 * @author hpPlayer
 * @date Sep 11, 2015 12:53:00 AM
 */
public class p278_sol1 {
	public static void main(String[] args){
		System.out.println(new p278_sol1().firstBadVersion(3));
	}
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        
        while(start <= end){
            int mid = start + (end - start)/2;
            if(isBadVersion(mid)){
                end = mid - 1;
            }else{
                start = mid + 1;    
            }
        }
        
        return start;
    }
    
    public boolean isBadVersion(int i){
    	if(i >= 2) return true;
    	return false;
    }
}
