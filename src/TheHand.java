import java.util.*;

public class TheHand {
    protected Tree<Location> locPassed;
    protected Tree<Location> toRemoved;
    protected List<Location> allLoc;
    Location startingPoint;

    public TheHand(){
        startingPoint = Main.townHall;
        allLoc = Main.locations;
        necessaryPath();
        removedPath();
        System.out.println("Unnecessary Water Connection: ");
        displayRemoved();
        System.out.println("========================================================================");
    }

    private void removedPath(){
        toRemoved = new Tree<>();
        for(int i = 0; i < allLoc.size(); i++){
            Location source = allLoc.get(i);
            List<Location> sourceAdjacent = source.getAdjacent();
            for(int j = 0; j < sourceAdjacent.size(); j++){
                Location des = sourceAdjacent.get(j);
                if(!pathExist(source,des)){
                    toRemoved.addPath(source,des);
                }
            }
        }
    }

    private void necessaryPath(){
        locPassed = new Tree<>(startingPoint);
        int numOfLoc = Main.locations.size();
        while(locPassed.getNumOfPath() != numOfLoc-1) {
            int maxD = -1;
            Location source = null;
            Location des = null;
            Node<Location> temp = locPassed.head;
            while (temp != null) {
                Location tempSource = temp.location;
                List<Location> adjacents = tempSource.getAdjacent();
                for (int j = 0; j < adjacents.size(); j++) {
                    Location tempDes = adjacents.get(j);
                    if (!locPassed.hasNode(tempDes)) {
                        maxD = Math.max(maxD, getDistanceTo(tempSource, tempDes));
                        if (maxD == getDistanceTo(tempSource, tempDes)) {
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

    private void displayLocPassed(){
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

    private void displayRemoved(){
        int num = 1;
        int distance = 0;
        Node<Location> sourceNode = toRemoved.head;
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

    /**
     * This method returns boolean to check the path whether it is consisted in the locPassed in either way
     * @param loc1 a Location object that can be either a source or a destination
     * @param loc2 another Location object that can be either a source or destination
     * @return true if there is no path between loc1 and loc 2 in locPassed and should be removed as well as add into
     *  toRemove, else return false
     */
    private boolean pathExist(Location loc1, Location loc2){
        return locPassed.hasPath(loc1,loc2) || locPassed.hasPath(loc2,loc1) ||
                toRemoved.hasPath(loc1,loc2) || toRemoved.hasPath(loc2,loc1);
    }
}