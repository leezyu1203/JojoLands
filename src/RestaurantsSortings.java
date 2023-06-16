import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class RestaurantsSortings {

    public void display(LinkedList<ArrayList<String>> lists){  
        System.out.println("+----+-------------------------+-----+--------+-----------------------------------------+-");
        System.out.printf("| %-2s | %-22s  | %-3s | %-6s | %-39s |", "No", "Name", "Age", "Gender", "Order");
        System.out.println("\n+----+-------------------------+-----+--------+-----------------------------------------+-");
        
        int i=1;
        String name;
        int lastFood;
        for(int j=0; j<lists.size(); j++){
            name= lists.get(j).get(0);
            lastFood= lists.get(j).size()-1;
            
            System.out.printf("| %2d | %-22s  | %-3s | %-6s | %-39s |%n", i, lists.get(j).get(0), lists.get(j).get(1), 
                    lists.get(j).get(2), lists.get(j).get(lastFood));
            
            i++;
        }
        System.out.println("+----+-------------------------+-----+--------+-----------------------------------------+-");

    }
    
    public void jadeGardenProcessingList(LinkedList<ArrayList<String>> lists){
        //first half- queue, second half-stack
        int half= lists.size()/2;
        
        Queue<ArrayList<String>> q= new LinkedList<>();
        for(int i=0; i<half; i++){
            q.add(lists.get(i));
        }
        
        Stack<ArrayList<String>> s= new Stack<>();
        for(int j=half; j<lists.size(); j++){
            s.push(lists.get(j));
        }
        
        LinkedList<ArrayList<String>> array= new LinkedList<>();
        int size= s.size();
        for(int k=0; k<size; k++){
            //if size of lists is odd
            if(lists.size()%2!=0 && k==size-1){
                array.add(s.pop());
                continue;
            }
            array.add(q.poll());
            array.add(s.pop());
        }
        
        System.out.println("\nOrder Processing List");
        display(array);
    }
    
    public void cafeDeuxMagotsProcessingList(LinkedList<ArrayList<String>> lists){
        PriorityQueue<Integer> oldest= new PriorityQueue<>();  
        PriorityQueue<Integer> youngest= new PriorityQueue<>(Collections.reverseOrder());  
        int age;
        
        for(int i=0; i<lists.size(); i++){
            if(lists.get(i).get(1).equals("N/A"))  //will not add age N/A
                continue;
            
            age= Integer.parseInt(lists.get(i).get(1));
            oldest.add(age);
            youngest.add(age);
        }
        
        LinkedList<ArrayList<String>> sorted= new LinkedList<>();
        LinkedList<ArrayList<String>> listsCopy= new LinkedList<>();
        listsCopy.addAll(lists);
        
        int age1, age2;
        String str1, str2;
        int size= oldest.size()/2;   //oldest q and youngest q should have the same size, i just use oldest q size here
        
        for(int j=0; j<size; j++){
            age1= oldest.poll();
            str1= String.valueOf(age1);
            for(int x=0; x<listsCopy.size(); x++){
                if(str1.equals(listsCopy.get(x).get(1))){
                    sorted.add(listsCopy.get(x));
                    listsCopy.remove(x);
                    break;
                }
            }
            
            age2= youngest.poll();
            str2= String.valueOf(age2);
            for(int x=0; x<listsCopy.size(); x++){
                if(str2.equals(listsCopy.get(x).get(1))){
                    sorted.add(listsCopy.get(x));
                    listsCopy.remove(x);
                    break;
                }
            }
        }
        
        if(listsCopy.size()!=0){
            int size2= listsCopy.size();
            PriorityQueue<String> q= new PriorityQueue<>();  //String- youngest to oldest, N/A will be last
            String str3;
            
            for(int j=0; j<size2; j++){
                q.add(listsCopy.get(j).get(1));
            }
            
            for(int j=0; j<size2; j++){
                str3= q.poll();
                for(int k=0; k<listsCopy.size(); k++){
                    if(str3.equals(listsCopy.get(k).get(1))){
                        sorted.add(listsCopy.get(k));
                        listsCopy.remove(k);
                        break;
                    }
                }
            }
        }
        
        System.out.println("\nOrder Processing List");
        display(sorted);
    }
    
    public void trattoriaTrussardiProcessingList(LinkedList<ArrayList<String>> lists){
        LinkedList<ArrayList<String>> male= new LinkedList<>();
        LinkedList<ArrayList<String>> female= new LinkedList<>();
        LinkedList<ArrayList<String>> unspecified= new LinkedList<>();
        String gender, age;
        int a1=0, a2=0;
        
        for(int i=0; i<lists.size(); i++){
            gender= lists.get(i).get(2);
            age= lists.get(i).get(1);
            
            if(gender.equals("Male") && !age.equals("N/A")){
                male.add(lists.get(i));
            }
            
            if(gender.equals("Female") && !age.equals("N/A")){
                female.add(lists.get(i));
            }
            
            if(age.equals("N/A")){
                unspecified.add(lists.get(i)); 
            }
        }
        
        PriorityQueue<Integer> oldMale= new PriorityQueue<>();
        PriorityQueue<Integer> youngMale= new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> oldFemale= new PriorityQueue<>();
        PriorityQueue<Integer> youngFemale= new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<male.size(); i++){
            a1= Integer.parseInt(male.get(i).get(1));
            oldMale.add(a1);
            youngMale.add(a1);
        }
        
        for(int j=0; j<female.size(); j++){
            a1= Integer.parseInt(female.get(j).get(1));
            oldFemale.add(a1);
            youngFemale.add(a1);
        }
        
        //youngest man, oldest women first
        //then oldest man, youngest women
        //1 gender left in the queue, only 1 person will be chosen
        int size=0;
        if(male.size()>female.size()){
            size= male.size();
        }
        else if(female.size()>male.size()){
            size= female.size();
        }
        else{  //male.size = female.size
            size= female.size();  //use either size
        }
        
        LinkedList<ArrayList<String>> sorted= new LinkedList<>();
        String str;
        int a=0;
        
        for(int j=0; j<size; j++){
            if(male.size()!=0){
                a= youngMale.poll();
                str= String.valueOf(a);
                //System.out.println(str);
                for(int x=0; x<male.size(); x++){
                    if(str.equals(male.get(x).get(1))){
                        sorted.add(male.get(x));
                        male.remove(x);
                        break;
                    }
                }
            }
                
            if(female.size()!=0){
                a= oldFemale.poll();
                str= String.valueOf(a);
                //System.out.println(str);
                for(int x=0; x<female.size(); x++){
                    if(str.equals(female.get(x).get(1))){
                        sorted.add(female.get(x));
                        female.remove(x);
                        break;
                    }
                }
            }
            
            if(male.size()!=0){
                a= oldMale.poll();
                str= String.valueOf(a);
                for(int x=0; x<male.size(); x++){
                    if(str.equals(male.get(x).get(1))){
                        sorted.add(male.get(x));
                        male.remove(x);
                        break;
                    }
                }
            }
            
            if(female.size()!=0){
                a= youngFemale.poll();
                str= String.valueOf(a);
                for(int x=0; x<female.size(); x++){
                    if(str.equals(female.get(x).get(1))){
                        sorted.add(female.get(x));
                        female.remove(x);
                        break;
                    }
                }
            }
        }
        
        //add unspecified age list at the end of the list
        if(unspecified.size()!=0){
            for(int i=0; i<unspecified.size(); i++){
                sorted.add(unspecified.get(i));
            }
        }
        
        System.out.println("\nOrder Processing List");
        display(sorted);
    }
    
    public void libeccioProcessingList(LinkedList<ArrayList<String>> lists, int day){
        Random random= new Random();
        LinkedList<ArrayList<String>> listsCopy= new LinkedList<>();
        listsCopy.addAll(lists);
        Stack<ArrayList<String>> s= new Stack<>();   //stack- LIFO,FILO
        int number= 0;
        
        while(listsCopy.size()!=0){
            
            for(int i=0; i<listsCopy.size(); i++){
                number= random.nextInt(200)+1;
                
                if(number%day==0){
                    s.push(listsCopy.get(i));
                    listsCopy.remove(i);
                    i-=1;
                }
            }
        }
        System.out.println("");
        
        LinkedList<ArrayList<String>> sorted= new LinkedList<>();
        
        for(int j=0; j<lists.size(); j++){
            sorted.add(s.pop());
        }
        
        System.out.println("\nOrder Processing List");
        display(sorted);
    }
    
    public void savageGardenProcessingList(LinkedList<ArrayList<String>> lists, int day){
        Random random= new Random();
        LinkedList<ArrayList<String>> listsCopy= new LinkedList<>();
        listsCopy.addAll(lists);
        Queue<ArrayList<String>> q= new LinkedList<>();   //stack- FIFO,LILO
        int number= 0;
        
            //System.out.println("Number picked: ");
            while(listsCopy.size()!=0){

                //start from the front
                int i=0;
                while(i<listsCopy.size()){
                    number= random.nextInt(7)+1;  //7 days in a week
                    
                    if(number==day){
                        q.add(listsCopy.get(i));
                        listsCopy.remove(i);
                    }
                    else{
                        i++;
                    }
                }
                
                if(listsCopy.size()==0)
                    break;
                
                //reach the end of the list
                //start from the last person, reverse
                int j=listsCopy.size()-1;
                //j= j-1;
                while(j>=0){
                    number= random.nextInt(7)+1;
                    //System.out.println(j + " ");
                    if(number==day){
                        q.add(listsCopy.get(j));
                        listsCopy.remove(j);
                    }
                    j--;
                }
            }

            LinkedList<ArrayList<String>> sorted= new LinkedList<>();

            for(int j=0; j<lists.size(); j++){
                sorted.add(q.poll());
            }

            System.out.println("\nOrder Processing List");
            display(sorted);
    }
}