import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class p01 {
	public static void main(String[] args){
		ArrayList<Integer[]> result = twoSum(new int[]{3,3,2,2}, 5);
		for(Integer[] intAry : result){
			System.out.println(Arrays.toString(intAry));
		}
	
	}
    public static ArrayList<Integer[]> twoSum(int[] numbers, int target) {
        HashMap<Integer, HashSet<Integer>> hs= new HashMap<Integer, HashSet<Integer>>();//map key:value, value: index
        ArrayList<Integer[]> result = null;
        for(int i=0; i < numbers.length; i++){
            if(hs.containsKey(target -numbers[i])){
            	if(result == null){
            		//System.out.println("Im here when numbers[i] = " + numbers[i]);
            		result = new ArrayList<Integer[]>();
            	}
            //if this value is same, the first value should already exist in hs, so no problem(index will never same)
            	for(Integer index: hs.get(target -numbers[i])){
                    Integer[] curr =  new Integer[] {index+1, i+1};
                	result.add(curr);
            	}

            }
            if(!hs.containsKey(numbers[i])){
                HashSet<Integer> hsSet = new HashSet<Integer>();
                hsSet.add(i);
                hs.put(numbers[i], hsSet);
            }else{//handle with duplicate
            	HashSet<Integer> hsSet = hs.get(numbers[i]);
            	hsSet.add(i);
            }
     
        }
        if(result == null){
            throw new IllegalArgumentException("no solutions");
        }else{
        	return result;
        }

    }
}
