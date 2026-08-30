class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n= friends.length;

        boolean []visited  =new boolean[n];
        Arrays.fill(visited,false);
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offer(id);
        visited[id]=true;
        int lvl = 0;
        while(!dq.isEmpty()){
            int size = dq.size();
            if(lvl==level)break;
            for(int i=0;i<size;i++){
                int element  = dq.poll();
                for(int friend:friends[element]){
                    if(!visited[friend]){
                        visited[friend]=true;
                        dq.offer(friend);
                    }
                }
            }
            lvl++;
        }
        Map<String,Integer> freq = new HashMap<>();
        while(!dq.isEmpty()){
            int friend =dq.poll();
            for(String movie:watchedVideos.get(friend)){
                freq.put(movie,freq.getOrDefault(movie,0)+1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if( o1.getValue().compareTo(o2.getValue())==0){
                    return o1.getKey().compareTo(o2.getKey());
                }
                else return  o1.getValue().compareTo(o2.getValue());

            }
        });
        ArrayList<String> ans = new ArrayList<>();
        for(Map.Entry<String,Integer> entry:list){
            ans.add(entry.getKey());
        }
        return ans;
    }
}