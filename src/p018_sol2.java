import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/**
 * Three implementation of HashMap-based approach, which reduce 4-sum problem to 2-sum problem(O(n^2))
 * Surprisingly, they are all very slow, around 450 - 600 ms, compared with trivial O(n^3) approach(around 420ms, best can be 393ms)
 * fourSum() stores pair in hashtable with arraylist, and skip duplicates in real computation with variable control, this is the fastest
 * implementation of hashMap-based approach (time: around 450ms)
 * 
 * fourSum2() stores pair in hashtable with arraylist, but use hashset to remove duplicate from results, this is not so fast since 
 * we need iterate all loops (though we didnt do any calculation in invalid loop), and all take O(n) time to convert from hashSet
 * to ArrayList(time: around 450ms)
 * 
 * fourSum3() stores pair in hashtable with linkedhashset. At first, I used hashset, but then I found it will cause bug since 
 * hashset does not preserve the order, which will make our algorithm in mass. But linkedhashset's insertion time is O(n), so it is 
 * also very time consuming (time: 600ms)
 * 
 * This approach converts 4 sum to 2 sum problem,
 * Basically, we first build a hashMap that stores the two sum of each possible pair, say p.
 * Then we check each possible sum pair in the hashtable, if there is corresponding one, say q, which we have p + q = target,
 * and index in p and q are all valid, then we can add this 4 pairs into our result
 * 
 * Remark:
 * As usual, we need sort array first to save a lot of time
 * We can combine two index into a pair object, so manipulation will be more convenient.
 * The hashtable approach only taks O(n^2) time, O(n) for building hashtable, O(n) for search the hashtable and find cooresponding part
 * maybe we also need to consider the time for sort array and handle collision, but overall hashtable is a good approach to this problem
 * @author hpPlayer
 * @date Mar 23, 2015 5:51:57 PM
 */
public class p018_sol2 {
	public static void main(String[] args) {
		// int[] ary = {-1, 0, 1, 2, -1, -4};
		// int[] ary = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
		// int[] ary = {0,0,0};
		//int[] ary = { 1, 0, -1, 0, -2, 2 };
		int[] ary = {-3,-2,-1,0,0,1,2,3};
		//int[] ary = {-5,5,4,-3,0,0,4,-2};
		for (List<Integer> temp : new p018_sol2().fourSum3(ary, 0)) {
			System.out.println(temp);
		}
	}
	class Pair{
		int smallNode;
		int largeNode;
		public Pair(int small, int large){
			this.smallNode = small;
			this.largeNode = large;
		}
		
		//if node p is a valid next node of current Node
		public boolean isNext(Pair p){
			//there is no relationship between 
			boolean result;
			//System.out.println("p's small: " + p.smallNode + " p's large: " + p.largeNode);
			//System.out.println("this's small: " + this.smallNode + " this's large: " + this.largeNode);
			/*
			if(this.smallNode >= p.largeNode || this.smallNode >= p.smallNode){
				result = false;
				System.out.println("result: " + result);
				return false;
			}
			if(this.largeNode >= p.smallNode || this.largeNode >= p.largeNode){
				result = false;
				System.out.println("result: " + result);
				return false;
			}
			result = true;
			System.out.println("result: " + result);
			return true;
			*/
			return this.largeNode < p.smallNode;
		}
		
		public boolean isSame(Pair p, int[] num){
			return num[p.smallNode] == num[this.smallNode] && num[p.largeNode] == num[this.largeNode];
		}
	}
	
	
	//best approach!!!
		public List<List<Integer>> fourSum(int[] num, int target) {
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			//HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
			if(num == null || num.length < 4){
				return new ArrayList<List<Integer>>();
			}
			
			HashMap<Integer, List<Pair>> twoSums = new HashMap<Integer, List<Pair>> ();
			Arrays.sort(num);//sort array, so we know the small node in ArrayList is increasing
			for(int i = 0; i < num.length; i++){
				for(int j = i+1; j < num.length; j++){
					int sum = num[i] + num[j];
					Pair p = new Pair(i, j);//num[i] may equal num[j]
					if(!twoSums.containsKey(sum)){
						twoSums.put(sum, new ArrayList<Pair>());//store all valid pair in the sum
					}
					
					List<Pair> pairs = twoSums.get(sum);
					pairs.add(p);
				}
			}
			//System.out.println(twoSums.size());
			for(int twoSum:twoSums.keySet()){
				int diff = target - twoSum;//find the corresponding part
				if(twoSums.containsKey(diff)){//found match
					List<Pair> pairs1 = twoSums.get(twoSum);
					List<Pair> pairs2 = twoSums.get(diff);
					Pair prev1 = null;//record prev pair1
			
					for(Pair p1 : pairs1){//order of pairs1 or pairs2 does not matter, since we will check all keys in hashtable anyway
						//we will keep the order by p1.isNext(p2)
					
						if(prev1 != null){//if we have stored the prev pair1
							if(prev1.isSame(p1, num)) continue;//help remove duplicate like 2,2,3
						}
						Pair prev2 = null;//record prev pair2, why here? we may use same p2 but different p1
						//ex: p1:(-5,0), (-3,-2) p2 :(4,5) , (4,5) both sum equals to 4
						for(Pair p2 : pairs2){
							if(prev2!=null){//help remove duplicate like 2,2,3
								//System.out.println("prev2's small: " + prev2.smallNode + " prev2's large: " + prev2.largeNode);
								//System.out.println("p2's small: " + p2.smallNode + " p2's large: " + p2.largeNode);
								//System.out.println();
								if(prev2.isSame(p2, num)) continue;
							}
							if(p1.isNext(p2)){//p1 and p2 are different
								prev1 = p1;
								prev2 = p2;
								List<Integer> temp = new ArrayList<Integer>();
								temp.add(num[p1.smallNode]);
								temp.add(num[p1.largeNode]);
								temp.add(num[p2.smallNode]);
								temp.add(num[p2.largeNode]);
								result.add(temp);
							}
						}
					}
				}
				
			}
			return result;
		}
		
