class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        Collections.sort(offers,
            (a, b) -> Integer.compare(a.get(1), b.get(1))
        );
        
        TreeMap<Integer, Integer> maxDP = new TreeMap<>();
        maxDP.put(-1, 0);
        
        for(List<Integer> each : offers){
            int currentStart = each.get(0);
            int currentEnd = each.get(1);
            int currentProfit = each.get(2);
            
            Integer maxProfitBeforeThisStart = maxDP.floorEntry(currentStart - 1).getValue();
            if(maxProfitBeforeThisStart != null){
                int profitWhenWeDo = maxProfitBeforeThisStart + currentProfit;
                if(profitWhenWeDo > maxDP.lastEntry().getValue()){
                    maxDP.put(currentEnd, profitWhenWeDo);
                }
            }
        }
        
        return maxDP.lastEntry().getValue();
    }
}