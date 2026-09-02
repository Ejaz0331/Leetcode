class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, java.util.List<java.util.List<String>> people) {
        int n = req_skills.length;
        String[] skillList = req_skills;
        
        int numPeople = people.size();
        int[] personSkills = new int[numPeople];
        for (int i = 0; i < numPeople; i++) {
            java.util.List<String> pSkills = people.get(i);
            int skillMask = 0;
            for (int s = 0; s < pSkills.size(); s++) {
                String skill = pSkills.get(s);
                for (int j = 0; j < n; j++) {
                    if (skillList[j].equals(skill)) {
                        skillMask |= (1 << j);
                        break;
                    }
                }
            }
            personSkills[i] = skillMask;
        }
        
        int maxMask = 1 << n;
        int[] dp = new int[maxMask];
        for (int i = 0; i < maxMask; i++) {
            dp[i] = numPeople + 1;
        }
        dp[0] = 0;
        
        int[] parentMask = new int[maxMask];
        int[] parentPerson = new int[maxMask];
        
        for (int i = 0; i < maxMask; i++) {
            if (dp[i] > numPeople) {
                continue;
            }
            for (int p = 0; p < numPeople; p++) {
                int nextMask = i | personSkills[p];
                if (dp[i] + 1 < dp[nextMask]) {
                    dp[nextMask] = dp[i] + 1;
                    parentMask[nextMask] = i;
                    parentPerson[nextMask] = p;
                }
            }
        }
        
        int[] tempTeam = new int[numPeople];
        int teamSize = 0;
        int curr = maxMask - 1;
        while (curr > 0) {
            tempTeam[teamSize++] = parentPerson[curr];
            curr = parentMask[curr];
        }
        
        int[] result = new int[teamSize];
        for (int i = 0; i < teamSize; i++) {
            result[i] = tempTeam[teamSize - 1 - i];
        }
        
        return result;
    }
}