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
    public boolean isUnivalTree(TreeNode root) {
           return dfs(root);
    }
    private boolean dfs(TreeNode root){
        if(root == null) return true;
        boolean leftValid = dfs(root.left);
        boolean rightValid = dfs(root.right);
        if(!leftValid || !rightValid) return false;
        if(root.left != null && root.val != root.left.val) return false;
        if(root.right != null && root.val != root.right.val) return false;
        return true;
    }
}