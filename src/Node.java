import java.util.NoSuchElementException;

public class Node<E> {
    E location;
    Node<E> previous;
    Node<E> next;
    Node<E> forward;

    public Node(E location, Node next, Node previous) {
        this.location = location;
        this.next = next;
        this.previous = previous;
        this.forward = null;
    }
    public Node(E location){
        this.location = location;
        next = null;
        previous = null;
        forward = null;
    }
}

class visitedLocations<E>{
    Node<E> head;
    Node<E> tail;
    Node<E> temp;
    public int size;
    E forwardLocation;
    E previousLocation;

    public visitedLocations(){
        size = 0;
        this.head = head;
        this.tail = tail;
    }

    public int getSize(){
	return size;
    }
    public void addFirst(E location){
        Node<E> tmp = new Node(location, head, null);
        if(head != null){
            head.previous = tmp;
        }
        head = tmp;
        if(tail==null){
        tail = tmp;
        }
        size++;
    }

    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        Node<E> tmp = tail;

        if (tail.previous != null) {
            if(tail.previous == head){
                tail = head;
                tail.next = tail.previous = null;
                head.next = head.previous = null;
            }else{
                tail = tail.previous;
                tail.next = null;}
        } else if(head==tail) {
            while(head!=null){
                tmp = head.next;
                head.previous = head.next = null;
                head = tmp;
            }
            tmp = null;
            tail.previous = tail.next = null;
        }

        size--;
        return tmp.location;
    }
    
    public void addLast(E location){
        Node<E> tmp = new Node(location, null, tail);
        if(tail != null){
            tail.next = tmp;
        }
        tail = tmp;
        if(head == null){
            head = tmp;
        }
        size++;
    }
    
    public E getPrevious() {
        if (tail != null && tail.previous != null) {
            previousLocation =  tail.previous.location;
            return previousLocation;
        }
        return null;
    }

    public E getNext() {
        if (tail != null && tail.forward != null) {
            return tail.forward.location;
        }
        return null;
    }
    
    public void setNext(E location) {
        if (tail != null) {
            temp = tail; // i dk should i add temp var bcs i worry if i add forward the whole list change
            temp.forward = new Node<>(location, null, tail);
            forwardLocation = temp.forward.location;
        }
    }
}