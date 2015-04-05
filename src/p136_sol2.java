/**
 * Magic of bit manipulation:
 * XOR of two equals number is 0, i.e a ^a = 0
 * XOR of any number with 0 is itself, i.e a^ 0 = a
 * and XOR is commutative, i.e. a ^ b ^ b ^ a = a ^ a ^ b ^ b
 * and XOR is associative, i.e. a ^ b ^ b = a ^ (b^b)
 * so we can go through the array, if the one number only appears once, then the final computation result 
 * will like a ^ b ^ c ^ c ^ b= a^ (b^b) ^ (c^c) using associative and commutative
 * and it will like a ^ 0 ^ 0 = a using property of a^a = 0 and ^ 0 = itself 
 * 
 * Very smart application of XOR
 *  
 */
public class p136_sol2 {
	public static void main(String[] args){
		int[] ary = {16705, 9374, 16705, 16705, 16705, 9374,123};
		System.out.println(singleNumber(ary));
	}
	public static int singleNumber(int[] A) {
		int a = 0;//helper, every value XOR 0 is itself
		for(int i = 0; i < A.length; i++){
			a ^= A[i];//XOR is commutative, and every value XOR itself is 0
		}
		return a;
	}
}
