import java.util.ArrayList;
import java.util.List;

/**
 * The main part is same with sol2
 * But here we use binary search to find the right bound of first part
 * For remaining parts, we still have to use linear search
 * @author hpPlayer
 * @date Sep 3, 2015 7:18:30 PM
 */

public class p057_sol3 {
	public static void main(String[] args){
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1,5));
		
		for(Interval i : new p057_sol3().insert(list, new Interval(5,7))){
			i.print();
		}
	}
	
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
        int i = searchIndex(newInterval.start, intervals, 0, intervals.size() -1);
       
        while(i < intervals.size() && newInterval.end >= intervals.get(i).start){
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), Math.max(intervals.get(i).end, newInterval.end));
            intervals.remove(i);
        }
        
        intervals.add(i, newInterval);
        return intervals;
    }
    
    public int searchIndex(int target, List<Interval> intervals, int start, int end){
        while(start <= end){
            int mid = (start + end)/2;
            int value = intervals.get(mid).end;
          //too large, move left, we move end when >= target, so in the end, the end will point to the 
           //leftmost value that is larger than target. By comparison, the start will point to the rightmost value
            //that is smaller than target
            if (value >= target){
                end = mid - 1;
            }else{//too small, move right
                start = mid + 1;
            }
        }
        
        return start;
    }
}
