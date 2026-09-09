class Solution {
    public int longestSquareStreak(int[] nums) {
        int n = nums.length;
        Set<Long> set = new HashSet<>();
        int maxi=0;
        for(int i=0;i<n;i++)
            {
                set.add((long)nums[i]);
            }
        for(int i=0;i<n;i++)
            {
                int count=1;
                long num =(long)nums[i];
                while(true)
                    {
                        num *= num;
                        if(set.contains(num))
                            count++;
                        else break;
                    }
                maxi = Math.max(maxi,count);
            }
        return maxi==1 ? -1 : maxi;
    }
}