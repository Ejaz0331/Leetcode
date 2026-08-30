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
    public int amountOfTime(TreeNode root, int start) {
        HashMap<TreeNode, TreeNode>map = new HashMap<>();
        map.put(root,null);
        ArrayDeque<TreeNode>q = new ArrayDeque<>();
        TreeNode x=null;
        q.add(root);
        while(!q.isEmpty()){
            x = q.remove();
            if(x.val == start)break;
            if(x.left != null){
                q.add(x.left);
                map.put(x.left, x);
            }
            if(x.right != null){
                q.add(x.right);
                map.put(x.right,x);
            }
        }
        q = new ArrayDeque<>();
        q.add(x);
        HashSet<TreeNode>v = new HashSet<>();
        v.add(x);

        int ans = -1, len;
        while(!q.isEmpty()){
            ans++;
            len = q.size();

            while(len-- > 0){
                x = q.remove();
                if(map.containsKey(x) && map.get(x)!=null && !v.contains(map.get(x))){
                    q.add(map.get(x));
                    v.add(map.get(x));
                }
                if(x.left != null && !v.contains(x.left)){
                    q.add(x.left);
                    v.add(x.left);
                }
                if(x.right!=null && !v.contains(x.right)){
                    q.add(x.right);
                    v.add(x.right);
                }
            }
        }
        return ans;
    }
}