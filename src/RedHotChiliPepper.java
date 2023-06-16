import java.util.List;

public class RedHotChiliPepper {
    protected Tree<Location> locPassed;
    Location startingPoint;

    public RedHotChiliPepper(){
        startingPoint = Main.angeloRock;
        algorithm();
        System.out.println("Necessary Power Cables to be Upgraded: ");
        display();
        System.out.println("========================================================================");
    }

    private void algorithm(){
        locPassed = new Tree<>(startingPoint);
        int numOfLoc = Main.locations.size();
        while(locPassed.getNumOfPath() != numOfLoc-1) {
            int minD = 100000;
            Location source = null;
            Location des = null;
            Node<Location> temp = locPassed.head;
            while (temp != null) {
                Location tempSource = temp.location;
                List<Location> adjacents = tempSource.getAdjacent();
                for (int j = 0; j < adjacents.size(); j++) {
                    Location tempDes = adjacents.get(j);
                    if (!locPassed.hasNode(tempDes)) {
                        minD = Math.min(minD, getDistanceTo(tempSource, tempDes));
                        if (minD == getDistanceTo(tempSource, tempDes)) {
                            source = tempSource;
                            des = tempDes;
                        }
                    }
                }
                temp = temp.next;
            }
            locPassed.addPath(source, des);
        }
    }

    private int getDistanceTo(Location locFrom, Location locTo){
        List<Integer> distances = locFrom.getDistances();
        List<Location> adjacents = locFrom.getAdjacent();
        int index = adjacents.indexOf(locTo);
        return distances.get(index);
    }

    private void display(){
        int num = 1;
        int distance = 0;
        Node<Location> sourceNode = locPassed.head;
        while(sourceNode != null){
            Node<Location> desNode = sourceNode.forward;
            while(desNode != null) {
                Location source = sourceNode.location;
                Location des = desNode.location;
                System.out.printf("%d. %s --- %s (%d km)",num++,source.getName(),des.getName(),getDistanceTo(source,des));
                distance += getDistanceTo(source,des);
                System.out.println();
                desNode = desNode.forward;
            }
            sourceNode = sourceNode.next;
        }
        System.out.println("\nTotal Length: " + distance + "km");
    }
}

class Tree<E>{
    Node<E> head;   //starting point
    int size;
    int numOfPath;

    public Tree(E startingPoint){
        head = new Node<>(startingPoint);
        size = 1;
        numOfPath = 0;
    }
    public Tree(){
        head = null;
        size = 0;
        numOfPath = 0;
    }

    public int getSize(){
        return size;
    }

    public int getNumOfPath() {
        return numOfPath;
    }

    public boolean hasNode(E loc){
        if(head == null) return false;
        Node<E> temp = head;
        while(temp != null){
            if(temp.location.equals(loc))
                return true;
            temp = temp.next;
        } return false;
    }

    public boolean addNode(E loc){
        if(head == null){
            head = new Node<>(loc);
            return true;
        }
        if(!hasNode(loc)) {
            Node<E> temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = new Node<>(loc, null, temp);
            size++;
            return true;
        } return false;
    }

    public boolean hasPath(E source, E des){
        if(head == null) return false;
        if(!hasNode(source) || !hasNode(des)) return false;
        Node<E> temp = head;
        while(temp != null){
            if(temp.location.equals(source)){
                if(temp.forward != null){
                    Node<E> current = temp.forward;
                    while(current != null){
                        if(current.location.equals(des))
                            return true;
                        current = current.forward;
                    }
                } else return false;
            }
            temp = temp.next;
        } return false;
    }

    public boolean addPath(E source, E des){
        if(hasPath(source,des)) return false;
        if(!hasNode(source)) addNode(source);
        if(!hasNode(des)) addNode((des));
        Node<E> temp = head;
        while(!temp.location.equals(source)){
            temp = temp.next;
        }
        while(temp.forward != null){
            temp = temp.forward;
        } temp.forward = new Node<>(des);
        numOfPath++;
        return true;
    }

}