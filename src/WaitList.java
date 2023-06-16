import java.util.*;

public class WaitList extends Main {
    
    ArrayList<Integer> waitingList= setWaitingList();
    EatingHabits e= new EatingHabits();

    //need to call every new day
    private ArrayList<Integer> setWaitingList(){
        ArrayList<Integer> indexes= new ArrayList<>();
        
        for(int i=0; i<71; i++){
            indexes.add(i);
        }
        
        return indexes;
    }
        
    private LinkedList<ArrayList<String>> wl(int r, int day){
        Random rand= new Random();
        LinkedList<ArrayList<String>> wl= new LinkedList<>();
        LinkedList<ArrayList<String>> foodList= new LinkedList<>();
        int noOfCustomers= rand.nextInt(9)+6, index, residentIndex, foodNum, last;
        int jotaro=0, jolyne=0;
        String food= "";
        
        switch(r){
            case 1:
                foodList = jadeGardenFood;
                break;
            case 2:
                foodList = cafeMagotsFood;
                break;
            case 3:
                foodList = trattoriaFood;
                break;
            case 4:
                foodList = liberrioFood;
                break;
            case 5:
                foodList = savageGardenFood;
                break;
        }
    
        if(day%7==0){  //saturday
            if(e.canEatThereJolyneorJotaro(ResidentsList.residentList.get(8), 1)==false){
                food= e.josephJoestarNjotaroKujo(ResidentsList.residentList.get(8), 1);

                if(!food.equals("done")){  //jotaro can eat here
                    for(int i=0; i<waitingList.size(); i++){
                        if(waitingList.get(i)==8){
                            jotaro= i;
                            break;
                        }
                    }
                    wl.add(ResidentsList.residentList.get(8));  //add jotaro
                    wl.get(0).add(food);  //the first person in the waitlist, so index 0
                    waitingList.remove(jotaro);
                    
                    for(int i=0; i<waitingList.size(); i++){
                        if(waitingList.get(i)==4){
                            jolyne= i;
                            break;
                        }
                    }
                    wl.add(ResidentsList.residentList.get(4));  //add jolyne
                    wl.get(1).add(food);  //the second person in the waitlist, so index 1
                    waitingList.remove(jolyne);
                }
            }
        }
        
        for(int i=0; i<noOfCustomers; i++){
            index= rand.nextInt(waitingList.size());
            residentIndex= waitingList.get(index);
            wl.add(ResidentsList.residentList.get(residentIndex));
            String name= wl.get(i).get(0);
            if(name.equals("Jonathan Joestar")){
                if(wl.get(i).size()==6){
                    food= e.ifSizeis6(r);
                    wl.get(i).add(food);
                    waitingList.remove(index);
                }
                else{
                    food= e.jonathanJoestar(wl.get(i), r);
                    wl.get(i).add(food);
                    waitingList.remove(index);
                }
                continue;
            }
            if(name.equals("Joseph Joestar")){
                if(wl.get(i).size()==6){
                    food= e.ifSizeis6(r);
                    wl.get(i).add(food);
                    waitingList.remove(index);
                }
                else{
                    food= e.josephJoestarNjotaroKujo(wl.get(i), r);
                    if(food.equals("done")){
                        wl.removeLast();
                        i--;
                    }
                    else{
                        wl.get(i).add(food);
                        waitingList.remove(index);
                    }
                }
                continue;
            }
            
            if(day%7!=0){
                if(name.equals("Jotaro Kujo")){
                    if(wl.get(i).size()==6){ 
                        food= e.ifSizeis6(r);
                        wl.get(i).add(food);
                        waitingList.remove(index);
                    }
                    else{
                        if(e.canEatThereJolyneorJotaro(wl.get(i), r)==false){
                            food= e.josephJoestarNjotaroKujo(wl.get(i), r);

                            if(food.equals("done")){
                                wl.removeLast();
                                i--;
                            }
                            else{
                                wl.get(i).add(food);
                                waitingList.remove(index);
                            }
                        }
                        else{  //he is eating at another restaurant
                            wl.removeLast();
                            i--;
                        }
                    }
                    continue;
                }
            }

            if(name.equals("Josuke Higashikata")){
                if(wl.get(i).size()==6){
                    food= e.ifSizeis6(r);
                    wl.get(i).add(food);
                    waitingList.remove(index);
                }
                else{
                    food= e.josukeHigashikata(wl.get(i), r);
                    wl.get(i).add(food);
                    waitingList.remove(index);
                }
                continue;
            }
            
            if(name.equals("Giorno Giovanna")){
                if(r==3){
                    if(wl.get(i).size()==6){
                        food= e.ifSizeis6(r);
                        wl.get(i).add(food);
                        waitingList.remove(index);
                    }
                    else{
                        food= e.giornoGiovanna(wl.get(i));

                        if(!food.equals("no")){
                            wl.get(i).add(food);
                            waitingList.remove(index);
                        }
                    }
                }
                else{
                    wl.removeLast();
                    i--;
                }
                continue;
            }
            
            if(day%7!=0){
                if(name.equals("Jolyne Cujoh")){
                    if(wl.get(i).size()==5){
                        food= e.ifSizeis6(r);
                        wl.get(i).add(food);
                        waitingList.remove(index);
                    }
                    else{
                        if(e.canEatThereJolyneorJotaro(wl.get(i), r)){
                            food= e.ifSizeis6(r);
                            wl.get(i).add(food);
                            waitingList.remove(index);
                        }
                        else{
                            wl.removeLast();
                            i--;
                        }
                    }
                    continue;
                }
            }

            waitingList.remove(index);
            foodNum= rand.nextInt(foodList.size());
            food= foodList.get(foodNum).get(0);
            wl.get(i).add(food);
        }
        
        if(r==5){
        //if there are residents who didn't eat out, add no for no eat out
            if(waitingList.size()!=0){
                for(int j=0; j<waitingList.size(); j++){
                    residentIndex= waitingList.get(j);
                    ResidentsList.residentList.get(residentIndex).add("no");
                }
            }
        }
        //System.out.println("Waiting List");
        //RestaurantsSortings.display(cdmWaitList);
        return wl;
    }
    
