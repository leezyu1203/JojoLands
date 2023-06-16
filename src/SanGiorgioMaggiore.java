import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SanGiorgioMaggiore extends Main {
    
    private static int num;
    protected static String inputSort;
    boolean caseExecuted = true;

    
    protected void sangiorgio() {
        num=1;
        System.out.println("["+num+"] Move to:");
        num++;
        AdjacentLocations adjacent = new AdjacentLocations();
        adjacent.displayOptions(currentLocation);
        
        System.out.println("["+num+"] View Resident Information");
        num++;
        System.out.println("["+num+"] Back ("+ visit.getPrevious().getName()+")");
        num++;


        if (visit.forwardLocation != null) {
            System.out.println("["+num+"] Forward (" + visit.forwardLocation.getName()+ ")");
            num++;
            caseExecuted = false;
        }

        System.out.println("["+num+"] Back to Town Hall");
        num++;
        
        System.out.println("["+num+"] Stay the Hell Away from Me!");

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
            System.out.println("========================================================================");
        }
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
                    System.out.println("========================================================================");
                }
            
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
                    currentLocation = visit.forwardLocation;
                    visit.addLast(currentLocation);
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
                exit = false;
            while (!exit) {
                System.out.println(";)  Welcome to the extra feature - Stay the Hell Away from me! ;)");
                
                Scanner input = new Scanner(System.in);
                List<Location> identifiedLocations = new ArrayList<>();
                
                //user input source
                System.out.print("Source: ");
                String sourceName = input.nextLine();
                Location source = PathFinder.getLocationByName(locations,sourceName);
                
                // user input destination
                System.out.print("Destination: ");
                String destinationName = input.nextLine();
                Location destination = PathFinder.getLocationByName(locations,destinationName);
                
                //user input identified locations
                System.out.print("Indentified Locations (seperated by commas): ");
                String inputLocations = input.nextLine();
                String [] locationNames = inputLocations.split(", ");
                
                // save inputted identified locations into array
                for(String locationName: locationNames){
                    Location location = PathFinder.getLocationByName(locations, locationName);
                    if(location != null){
                        identifiedLocations.add(location);
                    }else{
                        System.out.println("Invalid Location: " + locationName);
                    }
                }
                
                System.out.println("========================================================================");
                
                // Find optimal path
                Path optimalPath = DiavoloTravel.findOptimalPath(source, destination, identifiedLocations);

                if (optimalPath != null) {
                    DiavoloTravel.displayPath(optimalPath);
                } else {
                    System.out.println("No path found.");
                }
                
                System.out.println();
                System.out.println("========================================================================");
                
                System.out.print("Are you sure to exit this feature? (y/n): ");
                char option = input.next().charAt(0);
                System.out.println("========================================================================");
                if(option == 'y'){
                    exit = true;
                    System.out.println("\n\n");
                }
            }
            }
                break;
                
            case 6:
                exit = false;
                while (!exit) {
                    System.out.println(";)  Welcome to the extra feature - Stay the Hell Away from me! ;)");
                
                    Scanner input = new Scanner(System.in);
                    List<Location> identifiedLocations = new ArrayList<>();
                
                    //user input source
                    System.out.print("Source: ");
                    String sourceName = input.nextLine();
                    Location source = PathFinder.getLocationByName(locations,sourceName);
                
                    // user input destination
                    System.out.print("Destination: ");
                    String destinationName = input.nextLine();
                    Location destination = PathFinder.getLocationByName(locations,destinationName);
                
                    //user input identified locations
                    System.out.print("Indentified Locations (seperated by commas): ");
                    String inputLocations = input.nextLine();
                    String [] locationNames = inputLocations.split(", ");
                
                    // save inputted identified locations into array
                    for(String locationName: locationNames){
                        Location location = PathFinder.getLocationByName(locations, locationName);
                        if(location != null){
                            identifiedLocations.add(location);
                        }else{
                            System.out.println("Invalid Location: " + locationName);
                        }
                    }
                
                    System.out.println("========================================================================");
                
                    // Find optimal path
                    Path optimalPath = DiavoloTravel.findOptimalPath(source, destination, identifiedLocations);

                    if (optimalPath != null) {
                        DiavoloTravel.displayPath(optimalPath);
                    } else {
                        System.out.println("No path found.");
                    }
                
                    System.out.println();
                    System.out.println("========================================================================");
                
                    System.out.print("Are you sure to exit this feature? (y/n): ");
                    char option = input.next().charAt(0);
                    if(option == 'y'){
                        exit = true;
                        System.out.println("\n\n");
                    }
                }
                break;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}