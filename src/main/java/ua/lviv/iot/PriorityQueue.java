package ua.lviv.iot;

class PriorityQueue {
    private Element[] heap;
    private int heapSize, capacity;

    public Element[] getHeap() { return heap; }

    public PriorityQueue() {
        capacity = 1;
        heap = new Element[this.capacity];
        heapSize = 0;
    }

    public void clear() {
        heap = new Element[capacity];
        capacity = 1;
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    public void add(int value, int priority) {
        Element[] temp = heap;
        heap = new Element[++capacity];
        System.arraycopy(temp, 0, heap, 0, temp.length);

        Element newEl = new Element(value, priority);

        heap[++heapSize] = newEl;
        int pos = heapSize-1;
        while (pos != 0 && newEl.priority < heap[(pos-1)/2].priority) {
            heap[pos] = heap[(pos-1)/2];
            pos -=1;
            pos /=2;
        }
        heap[pos] = newEl;
    }

    public Element poll() {
        if (isEmpty()){
            return null;
        }
        int parent, child;
        Element item, temp;

        item = heap[0];
        temp = heap[--heapSize];

        parent = 0;
        child = 1;
        while (child <= heapSize) {
            if (child < heapSize && heap[child].priority > heap[child + 1].priority)
                child++;
            if (temp.priority <= heap[child].priority)
                break;

            heap[parent] = heap[child];
            parent = child;
            child *= 2;
            child += 1;
        }
        heap[parent] = temp;
        if (heapSize == 0){
            clear();
        }

        return item;
    }

    public Element peek() {
        return heap[0];
    }

    private boolean isEmpty() {
        return heapSize == 0;
    }
}