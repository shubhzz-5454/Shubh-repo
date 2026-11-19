class Student { 
    int rollNo; 
    String name; 
    double marks; 
  
    // Default constructor 
    Student() { 
        rollNo = 0; 
        name = "Not Assigned"; 
        marks = 0.0; 
    } 
  
    // Parameterized constructor 
    Student(int r, String n, double m) { 
        rollNo = r; 
        name = n; 
        marks = m; 
    } 
  
    // Copy constructor 
    Student(Student s) { 
        rollNo = s.rollNo; 
        name = s.name; 
        marks = s.marks; 
    } 
  
    void display() { 
        System.out.println("Roll No : " + rollNo); 
        System.out.println("Name    : " + name); 
        System.out.println("Marks   : " + marks); 
        System.out.println("-----------------------"); 
    } 
  
    public static void main(String[] args) { 
        Student s1 = new Student();                         // default 
        Student s2 = new Student(101, "shubh", 92.5);     // parameterized 
        Student s3 = new Student(s2);                       // copy 
  
        System.out.println("Object s1 (Default Constructor):"); 
        s1.display(); 
        System.out.println("Object s2 (Parameterized Constructor):"); 
        s2.display(); 
        System.out.println("Object s3 (Copy Constructor):"); 
        s3.display(); 
    } 
} 