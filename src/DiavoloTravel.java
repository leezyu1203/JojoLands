import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DiavoloTravel extends Main{
    public static Path findOptimalPath(Location source, Location destination, List<Location> identifiedLocations) {
        PriorityQueue<Path> queue = new PriorityQueue<>();
        Map<Location, Path> visitedPaths = new HashMap<>();

        // Initialize the source path
        Path sourcePath = new Path(source, 0, 0);
        queue.offer(sourcePath);
        visitedPaths.put(source, sourcePath);

        while (!queue.isEmpty()) {
            Path currentPath = queue.poll();
            Location currentLocation = currentPath.getLocation();

            // Check if the destination is reached
            if (currentLocation.equals(destination)) {
                return currentPath;
            }

            // Iterate over the adjacent locations
            for (Location neighbor : currentLocation.getAdjacent()) {
                int distance = currentLocation.getDistanceTo(neighbor);
                int totalDistance = currentPath.getDistance() + distance;

                // Check if Diavolo needs to activate his ability at the identified location
                int activations = currentPath.getActivations();
                if (identifiedLocations.contains(neighbor)) {
                    activations++;
                }

                // Create a new path for the neighbor location
                Path newPath = new Path(neighbor, activations, totalDistance);
                List<Location> newLocations = new ArrayList<>(currentPath.getLocations());
                boolean shouldAddLocation = true;
                for (Location location : newLocations) {
                    if (identifiedLocations.contains(location)) {
                        shouldAddLocation = false;
                        break;
                    }
                }
                if (shouldAddLocation) {
                    newLocations.add(neighbor);
                }
                newPath.setLocations(newLocations);

                // Check if the neighbor location has not been visited or if the new path has a better distance
                if (!visitedPaths.containsKey(neighbor) || (totalDistance < visitedPaths.get(neighbor).getDistance()
                        || (totalDistance == visitedPaths.get(neighbor).getDistance()
                        && activations < visitedPaths.get(neighbor).getActivations()))) {
                    queue.offer(newPath);
                    visitedPaths.put(neighbor, newPath);
                }
            }
        }

        // No path found
        return null;
    }

    
    public static void displayPath(Path path) {
        List<Location> locations = path.getLocations();
        int distance = path.getDistance();

        System.out.println("Optimal Path:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.print(locations.get(i).getName());
            if (i < locations.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.print("(" + distance + " km)");
    }
}
