/**
 * This is one pass algorithm. The trick here is to use a variable to record the length of descending sequence
 * We will always calculate the candies when we are in non descending sequence. Since we may just finish a descending sequence, we have to 
 * check the descending part first, then begin our calculation in current part. The way we used to calculate the candies used in descending part
 * is using sum equation for Arithmetic progression, where sum = (a1+ an)*n/2. That means we will treat the end of descending sequence as candy 1
 * then sum backward based on the length. After we done that, we may have to update the last index before the descending sequence as well.
 * In other words, if our rating is like a mountain, the descending sequence is the down slope, it starts just after we reach the peak (exclusively)
 * so, we will increase the peak accordingly based on the sum we calculated from sequence part. The peak must be higher than the sum, if is equal
 * or less than the sum, we will increase the peak accordingly. 
 * After we done the calculation of descending sequence, we will start calculation of non-descending part. The logic is straightforward, we check
 * if current rating is same with prev rating, if it is, then set candy to 1, else increase the candy by 1.
 * 
 * After the loop, we still need to check if we have descending part that has not been calculated, because we always calculate descending part
 * in non-descending part, but it is possible our last part in array is descending part, so we need check it and include it to result accordingly
 * @author hpPlayer
 * @date Sep 15, 2015 1:48:46 PM
 */
public class p135_sol2 {
    public int candy(int[] ratings) {
        int result = 1;//our loop start from index 1, so we treat index 0 as candy 1
        int prev = 1;//record previous peak, since candy must >= 1, so inital value is 1
        int desLen = 0;//record descend length
        
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] < ratings[i-1]){
                //if descending order start from index i
                desLen ++;
            }else{
                //if descending stop or we start with non-descending order
                
                //first finish the descending part, because it is in front,
                //calcuate descending part if we have one, 
                if(desLen > 0){
                    //we calculate the sum backward 
                    result += (1+desLen) * desLen /2;
                    //if we need increase the peak (the index just before the start of descending,
                    //because we have much more less nodes in right, then we need to update (increase) the peak
                    //Plus if the peak should always be higher than the start of descending, so even prev==desLen
                    //we still need to update the height
                    if(desLen >= prev){//or desLen + 1 > prev, i.e. if we need to update peak
                        result += desLen + 1 - prev;//prev is the peak, desLen + 1 is the new peak we need
                    }
                    
                    prev = 1;//reset prev
                    desLen = 0;//reset desLen
                }
                
                //then calculate non descending part, since it is after descending part
                prev = ratings[i] == ratings[i-1]? 1 : prev + 1;//update prev is necessary
                result += prev;
                

            }
        }
        //if our ending part is descending, we have to manually calculate it 
        if(desLen > 0){
            result += (1+desLen) * desLen /2;
            if(desLen >= prev){
                result += desLen + 1 - prev;//prev is the peak, desLen + 1 is the new peak we need
            }            
        }
        return result;
    }
}
