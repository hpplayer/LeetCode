class Solution:
    # @param {integer} A
    # @param {integer} B
    # @param {integer} C
    # @param {integer} D
    # @param {integer} E
    # @param {integer} F
    # @param {integer} G
    # @param {integer} H
    # @return {integer}
    def computeArea(self, A, B, C, D, E, F, G, H):
        shared = 0
        if E >= C or G <= A or F >= D or B >= H:
            shared = 0
        else:
            shared = (min(C,G) - max(A,E)) * (min(D,H) - max(B,F))
        return (C - A) * (D - B) - shared + (G - E) * (H - F)