import java.util.Stack;
/*
* Trapping Rain Water 

* Given n non-negative integers representing an elevation map where the width of each bar is 1,
* compute how much water it is able to trap after raining.
*
* For example, 
* Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*     ^                                             
*     |                                             
*   3 |                       +--+                  
*     |                       |  |                  
*   2 |          +--+xxxxxxxxx|  +--+xx+--+         
*     |          |  |xxxxxxxxx|  |  |xx|  |         
*   1 |   +--+xxx|  +--+xxx+--+  |  +--+  +--+      
*     |   |  |xxx|  |  |xxx|  |  |  |  |  |  |      
*   0 +---+--+---+--+--+---+--+--+--+--+--+--+----->
*       0  1   0  2  1   0  1  3  2  1  2  1        
* 
* The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 
* 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
*     
*/
		
/**
 * This is my original solution, and failed the test case
 * I used stack, two pointers as described in the problem, but found my idea is not on the correct way
 * The key point is to find the conditions of left and right bar of current bar
 * DP solution can be found in sol2
 * two pointer solution can be found in sol3
 * 
 * @author hpPlayer
 * @date Aug 5, 2015 6:34:09 PM
 */

public class p042_sol1 {
	public static void main(String[] args){
		int[] height = {4,2,3};
		System.out.println(new p042_sol1().trap(height));
	}
    public int trap(int[] height) {
        if(height == null || height.length < 1) return 0;
        Stack<Integer> Edges = new Stack<Integer>();
        int max = -1;
        int result = 0;
        for(int i = 0; i < height.length; i++){
        	//System.out.println(Edges);
        	//System.out.println(max);
            if (height[i] == 0){
                if(i == 0) continue;
                Edges.add(i);
            }else if(height[i] >= max){
            	if (i == 0 && height[0] > 0){
            		//System.out.println("Im here");
            		Edges.add(i);
            		max = height[i];
            		continue;
            	}
            	if(i == 0 && height[0] == 0){
            		continue;
            	}
                if(Edges.isEmpty()){
                    Edges.add(i);
                    max = height[i];
                    continue;
                }
                int top = Edges.pop();
                while(height[top] != max){
                	//System.out.println(max);
                    top = Edges.pop();
                }
                result += getArea(height, top, i, max);
                Edges.add(i);
                max = height[i];
            }else{//height < max
                if(i == height.length-1){
                    int top = Edges.pop();
                    while(height[top] != max){
                        top = Edges.pop();
                    }
                    int min = Integer.MAX_VALUE;
                    for(int j = i; j >= top; j--){
                    	min = Math.min(min, height[j]);
                    }
                    //System.out.println(min);
                    result += getArea(height, top, i, min);
                    continue;
                }
                Edges.add(i);
            }
        }
        
        return result;
    }
    
    public int getArea(int[] height, int start, int end, int max){
    	//System.out.println(start);
    	//System.out.println(end);
        int result = 0;
        while(start < end){
        	int temp =height[start];
        	int temp2 =height[start + 1];
        	if (temp > max){
        		temp = max;
        	}
        	if(temp2 > max){
        		temp2 = max;
        	}
        	
        	System.out.println(temp);
        	System.out.println(temp2);
        	result += Math.abs(temp - temp2);
        	//System.out.println(result);
        	temp = height[end];
        	temp2 = height[end-1];
        	if (temp > max){
        		temp = max;
        	}
        	if(temp2 > max){
        		temp2 = max;
        	}
        	result += Math.abs(temp - temp2);
            start ++;
            end --;
        }
        return result;
    }
}
