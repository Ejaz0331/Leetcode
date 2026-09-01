class Solution {
    private double[] fact;
    private double validWays = 0;
    private double totalWays = 0;

    public double getProbability(int[] balls) {
        int k = balls.length;
        int totalBalls = 0;
        for (int b : balls) {
            totalBalls += b;
        }
        int n = totalBalls / 2;

        fact = new double[totalBalls + 1];
        fact[0] = 1.0;
        for (int i = 1; i <= totalBalls; i++) {
            fact[i] = fact[i - 1] * i;
        }

        backtrack(balls, 0, 0, 0, 0, 0, 1.0, n);

        return validWays / totalWays;
    }

    private void backtrack(int[] balls, int idx, int countA, int countB, int distinctA, int distinctB, double ways, int n) {
        if (countA > n || countB > n) {
            return;
        }

        if (idx == balls.length) {
            if (countA == n && countB == n) {
                totalWays += ways;
                if (distinctA == distinctB) {
                    validWays += ways;
                }
            }
            return;
        }

        int count = balls[idx];
        for (int i = 0; i <= count; i++) {
            int toA = i;
            int toB = count - i;

            double nextWays = ways * comb(count, toA);

            backtrack(
                balls,
                idx + 1,
                countA + toA,
                countB + toB,
                distinctA + (toA > 0 ? 1 : 0),
                distinctB + (toB > 0 ? 1 : 0),
                nextWays,
                n
            );
        }
    }

    private double comb(int n, int k) {
        return fact[n] / (fact[k] * fact[n - k]);
    }
}