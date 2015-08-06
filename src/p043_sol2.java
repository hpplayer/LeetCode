/**
 * This approach also uses pure math algorithm.
 * The basic idea is similar to sol1.
 * It uses the reverse trick, calculate the result digit by digit from tail to head
 * Sol2 observes that each digit i in result can be calculated as sum(num1[k] * num2[i-k]), where k <= i 
 * 
 * The difference is:
 * in sol1, we store the raw sum of each digit in the array, then do mod and divide in each digit. Like this array[i + j] = num[i] + num[j]
 * The loops are on num1 and num2 
 * in sol2, we calculate the raw sum of each digit by doing sum like this: array[k] = num1[i] + num2[k-i], where 0 <= i <= k
 * The loops are mainly on result.
 * So sol1 will avoid the case that index is out of boundary, while sol2 we have to check whether i or k-i is out of boundary
 * 
 * Remaining part is same.
 * Basically, these two algorithms are very close, but sol1 is more clean and concise, so sol1 is the best solution!
 * @author hpPlayer
 * @date Aug 6, 2015 1:30:45 PM
 */
public class p043_sol2 {
		public static void main(String[] args){
			System.out.println(new p043_sol2().multiply("98", "9"));
		}
	   public String multiply(String num1, String num2) {
		   
		   num1 = new StringBuilder(num1).reverse().toString();
		   num2 = new StringBuilder(num2).reverse().toString();
		   
		   //convert string to int array
		   int[] array1 = new int[num1.length()];
		   int[] array2 = new int[num2.length()];
		   
		   //fill array with int at corresponding index
		   for(int i = 0; i < array1.length; i++){
			   array1[i] = num1.charAt(i) - '0';
		   }
		   
		   for(int i = 0; i < array2.length; i++){
			   array2[i] = num2.charAt(i) - '0';
		   }
		   
		   //temp record the sum in each digit
		   int temp = 0;
		   StringBuilder result = new StringBuilder();
		   
		   //calculate sum digit by digit
		   for(int i = 0; i < num1.length() + num2.length(); i++){
			   //calculate each possibility of given i 
			   for(int j = 0; j <= i; j++){
				   //in case out of range
				   if(j < array1.length && i - j < array2.length){
					   temp += array1[j] * array2[i-j];
				   }
			   }
			   //insert mod in front
			   result.insert(0, temp%10);
			   //update carry and pass to next digit
			   temp /= 10;
		   }
		   
		   //remove 0s in front
		   while(result.length() > 1 && result.charAt(0) == '0'){
			   result.deleteCharAt(0);
		   }
		   return result.toString();
	   }
}
