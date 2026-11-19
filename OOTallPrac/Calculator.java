class Calculator { 
    static int count = 0;          // static variable shared by all objects 
  
    // Static block - runs once when the class is loaded 
    static { 
        System.out.println("Static block executed: Calculator class loaded"); 
    } 
  
    // Method overloading - same name, different parameters 
    int add(int a, int b) { 
        count++; 
        return a + b; 
    } 
    int add(int a, int b, int c) { 
        count++; 
        return a + b + c; 
    } 
    double add(double a, double b) { 
        count++; 
        return a + b; 
    } 
  
    // Static method - called without creating an object 
    static void showCount() { 
        System.out.println("Total add() calls made = " + count); 
    } 
  
    public static void main(String[] args) { 
        Calculator c = new Calculator(); 
  
        System.out.println("add(10, 20)         = " + c.add(10, 20)); 
        System.out.println("add(10, 20, 30)     = " + c.add(10, 20, 30)); 
        System.out.println("add(5.5, 4.5)       = " + c.add(5.5, 4.5)); 
  
        Calculator.showCount();    // calling static method using class name 
    } 
}