import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
/**
 * My AC solution without help, though it is slow.
 * The basic idea is similar to most other solutions.
 * Using standard BFS to search the min path, then stop BFS on that level, then using backtrack to build the paths
 * The problem in my solution is that I cannot properly handle duplicate children nodes
 * (ex: Tom->Tam, Yam -> Tam, after first BFS is done, Tam has been listed as visited, so Yam may not continue the path include tam)
 * My solution is checking levels, if current temp is variant from string s, while it is in next levels, then build a new node and 
 * add parent node to it, so there will be many duplicates. (around: 1700 ms)
 * In this solution, I used tree node to record parent node, and a hashMap to lookup string with node
 * Sol2 uses a hashmap to record parent string and current string
 *  
 * Update:
 * Since the main idea is right, just small bug.
 * I rewrite the handler for duplicate case:
 * now the I build a lookup table, that can relate string with node, so if we found some variant of current string 
 * is already in visited set, but this variant's prev list does not include current string, we can simply add string to 
 * this list, and when do backtrack from end, this variant will have a path include this string.
 * And the running time has been reduced to (800ms)
 * 
 * @author hpPlayer
 * @date Apr 2, 2015 10:00:31 PM
 */
public class p126_sol1 {
	public static void main(String[] args) {
		//String[] str = { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" };
		String[] str =
	{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};

		Set<String> dict = new HashSet<String>(Arrays.asList(str));

	//for(List<String> l : new p126_sol1().findLadders("red", "tax", dict)){
		for (List<String> l : new p126_sol1().findLadders("cet", "ism", dict)) {
			System.out.println(l);
		}

	}

	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
		BFS(start, end, dict);
		return Result;
	}

	public class Node {
		int level = 0;
		ArrayList<Node> prevs = new ArrayList<Node>();
		String str;
		public Node(String str, int l){
			this.str =str;
			this.level = l;
		}
	}
	HashMap<String, Node> map = new HashMap<String, Node>();
	public void BFS(String start, String end, Set<String> dict) {
		HashSet<String> Visited = new HashSet<String>();
		//HashMap<String, Integer> hs = new HashMap<String, Integer>();
		boolean flag = false;
		Queue<Node> que = new LinkedList<Node>();
		Node tempNode = new Node(start, 1);
		que.add(tempNode);
		map.put(start, tempNode);
		Visited.add(start);
		//hs.put(start, 1);
		while(!flag && !que.isEmpty()){
			//System.out.println(que.size());
			int size = que.size();
			for(int j = 0; j < size; j++){
				Node n = que.poll();
				if(n.str.equals("rex")){
					//System.out.println(n.prevs.get(0).str);
				}
				for(int i = 0; i < n.str.length(); i++){
					StringBuilder sb = new StringBuilder(n.str);
					for(char c = 'a'; c <= 'z'; c++){
						sb.setCharAt(i, c);
						String temp = sb.toString();
						if(temp.equals(end)){
							Node endNode = new Node(temp, n.level +1);
							endNode.prevs.add(n);
							flag = true;
							List<String> path = new ArrayList<String>();
							path.add(temp);
							buildPath(endNode, path);
							//Visited.remove(n.str);
						}else{
							//if(dict.contains(temp) && !Visited.contains(temp)){
							//if(dict.contains(temp) && (!Visited.contains(temp) || (Visited.contains(temp) && hs.get(temp) > n.level))){
							if(dict.contains(temp)){// && (!Visited.contains(temp) || (Visited.contains(temp) && hs.get(temp) > n.level))){
								if(!Visited.contains(temp)){
									//hs.put(temp, n.level+1);
									Node newNode = new Node(temp, n.level+1);
									map.put(temp, newNode);
									newNode.prevs.add(n);
									Visited.add(temp);
									que.add(newNode);
								}else{
									if(!map.get(temp).prevs.contains(n) && map.get(temp).level > n.level){
										map.get(temp).prevs.add(n);
									}					
								}
							}
						}
					}
				}
			}
		
		}
	}
	List<List<String>> Result = new ArrayList<List<String>>();
	public void buildPath(Node n, List<String> path){
		if(n.prevs.size() == 0){
			List<String> temp = new ArrayList<String>(path);
			Collections.reverse(temp);
			Result.add(temp);
			return;
		}
		for(int i = 0; i < n.prevs.size(); i++){
			path.add(n.prevs.get(i).str);
			buildPath(n.prevs.get(i), path);
			path.remove(path.size()-1);
		}
	}

}
