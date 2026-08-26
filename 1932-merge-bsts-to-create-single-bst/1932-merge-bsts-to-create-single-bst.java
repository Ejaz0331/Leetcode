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
    HashMap<Integer, TreeNode> roots = new HashMap<>();
    public TreeNode canMerge(List<TreeNode> trees) {
        HashMap<Integer,Integer> freq = new HashMap<>();
            for(TreeNode root : trees){
            roots.put(root.val, root);
            freq.put(root.val,freq.getOrDefault(root.val,0)+1);
            if(root.left != null)
                freq.put(root.left.val,freq.getOrDefault(root.left.val,0)+1);
            if(root.right != null)
            freq.put(root.right.val,freq.getOrDefault(root.right.val,0)+1);
        }
        TreeNode root = null;
        for(TreeNode node : trees){
            if(freq.get(node.val) == 1){
                root = node;
                break;
            }
        }
        if(root == null)
            return null;
        roots.remove(root.val);
        if(!dfs(root, Long.MIN_VALUE, Long.MAX_VALUE))
            return null;
        return roots.isEmpty() ? root : null;
    }
    private boolean dfs(TreeNode node, long min,long max){
        if(node == null)
            return true;
        if(node.val <= min || node.val >= max)
            return false;
        if(node.left == null && node.right == null && roots.containsKey(node.val)){
            TreeNode temp =roots.get(node.val);
            node.left = temp.left;
            node.right = temp.right;
            roots.remove(node.val);
        }
        return dfs(node.left,min,node.val) && dfs(node.right,node.val,max);
    }
}