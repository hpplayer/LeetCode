import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
/** 
 * Best approach see 023_sol3, this approach is implemented when I thought min-heap's size is fixed to the initial capacity
 * which turns out to be unbound in reality.
 * 
 * this approach uses min-heap. Firstly, put the initial node of every node lists into the min-heap
 * then poll the min node, attach it to the output node. We need two pointers, one always points the head
 * the other one always points the tail, which will be changing until the end. Here we set the tail pointer
 * to the node from poll(), and let the previous tail node points to the current poll() node. After that, we check
 * if current smallest node has next, if it has then offer/add it into the minHeap. Thanks to the Heap structure, we can
 * automatically change its structure and always get the min node on top. So if current input node is smallest, we will still
 * be able to use it in next run;
 * finally, we will output the head node that we create before.
 * 
 * Notice:
 * MinHeap does not support null, so we need check if current Node is null before insert into heap
 * Also, we cant create a PriorityHeap without size less than 1(i.e. priority(0, comparator) is not allowed)
 */
public class p023_sol2 {

	 public ListNode mergeKLists(List<ListNode> lists) {
	        if(lists == null || lists.isEmpty()){
	            return null;
	        }
	        ListNode result = null;
	        ListNode curr = null;
	        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
	            public int compare(ListNode a, ListNode b){
	                return (a.val - b.val);
	            }
	        });
	        /*
	         * not good, as our minHeap can be actually very large
	         */
	        for(ListNode node : lists){
	            if(node != null){
	                minHeap.offer(node);
	            }
	        }
	        
	        while(!minHeap.isEmpty()){
	            ListNode currSmallestNode = minHeap.poll();
	            if(result == null){
	                result = currSmallestNode;
	                curr = result;
	            }else{
	                curr.next = currSmallestNode;
	                curr = curr.next;//get curr pointer to the smallest current node 
	            }
	            
	            ListNode enqueueNode = curr.next;
	            if(enqueueNode != null){
	                minHeap.add(enqueueNode);
	            }
	        }
	        
	        return result;
	        
	    }

	private static PriorityQueue<Integer> pq;
	 public static ListNode mergeKLists4(List<ListNode> lists) {
	        System.out.println(lists.size());
	        if(lists == null || lists.size() == 0){
	            return null;
	        }
	        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
	            public int compare(ListNode a, ListNode b){
	                return a.val - b. val;
	            }
	            });
	        for(ListNode node : lists){
	            while(node != null){
	                minHeap.add(node);
	                node = node.next;
	            }
	        }
	        System.out.println(minHeap.size());
	        //after above iteration is done, we should get the final heap we want
	        //now we are creating node list that we want 
	        ListNode dummyNode = new ListNode(-1);
	        ListNode currNode = dummyNode;
	        while(!minHeap.isEmpty()){
	            currNode.next = minHeap.poll();
	            currNode = currNode.next;
	        }
	        currNode.next =null;
	        return dummyNode.next;
	    }
	public static ListNode mergeKLists3(List<ListNode> lists) {
		pq = new PriorityQueue<>();
		for (ListNode listHead : lists) {
			while (listHead != null) {// get the smallest heap of size n of all
										// ListNodes
				pq.add(listHead.val);
				listHead = listHead.next;
			}
		}
		System.out.println(pq.size());
		
		ListNode first = null;
		ListNode end = null;
		while (!pq.isEmpty()) {
			ListNode min = new ListNode(pq.poll());
			if (first == null)
				first = end = min;
			else {
				end.next = min;
				end = min;
			}
		}
		return first;
	}

	public static void main(String[] args) {
		List<ListNode> test = new ArrayList<ListNode>();
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(2);

		a.next = b;
		b.next = c;

		ListNode a1 = new ListNode(1);
		ListNode b1 = new ListNode(1);
		ListNode c1 = new ListNode(2);

		a1.next = b1;
		b1.next = c1;

		test.add(a);
		test.add(a1);
		System.out.println(mergeKLists4(test).val);
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
