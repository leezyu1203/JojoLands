import java.util.*;

public class BurningDownTheHouse {
    private Location emporio;
    private Tree<Location> tree;
    private Queue<Location> printOrder = new LinkedList<>();
    private Queue<Integer> level = new LinkedList<>();

    public BurningDownTheHouse(){
        Scanner sc = new Scanner(System.in);
        feature:
        while(true) {
            System.out.print("Enter location of Emporio: ");
            String emporioName = sc.nextLine();
            checkExist:
            while (true) {
                for (int i = 0; i < Main.locations.size(); i++) {
                    if (Main.locations.get(i).isLoc(emporioName)) {
                        emporio = Main.locations.get(i);
                        break checkExist;
                    }
                }
                System.out.printf("No \'%s\' exist in the map.\nPlease enter again: ", emporioName);
                emporioName = sc.nextLine();
            }
            System.out.println("========================================================================");
            System.out.println("Guide: ");
            algorithm();
            display();
            System.out.println("========================================================================");
            System.out.print("Continue the next Emporio(y/n): ");
            String cont = sc.nextLine();
            if(cont.equalsIgnoreCase("n")){
                System.out.println("Exiting Burning Down The House...");
                System.out.println("========================================================================");
                break feature;
            }
            System.out.println("========================================================================");
        }
    }

    private void algorithm(){
        tree = new Tree<>(emporio);
        Node<Location> temp = tree.head;
        while(temp != null && tree.getSize() != Main.locations.size()){
            Location tempDes = temp.location;
            List<Location> tempAdjacent = tempDes.getAdjacent();
            for(int i = 0; i < tempAdjacent.size(); i++){
                Location tempSource = tempAdjacent.get(i);
                if(!tree.hasNode(tempSource)){
                    tree.addPath(tempDes,tempSource);
                }
            } temp = temp.next;
        }
    }
    private void setPrintOrder(){
        int lvl = 1;
        Node<Location> node = tree.head;
        printOrder.offer(node.location);
        level.offer(lvl);
        Stack<Node<Location>> branch = new Stack<>();
        Stack<Integer> branchLvl = new Stack<>();
        while(node != null){
            while(node.forward != null){
                Node<Location> temp = node.forward;
                while(printOrder.contains(temp.location))
                    temp = temp.forward;
                if(temp.forward != null){
                    branch.push(node);
                    branchLvl.push(lvl);
                } printOrder.offer(temp.location);
                level.offer(++lvl);
                while(node.location != temp.location)
                    node = node.next;
            }
            if(!branch.empty()) {
                node = branch.pop();
                lvl = branchLvl.pop();
            }
            else break;
        }
    }
    private void display(){
        setPrintOrder();
        String output = "    ↖";
        Stack<Integer> branchLvl = new Stack<>();
        Stack<Integer> space = new Stack<>();
        while(!printOrder.isEmpty()){
            int lvl = level.poll();
            if(lvl == 1)
                System.out.println(printOrder.poll().getName());
            else{
                if(!branchLvl.isEmpty()){
                    while (lvl < branchLvl.peek()){
                        branchLvl.pop();
                        space.pop();
                    }
                    if(lvl == branchLvl.peek()){
                        System.out.println(output);
                        output = "";
                        for(int i = 0; i < space.peek()-4; i++){
                            output += " ";
                        } output += "    ↖";
                        space.pop();
                        branchLvl.pop();
                    }
                } if(level.contains(lvl)){
                    branchLvl.push(lvl);
                    space.push(output.length()-1);
                } output += " ← " + printOrder.poll().getName();
            }
        }
        System.out.println(output);
    }

}