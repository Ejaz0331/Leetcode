import java.util.*;

class Twitter {
    private static int timestamp = 0;

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }

    private final Map<Integer, Set<Integer>> followees;
    private final Map<Integer, Tweet> userTweets;

    public Twitter() {
        followees = new HashMap<>();
        userTweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        followees.putIfAbsent(userId, new HashSet<>());
        followees.get(userId).add(userId);

        Tweet newTweet = new Tweet(tweetId, timestamp++);
        newTweet.next = userTweets.get(userId);
        userTweets.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        followees.putIfAbsent(userId, new HashSet<>());
        followees.get(userId).add(userId);

        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

        for (int followeeId : followees.get(userId)) {
            Tweet head = userTweets.get(followeeId);
            if (head != null) {
                maxHeap.offer(head);
            }
        }

        List<Integer> feed = new ArrayList<>();
        int count = 0;

        while (!maxHeap.isEmpty() && count < 10) {
            Tweet curr = maxHeap.poll();
            feed.add(curr.id);
            count++;

            if (curr.next != null) {
                maxHeap.offer(curr.next);
            }
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {
        followees.putIfAbsent(followerId, new HashSet<>());
        followees.get(followerId).add(followerId);
        followees.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (followees.containsKey(followerId)) {
            followees.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */