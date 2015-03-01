import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class p120 {
	public static void main(String[] args) {
		List<Integer> inner1 = new ArrayList<Integer>();
		inner1.add(-1);
		List<Integer> inner2 = new ArrayList<Integer>();
		inner2.add(-2);
		inner2.add(-3);
		List<List<Integer>> outer = new ArrayList<List<Integer>>();
		outer.add(inner1);
		outer.add(inner2);
		System.out.println(minimumTotal(outer));

	}

	public static int minimumTotal(List<List<Integer>> triangle) {
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		return dp(hs, triangle, 0, 0);
	}

	public static int dp(HashMap<String, Integer> hs,
			List<List<Integer>> triangle, int layer, int index) {
		String key = layer + " " + index;
		// System.out.println(key);

		if (hs.containsKey(key)) {
			// System.out.println("Im here");
			return hs.get(key);
		}

		if (triangle.isEmpty())
			return 0;
		if (layer >= triangle.size()) {
			return 0;
		}
		if (layer == triangle.size() - 1) {
			return triangle.get(layer).get(index);
		}
		int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;

		left = dp(hs, triangle, layer + 1, index);
		// System.out.println("left" + left);

		// System.out.println("index " + index);
		if (index + 1 < triangle.get(layer + 1).size()) {
			right = dp(hs, triangle, layer + 1, index + 1);
			// System.out.println("right" + right);
		}
		// System.out.println(Math.min(left, right));
		int result = triangle.get(layer).get(index) + Math.min(left, right);
		hs.put(key, result);
		return result;
	}

}
