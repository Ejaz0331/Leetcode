class Solution {
    private java.util.Map<List<Integer>, Integer> memo = new java.util.HashMap<>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }

        int directPrice = directBuyCost(price, needs);
        int minPrice = directPrice;

        for (List<Integer> offer : special) {
            List<Integer> nextNeeds = new java.util.ArrayList<>();
            boolean isValid = true;

            for (int i = 0; i < needs.size(); i++) {
                int remaining = needs.get(i) - offer.get(i);
                if (remaining < 0) {
                    isValid = false;
                    break;
                }
                nextNeeds.add(remaining);
            }

            if (isValid) {
                int offerPrice = offer.get(offer.size() - 1);
                minPrice = Math.min(minPrice, offerPrice + dfs(price, special, nextNeeds));
            }
        }

        memo.put(needs, minPrice);
        return minPrice;
    }

    private int directBuyCost(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            total += needs.get(i) * price.get(i);
        }
        return total;
    }
}