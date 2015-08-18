/*
Rectangle Area

* Find the total area covered by two rectilinear rectangles in a 2D plane.
* Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
* 
*                      Y                                        
*                      ^                                        
*                      |                                        
*                      |                                        
*                      |                                        
*                      |                                        
*                      |     (C,D):(3,4)                        
*            +------------------+                               
*            |         |        |                               
*            |         |        |                               
*            |         |        |               (G,H):(9,2)     
*            |         +----------------------------+           
*            |         |        |                   |           
*            |         |        |                   |           
*            |         |        |                   |           
*            +---------|--------+-------------------|---------> X
*      (A,B):(-3,0)    |                            |           
*                      +----------------------------+           
*                  (E,F):(0,-1)                                 
*      
*      
*      
* Assume that the total area is never beyond the maximum possible value of int.
*/

/**
 * This is my original AC solution without help
 * In the beginning, I created several if statements to check which edge is inside which edge
 * But I found this is difficult to list all cases manually.
 * Then I realized I can simply find the shared area by using Math.min and Math.max, The shared area is always composed from
 * (the lowest upper edge - the highest lower edge) * (the leftmost right edge - the rightmost left edge)
 * So my modified algorithm pass thousands of test cases, but still failed at some test cases/
 * Then I check those cases, I found my algorithm always assuming there will be overlapped area between too rectangles, but actually 
 * they can be separated in two ways, one is left one is right or one is top one is bottom, then I add these two special cases and 
 * my solution get AC!
 * So happy!
 * 
 * In sol1 I use if-else statement to check whether two rectangles are separated
 * Sol2 provides another way to achieve same check only with Max and Min
 * sol3 is the python version of sol1
 * sol4 is the python version of sol2
 * 
 * Sol1 is easy to write 
 * Sol2 provides deep thought
 * Both are good solutions!
 * @author hpPlayer
 * @date Aug 18, 2015 11:59:16 AM
 */
public class p223_sol1 {
	public static void main(String[] args){
		System.out.println(new p223_sol1().computeArea(-2, -2, 2, 2, 3, 3, 4, 4));
	}

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int areaA = (C - A) * (D - B), areaB = (G - E) * (H - F);
            int shared = 0;
            if(E >= C || G<= A || H <= B || F >= D){
            	shared = 0;
            }else{
            	shared = (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F));	
            }
            return areaA + areaB - shared;
     }
}
