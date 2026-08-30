/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node==null)return null;
        HashMap<Integer, List<Integer>>map = new HashMap<>();

        HashSet<Node>visi = new HashSet<>();
        Queue<Node>q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            Node nn = q.poll();

            if(visi.contains(nn))continue;

            visi.add(nn);
            if(nn==null)continue;
            map.put(nn.val, new ArrayList<>());

            for(Node ngbr : nn.neighbors){
                q.add(ngbr);
                map.get(nn.val).add(ngbr.val);
            }
        }

        HashMap<Integer, Node>map2 = new HashMap<>();
        for(int i : map.keySet()){
            map2.put(i, new Node(i));
        }

        for(int i : map.keySet()){
            Node n = map2.get(i);
            for(int ngbr : map.get(i)){
                n.neighbors.add(map2.get(ngbr));
            }
        }
        
        
        return map2.get(1);
    }
}