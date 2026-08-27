class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int c_sum=0,sum=releaseTimes[0];
        char ans=keysPressed.charAt(0);
        for(int i=0;i<releaseTimes.length-1;i++){
            c_sum=releaseTimes[i+1]-releaseTimes[i];
            if(c_sum>sum){
                ans=keysPressed.charAt(i+1);
                sum=c_sum;
            }
            else if(c_sum==sum){
                if(keysPressed.charAt(i+1)>ans){
                    ans=keysPressed.charAt(i+1);
                }
            }
        }
        return ans;
    }
}