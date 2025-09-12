
package banksystem2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


public class BankSystem2 {

    
    public static void main(String[] args) {
        Queue<String> customerNames = new LinkedList<>();
        customerNames.add("Alice");
        customerNames.add("Bob");
        customerNames.add("Charlie");
        customerNames.add("Diana");
        customerNames.add("Eve"); 
        
        Map<String, Integer> serviceTime = new HashMap<>();
        serviceTime.put("Alice", 5);
        serviceTime.put("Bob",3);
        serviceTime.put("Charlie",4);
        serviceTime.put("Diana",6);
        serviceTime.put("Eve",2);
        
        System.out.println(customerNames);
        //System.out.println(serviceTime);
        for(String i : serviceTime.keySet()){
            System.out.println("key: " + i + " value: " + serviceTime.get(i));
        }
        
        List<String> servedCustomers = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            String served = customerNames.poll();
            if(served != null){
                servedCustomers.add(served);
                System.out.println("names: " + served + " service time: "+ serviceTime.get(served) + " minutes");
                
            }
        }
        Stack<String> vipCustomers = new Stack<>();
        vipCustomers.push("Frank");
        vipCustomers.push("Grace");
        
        while(!vipCustomers.empty()){
            String vip = vipCustomers.pop();
            customerNames.add(vip);
        }
        System.out.println("final state of the queue: " + customerNames);
        System.out.println("List of serveed customers: " + servedCustomers);
    }
    
}
//Alice=5,
// Bob=3, Charlie=4, Diana=6, Eve=2