/*
Maximum Gap

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/


/**
 * This is a bucket sort solution. What is bucket sort?
 * We build a number of buckets, and assign our numbers into the buckets, then merge buckets together.
 * The key part is how to choose the number of buckets and how to assign our numbers into buckets
 * 
 * This problem can be solved by bucket sort perfectly.
 * Firstly, we calculate the average gap in our input array, which can be got from (max-min)/(n-1), where n-1 is the number of gaps
 * So if we build a bucket which contains all numbers < gap, then we know any pair of numbers in this bucket will not have diff >= gap, which means
 * we can skip the comparison of numbers in this bucket. We can build a bunch of such buckets and cover all numbers in input array, and any pair
 * of numbers in each bucket will not have diff >= gap. Thus finally we can just compare the diff among buckets while skip all comparisons inside
 * bucket. This is the main idea
 * 
 * For implementation, we firstly get the size of avg gap, then calculate the numbers of buckets we need. The size(range) of bucket is gap, so the
 * diff inside it will not be larger than gap, like gap: 2, we can have [0, 1], [2,3], we can have size 2, but the diff inside bucket will not
 * exceed gap 2. Here we mean the size, is actually the size of range, of course we have have as many as numbers in the bucket like [0,0,1,1], [2,2,3]
 * Then we begin assign numbers into each bucket, our bucket is based on the difference between max-min, so the index is (value k - min)/gap
 * We will store the max and min value of each bucket.
 * After assigning numbers, we check the difference among buckets, use bucket[i+1].min - bucket[i].max to get the max difference
 * 
 * So this is a beautiful O(n) time and space algorithm
 * 
 * Sol2 provides a radix sort algorithm. Although sol2 is not so beautiful as sol1, since the main part of sol2 is just a general radix
 * sort, I still put it here for a reference
 * 
 * Both sol1 and sol2 are recommended
 * @author hpPlayer
 * @date Sep 15, 2015 10:46:50 PM
 */

public class p164_sol1 {
	public static void main(String[] args){
		int[] nums = {0,2,3,4};
		System.out.println(maximumGap(nums));
	}

    public static class Bucket{
        int max = 0;
        int min = Integer.MAX_VALUE;
    }
    
    public static int maximumGap(int[] nums) {
        if(nums.length < 2) return 0;//boundary case
        
        int max = 0;
        int min = Integer.MAX_VALUE;
        
        //get max and min value in array
        for(int i : nums){
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        
        //!!!!!! don't forget case to double, otherwise integer/integer will get 0!!!!
        //or we can simply use gap = (max-min - 1)/nums.length + 1 to replace the ceiling function
        int gap = (int) Math.ceil((double) (max-min)/(nums.length-1));//get the average gap, which is bucket size
        if(gap == 0) return 0;//if whole array is consecutive 
        //System.out.println(gap);
        //if each bucket has size of gap, then Math.ceil((max-min)/gap) will assign them accordingly, but that's not we want
        //we want each bucket has max diff < gap, so we at least need have (max-min)/gap + 1 buckets. If it is hard to comprehend, then
        //use example: input: [1,2], gap(bucket size): 1, then we should have two buckets [1], [2], which is (2(max) - 1(min))/1(gap) + 1 = 2
        
        int numOfBuckets = (max-min)/gap + 1;//get num of buckets we need
        /*
         * It can also be rewrite as:
         *  int numOfBuckets = (max-min)/gap + 1;//if gap == 1
         *	if(gap -1 != 0) numOfBuckets = (int) Math.ceil((double) (max-min)/(gap-1));
         */
        Bucket[] buckets = new Bucket[numOfBuckets];
        
        //initalize array
        for(int i = 0; i < buckets.length; i++){
            buckets[i] = new Bucket();
        }
        
        //assign nums into buckets
        for(int i : nums){
            int index = (i - min)/gap;//we include min into our bucket, so we need considering min when get index
            
            buckets[index].max = Math.max(buckets[index].max, i);
            buckets[index].min = Math.min(buckets[index].min, i);
        }
        
        //calculate the max diff among buckets
        int result = 0;
        int prev = min;
        
        for(Bucket bucket : buckets){
        	//since we build buckets based on average gap, but it is possible we have a very large gap with several small gaps
        	//In this case, only first series of buckets and last bucket will be filled, and we will have several empty buckets in middle
            if(bucket.max == 0 || min == Integer.MAX_VALUE){
            	continue;//empty bucket
            }
            result = Math.max(result, bucket.min - prev);//the min value of next bucket - max value of last bucket
            prev = bucket.max;//update prev to max value
        }
        
        return result;
    }
}
