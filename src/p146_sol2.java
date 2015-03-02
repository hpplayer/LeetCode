import java.util.HashMap;
import java.util.LinkedList;

/**
 * this method should work.. but using single linked list is too slow in removal!
 * see p146, which is accepted by leetcode
 * @author hpPlayer
 *
 */
public class p146_sol2 {

	    LinkedList<Integer> LRU;
		HashMap<Integer, Integer> hs;
		int capacity = 0;
		public p146_sol2(int capacity) {
			LRU = new LinkedList<Integer>();
			hs = new HashMap<Integer, Integer>();
			this.capacity = capacity;
		}
		
		public int indexOfkey(int key){
			if(LRU.contains(key)){
				return LRU.indexOf(key);
			}
			return -1;
		}
		
		public int get(int key) {
			if(!hs.containsKey(key) || key < 0){
				return -1;
			}
			//set actions in key list
			int index = indexOfkey(key);
			LRU.remove(index);
			LRU.addLast(key);	
			
			return hs.get(key);
		}

		public void set(int key, int value) {
			if(hs.containsKey(key) || key < 0){
				return;
			}
			//check size before inserting
			if(hs.size() == capacity ){
				hs.remove(LRU.getFirst());
				LRU.removeFirst();
			}
			
			hs.put(key, value);
			LRU.addLast(key);
		}

}
