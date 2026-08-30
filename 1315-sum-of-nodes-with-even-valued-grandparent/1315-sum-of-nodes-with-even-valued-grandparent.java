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
    int sum = 0;
    List<TreeNode> list = new ArrayList<>();

    public int sumEvenGrandparent(TreeNode root) {
        storeGP(root);

        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if (node.val % 2 == 0) {
                if (node.left!=null&&node.left.left != null) {
                    sum += node.left.left.val;
                }
                if (node.left!=null&&node.left.right != null) {
                    sum += node.left.right.val;
                }
                if (node.right!=null&&node.right.left != null) {
                    sum += node.right.left.val;
                }
                if (node.right!=null&&node.right.right != null) {
                    sum += node.right.right.val;
                }
            }

        }
        return sum;
    }

    void storeGP(TreeNode root) {
        if (root == null)
            return;
        if ((root.left != null && (root.left.left != null || root.left.right != null)) ||
                (root.right != null && (root.right.left != null || root.right.right != null))) {
            list.add(root);
        }

        storeGP(root.left);
        storeGP(root.right);

    }
}