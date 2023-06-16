import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BitesTheDust {
public static void checkBitesTheDust(List<Location> path) {
    List<List<Location>> repeatedPaths = new ArrayList<>();

    for (int i = 0; i < path.size() - 1; i++) {
        for (int j = i + 1; j < path.size(); j++) {
            List<Location> subPath = path.subList(i, j + 1);
            if (isRepeated(subPath, path, j + 1) && !containsSameArray(repeatedPaths, subPath)) {
                repeatedPaths.add(new ArrayList<>(subPath));
            }
        }
    }

    List<Location> longestPath = null;
    int maxLength = 0;

    for (List<Location> repeatedPath : repeatedPaths) {
        if (repeatedPath.size() > maxLength) {
            maxLength = repeatedPath.size();
            longestPath = repeatedPath;
        }
    }

    if (longestPath != null) {
        System.out.println("Bites the Dust is most likely to be activated when Kira passed through:");

        for (int i = 0; i < longestPath.size() - 1; i++) {
        Location location = longestPath.get(i);
        System.out.print(location.getName() + " > ");
    }
    System.out.println(longestPath.get(longestPath.size() - 1).getName());
    } else {
        System.out.println("Bites the Dust is not activated.");
    }
}

private static boolean isRepeated(List<Location> subPath, List<Location> path, int startIndex) {
    for (int i = startIndex; i < path.size() - subPath.size() + 1; i++) {
        boolean isMatched = true;
        for (int j = 0; j < subPath.size(); j++) {
            if (!path.get(i + j).equals(subPath.get(j))) {
                isMatched = false;
                break;
            }
        }
        if (isMatched) {
            return true;
        }
    }
    return false;
}

private static boolean containsSameArray(List<List<Location>> repeatedPaths, List<Location> subPath) {
    for (List<Location> path : repeatedPaths) {
        if (path.size() == subPath.size() && path.containsAll(subPath)) {
            return true;
        }
    }
    return false;
}

}
