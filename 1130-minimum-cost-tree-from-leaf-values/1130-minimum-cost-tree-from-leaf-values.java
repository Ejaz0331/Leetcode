class Solution {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        int n = arr.length;
        int[] stack = new int[n + 1];
        int top = -1;
        stack[++top] = Integer.MAX_VALUE;
        
        for (int x : arr) {
            while (stack[top] <= x) {
                int mid = stack[top--];
                res += mid * Math.min(stack[top], x);
            }
            stack[++top] = x;
        }
        
        while (top > 1) {
            res += stack[top] * stack[top - 1];
            top--;
        }
        
        return res;
    }
}