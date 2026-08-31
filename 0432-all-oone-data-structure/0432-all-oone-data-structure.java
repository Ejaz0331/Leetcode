import java.util.*;

class AllOne {

    private static class Bucket {
        int count;
        Set<String> keys;
        Bucket prev;
        Bucket next;

        Bucket(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private final Bucket head;
    private final Bucket tail;
    private final Map<String, Integer> countMap;
    private final Map<Integer, Bucket> bucketMap;

    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        countMap = new HashMap<>();
        bucketMap = new HashMap<>();
    }

    public void inc(String key) {
        if (!countMap.containsKey(key)) {
            countMap.put(key, 1);
            if (head.next.count != 1) {
                addBucketAfter(new Bucket(1), head);
            }
            head.next.keys.add(key);
            bucketMap.put(1, head.next);
        } else {
            int count = countMap.get(key);
            countMap.put(key, count + 1);

            Bucket curBucket = bucketMap.get(count);
            Bucket nextBucket = curBucket.next;

            if (nextBucket.count != count + 1) {
                nextBucket = new Bucket(count + 1);
                addBucketAfter(nextBucket, curBucket);
                bucketMap.put(count + 1, nextBucket);
            }

            nextBucket.keys.add(key);
            curBucket.keys.remove(key);

            if (curBucket.keys.isEmpty()) {
                removeBucket(curBucket);
                bucketMap.remove(count);
            }
        }
    }

    public void dec(String key) {
        if (!countMap.containsKey(key)) {
            return;
        }

        int count = countMap.get(key);
        Bucket curBucket = bucketMap.get(count);

        if (count == 1) {
            countMap.remove(key);
        } else {
            countMap.put(key, count - 1);
            Bucket prevBucket = curBucket.prev;

            if (prevBucket.count != count - 1) {
                prevBucket = new Bucket(count - 1);
                addBucketAfter(prevBucket, curBucket.prev);
                bucketMap.put(count - 1, prevBucket);
            }

            prevBucket.keys.add(key);
        }

        curBucket.keys.remove(key);
        if (curBucket.keys.isEmpty()) {
            removeBucket(curBucket);
            bucketMap.remove(count);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.keys.iterator().next();
    }

    private void addBucketAfter(Bucket newBucket, Bucket prevBucket) {
        newBucket.next = prevBucket.next;
        newBucket.prev = prevBucket;
        prevBucket.next.prev = newBucket;
        prevBucket.next = newBucket;
    }

    private void removeBucket(Bucket bucket) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
    }
}
/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */