class Solution {
    public String largestMultipleOfThree(int[] digits) {
        int[] count = new int[10];
        int sum = 0;
        for (int d : digits) {
            count[d]++;
            sum += d;
        }
        
        java.util.List<Integer> rem1 = new java.util.ArrayList<>();
        java.util.List<Integer> rem2 = new java.util.ArrayList<>();
        
        for (int i = 0; i <= 9; i++) {
            if (i % 3 == 1) {
                for (int c = 0; c < count[i]; c++) rem1.add(i);
            } else if (i % 3 == 2) {
                for (int c = 0; c < count[i]; c++) rem2.add(i);
            }
        }
        
        java.util.Collections.sort(rem1);
        java.util.Collections.sort(rem2);
        
        if (sum % 3 == 1) {
            if (!rem1.isEmpty()) {
                count[rem1.get(0)]--;
            } else if (rem2.size() >= 2) {
                count[rem2.get(0)]--;
                count[rem2.get(1)]--;
            } else {
                return "";
            }
        } else if (sum % 3 == 2) {
            if (!rem2.isEmpty()) {
                count[rem2.get(0)]--;
            } else if (rem1.size() >= 2) {
                count[rem1.get(0)]--;
                count[rem1.get(1)]--;
            } else {
                return "";
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            for (int c = 0; c < count[i]; c++) {
                sb.append(i);
            }
        }
        
        if (sb.length() > 0 && sb.charAt(0) == '0') {
            return "0";
        }
        
        return sb.toString();
    }
}