class Solution {
    public double[] sampleStats(int[] count) {
        double min = -1.0;
        double max = -1.0;
        double sum = 0.0;
        long totalCount = 0;
        int maxFreq = 0;
        double mode = 0.0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                if (min == -1.0) {
                    min = i;
                }
                max = i;
                sum += (double) i * count[i];
                totalCount += count[i];
                if (count[i] > maxFreq) {
                    maxFreq = count[i];
                    mode = i;
                }
            }
        }

        double mean = sum / totalCount;

        long m1 = (totalCount + 1) / 2;
        long m2 = (totalCount + 2) / 2;
        long runningCount = 0;
        double median1 = -1.0;
        double median2 = -1.0;

        for (int i = 0; i < count.length; i++) {
            runningCount += count[i];
            if (median1 == -1.0 && runningCount >= m1) {
                median1 = i;
            }
            if (median2 == -1.0 && runningCount >= m2) {
                median2 = i;
            }
        }

        double median = (median1 + median2) / 2.0;

        return new double[]{min, max, mean, median, mode};
    }
}