    public LinkedList<ArrayList<String>> jgWaitList(int day){
        LinkedList<ArrayList<String>> jgWaitList= wl(1, day);
        ArrayList<String> orders= new ArrayList<>(); //if need
        int last;
        String foodOrder;
        
        for(int j=0; j<jgWaitList.size(); j++){
            last= jgWaitList.get(j).size() - 1;
            foodOrder= jgWaitList.get(j).get(last);
            orders.add(foodOrder);
        }
        
        return jgWaitList;
    }
    
    public LinkedList<ArrayList<String>> cdmWaitList(int day){
        LinkedList<ArrayList<String>> cdmWaitList= wl(2, day);
        ArrayList<String> orders= new ArrayList<>(); //if need
        int last;
        String foodOrder;
        
        for(int j=0; j<cdmWaitList.size(); j++){
            last= cdmWaitList.get(j).size() - 1;
            foodOrder= cdmWaitList.get(j).get(last);
            orders.add(foodOrder);
        }
        
        return cdmWaitList;
    }
    
    public LinkedList<ArrayList<String>> ttWaitList(int day){
        LinkedList<ArrayList<String>> ttWaitList= wl(3, day);
        ArrayList<String> orders= new ArrayList<>(); //if need
        int last;
        String foodOrder;
        
        for(int j=0; j<ttWaitList.size(); j++){
            last= ttWaitList.get(j).size() - 1;
            foodOrder= ttWaitList.get(j).get(last);
            orders.add(foodOrder);
        }
        
        return ttWaitList;
    }
    
    public LinkedList<ArrayList<String>> lWaitList(int day){
        LinkedList<ArrayList<String>> lWaitList= wl(4, day);
        ArrayList<String> orders= new ArrayList<>(); //if need
        int last;
        String foodOrder;
        
        for(int j=0; j<lWaitList.size(); j++){
            last= lWaitList.get(j).size() - 1;
            foodOrder= lWaitList.get(j).get(last);
            orders.add(foodOrder);
        }
        
        return lWaitList;
    }
    
    public LinkedList<ArrayList<String>> sgWaitList(int day){
        LinkedList<ArrayList<String>> sgWaitList= wl(5, day);
        ArrayList<String> orders= new ArrayList<>(); //if need
        int last;
        String foodOrder;
        
        for(int j=0; j<sgWaitList.size(); j++){
            last= sgWaitList.get(j).size() - 1;
            foodOrder= sgWaitList.get(j).get(last);
            orders.add(foodOrder);
        }
        
        return sgWaitList;
    }
}

