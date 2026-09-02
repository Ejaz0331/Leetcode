class Solution {
    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int n = chars.length;
        int[] forces = new int[n];
        
        int force = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'R') {
                force = n;
            } else if (chars[i] == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] += force;
        }
        
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == 'L') {
                force = n;
            } else if (chars[i] == 'R') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] -= force;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int f : forces) {
            if (f > 0) {
                sb.append('R');
            } else if (f < 0) {
                sb.append('L');
            } else {
                sb.append('.');
            }
        }
        
        return sb.toString();
    }
}