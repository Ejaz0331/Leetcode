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
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        root.val = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> currentLevel = new ArrayList<>();
            int nextLevelSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node);

                if (node.left != null) {
                    nextLevelSum += node.left.val;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    nextLevelSum += node.right.val;
                    queue.offer(node.right);
                }
            }

            for (TreeNode node : currentLevel) {
                int siblingSum = 0;
                if (node.left != null) siblingSum += node.left.val;
                if (node.right != null) siblingSum += node.right.val;

                if (node.left != null) {
                    node.left.val = nextLevelSum - siblingSum;
                }
                if (node.right != null) {
                    node.right.val = nextLevelSum - siblingSum;
                }
            }
        }

        return root;
    }
}