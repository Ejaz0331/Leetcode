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
    public long kthLargestLevelSum(TreeNode root, int k) {
        if (root == null) return -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                sum += curr.val;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            minHeap.offer(sum);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.size() < k ? -1 : minHeap.peek();
    }
}