/** This is the node implementation which is used by the list */
import java.util.ArrayList;

public class Node<E> {
    private static final double P = 0.5;
    private static final int MAX_LEVELS = 30;

    private E data; // value stored in this element
    private ArrayList<Node<E>> nextNodes; // ref to next

    public Node(E v) {
        data = v;
        int height = 1;
        while (Math.random() < P && height < MAX_LEVELS) {
            height++;
        }
        nextNodes = new ArrayList<Node<E>>(height);
        for (int i = 0; i < height; i++) {
            nextNodes.add(i,null);
        }
    }

    public int levels() {
        return nextNodes.size();
    }

    public Node<E> next(int level) {
        return nextNodes.get(level);
    }

    public void setNext(int level, Node<E> next)  {
        nextNodes.set(level, next);
    }

    public E value() {
        return data;
    }

    public void setValue(E value) {
        data = value;
    }
}
