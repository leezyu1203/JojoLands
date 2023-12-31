import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class TownHall extends Main {
    private static int num;
    static LinkedList<ArrayList<String>> jg, cdm, tt, lb, sg;

    protected static void hall() {
        num=1;
        System.out.println("["+num+"] Move to:");
        num++;
        AdjacentLocations adjacent = new AdjacentLocations();
        adjacent.displayOptions(currentLocation);
        
        System.out.println("["+num+"] Advance to next day");
        num++;
        System.out.println("["+num+"] Save game");
        num++;
        boolean caseExecuted = true;
        
        if(visit.getPrevious() != null){
            System.out.println("["+num+"] Back ("+ visit.getPrevious().getName()+")");
            num++;
            caseExecuted = false;
        }
       
        if (visit.forwardLocation != null) {
            System.out.println("["+num+"] Forward (" + visit.forwardLocation.getName()+ ")");
            num++;
        }

        System.out.println("["+num+"] Exit");
        num++;

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
                visit.head = null;
                visit.tail = null;
                visit.size = 0;
                visit.forwardLocation = null;
                visit.previousLocation = null;
                currentDay++;

                WaitList wl1= new WaitList();
                jg= wl1.jgWaitList(currentDay);
                cdm= wl1.cdmWaitList(currentDay);
                tt= wl1.ttWaitList(currentDay);
                lb= wl1.lWaitList(currentDay);
                sg= wl1.sgWaitList(currentDay);
                rawRecord.clear();
                salesRecord.clear();
                rawRecord.addAll(jg);
                rawRecord.addAll(cdm);
                rawRecord.addAll(tt);
                rawRecord.addAll(lb);
                rawRecord.addAll(sg);
                extractInfo();
                makePrice();
                storeSales();

                System.out.println("It's Day " + (currentDay) + " (" + getDayOfWeek(currentDay) + ") of our journey in JOJOLands!");
                visit.addFirst(townHall);
                currentLocation = townHall;
                break;
            
            case 3:
                System.out.print("Enter a path to save the game: ");
                String fileName = sc.nextLine();
                saveFile(fileName);
                System.out.println("Saving the game...");
                System.out.println("See you next time. Goodbye!");
                System.exit(0);
                break;

            case 4:
                if (visit.getPrevious() != null) {
                    visit.setNext(currentLocation);
                    currentLocation = visit.getPrevious();
                    visit.removeLast();
                } else if (visit.forwardLocation!= null){
                    currentLocation = visit.forwardLocation;
                    if(visit.head == null){
                        visit.addFirst(currentLocation);
                    }else{
                        visit.addLast(currentLocation);
                    }
                    visit.forwardLocation = null;
                }else {
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                }
                break;

            case 5:
                if (visit.forwardLocation!= null && caseExecuted == false ) {
                    currentLocation = visit.forwardLocation;
                    if(visit.head == null){
                        visit.addFirst(currentLocation);
                    }else{
                        visit.addLast(currentLocation);
                    }
                    visit.forwardLocation = null;
                } else {
                    System.out.println("Exiting the game. Goodbye!");
                    System.exit(0);
                }
                break;

            case 6:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void saveFile(String fileName){
        JSONObject day = new JSONObject();
        day.put("currentDay",Integer.toString(currentDay));
        JSONObject map = new JSONObject();
        map.put("mapSelection",Integer.toString(mapSelection));
        JSONObject visitedLoc = new JSONObject();
        Node<Location> temp = visit.head;
        char alphabet = 'A';
        while(temp != null){
            visitedLoc.put(Character.toString(alphabet),temp.location.getName());
            alphabet++;
            temp = temp.next;
        } if(visit.forwardLocation != null)
            visitedLoc.put(alphabet, visit.forwardLocation.getName());

        JSONObject waitingL = new JSONObject();
        waitingL.put("jgWaitingL", saveLinkedList(jg));
        waitingL.put("cdmWaitingL", saveLinkedList(cdm));
        waitingL.put("ttWaitingL", saveLinkedList(tt));
        waitingL.put("lbWaitingL", saveLinkedList(lb));
        waitingL.put("sgWaitingL", saveLinkedList(sg));

        JSONObject salesR = new JSONObject();
        for(int i = 0; i < Main.salesRecord.size(); i++){
            ArrayList<Object> tempArr = Main.salesRecord.get(i);
            String tempStr = "";
            for(int j = 0; j < tempArr.size(); j++){
                tempStr += tempArr.get(j).toString() + ",";
            }
            salesR.put(Integer.toString(i),tempStr);
        }

        JSONObject menu = new JSONObject();
        menu.put("jgMenu", saveLinkedList(jadeGardenFood));
        menu.put("cdmMenu", saveLinkedList(cafeMagotsFood));
        menu.put("ttMenu", saveLinkedList(trattoriaFood));
        menu.put("lbMenu", saveLinkedList(liberrioFood));
        menu.put("sgMenu", saveLinkedList(savageGardenFood));

        JSONObject foodP = new JSONObject();
        String[] tempArr = hashMapToArr(foodPrices);
        for(int i = 0; i < tempArr.length; i++){
            foodP.put(Integer.toString(i), tempArr[i]);
        }

        JSONArray saveFile = new JSONArray();
        saveFile.add(day);                  //index = 0
        saveFile.add(map);                  //index = 1
        saveFile.add(visitedLoc);           //index = 2
        saveFile.add(waitingL);             //index = 3
        saveFile.add(salesR);               //index = 4
        saveFile.add(menu);                 //index = 5
        saveFile.add(foodP);                //index = 6

        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(saveFile.toJSONString());
            writer.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static JSONObject saveLinkedList(LinkedList<ArrayList<String>> linkedList){
        JSONObject tempJSON = new JSONObject();
        for(int i = 0; i < linkedList.size(); i++){
            ArrayList<String> tempArr = linkedList.get(i);
            String tempStr = "";
            for(int j = 0; j < tempArr.size(); j++){
                tempStr += tempArr.get(j) + ",";
            }
            tempJSON.put(Integer.toString(i),tempStr);
        } return tempJSON;
    }
    private static String[] hashMapToArr(HashMap<Object,Object> hashMap){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<Object,Object> entry : hashMap.entrySet()){
            Object key = entry.getKey();
            Object value = entry.getValue();
            builder.append(key).append(",").append(value).append("\n");
        } String tempStr = builder.toString();
        String[] tempArr = tempStr.split("\n");
        return tempArr;
    }
}