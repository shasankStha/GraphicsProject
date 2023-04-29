package oop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Account extends JFrame{
	private JFrame frame;
	private JPanel content;
	private JTextField firstname, lastname, userid;
	private JPasswordField password;
	private JButton submit;
	
	public void createAccount(){
		frame = new JFrame("Create Account");
		content = new JPanel();
        setContentPane(content);
        content.setLayout(null);
		
        JLabel registration = new JLabel("REGISTRATION");
        registration.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        registration.setBounds(160, 30, 200, 20);
        content.add(registration);
        
        JLabel fname = new JLabel("First Name :");
        fname.setBounds(30, 80, 200, 20);
        firstname = new JTextField();
        firstname.setBounds(100, 82, 125, 20);
        content.add(fname);
        content.add(firstname);
        
        JLabel lname = new JLabel("Last Name :");
        lname.setBounds(250, 80, 200, 20);
        lastname = new JTextField();
        lastname.setBounds(320, 82, 125, 20);
        content.add(lname);
        content.add(lastname);
        
        JLabel id = new JLabel("User ID :");
        id.setBounds(30, 130, 200, 20);
        userid = new JTextField();
        userid.setBounds(100, 132, 125, 20);
        content.add(id);
        content.add(userid);
        
        JLabel psw = new JLabel("Password :");
        psw.setBounds(250, 130, 200, 20);
        password = new JPasswordField();
        password.setBounds(320, 132, 125, 20);
        content.add(psw);
        content.add(password);
        
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String firstName = firstname.getText();
				String lastName = lastname.getText();
				String userId = userid.getText();
				String pswd = password.getText();
				if(!firstName.equals("") && !lastName.equals("") && !userId.equals("") && !pswd.equals("")) {
					try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/account", "root", "080309");

	                    String query = "INSERT INTO acc values('" + firstName + "','" + lastName + "','" + userId + "','" +
	                        pswd + "')";

	                    Statement sta = connection.createStatement();
	                    int x = sta.executeUpdate(query);
	                    if (x == 0) {
	                        JOptionPane.showMessageDialog(submit, "This is alredy exist");
	                    } else {
	                        JOptionPane.showMessageDialog(submit,
	                            "Welcome, " + firstName +" "+ lastName + "\nYour account is sucessfully created");
	                    }
	                } catch (Exception exception) {
	                	JOptionPane.showMessageDialog(null, "Unable to save");
	                }
					frame.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "These fields cannot be empty");
			}
        	
        });
        submit.setBounds(200, 162, 80, 30);
        content.add(submit);
        
        frame.add(content);
		frame.setResizable(false);
		frame.setBounds(500, 250, 500, 250);
		frame.setVisible(true);
	}
	
		public void loginAccount() {
			frame = new JFrame("Log in");
			content = new JPanel();
	        setContentPane(content);
	        content.setLayout(null);
	        
	        JLabel login = new JLabel("Log In");
	        login.setFont(new Font("Times New Roman", Font.PLAIN, 24));
	        login.setBounds(120, 30, 200, 30);
	        content.add(login);
	        
	        JLabel id = new JLabel("User ID :");
	        id.setBounds(30, 90, 200, 20);
	        userid = new JTextField();
	        userid.setBounds(100, 92, 145, 20);
	        content.add(id);
	        content.add(userid);
			
	        JLabel psw = new JLabel("Password :");
	        psw.setBounds(30, 130, 200, 20);
	        password = new JPasswordField();
	        password.setBounds(100, 132, 145, 20);
	        content.add(psw);
	        content.add(password);
	        
	        submit = new JButton("Submit");
	        submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String username = userid.getText();
					String psw = password.getText();
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/account", "root", "080309");
						String query = "SELECT * FROM acc WHERE user_name = '"+username+"' and password = '"+psw+"';";
						Statement sta = connection.createStatement();
						ResultSet rs = sta.executeQuery(query);
						if(rs.next()) {
							String fname = rs.getString(1);
							String lname = rs.getString(2);
							JOptionPane.showMessageDialog(null,"Hello, "+fname+" "+lname+"\nYou have logged in successfully.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Error!!! Enter valid username and password.");
						}
						frame.dispose();
					}
					catch(Exception e1) {
						
					JOptionPane.showMessageDialog(null,"Error!!! unable to read data");			
						}
				}
	        	
	        });
	        submit.setBounds(120, 162, 80, 30);
	        content.add(submit);
			
			frame.add(content);
			frame.setResizable(false);
			frame.setBounds(500, 250, 300, 250);
			frame.setVisible(true);
		}
		
		public void deleteAccount() {
			frame = new JFrame("Delete");
			content = new JPanel();
	        setContentPane(content);
	        content.setLayout(null);
	        
	        JLabel delete = new JLabel("Delete Account");
	        delete.setFont(new Font("Times New Roman", Font.PLAIN, 24));
	        delete.setBounds(80, 30, 200, 30);
	        content.add(delete);
	        
	        JLabel id = new JLabel("User ID :");
	        id.setBounds(30, 90, 200, 20);
	        userid = new JTextField();
	        userid.setBounds(100, 92, 145, 20);
	        content.add(id);
	        content.add(userid);
			
	        JLabel psw = new JLabel("Password :");
	        psw.setBounds(30, 130, 200, 20);
	        password = new JPasswordField();
	        password.setBounds(100, 132, 145, 20);
	        content.add(psw);
	        content.add(password);
	        
	        submit = new JButton("Submit");
	        submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String username = userid.getText();
					String psw = password.getText();
					try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/account", "root", "080309");
						String query = "SELECT * FROM acc WHERE user_name = '"+username+"' and password = '"+psw+"';";
						Statement sta = connection.createStatement();
						ResultSet rs = sta.executeQuery(query);
						if(rs.next()) {
							int reply = JOptionPane.showConfirmDialog(null, "Are you sure want to delete your account?","Delete Account",JOptionPane.YES_NO_OPTION);
							if(reply == JOptionPane.YES_OPTION) {
								String que = "DELETE FROM acc WHERE user_name = '"+username+"' and password = '"+psw+"';";
								sta.executeUpdate(que);
								JOptionPane.showMessageDialog(null, "Your account has been successfully deleted.");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Error!!! Enter valid username and password.");
						}
						frame.dispose();
					}
					catch(Exception err) {
							JOptionPane.showMessageDialog(null,"Error!!! Unable to delete data");			
						}
				}
	        	
	        });
	        submit.setBounds(120, 162, 80, 30);
	        content.add(submit);
			
			frame.add(content);
			frame.setResizable(false);
			frame.setBounds(500, 250, 300, 250);
			frame.setVisible(true);
		}
		

}
