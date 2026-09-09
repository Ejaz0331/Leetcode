class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n=nums.length;
        int dec[]=new int[n],inc[]=new int[n];
        List<Integer> ans=new ArrayList<Integer>();
        
        int start=nums[0],end=nums[0];
        int len=0;
        for(int i=0; i<n; i++){
            if(nums[i]<=start) len++;
            else len=1;
            start=nums[i];
            dec[i]=len;
        }
        len=0;
        for(int i=0; i<n; i++){
            if(nums[i]>=end) len++;
            else len=1;
            end=nums[i];
            inc[i]=len;
        }
        
        for(int i=k; i<n-k; i++){
            if(dec[i-1]>=k && inc[i+k]>=k) ans.add(i);
        }        
        return ans;
    }
}