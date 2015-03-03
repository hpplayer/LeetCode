/**
 * Accpeted solution of my work using priority quue. But may looks lengthy..
 * Notice:check if the input list is null, or some nodes in the list is null or even the list is totally composed of null nodes
 * 		  Remeber to set the next of last node of output = null
 * 		  Also when changing array during iteration, we need use iterator.remove(), instead of directly delete nodes in arraylist
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class p023_sol1 {
	public static void main(String[] args) {
		// ListNode a = null;
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
		
		List<ListNode> test = new ArrayList<ListNode>();
		test.add(a);
		test.add(a1);
		System.out.println("ary size: " + test.size());
		//test.add(c);
		ListNode node = mergeKLists(test);
		while(node != null){
			System.out.println(node.val);
			node = node.next;
		}
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	private static int countSize(ListNode node) {
		if (node == null) {
			return 0;
		}
		int count = 1;
		while (node.next != null) {
			count++;
			node = node.next;
		}
		return count;
	}

	public static ListNode mergeKLists(List<ListNode> lists) {
		if(lists.isEmpty()){
			return null;
		}
		int total = 0;
		List<PriorityQueue<ListNode>> ary = new ArrayList<PriorityQueue<ListNode>>();
		// convert node to priority
		for (int i = 0; i < lists.size(); i++) {
			int count = countSize(lists.get(i));
			if(count == 0){
				continue;
			}
			// System.out.println(count);
			PriorityQueue<ListNode> pq2 = new PriorityQueue<ListNode>(count,
					new Comparator<ListNode>() {
						public int compare(ListNode a, ListNode b) {
							return a.val - b.val;
						}
					});
			ListNode node = lists.get(i);
			while (node != null) {
				pq2.add(node);
				node = node.next;
			}
			ary.add(pq2);
			total += count;
		}

		// put queue into result
		if(total == 0){
			return null;
		}
		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(total,
				new Comparator<ListNode>() {
					public int compare(ListNode a, ListNode b) {
						return a.val - b.val;
					}
				});
		// System.out.println(ary.size());
		while (pq.size() != total) {
			
			//while (it.hasNext()) {
			for(Iterator<PriorityQueue<ListNode>> it = ary.iterator(); it.hasNext();){
				PriorityQueue<ListNode> queue = it.next();
				// for(PriorityQueue<ListNode> queue: ary){
				// System.out.println(queue.peek().val);
	
					//if (queue.peek().val < pq.peek().val) {
						// System.out.println("i am here");
						pq.offer(queue.poll());
						if (queue.isEmpty()) {
							it.remove();
						}
					//}
			
			}
		}
		//System.out.println(pq.size());
		ListNode current = pq.poll();
		ListNode result = current;

		while (!pq.isEmpty()) {
			System.out.println(current.val);
			current.next = pq.poll();
			current = current.next; 
		}
		current.next = null;
		System.out.println(result.val);
		return result;
	}
	/*
	 * static ListNode head = null;
	 * 
	 * public static ListNode mergeKLists(List<ListNode> lists) { if
	 * (lists.size() == 0 || lists.isEmpty()) return null; ListNode min = null;
	 * for(ListNode node: lists){ if (node != null){ min = node; break; } }
	 * while(lists.size() != 0){ for (ListNode node : lists) { if(node == null){
	 * lists.remove(node); } if (node != null && node.val <= min.val) {// we use
	 * this one, remove // it from lists ListNode temp = node.next; min = node;
	 * if (head == null) { head = min; } else { node.next = head; head = node; }
	 * node = temp; } } } System.out.println(head.val);
	 * System.out.println(head.next.val); return head; }
	 */
}
