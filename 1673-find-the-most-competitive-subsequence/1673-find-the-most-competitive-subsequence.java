class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> st=new Stack<>();
        int n=nums.length,remove=n-k;
        for(int i=0;i<n;i++){
            while(!st.isEmpty() && st.peek()>nums[i] && remove!=0){
                st.pop();
                remove--;
            }
            st.push(nums[i]);
        }
        while(!st.isEmpty() && remove !=0){
            st.pop();
            remove--;
        }

        int[] ans=new int[st.size()];
        for(int i=ans.length -1;i>=0;i--){
            ans[i]=st.pop();
        }
        return ans;
    }
}