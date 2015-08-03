class Solution:
    # @param {character[][]} board
    # @return {boolean}
    def isValidSudoku(self, board):
        for i in range(len(board)):
            print(i)
            for j in range(len(board[0])):
                #if board[i][j] is '.':, wrong code!
                if board[i][j] == '.':  #WARNING! '==' means value same while 'is' means same object 
                    continue
                else:
                    if not self.isValid(i, j, board, board[i][j]):
                        return False
        return True
    
    def isValid(self, row, col, board, val):
        for i in range(len(board)):
            if board[row][i] == val and i != col:
                return False
            if board[i][col] == val and i != row:
                return False
                
            rowMid = int(row / 3) * 3 + 1
            colMid = int(col / 3) * 3 + 1
            
        for i in range(-1, 2, 1):
            for j in range (-1, 2, 1):
                if board[rowMid+i][colMid+j] == val and (rowMid+i != row and colMid+j != col):
                    return False;
        
        return True
    

"below is test part"

matrix = [['.' for _ in range(9)] for _ in range(9)]
matrix[0] = ['.', '8', '7', '6', '5', '4', '3', '2', '1']
for i in range(1, 9):
    matrix[i][0] = str(i+1)
print(matrix)   
sol = Solution()
print(sol.isValidSudoku(matrix))