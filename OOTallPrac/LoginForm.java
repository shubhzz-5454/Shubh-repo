import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
  
public class LoginForm extends JFrame implements ActionListener { 
    JTextField username; 
    JPasswordField password; 
    JButton login; 
  
    LoginForm() { 
        setTitle("Login Form"); 
        setSize(400, 250); 
        setLayout(new GridLayout(3, 2)); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  
        add(new JLabel("Username:")); 
        username = new JTextField(); 
        add(username); 
  
        add(new JLabel("Password:")); 
        password = new JPasswordField(); 
        add(password); 
  
        login = new JButton("Login"); 
        login.addActionListener(this); 
        add(new JLabel("")); 
        add(login); 
  
        setVisible(true); 
    } 
  
    public void actionPerformed(ActionEvent e) { 
        String user = username.getText(); 
        String pass = new String(password.getPassword()); 
  
        if (user.equals("admin") && pass.equals("1234")) { 
            JOptionPane.showMessageDialog(this, "Login Successful"); 
        } else { 
            JOptionPane.showMessageDialog(this, "Invalid Username or Password"); 
        } 
    } 
  
    public static void main(String[] args) { 
        new LoginForm(); 
    } 
} 