class Solution {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Integer[] hCuts = new Integer[m - 1];
        for (int i = 0; i < m - 1; i++) {
            hCuts[i] = horizontalCut[i];
        }
        
        Integer[] vCuts = new Integer[n - 1];
        for (int i = 0; i < n - 1; i++) {
            vCuts[i] = verticalCut[i];
        }

        Arrays.sort(hCuts, Collections.reverseOrder());
        Arrays.sort(vCuts, Collections.reverseOrder());

        int hIndex = 0, vIndex = 0;
        int hPieces = 1, vPieces = 1;
        int totalCost = 0;

        while (hIndex < hCuts.length && vIndex < vCuts.length) {
            if (hCuts[hIndex] >= vCuts[vIndex]) {
                totalCost += hCuts[hIndex] * vPieces;
                hPieces++;
                hIndex++;
            } else {
                totalCost += vCuts[vIndex] * hPieces;
                vPieces++;
                vIndex++;
            }
        }

        while (hIndex < hCuts.length) {
            totalCost += hCuts[hIndex] * vPieces;
            hPieces++;
            hIndex++;
        }

        while (vIndex < vCuts.length) {
            totalCost += vCuts[vIndex] * hPieces;
            vPieces++;
            vIndex++;
        }

        return totalCost;
    }
}