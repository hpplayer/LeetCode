import java.util.Arrays;

/**
 * This is another brilliant solution using KMP algorithm
 * 
 * What we really need here is to find the longest palindrome start from index 0, so we can easily 
 * add the reverse of rest string in front to compose the final palindrome
 * 
 * KMP algorithm will exactly find the thing we want
 * In KMP algorithm, we have a Partial Match Table, in which we record the longest matching string between substring starts from index 0
 * and substring ends at current index.
 * ex: 
 * string: abcab
 * the table will be
 * index 0 1 2 3 4
 * value 0 0 0 1 2
 * In each index, our two substrings will not touch the boundary index
 * So index 0 will always be 0
 * index 1 here our left string is a, our right string is b, not match, fill 0
 * index 2 here our left string includes a, ab, our right string includes c, bc, not match, fill 0
 * index 3 here our left string includes a, ab, abc, our right string includes a, ca, bca, found match "a" with length 1, fill 1
 * index 4 here our left string includes a, ab, abc,abca, our right string includes b, ab, cab, bcab, found match "ab" with length 2, fill 2
 * So this is how KMP algorithm works
 * 
 * Regarding this problem, we will just slightly modify the KMP algorithm, we will manually create a new string in the form of
 * String s + "\0" + reverse of String s (i.e. mirror of s)
 * Why should we create such string? Because our goal is to find the longest palindrome starts from index 0. Our KMP table will tell us the 
 * longest matching string that shared by substring starts from 0 and ends at current string. So if we found there exists such shared longest
 * matching string between string s and its mirror string, that means such string will be the longest palindrome substring starts from index 0!
 * So this is our basic idea:
 * 1) create a KMP table or called Partial Match Table
 * 2) fill this table with KMP algorithm
 * 3) Based on the last value in the table to split the string s, the left part is a palindrome string, the right part is non palindrome string
 * 4) We simply create a new string that composed of: reverse of right part + left part + right part
 * 
 * Here are a couple of things that we need to be noticed
 * 1) To avoid the mix of input string s and its mix string, we put a space between when creating the new string
 * 2) As mentioned above, our KMP substring will never touch the boundary char in the other substring, so the value in index 0 will always be 0
 * 3) During the filling of table, we will firstly look at the previous cell value, if it is zero, we will treat current char as the start of 
 * a new string. However if the previous cell's value is not zero, then we need do some checks to decide the value
 * 4) We will firstly check if current char is matched with the next char of previous cell's matching string, if it is, then, we can extend the 
 * matching string. If not we cannot simply shrink the left string by 1, since it will destruct our matching rule
 * EX: abababb, we look at last b. we firstly match b with "a" at index 4, it does not match. We shall not simple shrink the left substring by 
 * 1 which will return abab, then we will report "wow, our current "b" match the last char in left substring and the length of longest substring is 4"
 * The actual case is that we will always look for the previous match left substring. What does that mean? we know the previous char before current "b"
 * is b, so we will try to find the previous substrings that ends also at b. Of course, we also hope such previous substring's prefix before b
 * should match our prefix of last left substring. This is exactly what is stored in KMP table. Each cell contains the information of previous
 * matched left substring(the length and the char after the left substring). So by looking at the value in the j-1 cell, we will locate 
 * the more previous left substring, which shares similar pattern of part of prefix but with shorter length. If we found a left substring, whose 
 * next char is same with our current char, then we can stop since it means we found a left substring that has matching prefix. 
 * In above case,  we firstly match b with "a" at index 4, it does not match. We will visit the more previous left substring which is "ab" starts from 
 * index 0, now the prefix matches as they are all "ab", we need to compare the char after left substring "a" with current char "b", they are 
 * not matched, so we want to search more previous left substring, but found the this is the leftmost left substring, so our search is done,
 * we can't find a match prefix, now j become 0
 * 5) please think 4) repeatly, as this simple logic is not easy to understand. I found this tip is very important when we are searching 
 * in a pattern like "abababab....".In general case we will easily get j = 0, if string not match, so we will jump the 
 * while(j > 0 && temp.charAt(j) != temp.charAt(i)) loop. If the string is extreme case like "aaaa", then j -- is same with j = table[j-1], 
 * in both cases, rule 4 will not affect the result. So maybe that's why LeetCode add an extra test case to distinguish
 * j-- and j = table[j-1]. And j-- will fail in this last case. 
 * 
 * Remark:
 * 1) sol4 of Implement strStr() (p28) also use a KMP algorithm, we can take it as a reference
 * 2) Space complexity: O(n), Time complexity: O(n), so this KMP algorithm is very fast
 * 
 * @author hpPlayer
 * @date Aug 25, 2015 1:49:06 PM
 */

public class p214_sol2 {
	public static void main(String[] args){
		//System.out.println('\0');
		System.out.println(new p214_sol2().shortestPalindrome("aacecaaa"));
	}
    //KMP algorithm
    public String shortestPalindrome(String s) {
        //create a pattern string consists of string s and its mirror version
        //With this pattern string, we can find the longest palindrome substring starts from index 0 
        //'\0' is a space char, it will help us seperate string s and its mirror version
        String temp = s + '\0' + new StringBuilder(s).reverse().toString();
        
        //Partial Match Table
        int[] table = new int[temp.length()];
        //boundary case, the start char itself has 0 match distance
        table[0] = 0;
        
        for(int i = 1; i < table.length; i++){
            //get value from prev cell (if current char pair match, then we can extend match)
            //this value will also tell us how long the match substring will be starting from index 0
            //Also, if we treat this value as index, it will indicate the next char in the string after the
            //matching string
            int j = table[i-1];
            
            //if next char after matching string is not same with current char, then our matching string will not
            //continue grow, instead, we will shrink our matching string one step backward, to see if the previous 
            //char matches our current char.
            //Of course, if the matching string has 0 length, there is no need to shrink it not even check it
            while(j > 0 && temp.charAt(j) != temp.charAt(i)){
                //we have two strings, one start from index 0, one ends at current index
                //j if treated as index, it will points to the end char of the left string
                //so if current char does not match, we will shrink left string to see if the previous char in
                //left string match our current char
            	//why not simply j--? Because, if we simply use j --, that means we assume the prefix of current char is also 
            	//same with the prefix of left substring after j--, but this is not true
            	//EX:   a, b, a, b, b
            	//index 0, 1, 2, 3, 4
            	//value 0, 0, 1, 2 
            	//when look at last b, we will look at index 2, "a" not match "b", if we simply b --
            	//then we found index at 2 -1 has "b" match "b", and now j is still 1,
            	//this assume prefix of current left string a is same with prefix of right string b, which is not true
            	//so the shrink of left string is not one char by one char, but instead recursively finding the last left substring
               System.out.println("here");
            	j = table[j-1];
            	//j--;
            }
            
            //if current char match the last char of left string, then increase the length of left string by 1
            if(temp.charAt(j) == temp.charAt(i)){
                j++;
            }
            
            //as dicussed above, j can be treated as the length of matching string or the index of next char after
            //left string, here we need set the length of matching string to current cell
            
            table[i] = j;
            
        }
        System.out.println(Arrays.toString(table));
        //what we really care is the last cell in Partial Match Table, it will tell us the longest palindrome
        //start from index 0, the reverse of rest substring of this palindrome will be inserted in front of index 0 to create the result
        //What we need to do is add the reverse string of this rest substring in front to create the result palindrome
        
        return new StringBuilder(s.substring(table[table.length -1])).reverse().toString() + s; 
        
        
    }
}
