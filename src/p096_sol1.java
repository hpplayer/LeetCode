import java.util.HashMap;
/*	Unique Binary Search Trees 
 * 
 *  Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *	For example,
 *	Given n = 3, there are a total of 5 unique BST's.
 *  1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * 
 * 
 */
/**
 * I got the idea that each tree has following properties:
 * left subtree is from somewhere to mid-1, right tree is from mid+1 to somewhere
 * (I firstly think somewhere is 1 and n, but that is wrong, since the right subtree's left will still be larger than 
 * mid point, while left subtree's right will be smaller than mid point)
 * 
 * Recursive approach:
 * for a given root node, the number of its variants is the variant of left subtree * the variant of right subtree
 * the root itself can also vary from left to right. So here is that
 * 
 * We need use a hashMap to be a cache otherwise it is slow
 * 
 * Actually, since each number has a fixed number of variants, we can use DP approach, see sol2
 * @author hpPlayer
 * @date Apr 8, 2015 11:35:52 AM
 */


public class p096_sol1 {
    public int numTrees(int n) {
        return DFS(1, n, new HashMap<String, Integer>());
    }
    
    public static int DFS(int left, int right, HashMap<String, Integer> hs){
        if(hs.containsKey(left + " " + right)){
            return hs.get(left + " " + right);
        }
        if(left >= right){
            return 1;
        }
        int result = 0;
        for(int i = left; i <= right; i++){
            int a = DFS(left, i-1, hs);
            int b =DFS(i+1, right, hs);
            result += a*b;
        }
        hs.put(left + " " + right, result);
        return result;
    }
}
