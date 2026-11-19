// Abstract class: has abstract and normal methods 
abstract class Shape { 
    abstract double area();            // abstract method (no body) 
  
    void display() {                   // normal method 
        System.out.println("Area of the shape = " + area()); 
    } 
} 
  
// Interface: all methods are abstract by default 
interface Printable { 
    void print(); 
} 
  
class Circle extends Shape implements Printable { 
    double radius = 5; 
  
    double area() { 
        return 3.14 * radius * radius; 
    } 
  
    public void print() { 
        System.out.println("Printing Circle with radius " + radius); 
    } 
} 
  
class Rectangle extends Shape implements Printable { 
    double length = 6, breadth = 4; 
  
    double area() { 
        return length * breadth; 
    } 
  
    public void print() { 
        System.out.println("Printing Rectangle " + length + " x " + breadth); 
    } 
} 
  
 class InterfaceAbstractDemo { 
    public static void main(String[] args) { 
        Circle c = new Circle(); 
        Rectangle r = new Rectangle(); 
  
        c.print(); 
        c.display(); 
        System.out.println(); 
        r.print(); 
        r.display(); 
    } 
}