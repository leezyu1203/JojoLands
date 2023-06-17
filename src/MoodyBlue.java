import java.util.Scanner;
import java.util.*;

public class MoodyBlue extends Main{
    private static int num;
    
    protected static void moody(){
        boolean going = true;
        while(going){
            num = 1;
            System.out.println("Restaurant: " + currentLocation);
            System.out.println("Sales Information");
            System.out.println("["+num+"] View Sales");
            num++;
            System.out.println("["+num+"] View Aggregated Information");
            num++;
                System.out.println("\t[A] Minimum Sales");
                System.out.println("\t[B] Maximum Sales");
                System.out.println("\t[C] Top k Highest Sales"); //top 3,5,10
                System.out.println("\t[D] Total and Average Sales");
            System.out.println("["+num+"] Exit");

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
            switch(select){
                case 1:
                    System.out.print("\nEnter Day: ");
                    Scanner sc1 = new Scanner(System.in);
                    String day = sc1.nextLine();
                    System.out.println("Day "+day+" Sales");
                    if(currentLocation.getName().equals("Trattoria Trussardi")){
                        calculateTotalPrice(trattoriaFood,salesRecord, day);
                    }
                    if(currentLocation.getName().equals("Cafe Deux Magots")){
                        calculateTotalPrice(cafeMagotsFood,salesRecord, day);
                    }
                    if(currentLocation.getName().equals("Savage Garden")){
                        calculateTotalPrice(savageGardenFood,salesRecord, day);
                    }
                    if(currentLocation.getName().equals("Jade Garden")){
                        calculateTotalPrice(jadeGardenFood,salesRecord, day);
                    }
                    if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                        calculateTotalPrice(liberrioFood,salesRecord, day);
                    }
                    break;

                case 2:
                    System.out.print("Select (Character): ");
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
                                    displayMinSales(trattoriaFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Cafe Deux Magots")){
                                    displayMinSales(cafeMagotsFood,salesRecord, startDay,endDay);
                                }
                                if(currentLocation.getName().equals("Savage Garden")){
                                    displayMinSales(savageGardenFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Jade Garden")){
                                    displayMinSales(jadeGardenFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                    displayMinSales(liberrioFood,salesRecord, startDay, endDay);
                                }
                                break;
                            case 1:
                                System.out.println("Maximum Sales for "+startDay+" to "+endDay+" is ");
                                if(currentLocation.getName().equals("Trattoria Trussardi")){
                                    displayMaxSales(trattoriaFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Cafe Deux Magots")){
                                    displayMaxSales(cafeMagotsFood,salesRecord, startDay,endDay);
                                }
                                if(currentLocation.getName().equals("Savage Garden")){
                                    displayMaxSales(savageGardenFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Jade Garden")){
                                    displayMaxSales(jadeGardenFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                    displayMaxSales(liberrioFood,salesRecord, startDay, endDay);
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
                                    topSales(trattoriaFood,salesRecord, startDay, endDay, top);
                                }
                                if(currentLocation.getName().equals("Cafe Deux Magots")){
                                    topSales(cafeMagotsFood,salesRecord, startDay,endDay, top);
                                }
                                if(currentLocation.getName().equals("Savage Garden")){
                                    topSales(savageGardenFood,salesRecord, startDay, endDay, top);
                                }
                                if(currentLocation.getName().equals("Jade Garden")){
                                    topSales(jadeGardenFood,salesRecord, startDay, endDay, top);
                                }
                                if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Pasionne Restaurant")){
                                    topSales(liberrioFood,salesRecord, startDay, endDay, top);
                                }
                                break;
                            case 3:
                                System.out.println("Restaurant : "+currentLocation);
                                System.out.println("Total and Average Sales (Day "+startDay+" - "+endDay+")");
                                if(currentLocation.getName().equals("Trattoria Trussardi")){
                                    calculateAverage(trattoriaFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Cafe Deux Magots")){
                                    calculateAverage(cafeMagotsFood,salesRecord, startDay,endDay);
                                }
                                if(currentLocation.getName().equals("Savage Garden")){
                                    calculateAverage(savageGardenFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Jade Garden")){
                                    calculateAverage(jadeGardenFood,salesRecord, startDay, endDay);
                                }
                                if(currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")){
                                    calculateAverage(liberrioFood,salesRecord, startDay, endDay);
                                }
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");

                        }

                    }
                case 3:
                    going = false;
                    break;

                default:
                    System.out.println("========================================================================");
                    System.out.println("Invalid choice. Please try again.");

            } System.out.println("========================================================================");
        }
    }
    public static void calculateTotalPrice(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String day) {
        HashMap<String, Integer> foodQuantity = new HashMap<>();
        double totalPrice = 0.0;

        for (ArrayList<Object> record : salesRecord) {
            String recordDay = record.get(0).toString();
            String food = record.get(2).toString();
            String price = record.get(3).toString();

            // Check if the record day matches the specified day
            if (recordDay.equals(day)) {
                foodQuantity.put(food, foodQuantity.getOrDefault(food, 0) + 1);
            }
        }

        // Display the results in the desired format
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

    public static void displayMinSales(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD) {
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

    public static void displayMaxSales(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD) {
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
    public static void calculateAverage(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD) {
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
    public static void topSales(LinkedList<ArrayList<String>> foodList, LinkedList<ArrayList<Object>> salesRecord, String startD, String endD, int top){
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

