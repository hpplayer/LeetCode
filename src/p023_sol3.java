import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 * Basic idea:
 * we create a Min-heap, firstly add all nodes to it with our comparator
 * then we need to create a linked list according to the Min-heap
 * The idea is using two nodes, one is head which is fixed and the other one is currNode which is current node
 * 
 * Notice:
 * Size of PriorityHeap can grow automatically if needed! (the value in the constructor is the initial capacity, we can do trick with it 
 * but it is not necessary to limit our size below it.
 * Remember boundary conditions like null input, input contains a null node, input composed of null(see line 63, check result == null before 
 * currNode.next =null
 * )
 * Also remebmer add null to the last node of output, since it may originally not the last node of its according list and
 *  comes with pointers to some other nodes. Here it becomes the last node of final output, so we need set it.next = null
 *  
 *  
 * @author hpPlayer
 *
 */



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
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(1, new Comparator<ListNode>(){
        	public int compare (ListNode a, ListNode b){
        		return a.val - b.val;
        	}
        });
        for(ListNode node: lists){
        	while(node != null){
        		minHeap.add(node);
        		node = node.next;
        	}
        }
        
        //after above for loop, our heap should contains all nodes we needed in a min-heap structure.
        //so now we just need to output a linked list based on this heap structure
        //we can use dummy head to start the list if we know the constructor
        ListNode result = null;
        ListNode currNode = null;
        
        while(!minHeap.isEmpty()){
        	if(result == null){//first node
        		result = minHeap.poll();
        		currNode = result;
        	}else{
        		currNode.next = minHeap.poll();//attach current min node to the list
        		currNode = currNode.next;//move pointer to its next value
        	}
        }
        
        if(result != null){//check if we actually do not have nodes at all, not alone currNode.next (i.e. result is null)
            currNode.next =null; //we need this step since the last output node may has its original next node
        }

        return result;
    }
}
