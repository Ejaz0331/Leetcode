class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        int[] stack = new int[n];
        int top = -1;
        
        for (int i = 0; i < n; i++) {
            while (top >= 0 && arr[stack[top]] >= arr[i]) {
                top--;
            }
            left[i] = top == -1 ? i + 1 : i - stack[top];
            stack[++top] = i;
        }
        
        top = -1;
        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && arr[stack[top]] > arr[i]) {
                top--;
            }
            right[i] = top == -1 ? n - i : stack[top] - i;
            stack[++top] = i;
        }
        
        long total = 0;
        long mod = 1000000007;
        for (int i = 0; i < n; i++) {
            total = (total + (long) arr[i] * left[i] * right[i]) % mod;
        }
        
        return (int) total;
    }
}