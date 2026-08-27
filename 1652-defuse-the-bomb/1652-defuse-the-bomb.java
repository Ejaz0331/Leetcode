class Solution {
    public int[] decrypt(int[] code, int k) {

        int n = code.length;

        if (k == 0)
            return new int[n];

        int[] ans = new int[n];

        int sum = 0;

        if (k > 0) {

            for (int i = 1; i <= k; i++)
                sum += code[i % n];

            ans[0] = sum;

            for (int i = 1; i < n; i++) {
                sum -= code[i];
                sum += code[(i + k) % n];
                ans[i] = sum;
            }

        } else {

            k = -k;

            for (int i = n - k; i < n; i++)
                sum += code[i];

            ans[0] = sum;

            for (int i = 1; i < n; i++) {
                sum -= code[(i - k - 1 + n) % n];
                sum += code[i - 1];
                ans[i] = sum;
            }
        }

        return ans;
    }
}