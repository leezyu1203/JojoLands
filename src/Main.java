import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {
    @SuppressWarnings("unchecked")
    static HashMap<Object, Object> foodPrices = new HashMap<>();
    static HashMap<Object, Object> MilagroPrices = new HashMap<>();
    
    protected static Location townHall = new Location("Town Hall");
    protected static int currentDay = 1;
    protected static Location currentLocation;
    protected static visitedLocations<Location> visit = new visitedLocations<>();
    protected static List<Location> locations= new ArrayList<>();
    protected static int mapSelection = 0;
    private static ArrayList<String> temp = new ArrayList<>();
    static boolean isLoad = false;
    
    public static void main(String[] args) {
        System.out.println("Welcome to the fantastical realm of JOJOLands.");
        System.out.println("[1] Start Game\n[2] Load Game\n[3] Exit\n");
        System.out.print("Select: ");
        Scanner sc = new Scanner(System.in);
        int keyIn = sc.nextInt();
        if(keyIn == 3){
            System.out.println("Exiting the Game...");
            System.exit(0);
        }
        if (keyIn == 2) {
            sc.nextLine();
            System.out.print("Enter the path of your save file: ");
            String fileName = sc.nextLine();
            setLoadedGame(fileName);
            isLoad = true;
        }
        System.out.println("========================================================================");
        if (mapSelection == 0) {
            System.out.println("Select a map: ");
            System.out.println("[1] Default Map\n[2] Parallel Map\n[3] Alternate Map\n");
            System.out.print("Select: ");
            mapSelection = sc.nextInt();
            System.out.println("========================================================================");
        }
        switch (mapSelection) {
            case 1 -> setDefault();
            case 2 -> setParallel();
            case 3 -> setAlternate();
        }
        System.out.println("It's Day " + (currentDay) + " (" + getDayOfWeek(currentDay) + ") of our journey in JOJOLands!");
        currentLocation = townHall;
        setMenu();
        if (isLoad) {
            for (int i = 0; i < temp.size(); i++) {
                for(int j = 0; j < locations.size(); j++){
                    if(locations.get(j).isLoc(temp.get(i))){
                        visit.addLast(locations.get(j));
                        break;
                    }
                }
            }
        } else visit.addFirst(townHall);
        setPrice();

        while (true) {
            System.out.println("Current location: " + currentLocation.getName());
            if (currentLocation.getName().equals("Morioh Grand Hotel")) {
                MoriohGrandHotel hotel = new MoriohGrandHotel();
                hotel.morioh();
            } else if (currentLocation.getName().equals("Trattoria Trussardi")) {
                TrattoriaTrussardi trattoria = new TrattoriaTrussardi();
                trattoria.trattoria();
            } else if (currentLocation.getName().equals("Town Hall")) {
                TownHall hall = new TownHall();
                hall.hall();
            } else if (currentLocation.getName().equals("Cafe Deux Magots")) {
                CafeDeuxMagots cafe = new CafeDeuxMagots();
                cafe.cafe();
            } else if (currentLocation.getName().equals("Polnareff Land")) {
                PolnareffLand polnareff = new PolnareffLand();
                polnareff.polnareff();
            } else if (currentLocation.getName().equals("Savage Garden")) {
                SavageGarden savage = new SavageGarden();
                savage.savage();
            } else if (currentLocation.getName().equals("Green Dolphin Street Prison")) {
                GreenDolphinStreetPrison prison = new GreenDolphinStreetPrison();
                prison.greendolphin(locations);
            } else if (currentLocation.getName().equals("DIO's Mansion")) {
                DiosMansion dios = new DiosMansion();
                dios.dio();
            } else if (currentLocation.getName().equals("Jade Garden")) {
                JadeGarden jade = new JadeGarden();
                jade.jade();
            } else if (currentLocation.getName().equals("Joestar Mansion")) {
                JoestarMansion joe = new JoestarMansion();
                joe.joestar();
            } else if (currentLocation.getName().equals("Libeccio") || currentLocation.getName().equals("Passione Restaurant")) {
                Libeccio lib = new Libeccio();
                lib.libeccio();
            } else if (currentLocation.getName().equals("San Giorgio Maggiore")) {
                SanGiorgioMaggiore San = new SanGiorgioMaggiore();
                San.sangiorgio();
            } else if (currentLocation.getName().equals("Vineyard")) {
                Vineyard vin = new Vineyard();
                vin.vineyard();
            } else if (currentLocation.getName().equals("Angelo Rock")) {
                AngeloRock angelo = new AngeloRock();
                angelo.angeloRock();
            }
        }
    }
    
    protected static String getDayOfWeek(int day) {
        String[] daysOfWeek = {"Saturday","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int index = day % 7; // Wrap around to the beginning of the week
        return daysOfWeek[index];
    }

    static Location cafe = new Location("Cafe Deux Magots");
    static Location jadeGarden = new Location("Jade Garden");
    static Location moriohGrandHotel = new Location("Morioh Grand Hotel");
    static Location polnareff = new Location("Polnareff Land");
    static Location savage = new Location("Savage Garden");
    static Location joestar = new Location("Joestar Mansion");
    static Location vineyard = new Location("Vineyard");
    static Location libeccio = new Location("Libeccio");
    static Location dio = new Location("DIO's Mansion");
    static Location san = new Location("San Giorgio Maggiore");
    static Location trattoria = new Location("Trattoria Trussardi");
    static Location prison = new Location("Green Dolphin Street Prison");
    static Location angeloRock = new Location("Angelo Rock");
    static Location passione = new Location("Passione Restaurant");

    static LinkedList<ArrayList<String>> cafeMenu = new LinkedList<>();
    static LinkedList<ArrayList<String>> jadeMenu = new LinkedList<>();
    static LinkedList<ArrayList<String>> savageMenu = new LinkedList<>();
    static LinkedList<ArrayList<String>> libeccioMenu = new LinkedList<>();
    static LinkedList<ArrayList<String>> trattoriaMenu = new LinkedList<>();
    static LinkedList<ArrayList<String>> cafeMagotsFood = new LinkedList<>();
    static LinkedList<ArrayList<String>> jadeGardenFood = new LinkedList<>();
    static LinkedList<ArrayList<String>> savageGardenFood = new LinkedList<>();
    static LinkedList<ArrayList<String>> liberrioFood = new LinkedList<>();
    static LinkedList<ArrayList<String>> trattoriaFood = new LinkedList<>();
    static LinkedList<ArrayList<String>> foodList = new LinkedList<>();

    static LinkedList<ArrayList<String>> rawRecord = new LinkedList<>();
    static LinkedList<ArrayList<Object>> extractRecord = new LinkedList<>();
    static LinkedList<ArrayList<Object>> salesRecord = new LinkedList<>();
    static LinkedList<ArrayList<Object>> milagroRecord = new LinkedList<>();
    private static void setDefault(){
        connectLocations(townHall, cafe,4);
        connectLocations(townHall, jadeGarden,5);
        connectLocations(townHall, moriohGrandHotel,5);
        connectLocations(moriohGrandHotel, trattoria,6);
        connectLocations(moriohGrandHotel, jadeGarden,3);
        connectLocations(jadeGarden, san,2);
        connectLocations(jadeGarden, cafe,3);
        connectLocations(jadeGarden, joestar,2);
        connectLocations(cafe, polnareff,4);
        connectLocations(cafe, savage,4);
        connectLocations(polnareff, savage,6);
        connectLocations(savage, joestar,4);
        connectLocations(savage, vineyard,8);
        connectLocations(joestar, vineyard,3);
        connectLocations(joestar, libeccio,6);
        connectLocations(san, trattoria,3);
        connectLocations(san, libeccio,4);
        connectLocations(trattoria, prison,6);
        connectLocations(libeccio, vineyard,6);
        connectLocations(libeccio, dio,2);
        connectLocations(libeccio, prison,3);
        connectLocations(vineyard, dio,3);
        connectLocations(dio, angeloRock,3);
        connectLocations(prison, angeloRock,2);
    }

    private static void setParallel(){
        connectLocations(townHall,trattoria,6);
        connectLocations(townHall,cafe,4);
        connectLocations(townHall,vineyard,3);
        connectLocations(townHall,libeccio,2);
        connectLocations(libeccio,vineyard,3);
        connectLocations(trattoria,dio,4);
        connectLocations(trattoria,angeloRock,3);
        connectLocations(trattoria,joestar,5);
        connectLocations(angeloRock,dio,1);
        connectLocations(angeloRock,prison,8);
        connectLocations(dio,prison,6);
        connectLocations(joestar,moriohGrandHotel,4);
        connectLocations(joestar,jadeGarden,3);
        connectLocations(joestar,san,5);
        connectLocations(san,savage,6);
        connectLocations(jadeGarden,savage,4);
        connectLocations(moriohGrandHotel,cafe,6);
        connectLocations(jadeGarden,cafe,3);
        connectLocations(savage,cafe,5);
        connectLocations(cafe,polnareff,2);
    }

    private static void setAlternate(){
        connectLocations(townHall,prison,3);
        connectLocations(townHall,moriohGrandHotel,2);
        connectLocations(townHall,passione,7);
        connectLocations(moriohGrandHotel,san,3);
        connectLocations(moriohGrandHotel,joestar,4);
        connectLocations(moriohGrandHotel,prison,2);
        connectLocations(san,savage,6);
        connectLocations(savage,vineyard,4);
        connectLocations(vineyard,cafe,4);
        connectLocations(cafe,passione,4);
        connectLocations(cafe,dio,1);
        connectLocations(passione,trattoria,1);
        connectLocations(passione,angeloRock,6);
        connectLocations(passione,dio,2);
        connectLocations(trattoria,joestar,5);
        connectLocations(trattoria,prison,4);
        connectLocations(angeloRock,jadeGarden,1);
        connectLocations(angeloRock,polnareff,2);
        connectLocations(polnareff,jadeGarden,2);
        connectLocations(dio,polnareff,2);
    }
    
    private static void connectLocations(Location location1, Location location2, int distance) {
        location1.addAdjacent(location2, distance);
        location2.addAdjacent(location1, distance);
	    if(!locations.contains(location1)) locations.add(location1);
        if(!locations.contains(location2)) locations.add(location2);
    }
    private static void setMenu(){
        cafeMagotsFood.add(new ArrayList<>(Arrays.asList("Sampling Matured Cheese Platter", "23.00")));
        cafeMagotsFood.add(new ArrayList<>(Arrays.asList("Spring Lobster Salad", "35.00")));
        cafeMagotsFood.add(new ArrayList<>(Arrays.asList("Spring Organic Omelette", "23.00")));
        cafeMagotsFood.add(new ArrayList<>(Arrays.asList("Truffle-flavoured Poultry Supreme", "34.00")));
        cafeMagotsFood.add(new ArrayList<>(Arrays.asList("White Asparagus", "26.00")));
        
        jadeGardenFood.add(new ArrayList<>(Arrays.asList("Braised Chicken in Black Bean Sauce", "15.00")));
        jadeGardenFood.add(new ArrayList<>(Arrays.asList("Braised Goose Web with Vermicelli", "21.00")));
        jadeGardenFood.add(new ArrayList<>(Arrays.asList("Deep-fried Hiroshima Oyster", "17.00")));
        jadeGardenFood.add(new ArrayList<>(Arrays.asList("Poached Tofu with Dried Shrimps", "12.00")));
        jadeGardenFood.add(new ArrayList<>(Arrays.asList("Scrambled Egg White with Milk", "10.00")));
        
        liberrioFood.add(new ArrayList<>(Arrays.asList("Formaggio", "12.50")));
        liberrioFood.add(new ArrayList<>(Arrays.asList("Ghiaccio", "1.01")));
        liberrioFood.add(new ArrayList<>(Arrays.asList("Melone", "5.20")));
        liberrioFood.add(new ArrayList<>(Arrays.asList("Prosciutto and Pesci", "20.23")));
        liberrioFood.add(new ArrayList<>(Arrays.asList("Risotto", "13.14")));
        liberrioFood.add(new ArrayList<>(Arrays.asList("Zucchero and Sale", "0.60")));
        
        savageGardenFood.add(new ArrayList<>(Arrays.asList("Abbacchio's Tea", "1.00")));
        savageGardenFood.add(new ArrayList<>(Arrays.asList("DIO's Bread", "36.14")));
        savageGardenFood.add(new ArrayList<>(Arrays.asList("Giorno's Donuts", "6.66")));
        savageGardenFood.add(new ArrayList<>(Arrays.asList("Joseph's Tequila", "35.00")));
        savageGardenFood.add(new ArrayList<>(Arrays.asList("Kakyoin's Cherry", "3.50")));
        savageGardenFood.add(new ArrayList<>(Arrays.asList("Kakyoin's Porridge", "4.44")));
        
        trattoriaFood.add(new ArrayList<>(Arrays.asList("Caprese Salad", "10.00")));
        trattoriaFood.add(new ArrayList<>(Arrays.asList("Creme caramel", "6.50")));
        trattoriaFood.add(new ArrayList<>(Arrays.asList("Lamb Chops with Apple Sauce", "25.00")));
        trattoriaFood.add(new ArrayList<>(Arrays.asList("Spaghetti alla Puttanesca", "15.00")));
        
        cafeMenu.add(new ArrayList<>(Arrays.asList("Sampling Matured Cheese Platter", "23.00")));
        cafeMenu.add(new ArrayList<>(Arrays.asList("Spring Lobster Salad", "35.00")));
        cafeMenu.add(new ArrayList<>(Arrays.asList("Spring Organic Omelette", "23.00")));
        cafeMenu.add(new ArrayList<>(Arrays.asList("Truffle-flavoured Poultry Supreme", "34.00")));
        cafeMenu.add(new ArrayList<>(Arrays.asList("White Asparagus", "26.00")));
        
        jadeMenu.add(new ArrayList<>(Arrays.asList("Braised Chicken in Black Bean Sauce", "15.00")));
        jadeMenu.add(new ArrayList<>(Arrays.asList("Braised Goose Web with Vermicelli", "21.00")));
        jadeMenu.add(new ArrayList<>(Arrays.asList("Deep-fried Hiroshima Oyster", "17.00")));
        jadeMenu.add(new ArrayList<>(Arrays.asList("Poached Tofu with Dried Shrimps", "12.00")));
        jadeMenu.add(new ArrayList<>(Arrays.asList("Scrambled Egg White with Milk", "10.00")));
        
        libeccioMenu.add(new ArrayList<>(Arrays.asList("Formaggio", "12.50")));
        libeccioMenu.add(new ArrayList<>(Arrays.asList("Ghiaccio", "1.01")));
        libeccioMenu.add(new ArrayList<>(Arrays.asList("Melone", "5.20")));
        libeccioMenu.add(new ArrayList<>(Arrays.asList("Prosciutto and Pesci", "20.23")));
        libeccioMenu.add(new ArrayList<>(Arrays.asList("Risotto", "13.14")));
        libeccioMenu.add(new ArrayList<>(Arrays.asList("Zucchero and Sale", "0.60")));
        
        savageMenu.add(new ArrayList<>(Arrays.asList("Abbacchio's Tea", "1.00")));
        savageMenu.add(new ArrayList<>(Arrays.asList("DIO's Bread", "36.14")));
        savageMenu.add(new ArrayList<>(Arrays.asList("Giorno's Donuts", "6.66")));
        savageMenu.add(new ArrayList<>(Arrays.asList("Joseph's Tequila", "35.00")));
        savageMenu.add(new ArrayList<>(Arrays.asList("Kakyoin's Cherry", "3.50")));
        savageMenu.add(new ArrayList<>(Arrays.asList("Kakyoin's Porridge", "4.44")));
        
        trattoriaMenu.add(new ArrayList<>(Arrays.asList("Caprese Salad", "10.00")));
        trattoriaMenu.add(new ArrayList<>(Arrays.asList("Creme caramel", "6.50")));
        trattoriaMenu.add(new ArrayList<>(Arrays.asList("Lamb Chops with Apple Sauce", "25.00")));
        trattoriaMenu.add(new ArrayList<>(Arrays.asList("Spaghetti alla Puttanesca", "15.00")));
        
        foodList.add(new ArrayList<>(Arrays.asList("Sampling Matured Cheese Platter", "23.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Spring Lobster Salad", "35.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Spring Organic Omelette", "23.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Truffle-flavoured Poultry Supreme", "34.00")));
        foodList.add(new ArrayList<>(Arrays.asList("White Asparagus", "26.00")));  
        foodList.add(new ArrayList<>(Arrays.asList("Braised Chicken in Black Bean Sauce", "15.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Braised Goose Web with Vermicelli", "21.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Deep-fried Hiroshima Oyster", "17.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Poached Tofu with Dried Shrimps", "12.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Scrambled Egg White with Milk", "10.00")));        
        foodList.add(new ArrayList<>(Arrays.asList("Formaggio", "12.50")));
        foodList.add(new ArrayList<>(Arrays.asList("Ghiaccio", "1.01")));
        foodList.add(new ArrayList<>(Arrays.asList("Melone", "5.20")));
        foodList.add(new ArrayList<>(Arrays.asList("Prosciutto and Pesci", "20.23")));
        foodList.add(new ArrayList<>(Arrays.asList("Risotto", "13.14")));
        foodList.add(new ArrayList<>(Arrays.asList("Zucchero and Sale", "0.60")));      
        foodList.add(new ArrayList<>(Arrays.asList("Abbacchio's Tea", "1.00")));
        foodList.add(new ArrayList<>(Arrays.asList("DIO's Bread", "36.14")));
        foodList.add(new ArrayList<>(Arrays.asList("Giorno's Donuts", "6.66")));
        foodList.add(new ArrayList<>(Arrays.asList("Joseph's Tequila", "35.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Kakyoin's Cherry", "3.50")));
        foodList.add(new ArrayList<>(Arrays.asList("Kakyoin's Porridge", "4.44")));    
        foodList.add(new ArrayList<>(Arrays.asList("Caprese Salad", "10.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Creme caramel", "6.50")));
        foodList.add(new ArrayList<>(Arrays.asList("Lamb Chops with Apple Sauce", "25.00")));
        foodList.add(new ArrayList<>(Arrays.asList("Spaghetti alla Puttanesca", "15.00")));
    }
    
    public static void setPrice(){
        foodPrices.put("Sampling Matured Cheese Platter", "23.00");
        foodPrices.put("Spring Lobster Salad", "35.00");
        foodPrices.put("Spring Organic Omelette", "23.00");
        foodPrices.put("Truffle-flavoured Poultry Supreme", "34.00");
        foodPrices.put("White Asparagus", "26.00");  
        foodPrices.put("Braised Chicken in Black Bean Sauce", "15.00");
        foodPrices.put("Braised Goose Web with Vermicelli", "21.00");
        foodPrices.put("Deep-fried Hiroshima Oyster", "17.00");
        foodPrices.put("Poached Tofu with Dried Shrimps", "12.00");
        foodPrices.put("Scrambled Egg White with Milk", "10.00");        
        foodPrices.put("Formaggio", "12.50");
        foodPrices.put("Ghiaccio", "1.01");
        foodPrices.put("Melone", "5.20");
        foodPrices.put("Prosciutto and Pesci", "20.23");
        foodPrices.put("Risotto", "13.14");
        foodPrices.put("Zucchero and Sale", "0.60");      
        foodPrices.put("Abbacchio's Tea", "1.00");
        foodPrices.put("DIO's Bread", "36.14");
        foodPrices.put("Giorno's Donuts", "6.66");
        foodPrices.put("Joseph's Tequila", "35.00");
        foodPrices.put("Kakyoin's Cherry", "3.50");
        foodPrices.put("Kakyoin's Porridge", "4.44");    
        foodPrices.put("Caprese Salad", "10.00");
        foodPrices.put("Creme caramel", "6.50");
        foodPrices.put("Lamb Chops with Apple Sauce", "25.00");
        foodPrices.put("Spaghetti alla Puttanesca", "15.00");
        
        MilagroPrices.put("Sampling Matured Cheese Platter", "23.00");
        MilagroPrices.put("Spring Lobster Salad", "35.00");
        MilagroPrices.put("Spring Organic Omelette", "23.00");
        MilagroPrices.put("Truffle-flavoured Poultry Supreme", "34.00");
        MilagroPrices.put("White Asparagus", "26.00");  
        MilagroPrices.put("Braised Chicken in Black Bean Sauce", "15.00");
        MilagroPrices.put("Braised Goose Web with Vermicelli", "21.00");
        MilagroPrices.put("Deep-fried Hiroshima Oyster", "17.00");
        MilagroPrices.put("Poached Tofu with Dried Shrimps", "12.00");
        MilagroPrices.put("Scrambled Egg White with Milk", "10.00");        
        MilagroPrices.put("Formaggio", "12.50");
        MilagroPrices.put("Ghiaccio", "1.01");
        MilagroPrices.put("Melone", "5.20");
        MilagroPrices.put("Prosciutto and Pesci", "20.23");
        MilagroPrices.put("Risotto", "13.14");
        MilagroPrices.put("Zucchero and Sale", "0.60");      
        MilagroPrices.put("Abbacchio's Tea", "1.00");
        MilagroPrices.put("DIO's Bread", "36.14");
        MilagroPrices.put("Giorno's Donuts", "6.66");
        MilagroPrices.put("Joseph's Tequila", "35.00");
        MilagroPrices.put("Kakyoin's Cherry", "3.50");
        MilagroPrices.put("Kakyoin's Porridge", "4.44");    
        MilagroPrices.put("Caprese Salad", "10.00");
        MilagroPrices.put("Creme caramel", "6.50");
        MilagroPrices.put("Lamb Chops with Apple Sauce", "25.00");
        MilagroPrices.put("Spaghetti alla Puttanesca", "15.00");
    }

    public static void extractInfo(){
        for (ArrayList<String> row : rawRecord) {           
            ArrayList<Object> extractedRecord = new ArrayList<>();
            
            int numItems = row.size();
            String food = row.get(numItems-1);
            
            extractedRecord.add(String.valueOf(currentDay));
            extractedRecord.add(row.get(0)); // Add the name
            extractedRecord.add(food);

            extractRecord.add(extractedRecord);
        }
    }

    public static void makePrice(){
        for (ArrayList<Object> row : extractRecord){
            ArrayList<Object> extractedRecord = new ArrayList<>();
            
            String food = (String)row.get(2);
            String price = (String)foodPrices.getOrDefault(food, "Price not found");
            
            extractedRecord.add(row.get(0));
            extractedRecord.add(row.get(1));
            extractedRecord.add(food);
            extractedRecord.add(price);
            
            salesRecord.add(extractedRecord);
        }
    }

    public static void storeSales(){
        try (FileWriter writer = new FileWriter("sales.txt")) {
            for (ArrayList<Object> record : salesRecord) {
                for (Object value : record) {
                    writer.write(value + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
        }
    }

    public static void setLoadedGame(String fileName){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);
            JSONArray savedFile = (JSONArray) obj;
            JSONObject day = (JSONObject) savedFile.get(0);
            currentDay = Integer.parseInt((String) day.get("currentDay"));
            JSONObject map = (JSONObject) savedFile.get(1);
            mapSelection = Integer.parseInt((String) map.get("mapSelection"));
            JSONObject visitedLoc = (JSONObject) savedFile.get(2);
            char alphabet = 'A';
            while(visitedLoc.containsKey(Character.toString(alphabet))) {
                temp.add((String) visitedLoc.get(Character.toString(alphabet)));
                alphabet++;
            }
            JSONObject saleR = (JSONObject) savedFile.get(3);
            int num = 1;
            while(saleR.containsKey(Integer.toString(num))){
                String str = (String) saleR.get(Integer.toString(num));
                String[] arr = str.split(",");
                ArrayList<Object> tempArr = new ArrayList<>();
                tempArr.add(Integer.valueOf(arr[0]));
                tempArr.add(arr[1]);
                tempArr.add(arr[2]);
                tempArr.add(Double.valueOf(arr[3]));
                salesRecord.add(tempArr);
                num++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}




