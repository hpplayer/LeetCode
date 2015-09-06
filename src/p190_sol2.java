/**
 * This algorithm is very smart but hard to write in code interview
 * In this algorithm, we don't need the loop, instead we just do 5 swaps which can be done with marker and shift
 * The basic idea is very similar to divide and conquer or merge-sort
 * First swap left 16 bits and right 16 bits
 * Then swap left 8 bits and right 8 bits
 * ...
 * Finally swap each two bits and we are done
 * 
 * The importance of this algorithm is how to pick the marker
 * The marker can help us get the value at certain bits, then we use shift to mimic the swap operations
 * Do this into two directions then merge, which give us an effect of swap
 * Firstly, we swap the left part of 16bits and right part of 16bits, We even don't need the marker, just do shift
 * Then we need swap each two bytes, so our marker should get something like 1111 1111 0000 0000
 * Then we need swap each pair of bytes, so our marker should get something like 1111 0000 1111 0000
 * Then we need swap each 4 bits, i.e. half of a byte, so our marker should get something like 1100 1100 1100 1100
 * Finally, we need just need to swap each 2 bits, i.e. a quarter of a byte, so our marker should get something like 1010 1010 1010 1010
 * 
 * EXAMPLE:
 * ABCD:
 * first swap: CD AB
 * Second swap: DC BA 
 * we are done
 * 
 * 
 * Example:
 * ~~~~~ZERO SWAP~~~~~~~
 * input n: 	0000 0010 1001 0100 0001 1110 1001 1100
 * n >>> 16 	0000 0000 0000 0000 0000 0010 1001 0100 byte 1,2,3,4 right shift to become 5,6,7,8
 * 
 * input n: 	0000 0010 1001 0100 0001 1110 1001 1100 byte 5,6,7,8 left shift to become 1,2,3,4
 * n <<< 16		0001 1110 1001 1100 0000 0000 0000 0000
 * 
 * n after "|": 0001 1110 1001 1100 0000 0010 1001 0100
 * ~~~~~FIRST SWAP~~~~~~
 * input n: 	0001 1110 1001 1100 0000 0010 1001 0100
 * 0xff00ff00:	1111 1111 0000 0000 1111 1111 0000 0000
 * &:			0001 1110 0000 0000 0000 0010 0000 0000  get 1,2,5,6 bytes, we don't care about byte 7, 8, its safe to right shift 2 bytes
 * >>> 8: 		0000 0000 0001 1110 0000 0000 0000 0010	 after right shift, 1,2,5,6 bytes become 3, 4, 7, 8 bytes
 * 
 * input n: 	0001 1110 1001 1100 0000 0010 1001 0100
 * 0x00ff00ff:  0000 0000 1111 1111 0000 0000 1111 1111 
 * &:			0000 0000 1001 1100 0000 0000 1001 0100 get 3,4,7,8 bytes, we don't care about byte 1,2 , its safe to left shift 2 bytes
 * <<<8:		1001 1100 0000 0000 1001 0100 0000 0000 after left shift, 3, 4, 7, 8 bytes becomes 1,2,5,6 bytes
 * 
 * n after "|": 1001 1100 0001 1110 1001 0100 0000 0010 now 3,4 bytes have been swapped with 1,2 bytes, 5,6 bytes just swapped with 7,8
 *  
 * ~~~~~SECOND SWAP~~~~~~
 * new n:		1001 1100 0001 1110 1001 0100 0000 0010
 * 0xf0f0f0f0:  1111 0000 1111 0000 1111 0000 1111 0000 
 * &: 			1001 0000 0001 0000 1001 0000 0000 0000 get 1, 3, 5, 7 bytes, we don't care about byte 8, its safe to right shift 1 byte
 * >>> 4: 		0000 1001 0000 0001 0000 1001 0000 0000 after right shift, now 1,3,5,7 bytes become 2,4,6,8 bytes
 * 
 * new n:		1001 1100 0001 1110 1001 0100 0000 0010
 * 0x0f0f0f0f:  0000 1111 0000 1111 0000 1111 0000 1111
 * &:			0000 1100 0000 1110 0000 0100 0000 0010 get 2, 4, 6, 8 bytes, we don't care about byte 1, its safe to left shift 1 byte
 * <<< 4:		1100 0000 1110 0000 0100 0000 0010 0000 after left shift, now 2,4, 6, 8 bytes become 1,3,5,7 bytes
 * 
 * n after "|": 1100 1001 1110 0001 0100 1001 0010 0000 now each pair of byte has been swapped 1 <> 2, 3<>4, 5<>6, 7<>8
 * 
 * ~~~~~THIRD SWAP~~~~~~
 * new n:		1100 1001 1110 0001 0100 1001 0010 0000
 * 0xcccccccc:  1100 1100 1100 1100 1100 1100 1100 1100
 * &:			1100 1000 1100 0000 0100 1000 0000 0000 get 1-2, 5-6, 9-10, 13-14, 17-18, 21-22, 25-26, 29-30 bits, safe to remove bit 31-32
 * >>> 2:		0011 0010 0011 0000 0001 0010 0000 0000 after right shift, now 1-2, 5-6, 9-10, 13-14, 17-18, 21-22, 25-26, 29-30 bits are + 2
 * 
 * new n:		1100 1001 1110 0001 0100 1001 0010 0000
 * 0x33333333:	0011 0011 0011 0011 0011 0011 0011 0011
 * &:			0000 0001 0010 0001 0000 0001 0010 0000 get 3-4, 7-8, 11-12, 15-16, 19-20, 23-24, 27-28, 31-32 bits, safe to remove bit 1-2
 * <<<2: 		0000 0100 1000 0100 0000 0100 1000 0000 after left shift, now 3-4, 7-8, 11-12, 15-16, 19-20, 23-24, 27-28, 31-32 bits are - 2
 * 
 * n after "|": 0011 0110 1011 0100 0001 0110 1000 0000 now each pair of two bits has been swapped
 * 
 * ~~~~~FOUTH SWAP~~~~~~
 * new n:		0011 0110 1011 0100 0001 0110 1000 0000
 * 0xcccccccc:  1010 1010 1010 1010 1010 1010 1010 1010
 * &:			0010 0010 1010 0000 0000 0010 1000 0000 get 1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31 bits, safe to remove bit 32
 * >>>1 : 		0001 0001 0101 0000 0000 0001 0100 0000 after right shift, now 1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31 are + 1
 * 
 * new n:		0011 0110 1011 0100 0001 0110 1000 0000
 * 0x55555555:  0101 0101 0101 0101 0101 0101 0101 0101 
 * &:			0001 0100 0001 0100 0001 0100 0000 0000 get 2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32 bits, safe to remove bit 1
 * <<<1: 		0010 1000 0010 1000 0010 1000 0000 0000 after left shift, now 2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32 are -1
 * 
 * n after "|": 0011 1001 0111 1000 0010 1001 0100 0000 now each pair of bit has been swapped
 * Original n:  0000 0010 1001 0100 0001 1110 1001 1100
 * Expected n:  0011 1001 0111 1000 0010 1001 0100 0000
 * 
 * Remark:
 * 1) marker is very important in picking bits, but here we also need use left/right shifts to finish the swap operation
 * 2) 0x is the heading of hexadecimal
 * 3) f is 1111, a is 1010, c is 1100
 * 3) I made a little mistake above. One byte is 8 bits. However, I assume one byte is 4 bits above.
 * @author hpPlayer
 * @date Sep 5, 2015 11:52:40 PM
 */
public class p190_sol2 {
	public static void main(String[] args){
		System.out.println(1<<2);
		System.out.println(new p190_sol2().reverseBits(43261596 ));
	}
    public int reverseBits(int n) {
        //16->8->4->2
        //f is 1111, 0 is 0000
        //c is 1100, 3 is 0011
        //a is 1010, 5 is 0101
        n = (n>>>16) | (n<<16);
        n = ((n&0xff00ff00)>>>8) | ((n&0x00ff00ff) << 8);
        n = ((n&0xf0f0f0f0)>>>4) | ((n&0x0f0f0f0f) << 4);
        n = ((n&0xcccccccc)>>>2) | ((n&0x33333333) << 2);
        n=  ((n&0xaaaaaaaa)>>>1) | ((n&0x55555555) << 1);
        
        return n;
    }
}
