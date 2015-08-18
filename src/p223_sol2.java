/**
 * The basic idea is same, but here we use max and min to check overlapping
 * Example:
 * if two rectangles are separated as left and right part
 * Then math.max(A,E) will return E, as E is the left edge of right part
 * Max.min(C,G) will return C, as C is the right edge of left part
 * Then Math.max(C, E) will return E
 * That means when we calculate the are of shared part, we will get area 0, since E - E = 0
 * Think in this way, if two rectangles are overlapping, the right edge of shared part will definitely larger than left edge
 * however if two rectangles are set apart, then the right part will <= left part(like pulling two rectangles away, the left edge is moving 
 * rightward, the right edge is moving leftward, finally after apart, the right edge will smaller then left edge)
 * 
 * Same to the getting the height
 * 
 * @author hpPlayer
 * @date Aug 18, 2015 12:31:17 PM
 */
public class p223_sol2 {
	int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
	    int left = Math.max(A,E), right = Math.max(Math.min(C,G), left);
	    int bottom = Math.max(B,F), top = Math.max(Math.min(D,H), bottom);
	    //System.out.println("left: " + left + " right: " + right + " bottom: " + bottom + " top: " + top);
	    return (C-A)*(D-B) - (right-left)*(top-bottom) + (G-E)*(H-F);
	}
	
}
