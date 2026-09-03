class Solution {
    public long numberOfWays(String s) {
        long count0 = 0, count1 = 0;
        long count01 = 0, count10 = 0;
        long ways010 = 0, ways101 = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '0') {
                count0++;
                count10 += count1;
                ways010 += count01;
            } else {
                count1++;
                count01 += count0;
                ways101 += count10;
            }
        }
        
        return ways010 + ways101;
    }
}