class Solution {
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        if(n == 1){
            return stones[0];
        }
        Arrays.sort(stones);
        helper(stones, n - 1);
        return stones[n-1];
    }
    private void helper(int[] arr, int i){
        if(arr[i - 1] == 0){
            return;
        }
        int diff = arr[i] - arr[i-1];
        arr[i] = 0;
        arr[i - 1] = diff;
        Arrays.sort(arr);
        helper(arr, i);
    }
}