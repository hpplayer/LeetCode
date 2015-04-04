import java.util.*;
/**
 * Another version of BFS + Backtracking
 * In sol2, I used Node to record level and previous node
 * This approach uses the HashMap approach, we use a HashMap to store the parent string of current String 
 * There is a very important thing need to be taken care of, we must avoid add nodes other than parent node (ex: not add same level node) to its parent node list 
 * In previous version sol2, we used level to indicate whether this node is the correct parent node, here we use another approach: build next level set during BFS
 * if we found next current level node has same child that previous current level node has been visited, we can simply update this child's parent node lists, if 
 * current node has not been visited before, we can do operations that normal BFS does. 
 * After we done BFS on this level, we simply replace it with next level set that we just created.
 * The way we backtrack and create is almost similar, except now we just add new string in the front by add(0, string).
 * 
 * Other parts are simply to sol2, details please see code below.
 * 
 * Remark:
 * this approach has the similar speed to previous approach,
 * but if we change the way that we create new string(instead of using string buffer, we use char array), then the speed will improve a little bit(around 50ms)
 * 
 * @author hpPlayer
 * @date Apr 3, 2015 1:36:19 AM
 */

public class p126_sol2 {
	public static void main(String[] args) {
		//String[] str = { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" };
		String[] str =
	{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};

		Set<String> dict = new HashSet<String>(Arrays.asList(str));

	//for(List<String> l : new p126_sol1().findLadders("red", "tax", dict)){
		for (List<String> l : new p126_sol2().findLadders("cet", "ism", dict)) {
			System.out.println(l);
		}

	}
	
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> list = new LinkedList<String>();//list to store all nodes in the prev level of end node that in paths from start to end
       // Set<String> visited = new HashSet<String>();
        Set<String> que = new HashSet<String>();
        HashMap<String, Set<String>> map = new HashMap<String, Set<String>>();//use set to store parent node, so we can automatically remove those duplicate parent nodes
        map.put(start, null);
        //visited.add(start);
        que.add(start);
        boolean flag = false;
        while(!que.isEmpty() && !flag){
        	Set<String> nextLevel = new HashSet<String>();
        	//int size = que.size();//get current que size, it means how many nodes in current level 
        	for(String s : que){
        		char[] charArray=s.toCharArray();
        	//for(int i = 0; i < size; i++){//visit all nodes in current level
        		//String s = que.poll();
        		for(int j = 0; j < s.length(); j++){
        			//StringBuilder sb = new StringBuilder(s);
        			for(char c = 'a'; c <= 'z'; c++){
        				charArray[j]=c;
        				String temp = String.valueOf(charArray);
        				//if(c==sb.charAt(j)) continue;
        				//sb.setCharAt(j, c);
        				//String temp = sb.toString();
        				if(temp.equals(s)) continue;
        				if(temp.equals(end)){//found node before end
        					System.out.println("im here");
        					list.add(s);//add it into the list 
        					flag = true;
        				}else{
        					if(dict.contains(temp)){
        						if(nextLevel.contains(temp)){//if next level has this variant 
        							map.get(temp).add(s);
        						}else if (!map.containsKey(temp)){//unvisited node, add to our que
        							nextLevel.add(temp);
        							Set<String> tempSet = new HashSet<String>();
        							tempSet.add(s);
        							map.put(temp, tempSet);
        						}
        					}
        				}
        			}
        			charArray[j]=s.charAt(j);//dont remember to set char array back when done BFS on this temp string
        		}
        	}
        	que = nextLevel;
        }//end while loop
      // System.out.println(map);
        for(String s: list){
        	List<String> path = new ArrayList<String>();
        	path.add(end);//we always add new string into the head, so we can safely add end now 
        	buildPath(s, start, path, map, result);
        }
        return result;
    }
	
	
    public void buildPath(String s, String start, List<String> path, Map<String,Set<String>> map, List<List<String>> result){
    	//System.out.println(path);
	
    	if(s.equals(start)){
    	    path.add(0, s);
    		result.add(new ArrayList<String>(path));
    		path.remove(0);
    	}else{
    		for(String temp : map.get(s)){//for all its parent nodes, do DFS
 	            path.add(0, s);//always add front
    		//	System.out.println(path.size());
    			buildPath(temp, start, path, map, result);
    			path.remove(0);
    		}
    	}
	
    }
	
}
