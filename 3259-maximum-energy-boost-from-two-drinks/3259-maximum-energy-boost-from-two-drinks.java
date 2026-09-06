class Solution {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[] dpA = new long[n];
        long[] dpB = new long[n];

        dpA[0] = energyDrinkA[0];
        dpB[0] = energyDrinkB[0];

        if (n > 1) {
            dpA[1] = dpA[0] + energyDrinkA[1];
            dpB[1] = dpB[0] + energyDrinkB[1];
        }

        for (int i = 2; i < n; i++) {
            dpA[i] = Math.max(dpA[i - 1], dpB[i - 2]) + energyDrinkA[i];
            dpB[i] = Math.max(dpB[i - 1], dpA[i - 2]) + energyDrinkB[i];
        }

        return Math.max(dpA[n - 1], dpB[n - 1]);
    }
}