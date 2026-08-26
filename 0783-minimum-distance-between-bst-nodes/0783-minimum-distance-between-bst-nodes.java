/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int rs = Integer.MAX_VALUE;
    private int[] minMaxSubTree(TreeNode head) { 
        if(head == null) return null;
        int[] L = minMaxSubTree(head.left);   // min,max left sub tree
        int[] R = minMaxSubTree(head.right);  // min,max right sub tree

        int min = head.val, max = head.val;
        if(L != null){
            rs = Math.min(rs, head.val - L[1]);
            min = L[0];
        }

        if(R != null){
            rs = Math.min(rs, R[0] - head.val);
            max = R[1];
        }
        return new int[]{min, max};
    }
    public int minDiffInBST(TreeNode root) {
        minMaxSubTree(root);
        return rs;
    }
}