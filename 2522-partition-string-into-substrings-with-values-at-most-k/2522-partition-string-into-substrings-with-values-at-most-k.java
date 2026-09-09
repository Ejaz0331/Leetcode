class Solution {
    public int minimumPartition(String s, int k) {
        return dfs(0, s.toCharArray(), k);
    }

    public int dfs(int idx, char[] arr, int k){
        if(idx == arr.length){
            return 0;
        }
        if(arr[idx] - '0' > k){
            return -1;
        }
        long curr = 0;
        while(idx < arr.length && curr * 10 + (arr[idx] - '0') <= k){
            curr = curr * 10 + (arr[idx++] - '0');
        }
        int res = dfs(idx, arr, k);
        if(res == -1){
            return res;
        }
        return 1 + res;
    }
}