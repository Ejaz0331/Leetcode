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
    private TreeNode[][] memoList = new TreeNode[20][30000];
    private int[] memoSize = new int[20];

    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            return new ArrayList<>();
        }
        if (memoSize[n] > 0) {
            List<TreeNode> res = new ArrayList<>();
            for (int i = 0; i < memoSize[n]; i++) {
                res.add(memoList[n][i]);
            }
            return res;
        }
        
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            TreeNode node = new TreeNode(0);
            res.add(node);
            memoList[1][0] = node;
            memoSize[1] = 1;
            return res;
        }
        
        int idx = 0;
        for (int i = 1; i < n; i += 2) {
            int j = n - 1 - i;
            List<TreeNode> leftTrees = allPossibleFBT(i);
            List<TreeNode> rightTrees = allPossibleFBT(j);
            
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                    memoList[n][idx++] = root;
                }
            }
        }
        
        memoSize[n] = idx;
        return res;
    }
}