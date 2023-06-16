import java.util.ArrayList;
import java.util.List;

public class AdjacentLocations {
    
    public AdjacentLocations(){}
    
    protected static void displayOptions(Location location) {
        List<Location> adjacentLocations = location.getAdjacent();
        int optionCount = adjacentLocations.size();
        char option = 'A';

        for (Location adjacentLocation : adjacentLocations) {
            System.out.print("   [" + option + "] " + adjacentLocation.getName()+"   ");
            option++;
        }

        System.out.println("");

    }
}

class Location{
    
    private String name;
    public List<Location> adjacent;
    public List<Integer> distances;
    
    public Location(){}
    
    public Location(String name){
        this.name = name;
        this.adjacent = new ArrayList<>();
        this.distances = new ArrayList<>();
    }
    
    public void addAdjacent(Location location, int distance){
        adjacent.add(location);
        distances.add(distance);
    }
    
    
    public String getName(){
        return name;
    }
    
    public List<Location> getAdjacent(){
        return adjacent;
    }
    
    public List<Integer> getDistances(){
        return distances;
    }
    
    public int getDistanceTo(Location location) {
        for (int i = 0; i < adjacent.size(); i++) {
            if (adjacent.get(i).equals(location)) {
                return distances.get(i);
            }
        }
        return Integer.MAX_VALUE;
    }   

    public boolean isLoc(String locName){
        return name.equals(locName);
    }
    
    @Override
    public String toString(){
        return name;
    }
}
