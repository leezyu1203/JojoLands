import java.util.List;
import java.util.Scanner;

public class Move extends Main {
    public Move(){}
    
    protected static Location move(Location currentLocation, int select) {
        Scanner sc = new Scanner(System.in);
        String option;
        while(true){
            System.out.print("Enter the option of move: ");
            option = sc.nextLine();
            if(option.length() == 1 && Character.isUpperCase(option.charAt(0))){
                break;
            } else{
                System.out.println();
                System.out.println("*Invalid input. Please enter a single capital alphabet.\n");
            }
        }
        System.out.println("========================================================================");
        visit.forwardLocation = null;

        List<Location> adjacentLocations = currentLocation.getAdjacent();
        int optionCount = adjacentLocations.size();
        int selectedOption = option.charAt(0) - 'A';

        if (selectedOption >= 0 && selectedOption < optionCount) {
            Location nextLocation = adjacentLocations.get(selectedOption);
            System.out.println("Moving to " + nextLocation.getName());

            visit.addLast(nextLocation);
            return nextLocation;
        } return null;
    }

}