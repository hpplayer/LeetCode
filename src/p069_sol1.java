public class p069_sol1 {

	public static int sqrt(int x) {
	
	if(x < 0){
		return -1;
	}else if( 0<= x && x<1){
		return 0;
	}else if ( 1<=x && x <4){
		return 1;
	}
	else if ( 4 <=x && x <9){
		return 2;
	}
	else if ( 9 <=x && x <10){
		return 3;
	}
	
		String temp = x + "";
		int tempInt = 0;
		int index = 0;
		int result = 0;
		if (temp.length() >= 2) {
			if (temp.charAt(1) == 0) {
				tempInt = 2;
			} else {
				tempInt = 6;
			}
			if (temp.length() % 2 == 0) {
				index = temp.length() / 2;
			} else {
				index = temp.length() / 2 + 1;
			}
			System.out.println(index);
			StringBuffer sb = new StringBuffer();
			sb.append(tempInt);
			for (int i = 0; i < index; i++) {
				sb.append(0);
			}
			String ten = sb.toString();
			result = Integer.valueOf(ten);
			for (int i = 0; i < 100; i++) {
				result = (int) (0.5 * (result + x / result));
			}
		} 

		return result;
	}

	public static int sqrt3(int x) {
		return Integer.valueOf((int) Math.sqrt(x)); // AC solution..lol
		// return (int) Math.pow(x, 0.5);
		// return (int) Math.sqrt(x);

	}

	public static int sqrt2(int x) {
		if (x < 0)
			return -1;
		int temp = x / 2, i = 0;
		outer: for (i = 0; i < temp; i++) {
			// System.out.println(i);
			int result = i;
			for (int j = 0; j < i - 1; j++) {
				result *= result;
				if (result == x) {
					break outer;
				}
			}
		}
		return i;
	}

	public static void main(String[] args) {
		// System.out.println(sqrt(183692038));
		System.out.println(sqrt(1024));
	}

}
