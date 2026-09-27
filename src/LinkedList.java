public class LinkedList {

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public void add(int x) {
        if (head == null) {
            head = new Node(x);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(x);
        }
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
        } else {
            Node prev = getNode(index - 1);
            Node newNode = new Node(x);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        int removedVal;
        if (index == 0) {
            removedVal = head.val;
            head = head.next;
        } else {
            Node prev = getNode(index - 1);
            removedVal = prev.next.val;
            prev.next = prev.next.next;
        }
        size--;
        return removedVal;
    }

    public int get(int index) {
        return getNode(index).val;
    }

    public boolean contains(int x) {
        Node current = head;
        while (current != null) {
            if (current.val == x) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}