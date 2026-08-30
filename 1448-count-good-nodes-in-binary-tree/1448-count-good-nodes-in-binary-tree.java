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
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val); // start DFS with root value
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0; // base case

        int count = 0;
        if (node.val >= maxSoFar) count = 1; // good node?

        // update max value seen so far
        maxSoFar = Math.max(maxSoFar, node.val);

        // recurse left and right
        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);

        return count;
    }
}