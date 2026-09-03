class Solution {
    public int minimumBuckets(String hamsters) {
        char[] arr = hamsters.toCharArray();
        int n = arr.length;
        int buckets = 0;
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'H') {
                if (i + 1 < n && arr[i + 1] == '.') {
                    buckets++;
                    i += 2;
                } else if (i - 1 >= 0 && arr[i - 1] == '.') {
                    buckets++;
                } else {
                    return -1;
                }
            }
        }
        
        return buckets;
    }
}