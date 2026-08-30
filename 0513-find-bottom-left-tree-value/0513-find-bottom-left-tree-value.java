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
    int maxLevel = -1;
    int ans = 0;
    public int findBottomLeftValue(TreeNode root) {
        value(root, 0);

        return ans;
    }
    private void value(TreeNode root, int level){
        if(root == null) return;

        if(level > maxLevel){
            maxLevel = level;
            ans = root.val;
        }

        value(root.left, level + 1);
        value(root.right, level + 1);
    }
}