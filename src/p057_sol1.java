import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * This is my original AC solution without help
 * My first version of solution is very lengthy and buggy
 * Usually, when you got a such code it means your idea may be incorrect
 * So I rewrite my code and get this solution, which I think is very clear and fast
 * 
 * My idea is:
 * We will split the list into three parts:
 * first part will contain all Intervals that are before interfered part 
 * second part will contain all Intervals that are interfered by the new node
 * third part will contain all Intervals that are after interfered part 
 * 
 * For first and third parts, we can simply add intervals into our result list
 * For second parts, we should merge them into one interval, which contains the leftmost and rightmost values from all intervals in this range
 * 
 * The leftmost edge should be the min(newInterval.start, start of the first interval in this part)
 * The rightmost edge should be the max(newInterval.end, end of the last interval in this part)
 * 
 * We may check them in several ways, here I simply check left edge when creating this mixed merge node, and I check the right edge 
 * every time we visit a new interval in this part
 * 
 * Why I use a result list?
 * Because we may shrink the input list when merging intervals, the new list can help us get rid of problems come from indexing
 * 
 * Sol2 uses a similar idea but with shorter code without extra space
 * 
 * Some solution suggests use binary search to speedup the search. For me, I don't think it will help much.
 * Why? because binary search will only help us get the end of first part,
 * for second part, we still have to use O(n) to scan each interval, to check how far we can reach in second part
 * Anyway, similar idea with binary search is in sol3
 * 
 * If we are allowed to modify the input list, sol2 is recommended since it is short and clear
 * If we are not allowed to do that, than my solution sol1 is recommended
 * @author hpPlayer
 * @date Sep 3, 2015 5:41:13 PM
 */
public class p057_sol1 {
	public static void main(String[] args){
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(0,2));
		list.add(new Interval(3,5));
		list.add(new Interval(6,8));
		list.add(new Interval(10,12));
		list.add(new Interval(13,15));
		
		for(Interval i : new p057_sol1().insert(list, new Interval(4,7))){
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
		List<Interval> result = new LinkedList<Interval>();
		if(intervals == null || intervals.size() == 0 ){
			result.add(newInterval);
			return result;
		}
		//if new interval is seperated in front
		if(newInterval.end < intervals.get(0).start){
			intervals.add(0, newInterval);
			return intervals;
		}
		
		//if new interval is seperated in back
		if(newInterval.start > intervals.get(intervals.size() - 1).end){
			intervals.add(newInterval);
			return intervals;
		}		
		boolean isStart = false;
		intervals.get(0).start = Math.min(intervals.get(0).start, newInterval.start);
		for(int i = 0; i < intervals.size(); i++){
			//extreme case, that the newInterval can be inserted without interfering with neighbor blocks
			if(i - 1 >=0 && intervals.get(i-1).end < newInterval.start && intervals.get(i).start > newInterval.end){
				result.add(newInterval);
			}
			
			if(intervals.get(i).end < newInterval.start || intervals.get(i).start > newInterval.end){
				result.add(intervals.get(i));
			}else{
				//we need add the start point of mix part, be careful, mix part's left part should be the leftmost bound we can reach in this part
				//which may be the start of newInterval
				if(!isStart){
					intervals.get(i).start = Math.min(intervals.get(i).start, newInterval.start);
					result.add(intervals.get(i));//add the boundary node
					isStart = true;
				}
				
				//when searching the mix part, we need find the rightmost bound we can reach, which may also includes the end of newInterval
				Interval last = result.get(result.size() - 1);
				//last.start = Math.min(last.start, intervals.get(i).start);
				last.end = Math.max(last.end, Math.max(newInterval.end, intervals.get(i).end));
			}
			
		}
		
		return result;
	}
}
