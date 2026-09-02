class Solution {
	private int gp1Size;
	private int gp2Size;
    private Map<Integer, Integer> cache;
    private int[] right;
	public int connectTwoGroups(List<List<Integer>> cost) {
		gp1Size = cost.size();
		gp2Size = cost.get(0).size();
        cache = new HashMap<>();
		int mask = 0;
		return dfs(0, cost, mask);
	}
	private int dfs(int idx, List<List<Integer>> cost, int mask){
		if(idx == gp1Size + gp2Size) { 
			return 0;
		}
        int key = mask;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        int min = Integer.MAX_VALUE;
		if(idx < gp1Size) {
			//in group 1
            mask = mask | (1 << idx);
			for(int i = 0; i < gp2Size; i++){
                int newMask = mask | (1 << (i + gp1Size));
			    min = Math.min(min, dfs(idx+1, cost, newMask) + cost.get(idx).get(i));
			}
		} else {
			//in group 2
			if((mask & (1 << idx)) == 0){
				int minVal = Integer.MAX_VALUE;
				for(int i = 0; i < gp1Size; i++){
				    minVal = Math.min(minVal, cost.get(i).get(idx-gp1Size));
				}
                min = dfs(idx+1, cost, mask) + minVal;
			} else {
				min = dfs(idx+1, cost, mask);
			}
		}
        cache.put(key, min);
        return min;
	}
}