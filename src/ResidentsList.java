import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
/**
 *
 * @author USER
 */
public class ResidentsList extends Main{

    static LinkedList<ArrayList<String>> residentList= readCSVResidents("residents.csv");
    private static LinkedList<ArrayList<String>> standList= readCSVResidents("stands.csv");  //not needed
            
    public static LinkedList<ArrayList<String>> readCSVResidents(String fileName){
        LinkedList<ArrayList<String>> lists= new LinkedList<>();
        try{
            Scanner data= new Scanner(new FileInputStream(fileName));
            
            while(data.hasNextLine()){
                String str= data.nextLine();
                String[] split= str.split(",");
                
                ArrayList<String> list= new ArrayList<>();
                for(int i=0; i<split.length; i++){
                    list.add(split[i]);
                }
                
                lists.add(list);
            }
            
            lists.remove(0);
            data.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return lists;
    }
    
    public static void display3(String name){  //arraylist of chosen resident
            ArrayList<String> resident= searchResident(name);
            ArrayList<String> stand= searchStand(name);
            int sizeJG= EatingHabits.jadeGardenFood.size()-1;
            int sizeCDM= EatingHabits.cafeMagotsFood.size();
            int sizeTT= EatingHabits.trattoriaFood.size();
            int sizeLB= EatingHabits.liberrioFood.size();
            //int sizeSG= EatingHabits.savageGardenFood.size();
            int indexCDM= sizeJG+sizeCDM, indexTT= indexCDM+sizeTT, indexLB= indexTT+sizeLB;
                    
            System.out.println(name + "'s Profile");
            System.out.println("Name                  : " + name);
            System.out.println("Age                   : " + resident.get(1));
            System.out.println("Gender                : " + resident.get(2));
            
            int j=0;
            if(name.equals("George Joestar II") || name.equals("Giorno Giovanna") || name.equals("Holy Kujo") || name.equals("Jonathan Joestar") 
                    || name.equals("Joseph Joestar") || name.equals("Josuke Higashikata") || name.equals("Jotaro Kujo")){
                System.out.println("Parents               : " + resident.get(4) + ", " + resident.get(5));
                j=6;
            }
            else{
                System.out.println("Parents               : " + resident.get(4));
                j=5;
            }
            
            System.out.println("Stand                 : " + stand.get(0));
            System.out.println("Destructive Power     : " + stand.get(2));
            System.out.println("Speed                 : " + stand.get(3));
            System.out.println("Range                 : " + stand.get(4));
            System.out.println("Stamina               : " + stand.get(5));
            System.out.println("Precision             : " + stand.get(6));
            System.out.println("Development Potential : " + stand.get(7));
            
            System.out.println("\nOrder History");
            System.out.println("+-----+-------------------------------------+-------------------------+-");
            System.out.printf("| %-3s | %-34s  | %-23s |", "Day", "Food", "Restaurant");
            System.out.println("\n+-----+-------------------------------------+-------------------------+-");
            
            int day=1, index= 0; 
            String restaurant= "", food= "";
            while(j<resident.size()){
                
                food= resident.get(j);
                if(food.equals("no")){
                    day++;
                    j++;
                }
                else{
                    for(int k=0; k<EatingHabits.foodList.size(); k++){
                        if(food.equals(EatingHabits.foodList.get(k).get(0))){
                            index= k;
                            break;
                        }
                    }

                    if(index>=0&&index<=sizeJG)
                        restaurant= "Jade Garden";
                    else if(index>=sizeJG+1&&index<=indexCDM)
                        restaurant= "Cafe Deux Magots";
                    else if(index>=indexCDM+1&&index<=indexTT)
                        restaurant= "Trattoria Trussardi";
                    else if(index>=indexTT+1&&index<=indexLB)
                        restaurant= "Liberrio";
                    else 
                        restaurant= "Savage Garden";


                    System.out.printf("| %3d | %-34s  | %-23s |%n", day, resident.get(j), restaurant); 
                    day++;
                    j++;
                }
            }
            System.out.println("+-----+-------------------------------------+-------------------------+-");
    }
    
    private static ArrayList<String> searchResident(String name){
        ArrayList<String> resident= new ArrayList<>();
        String n;
        
        for(int i=0; i<residentList.size(); i++){
            n= residentList.get(i).get(0);
            
            if(name.equals(n)){
                resident= residentList.get(i);
                break;
            }
        }
        
        return resident;
    }
    
    private static ArrayList<String> searchStand(String name){
        ArrayList<String> stand= new ArrayList<>();
        String n;
        
        for(int i=0; i<standList.size(); i++){
            n= standList.get(i).get(1);
            
            if(name.equals(n)){
                stand= standList.get(i);
                break;
            }
        }
        
        return stand;
    }
}