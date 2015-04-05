/**
 * The key idea to rotated array is to realize that half of the array must be sorted, while half of the array must be rotated 
 * except for two extreme cases (whole sorted or whole rotated)
 * So, when we search the we can divide it into two cases:
 * whole sorted: return leftmost element
 * 
 * rotated(whole or partial):(1) left part sorted, right part rotated (ex: 45612) (2) left part rotated, right part sorted(ex:45123)
 * if the original array is sorted, the min value should always falls in the roated part
 * for (1), we compare mid with leftmost node, 4<6, hence we know right part is rotated, the min should be in the right
 * for (2) we compare mid with leftmost node, 3>1, hence we know left part is rotated, the min should be in the left
 * for (1), since we already know 4<6, the mid is not possible the min one, it is in the sorted part, so we will not include mid next iteration
 * for (2), since we already know 3>1, the mid is not in the sorted part and it is in the rotated part, so mid may still be considered 
 * as the min and we will include it in next iterations 
 * 
 * finally our loop will end when start = end, since we always select the part that will contain min,
 * so when start =end, it means we have only one element left, which should be the min
 * 
 * Remark:
 * if we check whether left part is rotated: num[mid] >= num[start], we need add the = case, since 
 * we may have case that min == start like [2,1], obviously, 2 is sorted with 2, so we need check right part without 2,
 * so a better way to avoid this is check whether num[mid] > num[end] and num[mid] < num[start]
 * if num[mid] > num[end], it means right part is rotated, and we can skip num[mid] (cuz mid already > end), 
 * if num[mid] < num[start], it means left part is rotated, and we can consider num[mid] (ciz mid < start)
 * 
 * We can also think in this way:
 * we have 3 elements: 1, 2, 3 
 * it may rotate 0 time, so we have 1 2 3
 * it may rotate 1 time, so we have 2 3 1
 * it may rotate 2 time, so we have 3 1 2
 * it may rotate 3 time, so we have 1 2 3....
 * for case 0, 123, we simply return the leftmost node
 * for case 1  231, we check if mid > right or mid > left
 * for case 2  312, we check if mid < left or mid < right
 * (proven on Leetcode, either combination is ok)
 * no other possibilities..
 * @author hpPlayer
 * @date Apr 4, 2015 12:15:17 AM
 */
public class p153_sol2 {
	public static void main(String[] args){
		int[] a = {3,1,2};
		System.out.println(findMin(a));
	}
	public static int findMin(int[] num) {
		if(num.length == 1) return num[0];
		int start = 0, end = num.length -1;
		while(start < end){
			int mid = start + (end -start)/2;
			//sorted case
			if(num[start] < num[end]){//if not rotate
				return num[start];
			}
			//rotated case, min must in the rotated part
			if (num[mid] < num[start]){//if num[mid] < num[start], num[start] may still be the min
				end = mid;
			}else if (num[mid] > num[end]){//if num[mid] > num[end], num[mid] will never be min, simply mid+1
				start = mid+1;//mid include in sorted part, so will not be the min
			}
		}
		//the loop is break when start ==end, since our loop will continue if min is not found, so after loop break, we definitely 
		//find the min one
		return num[start];//or return num[end];
	}
}
