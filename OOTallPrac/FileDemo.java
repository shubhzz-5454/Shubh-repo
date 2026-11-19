import java.io.*; 
  
public class FileDemo { 
    public static void main(String[] args) { 
        try { 
            // Writing to a file 
            FileWriter writer = new FileWriter("student.txt"); 
            writer.write("Student Name: Shubh\n"); 
            writer.write("Roll No: 2425\n"); 
            writer.write("Marks: 85"); 
            writer.close(); 
  
            // Reading from the file 
            BufferedReader reader = new BufferedReader( 
                    new FileReader("student.txt")); 
            String line; 
            System.out.println("File Contents:"); 
            while ((line = reader.readLine()) != null) { 
                System.out.println(line); 
            } 
            reader.close(); 
        } catch (IOException e) { 
            System.out.println("File error: " + e.getMessage()); 
        } 
    } 
} 
