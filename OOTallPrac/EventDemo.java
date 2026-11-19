import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
  
public class EventDemo extends JFrame implements ActionListener, MouseListener { 
    JButton button; 
    JLabel label; 
  
    EventDemo() { 
        setTitle("Event Handling Demo"); 
        setSize(500, 300); 
        setLayout(new FlowLayout()); 
  
        button = new JButton("Click Me"); 
        label = new JLabel("Move or click the mouse"); 
  
        button.addActionListener(this);   // ActionListener on button 
        addMouseListener(this);           // MouseListener on window 
  
        add(button); 
        add(label); 
  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true); 
    } 
  
    // ActionListener method 
    public void actionPerformed(ActionEvent e) { 
        label.setText("Button Clicked"); 
    } 
  
    // MouseListener methods 
    public void mouseClicked(MouseEvent e) { 
        label.setText("Mouse Clicked at: " + e.getX() + ", " + e.getY()); 
    } 
    public void mousePressed(MouseEvent e) { } 
    public void mouseReleased(MouseEvent e) { } 
    public void mouseEntered(MouseEvent e) { 
        label.setText("Mouse Entered"); 
    } 
    public void mouseExited(MouseEvent e) { 
        label.setText("Mouse Exited"); 
    } 
  
    public static void main(String[] args) { 
        new EventDemo(); 
    } 
} 