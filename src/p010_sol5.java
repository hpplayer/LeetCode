/**
 * Iterative approach, and did not pass all test cases 
 * As we can see from here, iterative approach is very complicated, and more lengthy and buggy than recursive approach
 * @author hpPlayer
 * @date Mar 16, 2015 3:16:22 PM
 */
public class p010_sol5 {
	public static void main(String[] args) {
		String a = "abcd";
		String b = "d*";
		System.out.println(a.matches(b));
		System.out.println(isMatch(a, b));
	}
	public static boolean isMatch(String s, String p) {
		return DFS(s, p);
	}

	public static boolean DFS(String s, String p) {
		
		int PointerInS = 0, PointerInP = 0, OldInS = -1, OldInP = -1;
		while (PointerInS < s.length()) {
			//System.out.println(PointerInS);
			// System.out.println(p.charAt(PointerInP +1));
			// System.out.println(PointerInP);
			if (isValid(s, p, PointerInS, PointerInP) && PointerInP+1 < p.length() && p.charAt(PointerInP+1) != '*') {
				//System.out.println("im here");
				PointerInS++;
				PointerInP++;
				continue;
			}
			if(PointerInP == p.length()-1 && isValid(s, p, PointerInS, PointerInP)){
				//System.out.println("im here");
				PointerInS++;
				PointerInP++;
				continue;
			}
			if (PointerInP+1 < p.length() && p.charAt(PointerInP+1) == '*') {
				//\\ System.out.println("im here");
				OldInS = PointerInS;
				OldInP = PointerInP;

				
				if(PointerInP < p.length()-2 && p.charAt(PointerInP +2 ) == s.charAt(PointerInS)){//zero case
					PointerInP= PointerInP +2;
					PointerInS++;
					if(PointerInP < p.length()) continue;
					
					if(PointerInP == p.length() && PointerInS == s.length()){
						return true;
					}
					else{
						return false;
					}
				}


				if(isValid(s, p, PointerInS, PointerInP)){//other casese
					PointerInS++;
					PointerInP= PointerInP +2;
					continue;
				}
			}

				//System.out.println("im here");
				if (OldInP == -1) {
					//System.out.println("im here");
					return false;
				} else {
					//System.out.println("im here");
					PointerInP = OldInP+2;
					OldInS++;
					PointerInS = OldInS;
					continue;

				}

		

		}
	
		while (PointerInP < p.length()-1 && p.charAt(PointerInP+1) == '*') {
		
			PointerInP+=2;
		}
		return (PointerInP == p.length());
	}
	
	public static boolean isValid(String s, String p, int ss, int pp){
		if(ss >= s.length() || pp >= p.length()) return false;
		return (s.charAt(ss) == p.charAt(pp) ||p.charAt(pp) == '.');
	}
}
