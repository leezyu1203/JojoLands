import java.util.*;

public class Menu extends Main{

    public static void display(){
        if(currentLocation.getName().equals("Trattoria Trussardi")){
            displayMenu(trattoriaFood);
        }
        if(currentLocation.getName().equals("Cafe Deux Magots")){
            displayMenu(cafeMagotsFood);
        }
        if(currentLocation.getName().equals("Savage Garden")){
            displayMenu(savageGardenFood);
        }
        if(currentLocation.getName().equals("Jade Garden")){
            displayMenu(jadeGardenFood);
        }
        if(currentLocation.getName().equals("Libeccio")){
            displayMenu(liberrioFood);
        }
    }
    
        public static void MilagroDisplay(){
        if(currentLocation.getName().equals("Trattoria Trussardi")){
            displayMenu(trattoriaMenu);
        }
        if(currentLocation.getName().equals("Cafe Deux Magots")){
            displayMenu(cafeMenu);
        }
        if(currentLocation.getName().equals("Savage Garden")){
            displayMenu(savageMenu);
        }
        if(currentLocation.getName().equals("Jade Garden")){
            displayMenu(jadeMenu);
        }
        if(currentLocation.getName().equals("Libeccio")){
            displayMenu(libeccioMenu);
        }
    }
    public static void displayMenu(LinkedList<ArrayList<String>> menu) {
        System.out.println("+---------------------------------------+---------+");
        System.out.format("| %-37s | %-7s |\n", "Food", "Price");
        System.out.println("+---------------------------------------+---------+");

        for (ArrayList<String> menuItem : menu) {
            String foodName = menuItem.get(0);
            double price = Double.parseDouble(menuItem.get(1));
            System.out.format("| %-37s | $%.2f  |\n", foodName, price);
        }

        System.out.println("+---------------------------------------+---------+");
    }
    
    public static void addFood(){
        System.out.print("Please enter the food name : ");
        Scanner sc = new Scanner(System.in);
        String foodNewName = sc.nextLine();
        System.out.print("Please enter the food price : $");
        Scanner sc1 = new Scanner(System.in);     
        double foodNewPrice = sc1.nextDouble();
        
        ArrayList<String> newFood = new ArrayList<>();
        newFood.add(foodNewName);
        newFood.add(Double.toString(foodNewPrice));
        
        if(currentLocation.getName().equals("Trattoria Trussardi")){
            trattoriaFood.add(newFood);
            foodPrices.put(foodNewName,foodNewPrice);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            trattoriaMenu.clear();
            trattoriaMenu.addAll(trattoriaFood);
            System.out.println("Menu updated successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Cafe Deux Magots")){
            cafeMagotsFood.add(newFood);
            foodPrices.put(foodNewName,foodNewPrice);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            cafeMenu.clear();
            cafeMenu.addAll(cafeMagotsFood);
            System.out.println("Menu updated successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Jade Garden")){
            jadeGardenFood.add(newFood);
            foodPrices.put(foodNewName,foodNewPrice);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            jadeMenu.clear();
            jadeMenu.addAll(jadeGardenFood);
            System.out.println("Menu updated successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Savage Garden")){
            savageGardenFood.add(newFood);
            foodPrices.put(foodNewName,foodNewPrice);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            savageMenu.clear();
            savageMenu.addAll(savageGardenFood);
            System.out.println("Menu updated successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Libeccio")){
            liberrioFood.add(newFood);
            foodPrices.put(foodNewName,foodNewPrice);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            libeccioMenu.clear();
            libeccioMenu.addAll(liberrioFood);
            System.out.println("Menu updated successfully !");
            System.out.println("========================================================================");
        }
    }
    
    public static void removeFood(){
        System.out.print("Please enter the food name : ");
        Scanner sc = new Scanner(System.in);
        String foodName = sc.nextLine();
        
        if(currentLocation.getName().equals("Trattoria Trussardi")){
            trattoriaFood.removeIf(item -> item.get(0).equals(foodName));
            foodPrices.remove(foodName);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            trattoriaMenu.clear();
            trattoriaMenu.addAll(trattoriaFood);
            System.out.println("Food removed successfully !");
            System.out.println("========================================================================");
        }

        if(currentLocation.getName().equals("Cafe Deux Magots")){
            cafeMagotsFood.removeIf(item -> item.get(0).equals(foodName));
            foodPrices.remove(foodName);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            cafeMenu.clear();
            cafeMenu.addAll(cafeMagotsFood);
            System.out.println("Food removed successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Jade Garden")){
            jadeGardenFood.removeIf(item -> item.get(0).equals(foodName));
            foodPrices.remove(foodName);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            jadeMenu.clear();
            jadeMenu.addAll(jadeGardenFood);
            System.out.println("Food removed successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Savage Garden")){
            savageGardenFood.removeIf(item -> item.get(0).equals(foodName));
            foodPrices.remove(foodName);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            savageMenu.clear();
            savageMenu.addAll(savageGardenFood);
            System.out.println("Food removed successfully !");
            System.out.println("========================================================================");
        }
        if(currentLocation.getName().equals("Libeccio")){
            liberrioFood.removeIf(item -> item.get(0).equals(foodName));
            foodPrices.remove(foodName);
            MilagroPrices.clear();
            MilagroPrices.putAll(foodPrices);
            libeccioMenu.clear();
            libeccioMenu.addAll(liberrioFood);
            System.out.println("Food removed successfully !");
            System.out.println("========================================================================");
        }
    }
    
