// Base class 
class Person { 
    String name; 
    int age; 
    void setPerson(String n, int a) { name = n; age = a; } 
    void showPerson() { 
        System.out.println("Name : " + name); 
        System.out.println("Age  : " + age); 
    } 
} 
  
// Single inheritance: Student extends Person 
class Student extends Person { 
    int rollNo; 
    void setRoll(int r) { rollNo = r; } 
    void showStudent() { 
        showPerson(); 
        System.out.println("Roll : " + rollNo); 
    } 
} 
  
// Multilevel inheritance: Graduate extends Student (which extends Person) 
class Graduate extends Student { 
    String course; 
    void setCourse(String c) { course = c; } 
    void showGraduate() { 
        showStudent(); 
        System.out.println("Course : " + course); 
    } 
} 
  
public class InheritanceDemo { 
    public static void main(String[] args) { 
        System.out.println("--- Single Inheritance ---"); 
        Student s = new Student(); 
        s.setPerson("Rahul", 20); 
        s.setRoll(101); 
        s.showStudent(); 
  
        System.out.println(); 
        System.out.println("--- Multilevel Inheritance ---"); 
        Graduate g = new Graduate(); 
        g.setPerson("Priya", 21); 
        g.setRoll(202); 
        g.setCourse("B.Tech CSE"); 
        g.showGraduate(); 
    } 
} 