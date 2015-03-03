import java.util.List;
import java.util.PriorityQueue;




public class p023_sol3 {
	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
    public ListNode mergeKLists(List<ListNode> lists) {
    	if(lists == null || lists.isEmpty()){
    		return null;
    	}
        PriorityQueue<ListNode> minHeap = new  PriorityQueue<ListNode>();
        for(ListNode node: lists){
        	while(node != null){
        		minHeap.add(node);
        		node = node.next;
        	}
        }
        
        //after above for loop, our heap should contains the min value of 
    }
}
