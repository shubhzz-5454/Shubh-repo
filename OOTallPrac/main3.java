import java.util.*; 
  
public class main3 { 
    public static void main(String[] args) { 
        // ArrayList 
        ArrayList<String> names = new ArrayList<>(); 
        names.add("Amit"); 
        names.add("Sneha"); 
        names.add("Rahul"); 
        names.add("Priya"); 
  
        System.out.println("ArrayList:"); 
        Iterator<String> itr = names.iterator(); 
        while (itr.hasNext()) { 
            System.out.println(itr.next()); 
        } 
  
        // HashMap 
        HashMap<Integer, String> students = new HashMap<>(); 
        students.put(101, "Amit"); 
        students.put(102, "Sneha"); 
        students.put(103, "Rahul"); 
  
        System.out.println("\nHashMap:"); 
        for (Map.Entry<Integer, String> entry : students.entrySet()) { 
            System.out.println(entry.getKey() + " : " + entry.getValue()); 
        } 
    } 
} 
