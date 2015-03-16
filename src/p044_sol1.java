public class p044_sol1 {
	public static void main(String[] args){
		String a = "aa";
		String b = "a";
		System.out.println(isMatch(a,b));
	}
	public static boolean isMatch(String s, String p) {
		if (s.length() == 0 || p.length() == 0)
			return false;
		return DFS(s, p);
	}

	public static boolean DFS(String s, String p) {
		// if(p.length() == 0){
		// return s.length() == 0;
		// }
		int PointerP = 0, PointerS = 0, BackupP = -1, BackupS = -1;
		while (PointerS < s.length()) {
	
			if (PointerP < p.length() && p.charAt(PointerP) != '*') {
				if (!isValid(s, p, PointerS, PointerP)) {
					return false;
				} else {
					PointerP++;
					PointerS++;
				}
				PointerP = nextStar(p, PointerP);
			} else if (PointerP < p.length() && p.charAt(PointerP) == '*') {
				PointerP = nextStar(p, PointerP);
				BackupP = PointerP;
				BackupS = PointerS;

				PointerP++;// get next bit
			} else {// reach end
				if (BackupP  == -1) {
					return false;// no previous star
				} else {
					BackupS++;// try 0, 1, ,2, 3 ,4 offset
					PointerS = BackupS;
					PointerP = BackupP + 1;
				}
			}

		}

		while (PointerP < p.length() && p.charAt(PointerP) == '*') {
			PointerP++;
		}

		return PointerP == p.length();
	}

	public static int nextStar(String p, int PointerP) {
		while (PointerP < p.length() - 1 && p.charAt(PointerP + 1) == '*') {
			PointerP++;
		}
		return PointerP;
	}

	public static boolean isValid(String s, String p, int PointerS, int PointerP) {
		if (s.length() <= PointerS || p.length() <= PointerP)
			return false;
		return s.charAt(0) == p.charAt(0) || p.charAt(0) == '?'
				|| p.charAt(0) == '*';
	}

}
