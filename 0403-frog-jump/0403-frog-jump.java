class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer,HashSet<Integer>> map = new HashMap<>();
        for(int s:stones)map.put(s,new HashSet<>());
        map.get(0).add(0);
        for(int s:stones){
            for(int x:map.get(s)){
                for(int i=x-1;i<=x+1;i++){
                    if(i>0 && map.containsKey(s+i))map.get(s+i).add(i);
                }
            }
        }
        return !map.get(stones[stones.length-1]).isEmpty();
    }
}


