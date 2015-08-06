class Solution:
    # @param {integer[]} height
    # @return {integer}
    
    "sol1, DP approach"
    def trap(self, height):
        if not height:
            return 0
        #dont forget use [0] * n instead of []*n
        #[] * n is still empty array with length 0
        left = [0] * len(height)
        right = [0] * len(height)
        for i in range(len(height)):
            left[i] = height[0] if i == 0 else max(height[i], left[i-1])
            j = len(height) - i - 1
            right[j] = height[j] if j == len(height) - 1 else max(height[j], right[j+1])
        
        #leftmost and rightmost bar will not trap water
        result = 0
        for i in range(1, len(height) -1):
            if height[i] < min(left[i-1], right[i+1]):
                result += min(left[i-1], right[i+1]) - height[i]
        return result
    
sol = Solution()
print(sol.trap([0]))