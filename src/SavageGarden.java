import java.util.*;

public class SavageGarden extends Main {
    
    private static int num;
    static Menu menu = new Menu();
    LinkedList<ArrayList<String>> sg= TownHall.sg;
    
    protected static void savage(){
        num=1;
        System.out.println("["+num+"] Move to:");
        num++;
        AdjacentLocations adjacent = new AdjacentLocations();
        adjacent.displayOptions(currentLocation);
        
        System.out.println("["+num+"] View Waiting List and Order Processing List");
        num++;
        System.out.println("["+num+"] View Menu");
        num++;
        System.out.println("["+num+"] View Sales Information");
        num++;
        System.out.println("["+num+"] Milagro Man");
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
                System.out.println("Restaurant: Savage Garden\nWaiting List");
                RestaurantsSortings r= new RestaurantsSortings();
                SavageGarden savage= new SavageGarden();
                r.display(savage.sg);
                r.savageGardenProcessingList(savage.sg, currentDay);
                break;

            case 3:
                boolean going = true;
                while(going){
                System.out.println("Restaurant: "+ currentLocation +"\n"+ currentLocation +" Menu");
                EatingHabits.display(savageGardenFood);
                System.out.println("\n[1] Add new food");
                System.out.println("[2] Remove existing food");
                System.out.println("[3] Modify food");
                System.out.println("[4] Exit");
                System.out.print("Select :");
                Scanner sc4 = new Scanner(System.in);
                int selectMenu = sc4.nextInt();

                switch (selectMenu){
                    case 1:
                        System.out.println("Restaurant: "+ currentLocation);
                        menu.display();
                        menu.addFood();
                        break;
                    case 2:
                        System.out.println("Restaurant: "+ currentLocation);
                        menu.display();
                        menu.removeFood();
                        break;
                    case 3:
                        System.out.println("Restaurant: "+ currentLocation);
                        menu.display();
                        menu.modifyFood();
                        break;
                    case 4:
                        going = false;
                        System.out.println("========================================================================");
                        break;
                    }
                }
                break;
            case 4:
                MoodyBlue moody = new MoodyBlue();
                moody.moody();
                break;

            case 5:
                MilagroMan milagro = new MilagroMan();
                milagro.milagro();
                break;

            case 6:
                visit.setNext(currentLocation);
                currentLocation = visit.getPrevious();
                visit.removeLast();
                break;

            case 7:
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

            case 8:
                currentLocation = townHall;
                visit.forwardLocation = null;
                visit.addLast(currentLocation);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}