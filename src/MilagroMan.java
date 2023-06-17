import java.util.*;

public class MilagroMan extends Main{
    private static int num;
    
    protected static void milagro(){
        Menu menu = new Menu();
        boolean going = true;
        while(going){
        num=1;
        System.out.println("Restaurant: "+currentLocation+" (Milagro Man Mode)");
        menu.MilagroDisplay();
        System.out.println("["+num+"] Modify Food Prices");
        num++;
        System.out.println("["+num+"] View Sales Information");
        num++;
        System.out.println("["+num+"] Exit Milagro Man");
        
        System.out.print("\n\nSelection: ");
        Scanner sc = new Scanner(System.in);
        int select;
        System.out.println("\n");
        while(true){
            System.out.print("Select (Numeric): ");
            if(sc.hasNextInt()){
                select = sc.nextInt();
                break;
            } else{
                System.out.println();
                System.out.println("*Invalid input. Please enter a numeric value.*\n");
                sc.next();
            }
        }
        if (select >= 1 && select<=num){
            System.out.println("========================================================================");
        }
        
        if (select==1){          
            menu.MilagroDisplay();
            menu.MilagroFoodMod();
                }
        else if (select == 2){
               boolean going1 = true;
               while(going1){
                   int MilagroNum = 1;
                        System.out.println("Restaurant: " + currentLocation);
                        System.out.println("Sales Information");
                        System.out.println("["+MilagroNum+"] View Sales");
                        MilagroNum++;
                        System.out.println("["+MilagroNum+"] View Aggregated Information");
                        MilagroNum++;
                            System.out.println("\t[A] Minimum Sales");
                            System.out.println("\t[B] Maximum Sales");
                            System.out.println("\t[C] Top k Highest Sales"); //top 3,5,10
                            System.out.println("\t[D] Total and Average Sales");
                            System.out.println("["+MilagroNum+"] Exit");
        
                            System.out.print("\n\nSelect: ");
                            Scanner sc5 = new Scanner(System.in);
                            int selection = sc5.nextInt();
                            if (selection >= 1 && selection<=MilagroNum){
                                System.out.println("========================================================================");
                            }
                switch(selection){
                    case 1:
                        System.out.print("\nEnter Day: ");
                        Scanner sc1 = new Scanner(System.in);
                        String day = sc1.nextLine();
                        System.out.println("Day "+day+" Sales");
                        if(currentLocation.getName().equals("Trattoria Trussardi")){
                            calculateTotalPriceMilagro(trattoriaMenu,milagroRecord, day);
                        }
                        if(currentLocation.getName().equals("Cafe Deux Magots")){
                            calculateTotalPriceMilagro(cafeMenu,milagroRecord, day);
                        }
                        if(currentLocation.getName().equals("Savage Garden")){
                            calculateTotalPriceMilagro(savageMenu,milagroRecord, day);
                        }
                        if(currentLocation.getName().equals("Jade Garden")){
                            calculateTotalPriceMilagro(jadeMenu,milagroRecord, day);
                        }
                        if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                            calculateTotalPriceMilagro(libeccioMenu,milagroRecord, day);
                        }

                        break;
            
                    case 2:
                        System.out.print("Select: ");
                        Scanner scOption = new Scanner(System.in);
                        String option = scOption.nextLine();
                        System.out.print("Enter Start Day: ");
                        Scanner scStart = new Scanner(System.in);
                        String startDay = scStart.nextLine();
                        System.out.print("Enter End Day: ");
                        Scanner scEnd = new Scanner(System.in);
                        String endDay = scEnd.nextLine();
                        System.out.println("========================================================================");
                        int selectedOption = option.charAt(0) - 'A';

                        if (selectedOption >= 0 && selectedOption < 4){

                            switch(selectedOption){
                                case 0:
                                    System.out.println("Minimum Sales for Day "+startDay+" to Day "+endDay+" is ");
                                        if(currentLocation.getName().equals("Trattoria Trussardi")){
                                            displayMinSalesMilagro(trattoriaMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Cafe Deux Magots")){
                                            displayMinSalesMilagro(cafeMenu,milagroRecord, startDay,endDay);
                                        }
                                        if(currentLocation.getName().equals("Savage Garden")){
                                            displayMinSalesMilagro(savageMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Jade Garden")){
                                            displayMinSalesMilagro(jadeMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                            displayMinSalesMilagro(libeccioMenu,milagroRecord, startDay, endDay);
                                        }
                                    break;
                                case 1:
                                    System.out.println("Maximum Sales for "+startDay+" to "+endDay+" is ");
                                        if(currentLocation.getName().equals("Trattoria Trussardi")){
                                            displayMaxSalesMilagro(trattoriaMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Cafe Deux Magots")){
                                            displayMaxSalesMilagro(cafeMenu,milagroRecord, startDay,endDay);
                                        }
                                        if(currentLocation.getName().equals("Savage Garden")){
                                            displayMaxSalesMilagro(savageMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Jade Garden")){
                                            displayMaxSalesMilagro(jadeMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                            displayMaxSalesMilagro(libeccioMenu,milagroRecord, startDay, endDay);
                                        }
                                    break;
                                case 2:
                                    System.out.println("[1] Top 1 Sale ");
                                    System.out.println("[2] Top 2 Sale ");
                                    System.out.println("[3] Top 3 Sale ");
                                    System.out.print("Please select : ");
                                    Scanner sc4 = new Scanner(System.in);
                                    int top = sc4.nextInt();
                                    System.out.println("Restaurant : "+currentLocation);
                                    System.out.println("Total and Average Sales (Day "+startDay+" - "+endDay+")");
                                        if(currentLocation.getName().equals("Trattoria Trussardi")){
                                            topSalesMilagro(trattoriaMenu,milagroRecord, startDay, endDay, top);
                                        }
                                        if(currentLocation.getName().equals("Cafe Deux Magots")){
                                            topSalesMilagro(cafeMenu,milagroRecord, startDay,endDay, top);
                                        }
                                        if(currentLocation.getName().equals("Savage Garden")){
                                            topSalesMilagro(savageMenu,milagroRecord, startDay, endDay, top);
                                        }
                                        if(currentLocation.getName().equals("Jade Garden")){
                                            topSalesMilagro(jadeMenu,milagroRecord, startDay, endDay, top);
                                        }
                                        if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                            topSalesMilagro(libeccioMenu,milagroRecord, startDay, endDay, top);
                                        }
                                    break;
                                case 3:
                                    System.out.println("Restaurant : "+currentLocation);
                                    System.out.println("Total and Average Sales (Day "+startDay+" - "+endDay+")");
                                        if(currentLocation.getName().equals("Trattoria Trussardi")){
                                            calculateAverageMilagro(trattoriaMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Cafe Deux Magots")){
                                            calculateAverageMilagro(cafeMenu,milagroRecord, startDay,endDay);
                                        }
                                        if(currentLocation.getName().equals("Savage Garden")){
                                            calculateAverageMilagro(savageMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Jade Garden")){
                                            calculateAverageMilagro(jadeMenu,milagroRecord, startDay, endDay);
                                        }
                                        if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                            calculateAverageMilagro(libeccioMenu,milagroRecord, startDay, endDay);
                                        }
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");

                                    }

                                }
                            case 3:
                                going1 = false;
                                break;

                            default: 
                                System.out.println("========================================================================");
                                System.out.println("Invalid choice. Please try again.");

                        }
                            System.out.println("========================================================================");
                    }

                        }
                        else if (select == 3){
                            going = false;
                                if(currentLocation.getName().equals("Trattoria Trussardi")){
                                    System.out.println("Exiting Milagro Man Mode");
                                    trattoriaMenu.clear();
                                    trattoriaMenu.addAll(trattoriaFood);
                                    MilagroPrices.clear();
                                    MilagroPrices.putAll(foodPrices);
                                }else if(currentLocation.getName().equals("Cafe Deux Magots")){
                                    System.out.println("Exiting Milagro Man Mode");
                                    cafeMenu.clear();
                                    cafeMenu.addAll(cafeMagotsFood);
                                    MilagroPrices.clear();
                                    MilagroPrices.putAll(foodPrices);
                                }else if(currentLocation.getName().equals("Savage Garden")){
                                    System.out.println("Exiting Milagro Man Mode");
                                    savageMenu.clear();
                                    savageMenu.addAll(savageGardenFood);
                                    MilagroPrices.clear();
                                    MilagroPrices.putAll(foodPrices);
                                }else if(currentLocation.getName().equals("Jade Garden")){
                                    System.out.println("Exiting Milagro Man Mode");
                                    jadeMenu.clear();
                                    jadeMenu.addAll(jadeGardenFood);
                                    MilagroPrices.clear();
                                    MilagroPrices.putAll(foodPrices);
                                }else if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                    System.out.println("Exiting Milagro Man Mode");
                                    libeccioMenu.clear();
                                    libeccioMenu.addAll(liberrioFood);
                                    MilagroPrices.clear();
                                    MilagroPrices.putAll(foodPrices);
                                }
                        }
                        else {
                                System.out.println("Invalid choice. Please try again.");

                        }
                    }


}
    public static void calculateTotalPriceMilagro(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String day) {
    HashMap<String, Integer> foodQuantity = new HashMap<>();
    double totalPrice = 0.0;

    for (ArrayList<Object> record : salesRecord) {
        String recordDay = (String) record.get(0); 
        String food = (String) record.get(2);
        String price = (String) record.get(3);
        if (recordDay.equals(day)) { 
            for (ArrayList<String> item : foodList) {
                String itemName = item.get(0);
                String itemPrice = item.get(1);

                if (food.equals(itemName)) {
                    foodQuantity.put(food, foodQuantity.getOrDefault(food, 0) + 1);

                    break;
                }
            }
        }
    }
    System.out.println("+-------------------------------------+----------+---------------+");
    System.out.println("| Food                                | Quantity |  Total Price  |");
    System.out.println("+-------------------------------------+----------+---------------+");

    for (ArrayList<String> item : foodList) {
        String itemName = item.get(0);
        int quantity = foodQuantity.getOrDefault(itemName, 0);
        double itemTotalPrice = Double.parseDouble(item.get(1)) * quantity;
        totalPrice += itemTotalPrice;

        System.out.printf("| %-35s | %-8d | $%-13.2f|\n", itemName, quantity, itemTotalPrice);
    }

    System.out.println("+-------------------------------------+----------+---------------+");
    System.out.printf("| Total Sales                         |          | $%-13.2f|\n", totalPrice);
    System.out.println("+-------------------------------------+----------+---------------+");
}

public static void displayMinSalesMilagro(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD) {
    HashMap<String, Integer> foodQuantity = new HashMap<>();
    double minPrice = Double.MAX_VALUE;
    String foodName="";
    for (ArrayList<Object> record : salesRecord) {
        String recordDay = String.valueOf(record.get(0));
        String food = (String) record.get(2);
        String price = (String) record.get(3);

        if (recordDay.compareTo(startD) >= 0 && recordDay.compareTo(endD) <= 0) {
            for (ArrayList<String> item : foodList) {
                String itemName = item.get(0);
                String itemPrice = item.get(1);

                if (food.equals(itemName)) {
                    foodQuantity.put(food, foodQuantity.getOrDefault(food, 0) + 1);


                    break;
                }
            }
        }
    }

    // Display the results in the desired format
    System.out.println("+-------------------------------------+--------------+---------------+");
    System.out.println("| Food                                |   Quantity   |  Total Price  |");
    System.out.println("+-------------------------------------+--------------+---------------+");

    for (ArrayList<String> item : foodList) {
        String itemName = item.get(0);
        int quantity = foodQuantity.getOrDefault(itemName, 0);
        double itemTotalPrice = Double.parseDouble(item.get(1)) * quantity;
        
        if (itemTotalPrice < minPrice) {
                        minPrice = itemTotalPrice;
                        foodName = itemName;
                    }
        System.out.printf("| %-35s | %-12d | $%-13.2f|\n", itemName, quantity, itemTotalPrice);
    }

    System.out.println("+-------------------------------------+--------------+---------------+");
    System.out.printf("| Minimum Sales : %-33s  | $%-13.2f|\n",foodName, minPrice);
    System.out.println("+----------------------------------------------------+---------------+");
}

public static void displayMaxSalesMilagro(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD) {
    HashMap<String, Integer> foodQuantity = new HashMap<>();
    double maxPrice = 0.0;
    String foodName="";
    for (ArrayList<Object> record : salesRecord) {
        String recordDay = String.valueOf(record.get(0));
        String food = (String) record.get(2);
        String price = (String) record.get(3);

        if (recordDay.compareTo(startD) >= 0 && recordDay.compareTo(endD) <= 0) {
            for (ArrayList<String> item : foodList) {
                String itemName = item.get(0);
                String itemPrice = item.get(1);

                if (food.equals(itemName)) {
                    foodQuantity.put(food, foodQuantity.getOrDefault(food, 0) + 1);


                    break;
                }
            }
        }
    }

    System.out.println("+-------------------------------------+--------------+---------------+");
    System.out.println("| Food                                |   Quantity   |  Total Price  |");
    System.out.println("+-------------------------------------+--------------+---------------+");

    for (ArrayList<String> item : foodList) {
        String itemName = item.get(0);
        int quantity = foodQuantity.getOrDefault(itemName, 0);
        double itemTotalPrice = Double.parseDouble(item.get(1)) * quantity;
        
        if (itemTotalPrice > maxPrice) {
                        maxPrice = itemTotalPrice;
                        foodName = itemName;
                    }
        System.out.printf("| %-35s | %-12d | $%-13.2f|\n", itemName, quantity, itemTotalPrice);
    }

    System.out.println("+-------------------------------------+--------------+---------------+");
    System.out.printf("| Maximum Sales : %-33s  | $%-13.2f|\n",foodName, maxPrice);
    System.out.println("+----------------------------------------------------+---------------+");
}
public static void calculateAverageMilagro(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD) {
    HashMap<String, Integer> foodQuantity = new HashMap<>();
    double totalPrice = 0.0;
    int totalDays = 0;
    totalDays = Integer.parseInt(endD)-Integer.parseInt(startD)+1;

    for (ArrayList<Object> record : salesRecord) {
        String recordDay = (String) record.get(0);
        String food = (String) record.get(2);
        String price = (String) record.get(3);
        if (recordDay.compareTo(startD) >= 0 && recordDay.compareTo(endD) <= 0) {
            for (ArrayList<String> item : foodList) {
                String itemName = item.get(0);
                String itemPrice = item.get(1);

                if (food.equals(itemName)) {
                    foodQuantity.put(food, foodQuantity.getOrDefault(food, 0) + 1);

                    totalPrice += Double.parseDouble(itemPrice);
                    break;
                }
            }
        }
    }

    System.out.println("+-------------------------------------+----------+---------------+");
    System.out.println("| Food                                | Quantity |  Total Price  |");
    System.out.println("+-------------------------------------+----------+---------------+");

    for (ArrayList<String> item : foodList) {
        String itemName = item.get(0);
        int quantity = foodQuantity.getOrDefault(itemName, 0);
        double itemTotalPrice = Double.parseDouble(item.get(1)) * quantity;

        System.out.printf("| %-35s | %-8d | $%-13.2f|\n", itemName, quantity, itemTotalPrice);
    }

    System.out.println("+-------------------------------------+----------+---------------+");
    System.out.printf("|                                    Total Sales | $%-13.2f|\n", totalPrice);
    System.out.println("+------------------------------------------------+---------------+");

    double averageSalesPerDay = totalPrice / totalDays;
    System.out.printf("|                                  Average Sales | $%-13.2f|\n",averageSalesPerDay);
    System.out.println("+------------------------------------------------+---------------+");

}
public static void topSalesMilagro(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD, int top){
    HashMap<String, Integer> foodQuantity = new HashMap<>();
    double totalPrice = 0.0;
    int totalDays = 0;
    String foodName1 ="";
    String foodName2 = "";
    String foodName3 = "";
    double price1 = 0.0;
    double price2 = 0.0;
    double price3 = 0.0;
    
    totalDays = Integer.parseInt(endD)-Integer.parseInt(startD)+1;

    // Count the quantity of each food item and calculate the total price
    for (ArrayList<Object> record : salesRecord) {
        String recordDay = (String) record.get(0);
        String food = (String) record.get(2);
        String price = (String) record.get(3);
        if (recordDay.compareTo(startD) >= 0 && recordDay.compareTo(endD) <= 0) { // Check if the day is within the range
            for (ArrayList<String> item : foodList) {
                String itemName = item.get(0);
                String itemPrice = item.get(1);

                if (food.equals(itemName)) {
                    // Increment the quantity or initialize it to 1
                    foodQuantity.put(food, foodQuantity.getOrDefault(food, 0) + 1);

                    totalPrice += Double.parseDouble(itemPrice);
                    break;
                }
            }
        }
    }

    System.out.println("+-------------------------------------+--------------+---------------+");
    System.out.println("| Food                                |   Quantity   |  Total Price  |");
    System.out.println("+-------------------------------------+--------------+---------------+");

    for (ArrayList<String> item : foodList) {
        String itemName = item.get(0);
        int quantity = foodQuantity.getOrDefault(itemName, 0);
        double itemTotalPrice = Double.parseDouble(item.get(1)) * quantity;
        
        if (itemTotalPrice > price1){
            foodName3 = foodName2;
            price3 = price1;
            foodName2 = foodName1;
            price2 = price1;
            foodName1 = itemName;
            price1 = itemTotalPrice;
        }
        else if (itemTotalPrice < price1 && itemTotalPrice > price2){
            foodName3 = foodName2;
            price3 = price2;
            foodName2 = itemName;
            price2 = itemTotalPrice;
        }
        else if (itemTotalPrice < price2 && itemTotalPrice > price3){
            foodName3 = itemName;
            price3 = itemTotalPrice;
        }
        System.out.printf("| %-35s | %-12d | $%-13.2f|\n", itemName, quantity, itemTotalPrice);
    }
    if (top == 1){
        System.out.println("+-------------------------------------+--------------+---------------+");;
        System.out.printf("| Top 1 Sales : %-35s  | $%-13.2f|\n",foodName1, price1);
        System.out.println("+----------------------------------------------------+---------------+");;
    }
    else if (top ==2){
    System.out.println("+-------------------------------------+--------------+---------------+");
        System.out.printf("| Top 1 Sales : %-35s  | $%-13.2f|\n",foodName1, price1);
        System.out.printf("| Top 2 Sales : %-35s  | $%-13.2f|\n",foodName2, price2);
        System.out.println("+----------------------------------------------------+---------------+");;
    }
    else if (top ==3){
    System.out.println("+-------------------------------------+--------------+---------------+");
        System.out.printf("| Top 1 Sales : %-35s  | $%-13.2f|\n",foodName1, price1);
        System.out.printf("| Top 2 Sales : %-35s  | $%-13.2f|\n",foodName2, price2);
        System.out.printf("| Top 2 Sales : %-35s  | $%-13.2f|\n",foodName3, price3);
        System.out.println("+----------------------------------------------------+---------------+");;
    }
}
}

