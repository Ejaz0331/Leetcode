class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int maxLength = 0;
        int i = 1;
        
        while (i < n) {
            while (i < n && arr[i] == arr[i - 1]) {
                i++;
            }
            
            int up = 0;
            int down = 0;
            
            while (i < n && arr[i] > arr[i - 1]) {
                up++;
                i++;
            }
            
            while (i < n && arr[i] < arr[i - 1]) {
                down++;
                i++;
            }
            
            if (up > 0 && down > 0) {
                maxLength = Math.max(maxLength, up + down + 1);
            }
            
            while (i < n && arr[i] == arr[i - 1]) {
                i++;
            }
        }
        
        return maxLength;
    }
}