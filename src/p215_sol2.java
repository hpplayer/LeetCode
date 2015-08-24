import java.util.PriorityQueue;
/*
 * Sol2 uses the heap to store sorted elements, we are using the min heap, so when the heap size is k, the top element will be the kth largest
 * element in the heap
 */

public class p215_sol2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();//min Heap, the top will be the kth largest num
        for(int num : nums){
             pq.offer(num);
             if(pq.size() > k){
                 pq.poll();
             }
        }
        
        return pq.peek();
     }
}
