/**
 * Ok, before introduce the algorithm, we need get to know some basic theory:
 * 1) from index i to j, if j is the first index that we cannot reach from i, then all indexes among i to j will not reach j as well
 * let's prove with contradiction: if we can reach index j from a index k which is among i to j, then we shall not reach k, otherwise we will
 * have a path from i to k, then k to j, which contradicts our statement that we cannot reach i to j. So if we have such k exist, then k will
 * be the first index that we cannot reach from index i, which also contradicts the statement that index j is the first index we cannot reach.
 * 2) As long as the total gas > total cost from i to j, then we will have a path from i to j:
 * If we have only two nodes, then it is easy to prove that there exists a path.
 * If we have three or more nodes, then we can merge reachable nodes together, then finally reduce to two node problem.
 * Formal proof can be found in attachment
 * 
 * So based on above two observations, we have following solution
 * We will keep a variable "tank" to check the possibility of travel from i to j. We start i with 0. If at some point the tank drop below 0, that
 * means we cannot reach index j from index 0, based on our observation 1), our start point must after j. Then we will start a new path started 
 * from j+1. During the trip, we can accumulate gas into the tank to balance later cost in this trip
 * We will also keep a variable "totalGas" to check the possibility of finishing whole path. Based on observation 2), if it is >= 0, then we must
 * have a path, otherwise we just return -1 
 * 
 * @author hpPlayer
 * @date Sep 14, 2015 11:27:53 PM
 */

public class p134_sol2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length != cost.length ) return -1;
        int tank = 0;
        int start = 0;
        int totalGas = 0;
        for(int i = 0; i < gas.length; i++){
            tank += gas[i] - cost[i];//tank's size is unlimited, so we can put extra gas if we have
            totalGas += gas[i] - cost[i];//keep accumulating gas
            //but if at some index i, the cost of road > tank's remain gas, then it means we can't reach 
            //index i from previous record start
            if(tank < 0){//if we can't reach index i, then the start point must after i
                start = i+1;//update start point
                tank = 0;//reset tank, search next valid point
            }
        }
        
        //as long as totalGas >= 0, we will have a unique solution
        return totalGas >=0? start : -1;
        
    }
}
