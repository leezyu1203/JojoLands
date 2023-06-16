import java.util.Scanner;

public class PolnareffLand extends Main {
    
    private static int num;
    protected static String inputSort;
    
    protected static void polnareff() {
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
        }

        System.out.println("["+num+"] Back to Town Hall");

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

                System.out.print("\n\nSelect (1/2/3 etc): ");
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
                    currentLocation = townHall;
                    visit.forwardLocation = null;
                    visit.addLast(currentLocation);
                    break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}