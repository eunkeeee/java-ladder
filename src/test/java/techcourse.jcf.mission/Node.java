package techcourse.jcf.mission;

public class Node {


    private String info;
    private Node next;

    public Node(String info) {
        this.info = info;
    }

    public Node(String info, Node next) {
        this.info = info;
        this.next = next;
    }

    public void connect(Node next) {
        this.next = next;
    }
}
