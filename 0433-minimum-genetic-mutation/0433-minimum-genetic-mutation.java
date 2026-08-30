class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) {
            return 0;
        }

        boolean[][] neighbors = new boolean[bank.length][bank.length];

        for (int i = 0; i < bank.length; i++) {
            for (int j = 0; j < bank.length; j++) {
                if (i == j) {
                    continue; 
                }

                if (neighbors[i][j]) {
                    continue;
                }

                if (isNeighbor(bank[i], bank[j])) {
                    neighbors[i][j] = true;
                    neighbors[j][i] = true;
                }
            }
        }

     
        Queue<int[]> q = new ArrayDeque<>(); 
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < bank.length; i++) {
            if (isNeighbor(startGene, bank[i])) {
                q.add(new int[]{i, 1});
                visited.add(i);
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curIndex = cur[0];
            int mutates = cur[1];
            if (bank[curIndex].equals(endGene)) {
                return mutates;
            }

            boolean[] neighborArr = neighbors[curIndex];
            for (int i = 0; i < neighborArr.length; i++) {
                if (neighborArr[i] && !visited.contains(i)){
                    q.add(new int[]{i, mutates + 1});
                    visited.add(i);
                }
            }
        }


        return -1;
    }

    private boolean isNeighbor(String a, String b) {
        if (a.equals(b)) {
            return false;
        }

        boolean foundDifference = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true; 
    }
}