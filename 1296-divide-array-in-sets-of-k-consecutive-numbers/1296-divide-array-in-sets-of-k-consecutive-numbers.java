class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if(n % k != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while(!map.isEmpty()) {
            int start = map.firstKey();

            int count = map.get(start);

            for(int i=0; i<k; i++) {
                int first = start + i;

                if(!map.containsKey(first) || map.get(first) < count) return false;

                if(map.get(first) == count) map.remove(first);
                else map.put(first, map.get(first) - count);
            }
        }

        return true;
    }
} 