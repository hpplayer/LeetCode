/*
Gas Station

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

/**
 * This is my original AC solution without help
 * Ok I admit my solution is based on the tag "greedy". The problem states that the solution is guaranteed to be unique, which means we need find
 * a place that is unique and also greedy. From my view, the possible candidate is the index after the place where we get the maximum gas gap between
 * cost[] and gas[]. (Notice: the gap here means the accumulative gap, not direct gap between cost[i] and gas[i]).
 * If we got only one possible start point then it must be the place that begin fill the maximum gap, which is just 1 index later. It is not,
 * then our maximum gap will still expand, which contradicts the definition of maximum gap
 * 
 * After read posts about this problem, actually I found my solution is one of the best solution.
 * I admit when I come up with this solution, I am not so deep understanding this problem. But I rewrite the problem and add comments which makes
 * my solution become a perfect solution.
 * 
 * Sol2 is a solution with a little difference from sol1. In sol2, we see the problem locally (we just consider the path from i to j), and in sol1
 * we always consider the whole path. But both solutions provide a deep thought of the problem, so both sol1 and sol2 are recommended
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 10:30:37 PM
 */
public class p134_sol1 {
	public static void main(String[] args){
		int[] gas = {1,2,20};
		int[] cost = {8,3,0};
		System.out.println(new p134_sol1().canCompleteCircuit(gas, cost));
	}
	
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length != cost.length) return -1;
        //the max gap must <0, otherwise we will have gas > cost at each station
        //then it means we can start anywhere
        int maxGap = 0;
        int remainGas = 0;
        int start = 0;//start point
        for(int i = 0; i < gas.length; i++){
            remainGas += gas[i] - cost[i];//accumulate the gas we allow it to be negative
            if(remainGas < maxGap){
                //since maxGap is <0, the smaller the remainGas is, the larger the gap is
                maxGap = remainGas;//update max gap
                //if we have a such start point, then it must behind the maxGap, which is the first index i
                //that we begin to fill the gap
                start = i+1;
            }
        }
        
        //if remain gas < 0, then we will not find a such path, just return -1, otherwise return the start
        return remainGas < 0? -1 : start;
    }
}
