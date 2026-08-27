class Solution {
    public int getMaximumGenerated(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        int max = Integer.MIN_VALUE;

        for(int i = 2; i <= n; i++) {
            if(i % 2 == 0) {
                int idx = i / 2;
                arr[i] = arr[idx];
                
            } else {
                int temp = i - 1;
                int idx = temp / 2;
                arr[i] = arr[idx] + arr[idx + 1];
            }
            max = Math.max(max, arr[i]);
        }

        return max;
    }
}