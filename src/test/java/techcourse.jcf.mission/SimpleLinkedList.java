package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {
    public Node start = null;

    @Override
    public boolean add(final String value) {
        Node newNode = new Node(value);
        if (start == null) {
            start = newNode;
            return true;
        }
        Node head = start;
        while (head.next != null) {
            head = head.next;
        }
        head.next = newNode;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        Node newNode = new Node(value);
        Node head = start;
        for (int i = 0; i < index - 1; i++) {
            head = head.next;
        }
        Node tmp = head.next;
        head.next = newNode;
        newNode.next = tmp;
    }

    @Override
    public String set(final int index, final String value) {
        Node head = start;
        for (int i = 0; i < index - 1; i++) {
            head = head.next;
        }
        String old = head.value;
        head.value = value;
        return old;
    }

    @Override
    public String get(final int index) {
        Node head = start;
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        return head.value;
    }

    @Override
    public boolean contains(final String value) {
        if (start == null) {
            return false;
        }
        Node head = start;
        while (true) {
            if (head.value.equals(value)) {
                return true;
            }
            if (head.next == null) {
                return false;
            }
            head = head.next;
        }
    }

    @Override
    public int indexOf(final String value) {
        int index = 0;
        if (start == null) {
            return -1;
        }
        Node head = start;
        while (true) {
            if (head.value.equals(value)) {
                return index;
            }
            if (head.next == null) {
                return -1;
            }
            head = head.next;
            index++;
        }
    }

    @Override
    public int size() {
        int index = 1;
        if (start == null) {
            return 0;
        }
        Node head = start;
        while (true) {
            if (head.next == null) {
                return index;
            }
            head = head.next;
            index++;
        }
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }

    @Override
    public boolean remove(final String value) {
        if (start == null || !contains(value)) {
            return false;
        }
        Node head = start;
        Node prev = start;
        while (true) {
            if (head.next == null) {
                return false;
            }
            if (head.value.equals(value)) {
                prev.next = head.next;
                return true;
            }
            prev = head;
            head = head.next;
        }
    }

    @Override
    public String remove(final int index) {
        if (size() <= index) {
            throw new IndexOutOfBoundsException();
        }
        Node head = start;
        Node prev = start;
        for (int i = 0; i < index; i++) {
            prev = head;
            head = head.next;
        }
        prev.next = head.next;
        return head.value;
    }

    @Override
    public void clear() {
        start.next = null;
        start = null;
    }
}
