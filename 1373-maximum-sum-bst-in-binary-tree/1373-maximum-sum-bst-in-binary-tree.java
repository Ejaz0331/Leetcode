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
class newNode {
    int maxSum;
    int minVal;
    int maxVal;

    public newNode(int sum, int minVal, int maxVal) {
        this.maxSum = sum;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }
}

class Solution {
    int max = 0;
    private newNode helper(TreeNode root) {
        if (root == null)
            return new newNode(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        newNode left = helper(root.left);
        newNode right = helper(root.right);
        if (left.maxVal < root.val && right.minVal > root.val) {
            int currSum = left.maxSum + right.maxSum + root.val;
            max = Math.max(currSum, max);
            int minVal = Math.min(left.minVal, root.val);
            int maxVal = Math.max(right.maxVal, root.val);
            return new newNode(currSum, minVal, maxVal);
        }
        return new newNode(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public int maxSumBST(TreeNode root) {
        helper(root);
        return max;
    }
}