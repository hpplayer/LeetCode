import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
/**
 * My original AC solution, without help..
 * Since there would be four parameters that needs check, so I used the hashset for simplicity
 * It is very similar to 3sum or 3sum closet..
 * fourSum2() is another way to avoid duplicates(skip duplicates when visiting), which should be faster
 * @author hpPlayer
 * @date Mar 23, 2015 2:35:52 PM
 */
public class p018_sol1 {
	public static void main(String[] args) {
		// int[] ary = {-1, 0, 1, 2, -1, -4};
		// int[] ary = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
		// int[] ary = {0,0,0};
		int[] ary = { 1, 0, -1, 0, -2, 2 };
		for (List<Integer> temp : new p018_sol1().fourSum(ary, 0)) {
			System.out.println(temp);
		}
	}

	public List<List<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);
		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		for (int i = 0; i < num.length - 3; i++) {
			for (int j = i + 1; j < num.length - 2; j++) {
				int start = j + 1;
				int end = num.length - 1;
				// System.out.println(currSum);
				while (start < end) {
					int currSum = num[i] + num[j] + num[start] + num[end];
					if (currSum == target) {
						List<Integer> temp = new ArrayList<Integer>();
						temp.add(num[i]);
						temp.add(num[j]);
						temp.add(num[start]);
						temp.add(num[end]);
						hs.add(temp);
						start++;
						end--;
					} else if (currSum < target) {
						// System.out.println("Im here");
						start++;
					} else {
						end--;
					}
				}

			}
		}
		return new ArrayList<List<Integer>>(hs);
	}
	
	public List<List<Integer>> fourSum2(int[] num, int target) {
		Arrays.sort(num);
		//HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < num.length - 3; i++) {
			if(i >= 1 && num[i] == num[i-1]) continue;
			for (int j = i + 1; j < num.length - 2; j++) {
				if(j >= i+ 2 && num[j] == num[j-1]) continue;
				int start = j + 1;
				int end = num.length - 1;
				// System.out.println(currSum);
				while (start < end) {
					int currSum = num[i] + num[j] + num[start] + num[end];
					if (currSum == target) {
						List<Integer> temp = new ArrayList<Integer>();
						temp.add(num[i]);
						temp.add(num[j]);
						temp.add(num[start]);
						temp.add(num[end]);
						//hs.add(temp);
						result.add(temp);
						start++;
						end--;
						while(start < end && num[start] == num[start -1]) start ++;
						while(start < end && num[end] == num[end+1]) end --;
					} else if (currSum < target) {
						// System.out.println("Im here");
						start++;
					} else {
						end--;
					}
				}

			}
		}
		//return new ArrayList<List<Integer>>(hs);
		return result;
	}
}
