/**
 * This is a pure math solution that uses the concepts of combination or permutation
 * we will scan the input, assume each digit become 1, then calculate the combinations around that digit, then sum up.
 * Upon current digit, the number is splitted into three parts, left part, current digit and right part
 * So when turn each digit to 1, there will be 3 cases:
 * 1)real number is 0: that means we have variation of(left part -1) * variation of(right part)
 * Ex: 1230456, left part we have variant from 0->122, since virtual number 1231456 will be larger than real number 1230456,
 * so we can only make left part reduce to 122 to make left part legal, for right part, we have variation from 0->999, since 
 * 1221000->1221999 are all legal numbers
 * 
 * 2) real number is 1:  that means we have variation of variation of(left part -1) * variation of(right part) PLUS 
 * variation of (left part) which is 1 * variation of (current right part)
 * Ex:1231456, left part we have variant from 0->122, right part we have variant from 0->999 since 0->1221999 are all legal
 * But we may also have left part as FIXED 123, right part as 0->456, since 1231000->1231456 all all legal number
 * 
 * 3) real number > 1: that means we can have variation of (left part) * variation of(right part)
 * EX:1232456, left part we can have variation from 0->123, right part we can have variation from 0->999, since 0->1231999 are all
 * legal numbers
 * 
 * So, from above, we can found left part can only from 0->left part, but right part can be 0 -> 999 or 0 ->right part depends on 
 * the case.
 * This solution just provides a new approach towards this problem in mathmatical way
 * But it can not be generalized to find number of digit k
 * @author hpPlayer
 * @date Aug 13, 2015 11:14:42 PM
 */
public class p233_sol3 {
    public int countDigitOne(int n) {
        int result = 0;
        //start with highNum = n, lowNum = 0, low digits can tell us totally how many variants can we have
        //lowDigits is from 0->999, which contains 0 already, so by default the value is 1
        int highNum = n, lowNum = 0, lowDigits = 1;
        while(highNum > 0){
        	//start from last digit, so highNum becomes all digits before last digit
        	int currNum = highNum % 10;
        	highNum = highNum / 10;
        	
        	//currNum = 0 means we have choice from (0->highNum-1) high numbers * (0->lowDigits-1) low numbers
        	if(currNum == 0){
        		result += highNum * lowDigits;
        	}else if (currNum == 1){
        	//currNum = 0 means we have choice from (0->highNum-1) high numbers * (0->lowDigits-1) low numbers
        	//and we also have choice from (highNum) 1 high numbers* (0->lowNum) low numbers
        		result += highNum * lowDigits;
        		result += 1 * (lowNum+1);
        	}else{
        		//free to chose from (0->highNum) high numbers * (0->lowDigits-1) low numbers
        		//since the actual value is larger than our current virtual value
        		result += (highNum+1)*lowDigits;
        	}
        	
        	//add one front bit(i.e. currNum) to it
        	lowNum += currNum * lowDigits;
        	lowDigits *= 10;
        }
        
        return result;
    }
}
