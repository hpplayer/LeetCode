import java.util.ArrayList;
import java.util.List;



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
        
        System.out.println(i);
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
            if (value >= target){//too large, move left
                end = mid - 1;
            }else{//too small, move right
                start = mid + 1;
            }
        }
        
        return start;
    }
}
