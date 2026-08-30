/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();

        if(root == null){
            return ans;
        }
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            Node temp = q.remove();

            if(temp == null){
               ans.add(list);
               list = new ArrayList<>();

               if(!q.isEmpty()){
                q.add(null);
               }
            }
            else {
                list.add(temp.val);
                for(Node child : temp.children){
                    if(child != null){
                        q.add(child);
                    }
                }
            }
        }
        return ans;
    }
}

