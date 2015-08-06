class Solution:
    # @param {integer[]} height
    # @return {integer}
    
    "two pointer approach"
    def trap(self, height):
        if not height:
            return 0
        left, right = 0, len(height) - 1
        leftMax, rightMax, result = height[0], height[right], 0
        
        while left < right:
            #always move the lower bar
            if height[left] < height[right]:
                # we will skip index 0 since the height[0] = leftMax initially
                if height[left] >= leftMax:
                    leftMax = height[left]
                else:
                    result += leftMax - height[left] #leftMax < height[right]
                left += 1
            else:
                #we will skip index len-1 since the height[len-1] == rightMax initially
                if height[right] >= rightMax:
                    rightMax = height[right]
                else:
                    result += rightMax - height[right] #rightMax < height[left]
                right -= 1
        return result