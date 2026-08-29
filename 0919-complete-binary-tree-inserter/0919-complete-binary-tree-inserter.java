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
class CBTInserter {
    TreeNode parent;
    public CBTInserter(TreeNode root) {
       parent = root;
    }
    
    public int insert(int val) {
        TreeNode root = parent;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        TreeNode newNode = new TreeNode(val);
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i =0;i<size;i++){
                TreeNode temp = queue.poll();
                if(temp == null){
                    temp = newNode;
                    return temp.val;
                }else if(temp.left == null){
                    temp.left = newNode;
                    return temp.val;
                }else if(temp.right == null){
                    temp.right = newNode;
                    return temp.val;
                }else
                {
                    queue.offer(temp.left);
                    queue.offer(temp.right);
                }
            }
        }
        return -1;
    }
    
    public TreeNode get_root() {
        return parent;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */