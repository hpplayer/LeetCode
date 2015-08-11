class Solution:
    # @param {integer[][]} matrix
    # @param {integer} target
    # @return {boolean}
    def searchMatrix(self, matrix, target):
        return self.searchTwoParts(matrix, 0, len(matrix[0])-1, 0, len(matrix)-1, target)
    
    def searchTwoParts(self, matrix, left, right, up, down, target):
        #recursion ending signal 
        if left > right or up > down:
            return False
        midCol = int((left + right)/2)
        row = self.binarySearch(matrix, midCol, up, down, target)
        if row == -1:
            return True
        #bottom left part, needs including current row, top right part can skipping current row
        #for cols, since we could not find target in binarySearch(), we are safely move leftward and rightward in next two searches
        return self.searchTwoParts(matrix, left, midCol - 1, row, down, target) or self.searchTwoParts(matrix, midCol + 1, right, up, row-1, target) 
    
    def binarySearch(self, matrix, col, up, down, target):
        while up <= down:
            mid = int((up + down) /2)
            if matrix[mid][col] == target:
                return -1 #row number will never be -1, so we can safly use -1 as marker of found target
            elif matrix[mid][col] < target:
                up += 1
            else:
                down -= 1
        #if we cannot find target in current row, return the first row that given larger value than target     
        return up 

matrix = [[1, 1]]           
sol = Solution()
print(sol.searchMatrix(matrix, 2))
