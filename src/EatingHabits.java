import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class EatingHabits extends Main{
    
    public static void display(LinkedList<ArrayList<String>> r){  //display menu
        System.out.println("+----+-----------------------------------------+------------+-");
        System.out.printf("| %-2s | %-39s | %-10s |", "No", "Order", "Price");
        System.out.println("\n+----+-----------------------------------------+------------+-");
        
        int number=1;
        for(int i=0; i<r.size(); i++){
            System.out.printf("| %-2d | %-39s | $%-9s |%n", number, r.get(i).get(0), r.get(i).get(1));
            number++;
        }
        System.out.println("+----+-----------------------------------------+------------+-");
    }
    
    public String jonathanJoestar(ArrayList<String> jonathanj, int r){
        //eat a type of food at most 2 times, once reach frequency 2, choose another food item
        Random rand= new Random();
        int foodNum, i=0;
        String food= "";
        
        switch(r){
            case 1:                
                while(i!=1){
                    foodNum= rand.nextInt(jadeGardenFood.size());
                    food= jadeGardenFood.get(foodNum).get(0);

                    if(checkIfCanEat(jonathanj, food)){
                        i++;
                    }
                }
                break;
            case 2:
                while(i!=1){
                    foodNum= rand.nextInt(cafeMagotsFood.size());
                    food= cafeMagotsFood.get(foodNum).get(0);

                    if(checkIfCanEat(jonathanj, food)){
                        i++;
                    }
                }                
                break;
            case 3:
                while(i!=1){
                    foodNum= rand.nextInt(trattoriaFood.size());
                    food= trattoriaFood.get(foodNum).get(0);

                    if(checkIfCanEat(jonathanj, food)){
                        i++;
                    }
                }
                break;
            case 4:
                while(i!=1){
                    foodNum= rand.nextInt(liberrioFood.size());
                    food= liberrioFood.get(foodNum).get(0);

                    if(checkIfCanEat(jonathanj, food)){
                        i++;
                    }
                }
                break;
            case 5:
                while(i!=1){
                    foodNum= rand.nextInt(savageGardenFood.size());
                    food= savageGardenFood.get(foodNum).get(0);

                    if(checkIfCanEat(jonathanj, food)){
                        i++;
                    }
                }
                break;
        }
        return food;
    }
    
    private boolean checkIfCanEat(ArrayList<String> jonathanj, String food){
        int i=6, f=0;
        
        if(jonathanj.size()==6)
            return true;
        
        while(i<jonathanj.size()){
            if(food.equals(jonathanj.get(i))){
                f++;
            }
            if(f==2){
                return false;
            }
            i++;
        }
        return true;
    }
    
    public String josephJoestarNjotaroKujo(ArrayList<String> josephj, int r){
        Random rand= new Random();
        int foodNum, i=0, count;
        String food= "";
        
        switch(r){
            case 1:
                //size= jg1.size();
                count= 0;
                for(int j=6; j<josephj.size(); j++){
                    for(int k=0; k<jadeGardenFood.size(); k++){
                        if(josephj.get(j).equals(jadeGardenFood.get(k).get(0))){
                            count++;
                            break;
                        }
                    }
                }
                if(count==jadeGardenFood.size()){
                    food= "done";
                }
                else{
                    while(i!=1){
                        foodNum= rand.nextInt(jadeGardenFood.size());
                        food= jadeGardenFood.get(foodNum).get(0);

                        if(checkUniqueFood(josephj, food)){
                            i++;
                        }
                    }
                }
                break;
            case 2:
                count= 0;
                for(int j=6; j<josephj.size(); j++){
                    for(int k=0; k<cafeMagotsFood.size(); k++){
                        if(josephj.get(j).equals(cafeMagotsFood.get(k).get(0))){
                            count++;
                            break;
                        }
                    }
                }
                if(count==cafeMagotsFood.size()){
                    food= "done";
                }
                else{
                    while(i!=1){
                        foodNum= rand.nextInt(cafeMagotsFood.size());
                        food= cafeMagotsFood.get(foodNum).get(0);

                        if(checkUniqueFood(josephj, food)){
                            i++;
                        }
                    }
                }
                break;
            case 3:
                count= 0;
                for(int j=6; j<josephj.size(); j++){
                    for(int k=0; k<trattoriaFood.size(); k++){
                        if(josephj.get(j).equals(trattoriaFood.get(k).get(0))){
                            count++;
                            break;
                        }
                    }
                }
                if(count==trattoriaFood.size()){
                    food= "done";
                }
                else{
                    while(i!=1){
                        foodNum= rand.nextInt(trattoriaFood.size());
                        food= trattoriaFood.get(foodNum).get(0);

                        if(checkUniqueFood(josephj, food)){
                            i++;
                        }
                    }
                }
                break;
            case 4:
                count= 0;
                for(int j=6; j<josephj.size(); j++){
                    for(int k=0; k<liberrioFood.size(); k++){
                        if(josephj.get(j).equals(liberrioFood.get(k).get(0))){
                            count++;
                            break;
                        }
                    }
                }
                if(count==liberrioFood.size()){
                    food= "done";
                }
                else{
                    while(i!=1){
                        foodNum= rand.nextInt(liberrioFood.size());
                        food= liberrioFood.get(foodNum).get(0);

                        if(checkUniqueFood(josephj, food)){
                            i++;
                        }
                    }
                }
                break;
            case 5:
                count= 0;
                for(int j=6; j<josephj.size(); j++){
                    for(int k=0; k<savageGardenFood.size(); k++){
                        if(josephj.get(j).equals(savageGardenFood.get(k).get(0))){
                            count++;
                            break;
                        }
                    }
                }
                if(count==savageGardenFood.size()){
                    food= "done";
                }
                else{
                    while(i!=1){
                        foodNum= rand.nextInt(savageGardenFood.size());
                        food= savageGardenFood.get(foodNum).get(0);

                        if(checkUniqueFood(josephj, food)){
                            i++;
                        }
                    }
                }
                break;
            }
        return food;
    }
    
    private boolean checkUniqueFood(ArrayList<String> resident, String food){
        int j=6; //start from index 6
        
        while(j<resident.size()){
            if(food.equals(resident.get(j))){
                return false;
            }
            j++;
        }
        return true;
    }
    
    public String jotaroMostRecentFood(ArrayList<String> jotaro){
        String food= "";
        
        for(int i=6; i<jotaro.size(); i++){
            if(jotaro.get(i).equals("no"))
                continue;
            
            food= jotaro.get(i);
        }
        
        return food;
    }
    //jotaro and joseph use the same method
  
    public String josukeHigashikata(ArrayList<String> jh, int r){
        Random rand= new Random();
        int foodNum, i=0;
        double total=0;
        ArrayList<String> food= new ArrayList<>();
        String foodName= "";
        
        for(int j=6; j<jh.size(); j++){
            for(int k=0; k<foodList.size(); k++){
                if(jh.get(j).equals("no"))
                    break;

                if(jh.get(j).equals(foodList.get(k).get(0))){
                    double price= Double.parseDouble(foodList.get(k).get(1));
                    total += price;
                    break;
                }
            }
        }

        switch(r){
            case 1:                
                foodNum= rand.nextInt(jadeGardenFood.size());
                food= jadeGardenFood.get(foodNum);

                if(total>0){
                    if(checkTotalUnder100(total, food)){
                        foodName= food.get(0);
                    }
                    else{
                        foodName= checkTotalAbove100(jadeGardenFood);
                    }
                }
                else{
                    foodName= food.get(0);
                }
                break;
            case 2:
                foodNum= rand.nextInt(cafeMagotsFood.size());
                food= cafeMagotsFood.get(foodNum);

                if(total>0){
                    if(checkTotalUnder100(total, food)){
                        foodName= food.get(0);
                    }
                    else{
                        foodName= checkTotalAbove100(cafeMagotsFood);
                    }
                }
                else{
                    foodName= food.get(0);
                }                
                break;
            case 3:
                foodNum= rand.nextInt(trattoriaFood.size());
                food= trattoriaFood.get(foodNum);

                if(total>0){
                    if(checkTotalUnder100(total, food)){
                        foodName= food.get(0);
                    }
                    else{
                        foodName= checkTotalAbove100(trattoriaFood);
                    }
                }
                else{
                    foodName= food.get(0);
                }
                break;
            case 4:
                foodNum= rand.nextInt(liberrioFood.size());
                food= liberrioFood.get(foodNum);

                if(total>0){
                    if(checkTotalUnder100(total, food)){
                        foodName= food.get(0);
                    }
                    else{
                        foodName= checkTotalAbove100(liberrioFood);
                    }
                }
                else{
                    foodName= food.get(0);
                }
                break;
            case 5:
                foodNum= rand.nextInt(savageGardenFood.size());
                food= savageGardenFood.get(foodNum);

                if(total>0){
                    if(checkTotalUnder100(total, food)){
                        foodName= food.get(0);
                    }
                    else{
                        foodName= checkTotalAbove100(savageGardenFood);
                    }
                }
                else{
                    foodName= food.get(0);
                }
                break;
        }
        return foodName;
    }
    
    //returns food name that has the cheapest price
    private String checkTotalAbove100(LinkedList<ArrayList<String>> restaurant){
        double foodPrices, cheapest;
        String cheapestFood= "", cheapestPrice;
        PriorityQueue<Double> price= new PriorityQueue<>();
        
        for(int i=0; i<restaurant.size(); i++){
            foodPrices= Double.parseDouble(restaurant.get(i).get(1));
            price.add(foodPrices);
        }

        cheapest= price.peek();
        cheapestPrice= Double.toString(cheapest);
        
        for(int j=0; j<restaurant.size(); j++){
            if(cheapestPrice.equals(restaurant.get(j).get(1))){
                cheapestFood= restaurant.get(j).get(0);
                break;
            }
        }
        
        return cheapestFood; 
    }

    private boolean checkTotalUnder100(double total, ArrayList<String> food){
        double total2, price2;
        
        price2= Double.parseDouble(food.get(1));
        total2= price2 + total;
        
        if(total2<100)
            return true;
        else
            return false;
    }
    
    public String giornoGiovanna(ArrayList<String> gg){
        //only visit 2 times a week
        Random rand= new Random();
        int i=0, foodNum, count=0;
        String food= "", recentFood= "";
        
        for(int k=6; k<gg.size(); k++){
            if(!gg.get(k).equals("no")){
                count++;
            }
        }
        
        if(count%2!=0){
            if(trattoriaFood.size()==1){  //only 1 option available
                food= trattoriaFood.get(0).get(0);
            }
            else{
                for(int j=6; j<gg.size(); j++){
                    if(gg.get(j).equals("no"))
                        continue;

                    recentFood= gg.get(j);
                }

                while(i!=1){
                    foodNum= rand.nextInt(trattoriaFood.size());
                    food= trattoriaFood.get(foodNum).get(0);

                    if(!food.equals(recentFood)){
                        i++;
                    }
                }
            }
        }
        else{
            food= "no";
        }
        return food;
    }
    
    //can use for both jotaro and jolyne
    public boolean canEatThereJolyneorJotaro(ArrayList<String> jolyne, int r){
        String recentFood= "";
        //return false- jolyne cannot eat there, jotaro can possibly eat there
        
        for(int i=6; i<jolyne.size(); i++){
            if(jolyne.get(i).equals("no"))
                continue;
            
            recentFood= jolyne.get(i);
        }
        
        if(r==1){
            for(int j=0; j<jadeGardenFood.size(); j++){
                if(recentFood.equals(jadeGardenFood.get(j).get(0)))
                    return false;
            }
        }
        else if(r==2){
            for(int j=0; j<cafeMagotsFood.size(); j++){
                if(recentFood.equals(cafeMagotsFood.get(j).get(0)))
                    return false;
            }
        }
        else if(r==3){
            for(int j=0; j<trattoriaFood.size(); j++){
                if(recentFood.equals(trattoriaFood.get(j).get(0)))
                    return false;
            }
        }
        else if(r==4){
            for(int j=0; j<liberrioFood.size(); j++){
                if(recentFood.equals(liberrioFood.get(j).get(0)))
                    return false;
            }
        }
        else{
            for(int j=0; j<savageGardenFood.size(); j++){
                if(recentFood.equals(savageGardenFood.get(j).get(0)))
                    return false;
            }
        }
        
        return true;
    }
    
    public String ifSizeis6(int r){
        Random rand= new Random();
        int foodNum;
        String food= "";
        
        switch(r){
            case 1:                
                foodNum= rand.nextInt(5);
                food= jadeGardenFood.get(foodNum).get(0);

                break;
            case 2:
                foodNum= rand.nextInt(5);
                food= cafeMagotsFood.get(foodNum).get(0);

                break;
            case 3:
                foodNum= rand.nextInt(4);
                food= trattoriaFood.get(foodNum).get(0);

                break;
            case 4:
                foodNum= rand.nextInt(6);
                food= liberrioFood.get(foodNum).get(0);

                break;
            case 5:
                foodNum= rand.nextInt(6);
                food= savageGardenFood.get(foodNum).get(0);

                break;
        }
        return food;
    }    
}
