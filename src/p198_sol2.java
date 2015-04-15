/**
 * This algorithm works in following way:
 * for current house, we have to choices:
 * 1) rob it, so its values should be the value exclude its previous house and plus current house's money, written as:
 * prevYes = num[i] + prevNo(exclude previous house + current house's money, sum of which will be previousYes for next house)
 * 2) not rob it, so its value should be max between robbing previous house or not robbing previous house
 * There in only one way to rob current house that is exclude previous house and rob current house
 * but there is two ways to not rob current house that is either robbing previous house or not
 * So there is that
 * 
 * Remark:
 * After the loop, our current house is out of boundary, so the last valid house should be previous house.
 * As discussed above, there are two ways to not rob current house (rob previous house or not rob previous house)
 * 
 * @author hpPlayer
 * @date Apr 14, 2015 1:19:23 PM
 */
public class p198_sol2 {
    public int rob(int[] num) {
        if(num == null || num.length == 0) return 0;
        	int prevNo = 0;//not include previous house
        	int prevYes = 0;//include previous house
        	for(int i = 0; i < num.length; i++){
        		int temp = prevYes;//back up the prevYes
        		prevYes = num[i] + prevNo;//update prevYes, i.e. exclude current previous node and include current node
        		prevNo = Math.max(temp, prevNo);//update prevNo, i.e. we pick the max of pick prevYes(before update) and prevNo
        	}
        	return Math.max(prevYes, prevNo);
    }
}
