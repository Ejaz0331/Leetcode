class LFUCache {

    private static class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addNode(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeTail() {
            if (size > 0) {
                Node node = tail.prev;
                removeNode(node);
                return node;
            }
            return null;
        }
    }

    private final int capacity;
    private int curSize;
    private int minFreq;
    private final Map<Integer, Node> cache;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        this.minFreq = 0;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateFreq(node);
        } else {
            curSize++;
            if (curSize > capacity) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                Node deletedNode = minFreqList.removeTail();
                cache.remove(deletedNode.key);
                curSize--;
            }

            minFreq = 1;
            Node newNode = new Node(key, value);
            DoublyLinkedList curList = freqMap.computeIfAbsent(1, k -> new DoublyLinkedList());
            curList.addNode(newNode);
            cache.put(key, newNode);
        }
    }

    private void updateFreq(Node node) {
        int curFreq = node.freq;
        DoublyLinkedList curList = freqMap.get(curFreq);
        curList.removeNode(node);

        if (curFreq == minFreq && curList.size == 0) {
            minFreq++;
        }

        node.freq++;
        DoublyLinkedList newList = freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList());
        newList.addNode(node);
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */