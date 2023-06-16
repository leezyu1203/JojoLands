import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathFinder extends Main{
    private static final int MAX_PATHS = 3;

    public static List<List<Location>> findShortestPaths(List<Location> locations, Location source, Location destination) {
        List<List<Location>> allPaths = new ArrayList<>();
        List<Location> path = new ArrayList<>();
        Set<Location> visited = new HashSet<>();
        int totalDistance = 0;

        findShortestPathsHelper(locations, source, destination, path, visited, allPaths, totalDistance);

        return allPaths;
    }

    private static void findShortestPathsHelper(List<Location> locations, Location current, Location destination,
                                                List<Location> path, Set<Location> visited, List<List<Location>> allPaths, int totalDistance) {
        path.add(current);
        visited.add(current);

        if (current == destination) {
            allPaths.add(new ArrayList<>(path));
        } else {
            List<Location> adjacentLocations = current.getAdjacent();
            List<Integer> distances = current.getDistances();

            for (int i = 0; i < adjacentLocations.size(); i++) {
                Location nextLocation = adjacentLocations.get(i);
                int distance = distances.get(i);

                if (!visited.contains(nextLocation)) {
                    findShortestPathsHelper(locations, nextLocation, destination, path, visited, allPaths,
                            totalDistance + distance);
                }
            }
        }
        path.remove(path.size() - 1);
        visited.remove(current);
    }

    public static int calculateTotalDistance(List<Location> locations, List<Location> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Location current = path.get(i);
            Location next = path.get(i + 1);

            int distance = getDistanceBetweenLocations(current, next);
            totalDistance += distance;
        }
        return totalDistance;
    }

    private static int getDistanceBetweenLocations(Location location1, Location location2) {
        List<Location> adjacentLocations = location1.getAdjacent();
        List<Integer> distances = location1.getDistances();

        for (int i = 0; i < adjacentLocations.size(); i++) {
            Location adjacent = adjacentLocations.get(i);
            if (adjacent.equals(location2)) {
                return distances.get(i);
            }
        }
        return 0; // return default distance if adjacent connection is invalid/ not found
    }

    public static Location getLocationByName(List<Location> locations, String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    public static List<List<Location>> getTopThreeShortestPaths(List<List<Location>> allPaths) {
        Comparator<List<Location>> pathComparator = Comparator
            .comparingInt((List<Location> path) -> path.size())
            .thenComparingInt(path -> calculateTotalDistance(locations,path));

        Collections.sort(allPaths, pathComparator);

        if (allPaths.size() > MAX_PATHS) {
            return allPaths.subList(0, MAX_PATHS);
        } else {
            return allPaths;
        }
    }
    
    public static void displayPaths(List<List<Location>> paths) {
        System.out.println("Top Three Shortest Paths:");

        if (paths.isEmpty()) {
            System.out.println("No paths found.");
            return;
        }

        // Sort the paths based on total distance
        paths.sort(Comparator.comparingInt(path -> calculateTotalDistance(locations, path)));

        int count = 1;
        for (List<Location> path : paths) {
            System.out.print(count + ". ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i).getName());
                if (i != path.size() - 1) {
                    System.out.print(" > ");
                }
            }
            int totalDistance = calculateTotalDistance(locations, path);
            System.out.println(" (" + totalDistance + " km)");
            count++;
        }

        System.out.println("=======================================================================");
    }
}

