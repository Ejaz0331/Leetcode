class Solution {
    public int getKth(int lo, int hi, int k) {
        int[][] list = new int[hi - lo + 1][2];
        for (int i = lo; i <= hi; i++) {
            list[i - lo][0] = i;
            list[i - lo][1] = getPower(i);
        }
        
        java.util.Arrays.sort(list, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        return list[k - 1][0];
    }
    
    private int getPower(int x) {
        int steps = 0;
        while (x != 1) {
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = 3 * x + 1;
            }
            steps++;
        }
        return steps;
    }
}