import java.util.Arrays;


public class p189 {
	public static void main(String[] args){
		int[] a = {1,2};
		rotate(a, 3);
		System.out.println(Arrays.toString(a));
	}

    	public static void rotate(int[] nums, int k) {
    		k = nums.length - k;
	    	int[] temp = nums.clone();
	    	if(k<0){
	    		k = nums.length + k;
	    	}
	
	    	for(int i = k; i < nums.length; i++){
	    		nums[i-k] = temp[i];
	    		//System.out.println("i-l: " + nums[i-k]);
	    	}
	    	for(int i = 0; i < k; i++){
	    		nums[nums.length - k +i] = temp[i];
	    	}
        }
}
