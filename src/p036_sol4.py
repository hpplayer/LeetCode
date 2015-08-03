class Solution:
    # @param {character[][]} board
    # @return {boolean}
    def isValidSudoku(self, board):
        rows = set()
        cols = set()
        blocks = set()
        
        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] != '.':
                    if board[i][j] in rows:
                        return False
                    else:
                        rows.add(board[i][j])
                if board[j][i] != '.':
                    if board[j][i] in cols:
                        return False
                    else:
                        cols.add(board[j][i])
                
                #scan each block from 0 to 8
                rowHelper = int(i/3)*3
                colHelper =  i%3*3
                if board[rowHelper + j/3][colHelper + j%3] != '.':
                    if board[rowHelper + j/3][colHelper + j%3] in blocks:
                        return False
                    else:
                        blocks.add(board[rowHelper + j/3][colHelper + j%3])
                        
            rows.clear()
            cols.clear()
            blocks.clear()
        
        return True