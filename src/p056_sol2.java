import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * This is a very brilliant merge-sort solution
 * Instead of sorting-then-insertion, here we do the sort and insertion at the same time
 * How?
 * We use merge sort.
 * Merge sort will split the list into many pairs of small list
 * We will merge those list based on their start value
 * When we merge small pairs, we will decide to insert or merge current Interval based on its end value
 * and the end value of last item in list. 
 * Since, during merging step, we guarantee the start values are following increasing order, we don't have to worry about 
 * inserting new cell in front.
 * 
 * Merge sort costs O(nlogn), the addition of checking end value costs O(1), so the total running time here is O(nlogn)
 * 
 * This solution uses the same idea as used in sol1, but with different implementation.
 * It can help us understand Merge-sort better
 * 
 * Remark:
 * 1) Merge sort, we always split in mid, then recursively visit left and right parts
 * But, how about the index assignment? should the left end in mid or mid -1? should t he right start from mid or mid +1?
 * Let's use an example to remember this
 * now, we have [1,2]
 * mid = (0 + 1)/2 = 0
 * left part range [0,0]
 * right part range [1,1]
 * so if we use (left, mid) to visit left part, it would give us same index range (0, 0)
 * if we use (mid+1, right) to visit right part, it would give us same index range (1,1)
 * perfect!
 * 
 * 2) Don't be afraid to use multiple temp lists in the recursion.
 * temp lists will help exactly mimic the operations in merge-sort like creating a larger merged list, or creating a small split list
 * contains two intervals
 * 
 * @author hpPlayer
 * @date Sep 3, 2015 3:45:12 PM
 */

public class p056_sol2 {
	public static void main(String[] args){
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(2,6));
		//list.add(new Interval(2,2));
		//list.add(new Interval(3,5));
		List<Interval> list2 = new ArrayList<Interval>();
		list2.add(new Interval(0,0));
		list2.add(new Interval(1,1));
		for(Interval i : new p056_sol2().merge(list, list2)){
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
        return  mergeSort(0, intervals.size() -1 , intervals);
    }
    
    public List<Interval> mergeSort(int start, int end, List<Interval> intervals){
        List<Interval> temp = new LinkedList<Interval>();// Like in mergeSort, we will produce several small List and then merge to a large list
        if(start > end) return temp;//out of boundary
        
        if(start == end){//we are looking at single cell, it happens when length of cell is odd
        	temp.add(intervals.get(start));
        	return temp;
        }
        
        if(start + 1 == end){//our split has reached bottom, now we have two cells that are ready to be merged
            Interval i1 = intervals.get(start);
            Interval i2 = intervals.get(end);
            if(i1.start < i2.start){//we should firstly put i1 into the result, then i2
                addOrMerge(temp, i1);
                addOrMerge(temp, i2);
            }else{//insert i2 then i1
                addOrMerge(temp, i2);
                addOrMerge(temp, i1);                
            }
            
            return temp;//we have reordered the bottom boundary case (2 intervals), just return, no need to merge it
        }
        
        int mid = (start + end) /2;//split in half, then recursive call
        //think about array of length 2, mid will return 0
        //to split it correctly, we let left part be left-mid, right part be mid+1, 1, which is exaclty we are visiting two cells.
        return merge(mergeSort(start, mid, intervals), mergeSort(mid + 1, end, intervals));
    }
    
    public List<Interval> merge(List<Interval> list1, List<Interval> list2){
         List<Interval> temp = new LinkedList<Interval>();//temp list to store merged list
         int i1 = 0, i2 = 0;//index in two lists, we can't remove list in front, so use index pointer instead
         while( i1 < list1.size() && i2 < list2.size()  ){
        	 //only move the pointer that we found the head is smaller
             if(list1.get(i1).start < list2.get(i2).start){
                 addOrMerge(temp, list1.get(i1++));
             }else{
                 addOrMerge(temp, list2.get(i2++));                    
             }
         }
         
         while(i1 < list1.size()){
             addOrMerge(temp, list1.get(i1++));
         }
         
         while(i2 < list2.size()){
             addOrMerge(temp, list2.get(i2++));
         }     
         
        return temp;
    }
    
    public void addOrMerge (List<Interval> temp, Interval i){
        if(temp.size() == 0){
            temp.add(i);
            return;
        }
        
        Interval last = temp.get(temp.size() - 1);
        if(last.end >= i.start){//if covered, don't forget last.end == i.start is also a covered case!!!!!!!!!!!!!!!!!!!!!!
            last.end = Math.max(last.end, i.end);
        }else{//not covered, just add it
            temp.add(i);
        }
        
    }
}
