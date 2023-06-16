import java.util.List;
import java.util.Scanner;

public class GreenDolphinStreetPrison extends Main {
    private static int num;
    protected static String inputSort;
    boolean caseExecuted = true;
    
    public void greendolphin(List<Location> locations) {
        num=1;
        // case 1
        System.out.println("["+num+"] Move to:");
        num++;
        AdjacentLocations adjacent = new AdjacentLocations();
        adjacent.displayOptions(currentLocation);
        
        //case 2
        System.out.println("["+num+"] View Resident Information");
        num++;
        
        // case 3
        System.out.println("["+num+"] Back ("+ visit.getPrevious().getName()+")");
        num++;

        
        if (visit.forwardLocation != null) {
            System.out.println("["+num+"] Forward (" + visit.forwardLocation.getName()+ ")");
            num++;
            caseExecuted = false;
        }
        
        System.out.println("["+num+"] Back to Town Hall");
        num++;
        
        // Case 5: Extra features
        System.out.println("[" + num+ "] Dirty Deeds Done Dirt Cheap");
        num++;
        // Case 6: Extra features
        System.out.println("[" + num+ "] Burning Down The House");

        Scanner sc = new Scanner(System.in);
        int select;
        System.out.println("\n");
        while(true){
            System.out.print("Select: ");
            if(sc.hasNextInt()){
                select = sc.nextInt();
                break;
            } else{
                System.out.println();
                System.out.println("*Invalid input. Please enter a numeric value.*\n");
                sc.next();
            }
        }
        if(select != 1){
        System.out.println("========================================================================");}
        sc.nextLine(); 
    
        switch (select) {
            case 1:
                Move move = new Move();
                currentLocation = move.move(currentLocation, select);
                break;

            case 2:
                ResidentInfo resident = new ResidentInfo();
                resident.processResidentData();
                resident.display(ResidentInfo.groupedResidents, ResidentInfo.stands, currentLocation.getName());
                boolean exit = false;
                while(exit != true){
                    int option = 1;
                    System.out.println("["+option+"] View Resident's Profile");
                    option++;
                    System.out.println("["+option+"] Sort");
                    option++;
                    System.out.println("["+option+"] Exit");
                    option++;
            
                    System.out.print("\n\nSelect: ");
                    Scanner sci = new Scanner(System.in);
                    int choice = sci.nextInt();
                    if(select != 2){
                    System.out.println("========================================================================");}
            
                    switch(choice){
                        case 1:
                            System.out.print("Enter the resident's name: ");
                            Scanner scb= new Scanner(System.in);
                            String name= scb.nextLine();
                            System.out.println("========================================================================");
                            ResidentsList.display3(name);
                            System.out.println("========================================================================");

                            break;
                        case 2:
                            System.out.print("Enter the sorting order: ");
                            Scanner sca = new Scanner(System.in);
                            inputSort = sca.nextLine();
                            resident.displaySort(ResidentInfo.groupedResidents, ResidentInfo.stands, currentLocation.getName(), inputSort);
                            break;
                        case 3:
                            exit = true;
                            break;
                        }
                    }
                break;
        
            case 3:
                visit.setNext(currentLocation);
                currentLocation = visit.getPrevious();
                visit.removeLast();
                break;
        
            case 4:
                if (visit.forwardLocation!= null) {
                    visit.addLast(currentLocation);
                    currentLocation = visit.forwardLocation;
                    visit.forwardLocation = null;
                } else {
                    currentLocation = townHall;
                    visit.forwardLocation = null;
                    visit.addLast(currentLocation);
                }
                break;
            
            case 5:
                if(caseExecuted == false){
                    currentLocation = townHall;
                    visit.forwardLocation = null;
                    visit.addLast(currentLocation);
                }else{
                    System.out.println("(: Welcome to Dirty Deeds Done Dirt Cheap :)");
                
                    exit = false;
                    while(!exit){
                        // User input
                        Scanner input = new Scanner(System.in);
                        System.out.print("\nEnter your source: ");
                        String sourceName = input.nextLine();
                        Location source = PathFinder.getLocationByName(locations,sourceName);

                        System.out.print("Enter the destination: ");
                        String destinationName = input.nextLine();
                        Location destination = PathFinder.getLocationByName(locations,destinationName);
                        System.out.println("========================================================================");

                        // Call the PathFinder methods with locations, source and destination
                        List<List<Location>> allPaths = PathFinder.findShortestPaths(locations, source, destination);

                        if (allPaths.isEmpty()) {
                            System.out.println("No paths found between " + source.getName() + " and " + destination.getName());
                        } else {
                            List<List<Location>> topThreePaths = PathFinder.getTopThreeShortestPaths(allPaths);
                            PathFinder.displayPaths(topThreePaths);
                        }

                        System.out.print("Exiting.... Confirm? (y/n): ");
                        Scanner sci = new Scanner(System.in);
                        char option = sci.next().charAt(0);
                        System.out.println("========================================================================");
                        if(option == 'y'){
                        exit = true;
                            System.out.println("\n\n");
                        }
                    }
                }
                break;

            case 6:
                if(caseExecuted == false) {
                    System.out.println("(: Welcome to Dirty Deeds Done Dirt Cheap :)");
                    exit = false;
                    while (!exit) {
                        // User input
                        Scanner input = new Scanner(System.in);
                        System.out.print("\nEnter your source: ");
                        String sourceName = input.nextLine();
                        Location source = PathFinder.getLocationByName(locations, sourceName);

                        System.out.print("Enter the destination: ");
                        String destinationName = input.nextLine();
                        Location destination = PathFinder.getLocationByName(locations, destinationName);
                        System.out.println("========================================================================");

                        // Call the PathFinder methods with locations, source and destination
                        List<List<Location>> allPaths = PathFinder.findShortestPaths(locations, source, destination);

                        if (allPaths.isEmpty()) {
                            System.out.println("No paths found between " + source.getName() + " and " + destination.getName());
                        } else {
                            List<List<Location>> topThreePaths = PathFinder.getTopThreeShortestPaths(allPaths);
                            PathFinder.displayPaths(topThreePaths);
                        }

                        System.out.print("Exiting tp the previous location. Comfirm? (y/n): ");
                        Scanner sci = new Scanner(System.in);
                        char option = sci.next().charAt(0);
                        if (select != 2) {
                            System.out.println("========================================================================");
                        }

                        if (option == 'y') {
                            exit = true;
                            System.out.println();
                        }
                    }
                } else{
                    BurningDownTheHouse burning = new BurningDownTheHouse();
                }
                break;
            case 7:
                BurningDownTheHouse burning = new BurningDownTheHouse();
                break;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
