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
    List<List<Integer>>res;
    public List<List<Integer>> solve(TreeNode root)
    {
        Queue<TreeNode>q=new ArrayDeque<>();
        res=new ArrayList<>();
        if(root==null)
            return res;
        q.add(root);
        while(!q.isEmpty())
        {
            int size=q.size();
            List<Integer>li=new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                TreeNode curr=q.poll();
                if(curr.left!=null)
                {
                    q.add(curr.left);
                }
                if(curr.right!=null)
                {
                    q.add(curr.right);
                }
                li.add(curr.val);
            }
            res.add(li);
        }
        Collections.reverse(res);
        return res;
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        return solve(root);
    }
}