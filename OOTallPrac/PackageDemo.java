import mypack.MathOperations;     // importing the user-defined package 
  
public class PackageDemo { 
    public static void main(String[] args) { 
        MathOperations m = new MathOperations(); 
  
        System.out.println("Addition of 15 and 25       = " + m.add(15, 25)); 
        System.out.println("Multiplication of 6 and 7   = " + m.multiply(6, 7)); 
        System.out.println("Square of 9                 = " + m.square(9)); 
    } 
} 