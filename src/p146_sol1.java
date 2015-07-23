/*
 * LRU Cache 
 
Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
it should invalidate the least recently used item before inserting a new item.
 
 *
 */


/*********
 * Basic idea: creating a double linked list that trace the least recently used key & value;
 * and using a HashMap to retrieve the key and value;
 * Notice: Double linked list works better on remove() then single linked list, so we should use dll(d-double) instead of sll(s-single)
 * when we set a key that already stored in HashMap, it means we need to update the value, so we need do two things: update value and move
 * that node to the most recently accessed node. Here, we create two virtual nodes: head and tail, which we will never change the value. 
 * The node next to head node is the least recently used node, which we will remove if size is full, the node in front of tail is the most
 * recently used node, whenever we add a new node, get a node or update a node, we need do this operation on that node. 
 * Notice: 
 * The creation of head and tail node will make us code easier since we don't need to check null case when handle operations on nodes as long as these nodes are
 * not tail or head (using dummy head and tail will be a plus in the interview!!!)
 * 
 * 
 * 
 ********/

import java.util.HashMap;

public class p146_sol1 {
	private Node head;
	private Node tail;
	int size;
	int capacity;
	HashMap<Integer, Node> LRU;

	public static void main(String[] args) {
		p146_sol1 test = new p146_sol1(2);
		// System.out.println(head.value);
		test.set(2, 1);
		//System.out.println(test.get(2));
		test.set(1, 1);

		test.set(2, 3);
		test.set(4, 1);
		System.out.println(test.get(1));
		System.out.println(test.get(2));
		// System.out.println(test.get(2));
	}

	// implement a double linked list
	private static class Node {
		int value;
		int key;
		Node prev;
		Node next;

		private Node() {
		}

		private Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}


	private void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	// Move each new Node/recently accessed node to the position before tail
	private void MoveTotail(Node node) {
		// System.out.println(tail.value);
		tail.prev.next = node;
		node.next = tail;
		node.prev = tail.prev;
		tail.prev = node;
	}

	public p146_sol1(int capacity) {
		size = 0;
		this.capacity = capacity;
		LRU = new HashMap<Integer, Node>();
		head = new Node();// dont make new head!
		tail = new Node();
		head.next = tail;
		head.prev = null;
		tail.next = null;
		tail.prev = head;
		// System.out.println(tail.prev.next.value);
	}

	public int get(int key) {
		if (!LRU.containsKey(key)) {
			return -1;
		}
		Node node = LRU.get(key);
		remove(node);
		MoveTotail(node);
		return node.value;
	}

	public void set(int key, int value) {
		System.out.println(head.next.key);

		if (LRU.containsKey(key)) {// old value, need update
			//System.out.println("Im here " + head.next.key);
			Node oldNode = LRU.get(key);
			oldNode.value = value;
			remove(oldNode);
			MoveTotail(oldNode);
			return;
		}
		// case we need create a new Node
		size++;
		if (size > capacity) {
			LRU.remove(head.next.key);
			remove(head.next);// head.next is the least recently used node
			//System.out.println(head.next == tail);
			size--;// the size does not change since we removed a node
		}
		Node node = new Node(key, value);
		// System.out.println(tail.prev.next.value);
		MoveTotail(node);
		LRU.put(key, node);

	}
}
