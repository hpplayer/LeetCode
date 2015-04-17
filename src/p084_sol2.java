import java.util.*;
/**
 * This approach works in this way:
 * 1) We use a stack to record all non-descending bar index, so we know they can form a rectangle
 * 2) If next incoming bar is smaller than the top bar in stack, then we need to pop bars in stack
 * to form rectangles. 
 * 3) The key idea is that, since our bars in stack are ascending, so we know for each top bar in stack, its left bar in stack
 * will be smaller than it, also, the next incoming bar is smaller than top bar, so the length of max rectangle in the height of top bar
 * will between the bar just in front of top and the incoming bar. (i-s.peek()-1), s.peek is after we pop current top bar
 * In other words, We need to know index of the first smaller (smaller than ¡®top¡¯) bar on left of¡®top¡¯ and index of
 * first smaller bar on right of¡®top¡¯. In our algorithm, the first smaller left bar is the prev top bar in stack, and the first smaller right
 * bar is the incoming bar 
 * 4) if the stack is empty after top, then we know we reach the start of list, then the length will just be i 
 * 5) Since our stack is non-descending, so we need to pair all previous bars in stack with bar i, this is indicated by "i--", which let
 * i stay the same
 * @author hpPlayer
 * @date Apr 17, 2015 1:21:59 AM
 */

public class p084_sol2 {
    public int largestRectangleArea(List<Integer> height) {
        int size = height.size();
        Stack<Integer> stack = new Stack<Integer>();//stack store the index of non-descending bars 
        int result = 0;
        for(int i = 0; i <= size; i++){//we handle right boundary case by adding 0 in index size
            int newBar = i== size? 0 : height.get(i);//if we reach the end of list, we just add dummy bar of height 0
            if(stack.isEmpty() || newBar >= height.get(stack.peek())){//if we get a new non-descending bard
                stack.push(i);
            }else{//if we get a lower bar, then we can match it with other bars in the stack
            //the most important point in this algorithm is that th left and right bar will be the closest bars just lower than top bar 
                int index = stack.pop();//we pop this index, so we know its left and right bars will all lower than it
        //left bar may not close to current top bar, since we stop pop bars as long as bar in index i is larger than it
        //we handle left boundary case (stack is empty after pop top) by directly get width i, 
        //In this case, the width will just be 0 to i(like: left bar: 0, right bar: 2,we have width 2, i.e. two bars)
                int newArea = height.get(index) * (stack.isEmpty()? i: (i-stack.peek()-1));//-1 for getting width of bar instead of intervals
        //like:left bar:1, right bar: 4, we may have 1 2 3 4, the width between 1 and 4 is acutally of 2 (2,3 two bars), not 4-1 =3 
                result = Math.max(newArea, result);
                i--;//keep i stay until we face some bars in stack that is smaller than bar in index i;
            }
        }
        return result;
    }
}
