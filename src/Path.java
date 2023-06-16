import java.util.ArrayList;
import java.util.List;
public class Path implements Comparable<Path> {
    private Location location;
    private int activations;
    private int distance;
    private List<Location> locations;

    public Path(Location location, int activations, int distance) {
        this.location = location;
        this.activations = activations;
        this.distance = distance;
        this.locations = new ArrayList<>();
        this.locations.add(location);
    }

    public Location getLocation() {
        return location;
    }

    public int getActivations() {
        return activations;
    }

    public int getDistance() {
        return distance;
    }

    public List<Location> getLocations() {
        return locations;
    }
    
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    @Override
    public int compareTo(Path other) {
        // Compare based on the number of activations
        int activationComparison = Integer.compare(this.activations, other.activations);
        if (activationComparison != 0) {
            return activationComparison;
        }

        // Compare based on the distance if the number of activations is the same
        return Integer.compare(this.distance, other.distance);
    }
}
