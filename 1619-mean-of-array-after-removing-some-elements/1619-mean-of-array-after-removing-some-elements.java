class Solution {
    public double trimMean(int[] arr) {
        int start = arr.length * 5 / 100;
        int end = arr.length - start;
        double total = end - start;
        double sum = 0;
        Arrays.sort(arr);
        for(int i = start; i < end; i++){
            sum += arr[i];
        }
        return sum / total;
    }
}