package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {

    private Node start;
    private Node end;
    private int size;

    public SimpleLinkedList() {
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    @Override
    public boolean add(final String value) {
        Node node = new Node(value);
        if (size == 0) {
            this.start = node;
            this.end = node;
            this.size++;
            return true;
        }
        end.connect(node);
        this.end = node;
        this.size++;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
    }

    @Override
    public String set(final int index, final String value) {
        return null;
    }

    @Override
    public String get(final int index) {
        return null;
    }

    @Override
    public boolean contains(final String value) {
        return false;
    }

    @Override
    public int indexOf(final String value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(final String value) {
        return false;
    }

    @Override
    public String remove(final int index) {
        return null;
    }

    @Override
    public void clear() {

    }
}
