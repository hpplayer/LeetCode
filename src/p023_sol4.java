import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Divide and conquer method..see CleanCodeHandBook
 * 
 * @author hpPlayer
 * @date Mar 3, 2015 2:10:16 PM
 */
public class p023_sol4 {
	public static void main(String[] args) {
		List<ListNode> test = new ArrayList<ListNode>();
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(2);
		ArrayList temp = new ArrayList();
		a.next = b;
		b.next = c;

		ListNode a1 = new ListNode(1);
		ListNode b1 = new ListNode(1);
		ListNode c1 = new ListNode(2);

		a1.next = b1;
		b1.next = c1;

		test.add(a);
		test.add(a1);
		System.out.println(mergeKLists(test).val);
	}
	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	public static ListNode mergeKLists(List<ListNode> lists ){
		if(lists.isEmpty()) return null;
		int end = lists.size()-1;
	//case we have list but contains empty node, and most importantly, 
  //during the loops that we are moving "end" forward, we actually are merging lists
// after end = 0, we have merged all lists
		while(end > 0){
			int begin = 0;
			while(begin < end){
				lists.set(begin, merge2Lists(lists.get(begin), lists.get(end)));
				
				begin ++;
				end --;
			}
			//after each loop, we got half lists merged, we store them to first half of lists
		}
		return lists.get(0);
	}
	
	/*
	 * similar method to P21.( merge 2 lists)
	 */
	private static ListNode merge2Lists(ListNode l1, ListNode l2){
		ListNode dummyhead = new ListNode(0);//always points to head
		ListNode p = dummyhead;//points to current node
		while(l1 != null && l2 != null){
			if(l1.val < l2.val){
				p.next = l1;
				l1 = l1.next;
			}else{
				p.next = l2;
				l2 = l2.next;
			}
			p = p.next;//move to l1 or l2
		}
		//end here we must have at least one list becomes 0 size
		if(l1 != null) p.next = l1;
		if(l2 != null) p.next = l2;
		return dummyhead.next;
	}
}