    public static void modifyFood(){
        System.out.println("[1] Change food name");
        System.out.println("[2] Change food price");
        System.out.print("Please enter the option :");
        Scanner sc2 = new Scanner(System.in);
        int selection = sc2.nextInt();
        if (selection == 1 ){
            System.out.print("Please enter the food name : ");
            Scanner sc3 = new Scanner(System.in);
            String foodModName = sc3.nextLine();
            System.out.print("Please enter the new food name : ");
            Scanner sc4 = new Scanner(System.in);
            String foodNewName = sc4.nextLine();

            if(currentLocation.getName().equals("Trattoria Trussardi")){
                if (foodPrices.containsKey(foodModName)){
                    Object price = foodPrices.get(foodModName);
                    foodPrices.remove(foodModName);
                    foodPrices.put(foodNewName,price);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : trattoriaFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(0,foodNewName);
                        trattoriaMenu.clear();
                        trattoriaMenu.addAll(trattoriaFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break;
                    }
                }
            }
            if(currentLocation.getName().equals("Savage Garden")){
                if (foodPrices.containsKey(foodModName)){
                    Object price = foodPrices.get(foodModName);
                    foodPrices.remove(foodModName);
                    foodPrices.put(foodNewName,price);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : savageGardenFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(0,foodNewName);
                        savageMenu.clear();
                        savageMenu.addAll(savageGardenFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break;  
                    }
                }
            }
            if(currentLocation.getName().equals("Jade Garden")){
                if (foodPrices.containsKey(foodModName)){
                    Object price = foodPrices.get(foodModName);
                    foodPrices.remove(foodModName);
                    foodPrices.put(foodNewName,price);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : jadeGardenFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(0,foodNewName);
                        jadeMenu.clear();
                        jadeMenu.addAll(jadeGardenFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break;  
                    }
                }
            }
            if(currentLocation.getName().equals("Libeccio")){
                if (foodPrices.containsKey(foodModName)){
                    Object price = foodPrices.get(foodModName);
                    foodPrices.remove(foodModName);
                    foodPrices.put(foodNewName,price);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : liberrioFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(0,foodNewName);
                        libeccioMenu.clear();
                        libeccioMenu.addAll(liberrioFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
            if(currentLocation.getName().equals("Cafe Deux Magots")){
                if (foodPrices.containsKey(foodModName)){
                    Object price = foodPrices.get(foodModName);
                    foodPrices.remove(foodModName);
                    foodPrices.put(foodNewName,price);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : cafeMagotsFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(0,foodNewName);
                        cafeMenu.clear();
                        cafeMenu.addAll(cafeMagotsFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
        }
        else if ( selection == 2){
            System.out.print("Please enter the food name : ");
            Scanner sc = new Scanner(System.in);
            String foodModName = sc.nextLine();
            System.out.print("Please enter the food price : $");
            Scanner sc1 = new Scanner(System.in);
            double foodModPrice = sc1.nextDouble();

            if(currentLocation.getName().equals("Trattoria Trussardi")){
                if (foodPrices.containsKey(foodModName)){
                    foodPrices.put(foodModName,foodModPrice);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : trattoriaFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,Double.toString(foodModPrice));
                        System.out.println("Food updated successfully !");
                        trattoriaMenu.clear();
                        trattoriaMenu.addAll(trattoriaFood);
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
            if(currentLocation.getName().equals("Savage Garden")){
                if (foodPrices.containsKey(foodModName)){
                    foodPrices.put(foodModName,foodModPrice);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : savageGardenFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,Double.toString(foodModPrice));
                        savageMenu.clear();
                        savageMenu.addAll(savageGardenFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
            if(currentLocation.getName().equals("Jade Garden")){
                if (foodPrices.containsKey(foodModName)){
                    foodPrices.put(foodModName,foodModPrice);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : jadeGardenFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,Double.toString(foodModPrice));
                        jadeMenu.clear();
                        jadeMenu.addAll(jadeGardenFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
            if(currentLocation.getName().equals("Libeccio")){
                if (foodPrices.containsKey(foodModName)){
                    foodPrices.put(foodModName,foodModPrice);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : liberrioFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,Double.toString(foodModPrice));
                        libeccioMenu.clear();
                        libeccioMenu.addAll(liberrioFood);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
            if(currentLocation.getName().equals("Cafe Deux Magots")){
                if (foodPrices.containsKey(foodModName)){
                    foodPrices.put(foodModName,foodModPrice);
                    MilagroPrices.clear();
                    MilagroPrices.putAll(foodPrices);
                }
                for (ArrayList<String> item : cafeMagotsFood) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,Double.toString(foodModPrice));
                        System.out.println("Food updated successfully !");
                        cafeMenu.clear();
                        cafeMenu.addAll(cafeMagotsFood);
                        System.out.println("========================================================================");
                        break; 
                    }
                }
            }
        }
    }
    
    public static void MilagroFoodMod(){
            System.out.print("Please enter the food name : ");
            Scanner sc = new Scanner(System.in);
            String foodModName = sc.nextLine();
            System.out.print("Please enter the food price : $");
            Scanner sc1 = new Scanner(System.in);
            String foodModPrice = sc1.nextLine();
            System.out.print("Please enter Start Day : ");
            Scanner sc2 = new Scanner(System.in);
            String startDay = sc2.nextLine();
            System.out.print("Please enter End Day : ");
            Scanner sc3 = new Scanner(System.in);
            String endDay = sc3.nextLine();

            if(currentLocation.getName().equals("Trattoria Trussardi")){
                if (MilagroPrices.containsKey(foodModName)){
                    MilagroPrices.put(foodModName,foodModPrice);
                }
                for (ArrayList<String> item : trattoriaMenu) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,foodModPrice);
                        milagroRecord.clear();
                        milagroPriceSet(startDay,endDay);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
                
                
                
            }
            if(currentLocation.getName().equals("Savage Garden")){
                if (MilagroPrices.containsKey(foodModName)){
                    MilagroPrices.put(foodModName,foodModPrice);
                }
                for (ArrayList<String> item : savageMenu) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,foodModPrice);
                        milagroRecord.clear();
                        milagroPriceSet(startDay,endDay);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
                
                
                
            }
            if(currentLocation.getName().equals("Jade Garden")){
                if (MilagroPrices.containsKey(foodModName)){
                    MilagroPrices.put(foodModName,foodModPrice);
                }
                for (ArrayList<String> item : jadeMenu) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,foodModPrice);
                        milagroRecord.clear();
                        milagroPriceSet(startDay,endDay);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
                
                
                
            }
            if(currentLocation.getName().equals("Libeccio")){
                if (MilagroPrices.containsKey(foodModName)){
                    MilagroPrices.put(foodModName,foodModPrice);
                }
                for (ArrayList<String> item : libeccioMenu) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,foodModPrice);
                        milagroRecord.clear();
                        milagroPriceSet(startDay,endDay);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break; 
                    }
                }
                
                
                
            }
            if(currentLocation.getName().equals("Cafe Deux Magots")){
                if (MilagroPrices.containsKey(foodModName)){
                    MilagroPrices.put(foodModName,foodModPrice);
                }
                for (ArrayList<String> item : cafeMenu) {
                    if (item.get(0).equals(foodModName)) {
                        item.set(1,foodModPrice);
                        milagroRecord.clear();
                        milagroPriceSet(startDay,endDay);
                        System.out.println("Food updated successfully !");
                        System.out.println("========================================================================");
                        break;  
                    }
                }
            }
        }  
    public static void milagroPriceSet(String startDay, String endDay) {
        for (ArrayList<Object> row : extractRecord) {
            String recordDay = (String) row.get(0);
            String food = (String) row.get(2);
            String price = (String) MilagroPrices.getOrDefault(food, "Price not found");

            if (recordDay.compareTo(startDay) >= 0 && recordDay.compareTo(endDay) <= 0) {
                ArrayList<Object> extractedRecord = new ArrayList<>();
                extractedRecord.add(row.get(0));
                extractedRecord.add(row.get(1));
                extractedRecord.add(food);
                extractedRecord.add(price);
                milagroRecord.add(extractedRecord);
            }
        }
}

}