class Solution {
    public int videoStitching(int[][] clips, int time) {
        int[] maxReach = new int[time];
        for (int[] clip : clips) {
            int start = clip[0];
            int end = clip[1];
            if (start < time) {
                maxReach[start] = Math.max(maxReach[start], end);
            }
        }
        
        int count = 0;
        int currEnd = 0;
        int nextEnd = 0;
        int i = 0;
        
        while (i < time) {
            while (i <= currEnd && i < time) {
                nextEnd = Math.max(nextEnd, maxReach[i]);
                i++;
            }
            
            if (currEnd == nextEnd) {
                return -1;
            }
            
            currEnd = nextEnd;
            count++;
            
            if (currEnd >= time) {
                return count;
            }
        }
        
        return currEnd >= time ? count : -1;
    }
}