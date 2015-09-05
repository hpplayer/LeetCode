import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
Text Justification

Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.

*/

/**
 * This is my original AC solution without help.
 * The difficulty part of this problem is how to add spaces in each line.
 * Here I use a dequeue to add spaces. I firstly store words that can form a line in the queue
 * Then I calculate the spaces that we need to add for current line.
 * Then I pop the word of queue from left end, add one space, and push back to the right end
 * So it is like a loop. I pop one word, add space, then push it back.
 * After we have inserted all spaces into the queue. Then I let the loop continue without add space, I will stop the queue once I found 
 * the last word in dequeue is the same word as original input. Because we only add spaces after each word we skip the last word. So the last 
 * word won't change.
 * The above steps only apply to general lines, for last line, I have to write a specific function that add one space after each word, then 
 * add all extra spaces after the updated sentence
 * 
 * The main loop here scans the given word array.
 * 1)If current word is a new start of line, we just add it into the dequeue
 * 2)If current word is able to squeeze into the line, we just add it with a space (which is required) into the dequeue
 * 3)If current word is not able to to squeeze into the line, we will start buildString with the dequeue
 * After the built is done, we will clear dequeue, and reset markers, so next word will begin a new start
 * 
 * My algorithm works and is very fast. Here I use the dequeue to form a loop to handle extra spaces 
 * But there is another solution that use calculation to handle extra spaces with division. So it will make code shorter and does not need
 * extra data structure, thus the code is shorter and more elegant. Sol2 shows such solution
 * 
 * So sol2 is more recommended, but my sol1 is still a great solution
 * 
 * @author hpPlayer
 * @date Sep 5, 2015 6:44:05 PM
 */

public class p068_sol1 {
	public static void main(String[] args){
		//String words[] = {"This", "is", "an", "example", "of", "text", "justification."};
		//String words[] = {"Listen","to","many,","speak","to","a","few."};
		//String[] words = {"a","b","c","d","e"};
		String[] words = {"a", "b"};
		for(String s : fullJustify(words, 2)){
			System.out.println(s);
		}
	}
    public static List<String> fullJustify(String[] words, int maxWidth) {
        Deque<String> sb = new LinkedList<String>();
       
        int count = 0, next = 0;
        List<String> result = new ArrayList<String>();
        if(words == null || words.length == 0) return result;
        
        for(int i= 0; i < words.length; i++){
        	String s = words[i];
            if(count == 0){
                sb.offer(s);
                count = s.length();
            }else if(count + 1 + s.length() <= maxWidth){
                sb.offer(" " + s);
                count += s.length() + 1;
            }else{//can't squeeze current string into line, start a new line
                result.add(buildString(sb, maxWidth - count));
                count = s.length();
                sb.clear();
                sb.offer(s);
                next = i;//the start of new line
            }
            
        }
        //if we have last line that is not full
        if(result.size() == 0 || !result.get(result.size() - 1).equals(words[words.length - 1])){
        	result.add(buildLastString(words, next, maxWidth));
        }
        return result;
    }
    
    public static String buildLastString(String[] words, int start, int maxWidth){
    	//extreme case that first line is also the last line, and it is not full
    	int count = 0;
    	String temp = "";
    	for(int i = start; i < words.length; i++){
    		temp += words[i] + " ";
    		count += 1 + words[i].length();
    	}
    	if(count > maxWidth){
    		temp = temp.substring(0, temp.length() - 1);
    	}
    	for(int i = 0; i < maxWidth - count; i++){
    		temp += " ";
    	}   	
    	
    	return temp;
    }
    
    public static String buildString(Deque<String> deq, int diff){
    	String result = "";
    	String last = deq.peekLast();
    	while (diff > 0){
    		//extreme case, single word
    		if(deq.size() == 1){
    			deq.addLast(deq.pollFirst() + " ");
        		diff --;
        		continue;
    		}
    		//System.out.println(deq);
    		if(deq.peekFirst().equals(last)){
    			deq.addLast(deq.pollFirst());
    		}else{
        		deq.addLast(deq.pollFirst() + " ");
        		diff --;
    		}
    	}
    	
    	//a bug here, here I assume if string is unique, but we may have duplicate strings in one line
    	//while(!deq.peekLast().equals(last) && deq.size() != 1){//if extreme case, single word, don't enter the loop
       	//so, instead of comparing characters in string, we also need compare the reference, which is perfect to use "==" 
    	while(deq.peekLast() != last && deq.size() != 1){//if extreme case, single word, don't enter the loop
    		deq.addLast(deq.pollFirst());
    	}
    	for(String s : deq) result += s; 
    	return result;
    }
}
