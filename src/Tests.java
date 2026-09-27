public class Tests {
    public static void main(String[] args) {
        System.out.println("Running tests...");

        testDynamicArray();
        testLinkedList();
        testMinHeap();

        System.out.println("All tests passed successfully!");
    }

    private static void testDynamicArray() {
        DynamicArray arr = new DynamicArray(2);
        arr.add(10);
        arr.add(20);
        arr.add(30);

        assert arr.size() == 3 : "Size should be 3";
        assert arr.get(0) == 10 : "Element at 0 should be 10";
        assert arr.get(2) == 30 : "Element at 2 should be 30";
        assert arr.contains(20) : "Array should contain 20";

        arr.add(1, 15);
        assert arr.get(1) == 15 : "Element at 1 should be 15";

        int removed = arr.remove(0);
        assert removed == 10 : "Removed element should be 10";
        assert arr.size() == 4 : "Size should be 4 after removal";

        System.out.println("DynamicArray tests passed.");
    }

    private static void testLinkedList() {
        LinkedList list = new LinkedList();
        list.add(100);
        list.add(200);
        list.add(300);

        assert list.size() == 3 : "List size should be 3";
        assert list.get(1) == 200 : "Element at 1 should be 200";
        assert list.contains(300) : "List should contain 300";

        list.add(0, 50);
        assert list.get(0) == 50 : "Element at 0 should be 50";

        int removed = list.remove(0);
        assert removed == 50 : "Removed element should be 50";
        assert list.size() == 3 : "List size should be 3 after removal";

        System.out.println("LinkedList tests passed.");
    }

    private static void testMinHeap() {
        MinHeap heap = new MinHeap();
        heap.insert(50);
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);

        assert heap.size() == 4 : "Heap size should be 4";
        assert heap.peekMin() == 10 : "Minimum element should be 10";

        assert heap.extractMin() == 10 : "Extracted min should be 10";
        assert heap.extractMin() == 20 : "Extracted min should be 20";
        assert heap.peekMin() == 30 : "New minimum should be 30";

        System.out.println("MinHeap tests passed.");
    }
}