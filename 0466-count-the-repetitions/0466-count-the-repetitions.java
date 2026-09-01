class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        int l1 = c1.length, l2 = c2.length;
        int count1 = 0, count2 = 0, index = 0;
        
        int[] repeatCount = new int[l2 + 1];
        int[] nextIndex = new int[l2 + 1];
        
        while (count1 < n1) {
            for (int i = 0; i < l1; i++) {
                if (c1[i] == c2[index]) {
                    index++;
                    if (index == l2) {
                        index = 0;
                        count2++;
                    }
                }
            }
            count1++;
            
            repeatCount[count1] = count2;
            nextIndex[count1] = index;
            
            for (int start = 0; start < count1; start++) {
                if (nextIndex[start] == index) {
                    int prevCount1 = start;
                    int prevCount2 = repeatCount[start];
                    
                    int patternCount1 = count1 - prevCount1;
                    int patternCount2 = count2 - prevCount2;
                    
                    int remainingLoops = n1 - prevCount1;
                    int repeatCycles = remainingLoops / patternCount1;
                    
                    int totalCount2 = prevCount2 + repeatCycles * patternCount2;
                    
                    int leftoverLoops = remainingLoops % patternCount1;
                    totalCount2 += repeatCount[prevCount1 + leftoverLoops] - prevCount2;
                    
                    return totalCount2 / n2;
                }
            }
        }
        
        return repeatCount[n1] / n2;
    }
}