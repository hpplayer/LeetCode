import java.util.List;
/**
 * It uses the similar idea as in sol1
 * Our sequence can be split into three parts,
 * first part is all intervals before merge part
 * Second part is all intervals that needs to be merged 
 * third part is all intervals after merge part
 * 
 * The boundary of first part is all intervals that have end < new interval's start
 * The boundary of second part is all intervals after first part until we have interval's start > merged part's largest end
 * this largest end may come from new interval's end or some merged interval's end
 * The boundary of third part is all intervals after the second part
 * 
 * The algorithm here is simple:
 * use an index pointer, it will first skip all intervals into the second part
 * then use newInterval to store the left bound of second part
 * for example 
 * original:[3, 5] new:[2, 4]. In this example, the left bound will be the start of new node
 * original [1,3] new [2,4]. In this example, the left bound will be the start of old node
 * In either way, we will update new interval's left bound to be the leftmost bound. As described above, after found the left bound,
 * we no longer need it as we only care about the right bound, that will separate second and third part. 
 * 
 * In addition, we let the newInterval also store the right bound of second part.
 * The range of second merge part is mainly decided by the length of new Interval
 * When we found the last interval in second part, we just compare its end with new interval's end,
 * So we can get the rightmost bound of second part
 * 
 * Now new interval's start is the leftmost bound and its end is the rightmost bound
 * In other words, now new interval is the merged interval of all intervals in second part
 * We just insert it into index i and left third part there, so then our sequence will automatically become
 * first part-> merged second -> third part
 * 
 * which is the result 
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 6:24:34 PM
 */

public class p057_sol2 {
	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
		
		void print(){
			System.out.println("Start: " + start + " End: " + end);
			
		}
	}
	   public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	        int i = 0;
	        //skip first part
	        while(i < intervals.size() && intervals.get(i).end < newInterval.start) i++;
	        //when stop here, we have passed all interval's that have end < newInterval's start
	        //second part starts when newInterval's start smaller than interval's end
	        //end when interval's start larger than newInterval's end
	        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
	            //we are updating the newInterval where its min and max are the bounds of this range
	            newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start), Math.max(newInterval.end, intervals.get(i).end));
	            intervals.remove(i);//we will stop in index i, and continously remove intervals
	        }
	        //the while loop stops when our mix end, which means newInterval.end < intervals.get(i).start
	        //now i is the start of third part
	        //so we need insert new interval before third part
	        intervals.add(i, newInterval);
	        return intervals;
	    }
}
