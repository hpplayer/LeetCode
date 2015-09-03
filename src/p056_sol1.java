import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Merge Intervals 

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/**
 * This is my original AC solution without help
 * My algorithm assumes, our input Intervals are in sequence and will only affect the last Interval in result list
 * This means, we will never insert the intervals in front of last Interval in result list.
 * But, unfortunately, the given Intervals are not in order having test case like [[1,4], [0,0]]
 * So my idea is firstly using the comparator to sort the input list based on start.
 * By doing this, we no longer need to worry about the inserting front or changing the start of Intervals in result list
 * 
 * The main program is keeping a parameter that tells how far current intervals in result list can reach.
 * If next input interval is out of the range, we create a new interval, otherwise, we will expand the range that indicated by 
 * the last cell in the result list
 * 
 * My algorithm is simple
 * Sorting costs nlogn, then scanning the list costs n
 * So the total running time should be O(nlogn + n), or simply O(nlogn) if n is very large
 * 
 * Remark:
 * Comparator() will always ask you to implement a compare() function
 * To write it correctly, you need add the generic type of comparator
 * like Comparator<Interval>(){
 * 	public int compare (int x, int y)
 * }
 * Compare the front interval with back interval, if we return negative value it means, we will keep it in increasing order without change
 * if we return a positive value it means we will keep swap x and y, and sort in descending order
 * It is like Compare asks: whether swap x and y if x < y? if no, return negative, if yes, return positive
 * 
 * 
 * Sol1 is my solution that using sort-then-merge which is very good, it's running time is O(nlogn + n)
 * Sol2 lists a merge-sort algorithm that doing sort-and-merge at the same time, which is also a good solution, its time is O(nlogn)
 * 
 * Both sol1 and sol2 are recommended, sol1 is more clear but slower, sol2 is more faster but a bit lengthy
 * @author hpPlayer
 * @date Sep 3, 2015 2:22:47 PM
 */
public class p056_sol1 {
	public static void main(String[] args){
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1,4));
		list.add(new Interval(0,0));
		for(Interval i : new p056_sol1().merge(list)){
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

	public List<Interval> merge(List<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>(){

			public int compare(Interval i1,  Interval i2) {
				return i1.start - i2.start;
			}

			
		});
		int maxRange = 0;
		//int minRange = Integer.MAX_VALUE;
		List<Interval> result = new ArrayList<Interval>();
		for (Interval i : intervals) {
			//System.out.println("AAAA");
			if (i.start <= maxRange) {
				if (result.size() == 0) {
					result.add(new Interval(i.start, i.end));
				} else {
					if (i.end >= maxRange) {
						int start = result.get(result.size() - 1).start;
						result.remove(result.size() - 1);
						result.add(new Interval(start, i.end));
					}
				}

			} else {
				result.add(new Interval(i.start, i.end));
			}
			/* can be get rid of due to now input list is sorted on the i.start
			if(i.start < minRange){
				//System.out.println(i.start);
				int end = result.get(result.size() - 1).end;
				result.remove(result.size() - 1);
				result.add(new Interval(i.start, end));
			}
			*/
			maxRange = Math.max(maxRange, i.end);
			//minRange = Math.min(minRange, i.start);
		}


		return result;
	}
}
