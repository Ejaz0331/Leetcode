class Solution {
    public long findMaximumNumber(long k, int x) {
        long low = 1, high = 1L << 50;
        long ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countPrices(mid, x) <= k) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private long countPrices(long num, int x) {
        long totalPrices = 0;
        for (int i = x; i <= 60; i += x) {
            totalPrices += countBitAtPosition(num, i);
        }
        return totalPrices;
    }

    private long countBitAtPosition(long num, int pos) {
        long cycle = 1L << pos;
        long fullCycles = (num + 1) / cycle;
        long count = fullCycles * (cycle / 2);

        long remainder = (num + 1) % cycle;
        if (remainder > cycle / 2) {
            count += (remainder - cycle / 2);
        }

        return count;
    }
}