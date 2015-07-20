
public class p007_sol2 {
	public static void main(String[] args){
		System.out.println(Integer.MAX_VALUE);
		System.out.println(new p007_sol2().reverse(-10));
	}
    public int reverse(int x) {
        if (x == Integer.MAX_VALUE || x == Integer.MIN_VALUE){
        	return 0;
        }
    	double temp = 0;
    	//still has digits left
        while(x/10 != 0){
        	temp = temp * 10 + x%10;
        	x = x/10;
        }
        temp = temp * 10 + x%10;
        if (temp >= Integer.MAX_VALUE || temp <= Integer.MIN_VALUE) return 0;
        return (int)(temp);
    }
}
