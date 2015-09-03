import java.util.Arrays;

/**
 * sol3 is not easy to understand.
 * lets start with an Example:
 * a b
 * c d
 * 
 * After first swap:
 * b a
 * c d
 * 
 * After second swap:
 * d a
 * c b
 * 
 * After third swap:
 * c a
 * d b
 * 
 *     top 			 		left
 * left right    =>  bottom		 top
 *    bottom				right
 *    
 * Basically, it firstly swap top edge with right edge 
 * Then it swap top edge(old right edge) with bottom edge
 * Last it swap top edge(bottom edge) with left edge
 * So, now top edge will be the left edge
 * right edge will be top edge
 * bottom edge will be right edge
 * left edge will be bottom edge
 * 
 * Sol3 visit each cell one time and should be faster than sol2, who will visit each cell twice(reverse and transpose)
 * This is the algorithm, and I think it is not as easy as sol2
 *
 * Remark:
 * 1) here, the setting of value if like sol1, in which we swap values in different layers
 * 2) rotate2() is the exact in-place version of rotate()
 * @author hpPlayer
 * @date Sep 2, 2015 11:23:19 PM
 */
public class p048_sol3 {
	public static void main(String[] args) {
		int a[] = {1, 2};
		int b[] = {3, 4};
		int e[][] = {a,b};
		 new p048_sol3().rotate(e);
		for(int[] l : e){
			System.out.println(Arrays.toString(l));
		}
	}
	
    public void rotate(int[][] matrix) {
        int a = 0, b = matrix.length - 1;
        while(a < b){
            for(int i = 0; i < (b-a); i++){
            	System.out.println(i);
        		for(int[] l : matrix){
        			System.out.println(Arrays.toString(l));
        		}
        		
                swap(a, a+i, a+i, b, matrix);
                swap(a, a+i, b, b-i, matrix);
                swap(a, a+i, b-i, a, matrix);
            }
            
            a++;
            b--;
        }
        
    }
    
    public void rotate2(int[][] matrix) {
        int n = matrix.length -1;
        for(int j = 0; j < matrix.length /2; j++){
             for(int i = 0; i < (n-j-j); i++){
                swap(j, j+i, j+i, n-j, matrix);
                swap(j, j+i, n-j, n-j-i, matrix);
                swap(j, j+i, n-j-i, j, matrix);
            }           
        }
  
    }
    
    void swap(int a1, int b1, int a2, int b2, int[][] matrix){
        if(a1 == a2 && b1 == b2) return;
        matrix[a1][b1] += matrix[a2][b2];
        matrix[a2][b2] = matrix[a1][b1] - matrix[a2][b2];
        matrix[a1][b1] -= matrix[a2][b2];
    }
}
