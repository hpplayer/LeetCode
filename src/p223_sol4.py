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
        left = max(A,E)
        right = max(left, min(C,G))
        bottom = max(B, F)
        top = max(bottom, min(H,D))
        return (C - A)*(D-B) - (right -left)*(top - bottom) + (G - E) * (H-F)