	public List<List<Integer>> fourSum3(int[] num, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null || num.length < 4){
			return result;
		}
		
		HashMap<Integer, Set<Pair>> twoSums = new HashMap<Integer, Set<Pair>> ();
		Arrays.sort(num);//sort array, so we know the small node in ArrayList is increasing
		//we uses sort array here, so our following assumptions is based on the ordering
		//so if we are using hashset we need use LinkedHashSet, otherwise the order is not keeping
		//for example, maybe the value and node is good, but it is not validNext(), then we may overlook it
		for(int i = 0; i < num.length; i++){
			for(int j = i+1; j < num.length; j++){
				if( j== 3 && i == 0){
					//System.out.println("Im here");
				}
				int sum = num[i] + num[j];
				Pair p = new Pair(i, j);//num[i] may equal num[j]
				if(!twoSums.containsKey(sum)){
					twoSums.put(sum, new LinkedHashSet<Pair>());//store all valid pair in the sum
				}
				Set<Pair> pairs = twoSums.get(sum);
				if( j== 3 && i == 0){
					//System.out.println(twoSums.get(sum).size());
				}
				pairs.add(p);
			}
		}
		
		for(int twoSum:twoSums.keySet()){
			int diff = target - twoSum;//find the corresponding part
			if(twoSums.containsKey(diff)){//found match
				Set<Pair> pairs1 = twoSums.get(twoSum);
				Set<Pair> pairs2 = twoSums.get(diff);

				Pair prev1 = null;//record prev pair1
				for(Pair p1 : pairs1){
					if(prev1 != null){//if we have stored the prev pair1
						if(prev1.isSame(p1, num)) continue;//help remove duplicate like 2,2,3
					}
					Pair prev2 = null;//record prev pair1
					for(Pair p2 : pairs2){
						if(prev2!=null){//help remove duplicate like 2,2,3
							if(prev2.isSame(p2, num)) continue;
						}
						if(p1.isNext(p2)){//p1 and p2 are different
							prev1 = p1;
							prev2 = p2;
							List<Integer> temp = new ArrayList<Integer>();
							temp.add(num[p1.smallNode]);
							temp.add(num[p1.largeNode]);
							temp.add(num[p2.smallNode]);
							temp.add(num[p2.largeNode]);
							result.add(temp);
						}
					}
				}
			}
			
		}
		return result;
	}

	public List<List<Integer>> fourSum2(int[] num, int target) {
		//List<List<Integer>> result = new ArrayList<List<Integer>>();
		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		if(num == null || num.length < 4){
			return new ArrayList<List<Integer>>(hs);
		}
		
		HashMap<Integer, List<Pair>> twoSums = new HashMap<Integer, List<Pair>> ();
		Arrays.sort(num);//sort array, so we know the small node in ArrayList is increasing
		for(int i = 0; i < num.length; i++){
			for(int j = i+1; j < num.length; j++){
				int sum = num[i] + num[j];
				Pair p = new Pair(i, j);//num[i] may equal num[j]
				if(!twoSums.containsKey(sum)){
					twoSums.put(sum, new ArrayList<Pair>());//store all valid pair in the sum
				}
				
				List<Pair> pairs = twoSums.get(sum);
				pairs.add(p);
			}
		}
		
		for(int twoSum:twoSums.keySet()){
			int diff = target - twoSum;//find the corresponding part
			if(twoSums.containsKey(diff)){//found match
				List<Pair> pairs1 = twoSums.get(twoSum);
				List<Pair> pairs2 = twoSums.get(diff);

				for(Pair p1 : pairs1){
					for(Pair p2 : pairs2){
						if(p1.isNext(p2)){//p1 and p2 are different
							List<Integer> temp = new ArrayList<Integer>();
							temp.add(num[p1.smallNode]);
							temp.add(num[p1.largeNode]);
							temp.add(num[p2.smallNode]);
							temp.add(num[p2.largeNode]);
							hs.add(temp);
						}
					}
				}
			}
			
		}
		return new ArrayList<List<Integer>>(hs);
	}
}
