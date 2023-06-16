import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResidentInfo extends Main{
    protected static List<Resident> residents ;
    protected static Map<String, List<Resident>> groupedResidents ;
    protected static Map<String, Stand> stands;
    
    public ResidentInfo(){}
    
    public ResidentInfo(List<Resident> residents, Map<String, List<Resident>> groupedResidents, Map<String, Stand> stands){
        this.residents = residents;
        this.groupedResidents = groupedResidents;
        this.stands = stands;
    }
    
    protected static void processResidentData() {
        // Step 1: Read and parse the resident.csv file
        residents = parseResidents();
        
        // Step 2: Group residents based on their residential areas
        groupedResidents = groupResidents(residents);
        
        // Step 4: Read and parse the stands.csv file
        stands = parseStands();
    }
    
    private static List<Resident> parseResidents(){
        List<Resident> resident = new ArrayList<>();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("residents.csv"));
            boolean isFirstRow = true;
            String line;
            while((line = br.readLine()) != null){
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; 
                }
                
                String[] data = line.split(",");
                if(data.length == 5){
                    String name = data[0];
                    String age = data[1];
                    String gender = data[2];
                    String residentialArea = data[3];
                    String parents = data[4];
                    resident.add(new Resident(name, age, gender, residentialArea, parents));
                    
                }
            }
            br.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File is not found.");
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return resident;
    }
    
    private static Map<String, List<Resident>> groupResidents(List<Resident> resident){
        Map<String, List<Resident>> groupResidents = new HashMap<>();
        
        for(Resident residents : resident){
            String residentialArea = residents.getResidentialArea();
            groupResidents.putIfAbsent(residentialArea, new ArrayList<>());
            groupResidents.get(residentialArea).add(residents);
        }
        
        return groupResidents;
    }
    
    private static Map<String, Stand> parseStands(){
        Map<String, Stand> stand = new HashMap<>();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("stands.csv"));
            boolean isFirstRow = true;
            String line;
            while((line = br.readLine()) != null){
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip the first row
                }
                
                String[] data = line.split(",");
                if(data.length == 8){
                    String standName = data[0];
                    String standUser = data[1];
                    String destructivePower = data[2];
                    String speed = data[3];
                    String range = data[4];
                    String stamina = data[5];
                    String precision = data[6];
                    String developmentPotential = data[7];
                    stand.put(standUser, new Stand(standName, destructivePower, speed, range, stamina, precision, developmentPotential));
               }
            }
            br.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File is not found.");
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return stand;
    }
    
    protected static void display(Map<String, List<Resident>> groupedResidents, Map<String, Stand> stands, String currentLocation) {
         List<Resident> residents = groupedResidents.get(currentLocation);
    
        if (residents == null) {// mcm nonid
            System.out.println("No residents found in " + currentLocation);
            return;
        }
        
        System.out.println("Resident Information in " + currentLocation);
        System.out.println("+----+-------------------------+-----+--------+-------------------------+");
        System.out.println("| No | Name                    | Age | Gender | Stand                   |");
        System.out.println("+----+-------------------------+-----+--------+-------------------------+");

        int no = 1;
        for (Resident resident : residents) {
            Stand stand = stands.get(resident.getName());
            String standName = (stand != null) ? stand.getStandName() : "-";
            System.out.printf("| %2d | %-23s | %-3s | %-6s | %-23s |\n",
                    no, resident.getName(), resident.getAge(), resident.getGender(), standName);
            no++;

        }

        System.out.println("+----+-----------------------+-----+--------+---------------------------+");

        System.out.println("-+--------------------+------------+------------+--------------+----------------+-");
        System.out.println(" | Destructive Power  |   Speed    |   Range    |   Stamina    | Precision      |");
        System.out.println("-+--------------------+------------+------------+--------------+----------------+-");

        for (Resident resident : residents) {
            Stand stand = stands.get(resident.getName());
            if (stand != null) {
                System.out.printf(" | %-18s | %-10s | %-10s | %-12s | %-14s |\n",
                stand.getDestructivePower(), stand.getSpeed(), stand.getRange(),
                stand.getStamina(), stand.getPrecision());
            } else {
                System.out.printf(" | %-18s | %-10s | %-10s | %-12s | %-14s |\n",
                "-", "-", "-", "-", "-");
            }
        }

        System.out.println("-+--------------------+------------+------------+--------------+----------------+-");
        System.out.println("-+-----------------------+");
        System.out.println(" | Development Potential |");
        System.out.println("-+-----------------------+");

        for (Resident resident : residents) {
            Stand stand = stands.get(resident.getName());
            if (stand != null) {
                System.out.printf(" | %-21s |\n", stand.getDevelopmentPotential());
            } else {
                System.out.printf(" | %-21s |\n", "-");
            }
        }

        System.out.println("-+-----------------------+");
        System.out.println();
        
    }
    
    
    protected static class Resident{
        private String name, age, gender, residentialArea, parents;
        
        public Resident(){}
        
        public Resident(String name, String age, String gender, String residentialArea, String parents){
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.residentialArea = residentialArea;
            this.parents = parents;
        }
        
        public String getName(){
            return name;
        }
        
        public String getAge(){
            return age;
        }
        
        public String getGender(){
            return gender;
        }
        
        public String getResidentialArea(){
            return residentialArea;
        }
        
        public String getParents(){
            return parents;
        }
    }
    
    protected static class Stand{
        private String standName;
        private String destructivePower;
        private String speed;
        private String range;
        private String stamina;
        private String precision;
        private String developmentPotential;
        
        public Stand(){}
        
        public Stand(String standName, String destructivePower, String speed, String range,
                     String stamina, String precision, String developmentPotential) {
            this.standName = standName;
            this.destructivePower = destructivePower;
            this.speed = speed;
            this.range = range;
            this.stamina = stamina;
            this.precision = precision;
            this.developmentPotential = developmentPotential;
            
        }
        
        public String getStandName() {
            return standName;
        }

        public String getDestructivePower() {
            return destructivePower;
        }

        public String getSpeed() {
            return speed;
        }

        public String getRange() {
            return range;
        }

        public String getStamina() {
            return stamina;
        }

        public String getPrecision() {
            return precision;
        }

        public String getDevelopmentPotential() {
            return developmentPotential;
        }
    }
    
    protected static ArrayList<String> parameterOrderAscend = new ArrayList<>(Arrays.asList("-","Infinity", "A", "B", "C", "D", "E", "?", "Null"));
    
    protected static boolean compareResidents(Resident resident1, Resident resident2, String field, ArrayList<String> parameterOrder) {
        Stand stand1 = stands.get(resident1.getName());
        Stand stand2 = stands.get(resident2.getName());

        String value1, value2;

        switch (field) {
            case "Name":
                value1 = resident1.getName();
                value2 = resident2.getName();
                if (value1.compareTo(value2) <= 0) {
                    return false;
                } else if (value1.compareTo(value2) > 0) {
                    return true;
                }
                
            case "Gender":
                value1 = resident1.getGender();
                value2 = resident2.getGender();
                if (value1.compareTo(value2) <= 0) {
                    return false;
                } else if (value1.compareTo(value2) > 0) {
                    return true;
                }
                
            case "Age":
                int ageint1,ageint2;
                value1 = resident1.getAge();
                value2 = resident2.getAge();
                
                if (value1.equals("N/A")) {
                    ageint1 = Integer.MAX_VALUE;  // Set a large value for "N/A" age
                } else {
                    ageint1 = Integer.parseInt(value1);
                }
                
                if (value2.equals("N/A")) {
                    ageint2 = Integer.MAX_VALUE;  // Set a large value for "N/A" age
                } else {
                    ageint2 = Integer.parseInt(value2);
                }
                
                if(ageint1 <= ageint2){
                    return false;
                }else if(ageint1 > ageint2){
                    return true;
                }
                
            case "Stand":
                value1 = (stand1 != null) ? stand1.getStandName() : "-";
                value2 = (stand2 != null) ? stand2.getStandName() : "-";
                
                if (value1.equals("-") && !value2.equals("-")) {
                    return false;
                } else if (!value1.equals("-") && value2.equals("-")) {
                    return true;
                } else if (value1.equals("-") && value2.equals("-")) {
                    // Both values are "-", no swap needed
                    return false;
                } else {
                    // Compare the stand names lexicographically
                    if (value1.compareTo(value2) <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
                
            case "Stamina":
                value1 = (stand1 != null) ? stand1.getStamina() : "-";
                value2 = (stand2 != null) ? stand2.getStamina() : "-";
                break;
                
            case "Precision":
                value1 = (stand1 != null) ? stand1.getPrecision() : "-";
                value2 = (stand2 != null) ? stand2.getPrecision() : "-";
                break;
                
            case "Destructive Power":
                value1 = (stand1 != null) ? stand1.getDestructivePower() : "-";
                value2 = (stand2 != null) ? stand2.getDestructivePower() : "-";
                break;
                
            case "Speed":
                value1 = (stand1 != null) ? stand1.getSpeed() : "-";
                value2 = (stand2 != null) ? stand2.getSpeed() : "-";
                break;
                
            case "Range":
                value1 = (stand1 != null) ? stand1.getRange() : "-";
                value2 = (stand2 != null) ? stand2.getRange() : "-";
                break;
                
            case "Development Potential":
                value1 = (stand1 != null) ? stand1.getDevelopmentPotential() : "-";
                value2 = (stand2 != null) ? stand2.getDevelopmentPotential() : "-";
                break;
                
            default:
                return false;
        }
        
        int index1 = parameterOrder.indexOf(value1);
        int index2 = parameterOrder.indexOf(value2);

        if (index1 <= index2) {
            return false;
        } else if (index1 > index2) {
            return true;
        }

        return false;
    }

    
    

    
    protected static void displaySort(Map<String, List<Resident>> groupedResidents, Map<String, Stand> stands, String currentLocation, String sortingOrder) {
        List<Resident> residents = groupedResidents.get(currentLocation);

        if (residents == null) {
            System.out.println("No residents found in " + currentLocation);
            return;
        }

        String[] sortingFields = sortingOrder.split(";\\s*");
        
        for(int i = sortingFields.length-1 ; i>=0 ; i--) {
            String field = sortingFields[i];
            // Extract the field name and sort order using regular expressions
            String pattern = "(.+)\\s*\\((ASC|DESC)\\)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(field);

            if (matcher.find()) {
                String fieldName = matcher.group(1).trim();
                String sortOrder = matcher.group(2).trim();

                System.out.println("Field: " + fieldName);
                System.out.println("Sort Order: " + sortOrder);
                    
                for (int a = 0; a < residents.size() - 1; a++) {
                    for (int b = 0; b <(residents.size() - a)- 1; b++) {
                        boolean swapNeeded = true;
                        Resident r1 = residents.get(b);
                        Resident r2 = residents.get(b + 1);
                        
                        if(sortOrder.equals("ASC")){
                            swapNeeded = compareResidents(r1, r2, fieldName, parameterOrderAscend);
                        }else if (sortOrder.equals("DESC")) {
                            swapNeeded = compareResidents(r1, r2, fieldName, parameterOrderAscend);
                            swapNeeded = !swapNeeded;
                        }
                        
                        
                        if (swapNeeded == true ) {
                            Collections.swap(residents, b, b + 1);
                        }


                    }
                }
            }
        }

        System.out.println("========================================================================");
        System.out.println("Resident Information in " + currentLocation);
        System.out.println("+----+-------------------------+-----+--------+-------------------------+");
        System.out.println("| No | Name                    | Age | Gender | Stand                   |");
        System.out.println("+----+-------------------------+-----+--------+-------------------------+");

        int no = 1;
        for (Resident resident : residents) {
            Stand stand = stands.get(resident.getName());
            String standName = (stand != null) ? stand.getStandName() : "-";
            System.out.printf("| %2d | %-23s | %-3s | %-6s | %-23s |\n",
                    no, resident.getName(), resident.getAge(), resident.getGender(), standName);
            no++;

        }

        System.out.println("+----+-----------------------+-----+--------+---------------------------+");

        System.out.println("-+--------------------+------------+------------+--------------+----------------+-");
        System.out.println(" | Destructive Power  |   Speed    |   Range    |   Stamina    | Precision      |");
        System.out.println("-+--------------------+------------+------------+--------------+----------------+-");

        for (Resident resident : residents) {
            Stand stand = stands.get(resident.getName());
            if (stand != null) {
                System.out.printf(" | %-18s | %-10s | %-10s | %-12s | %-14s |\n",
                stand.getDestructivePower(), stand.getSpeed(), stand.getRange(),
                stand.getStamina(), stand.getPrecision());
            } else {
                System.out.printf(" | %-18s | %-10s | %-10s | %-12s | %-14s |\n",
                "-", "-", "-", "-", "-");
            }
        }

        System.out.println("-+--------------------+------------+------------+--------------+----------------+-");
        System.out.println("-+-----------------------+");
        System.out.println(" | Development Potential |");
        System.out.println("-+-----------------------+");

        for (Resident resident : residents) {
            Stand stand = stands.get(resident.getName());
            if (stand != null) {
                System.out.printf(" | %-21s |\n", stand.getDevelopmentPotential());
            } else {
                System.out.printf(" | %-21s |\n", "-");
            }
        }

        System.out.println("-+-----------------------+");
        System.out.println();
    
    
}
    
    

}