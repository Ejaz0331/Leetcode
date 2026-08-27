class Solution {
    public int[] frequencySort(int[] A) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : A)
            count.put(a, count.getOrDefault(a, 0) + 1);
        Integer[] arr = new Integer[A.length];
        for (int i = 0; i < A.length; i++)
            arr[i] = A[i];
        Arrays.sort(arr, (a, b) -> {return count.get(a).equals(count.get(b))? b - a : count.get(a) - count.get(b);});
        for (int i = 0; i < A.length; i++)
            A[i] = arr[i];

        return A;
    }

}