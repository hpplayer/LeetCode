import java.util.*;
/**
 * Rewrite node solution, running time around 1000ms
 * @author hpPlayer
 * @date Apr 3, 2015 3:12:10 PM
 */

public class p126_sol3 {
	public static void main(String[] args) {
		String[] str = { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" };
		//String[] str =
	//{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
		//String[] str = {"hot", "dog"};
		Set<String> dict = new HashSet<String>(Arrays.asList(str));

	for(List<String> l : new p126_sol1().findLadders("red", "tax", dict)){
		//for (List<String> l : new p126_sol3().findLadders("cet", "ism", dict)) {
		//for (List<String> l : new p126_sol3().findLadders("hot", "dog", dict)) {
			System.out.println(l);
		}

	}
	 public class Node{
	        LinkedHashSet<Node> prevs;
	        String str;
	        int level;
	        public Node(String str, int lvl){
	            this.str =str;
	            this.level = lvl;
	            prevs = new LinkedHashSet<Node>();
	        }
	    }
	    HashMap<String, Node> map = new HashMap<String, Node>();
	    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
	        return BFS(start, end, dict);
	    }
	    
	    public List<List<String>> BFS(String start, String end, Set<String> dict){
	        Queue<Node> que = new LinkedList<Node>();
	        Node startNode = new Node(start, 1);
	        que.add(startNode);
	        boolean flag = false;
	        while(!que.isEmpty() && !flag){
	            int size = que.size();
	            for(int i = 0; i < size; i++){
	                Node currNode = que.poll();
	                for(int j = 0; j < currNode.str.length(); j++){
	                    StringBuilder sb = new StringBuilder(currNode.str);
	                    for(char c = 'a'; c <= 'z'; c++){
	                        sb.setCharAt(j, c);
	                        String temp = sb.toString();
	                        if(temp.equals(currNode.str)) continue;
	                        if(temp.equals(end)){//found target node
	                        	//visit only once in this problem, the branches are from above
	                        	flag = true;
	                            if(!map.containsKey(end)){
	                                Node endNode = new Node(temp, currNode.level+1);
	                                endNode.prevs.add(currNode);
	                                map.put(end, endNode);
	                            }else{
	                                map.get(end).prevs.add(currNode);
	                            }
	                        }else{//if not found target node 
	                            if(dict.contains(temp)){//but in dict we can continue
	                                if(!map.containsKey(temp)){//first time visit this node, do normal BFS
	                                    Node tempNode = new Node(temp, currNode.level+1);
	                                    tempNode.prevs.add(currNode);
	                                    map.put(temp, tempNode);
	                                    que.add(tempNode);
	                                }else if (!map.get(temp).prevs.contains(currNode) && map.get(temp).level > currNode.level){//not first time visit, but have not add this parent node
	                                    map.get(temp).prevs.add(currNode);
	                                }
	                            }
	                        }
	                    }
	                }
	                
	            }
	        }
	        List<List<String>> result = new ArrayList<List<String>>();
	        List<String> path = new ArrayList<String>();
	        path.add(end);
	        buildPath(result, path, map.get(end), start);
	        return result;
	    }
	    
	    
	    public void buildPath(List<List<String>> result, List<String> path, Node node, String start){
	    	if(node == null) return;
	        if(node.str.equals(start)){//reach head
	            //System.out.println("im here");
	        	result.add(new ArrayList<String>(path));
	            return;
	        }
	        
	        for(Node n: node.prevs){
	            path.add(0, n.str);
	            buildPath(result, path, n, start);
	            path.remove(0);
	        }
	    }
}
