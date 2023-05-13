package oop;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import uk.ac.leedsbeckett.oop.LBUGraphics;

@SuppressWarnings("serial")
public class GraphicsSystem extends LBUGraphics{
	
	private final int FRAME_WIDTH = 850; //Width of MainFrame
	private final int FRAME_HEIGHT = 450; //HEight of MainFrame
	JFrame MainFrame;	// declaring JFrame
	private ArrayList<String> commands = new ArrayList<String>(); // ArrayList to store sucessfully ran commands for saving purpose
	
	public static void main(String[] args)
    {
    	new GraphicsSystem();
    }
	
	//Constructor
	public GraphicsSystem(){
		MainFrame = new JFrame(); //Initializing JFrame
		MainFrame.setLayout(new FlowLayout());
		MainFrame.add(this);
		MainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		MainFrame.setVisible(true);
		MainFrame.setResizable(false);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ending the program after the frame is closed
		penDown();
	}
	
	private void save() {
		// When save method is called filechooser gets launched and user could choose the file or type in the name of file
		try {
    		String extension = "";
    		JFileChooser fileChooser = new JFileChooser("D:\\");
    		fileChooser.setSelectedFile(new File("turtleGraphics"));
			int i = fileChooser.showSaveDialog(this);
			if(i==JFileChooser.APPROVE_OPTION) {
				//Checking if file is selected or not
				File file = fileChooser.getSelectedFile();
				String filename = file.getName();
				int exe = filename.lastIndexOf('.');
				if(exe >=0 )
					//getting the extension of file
					extension = filename.substring(exe+1).toLowerCase();
				
				if(extension.equals("")) {
					//if user hasn't entered extension then asking in a panel what does user wanna save
					Object [] options = {"Commands", "Image"};
					int choice = JOptionPane.showOptionDialog(null, "What do you want to save?","Saving", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,null);
					if(choice == JOptionPane.YES_OPTION) {
						// if user selects commands then file is added with ".txt" extension
						extension = "txt";
						file = new File(file+".txt");
					}
					else if(choice ==JOptionPane.NO_OPTION) {
						// if user selects image then file is added with ".txt" extension
						extension = "png";
						file = new File(file+".png");
					}
				}
				if(extension.equals("txt")) {
					if(file.exists()){
						//While saving command if the file already exists, it asks if user want to append or not
	    				int check = JOptionPane.showConfirmDialog(null,"Do you want to append the code?", "Confirm" , JOptionPane.YES_NO_OPTION);
						if(check == JOptionPane.YES_OPTION) {
							//If user choose yes then the code is appended
							FileWriter fileWriter = new FileWriter(file,true);
							for(String cmd : commands) {
		    					fileWriter.write(cmd+"\n");
		    				}
		    				fileWriter.close();
		    				displayMessage("Commands append successful.");
						}
						else {
							//While saving command if the file already exists and user doesn't want to append, it asks if user want to overwrite or not
							int check2 = JOptionPane.showConfirmDialog(null, "Are you sure want to override this code?","Confirm", JOptionPane.YES_NO_OPTION);
							if(check2 == JOptionPane.YES_OPTION) {
								//If user choose yes the code is overwritten
								FileWriter fileWriter = new FileWriter(file);
								for(String a : commands) {
			    					fileWriter.write(a+"\n");
			    				}
			    				fileWriter.close();
			    				displayMessage("Commands overwritten successful.");
							}
							else
								//If user again choose no then the save method is recalled 
								save();
						}
	    			}
					else {
						//If file doesn't exist the command is saved
	    				FileWriter fileWriter = new FileWriter(file);
	    				for(String a : commands) {
	    					fileWriter.write(a+"\n");
	    				}
	    				fileWriter.close();
	    				displayMessage("Commands saved successful.");
					}
				}
				
				else if(extension.equals("jpg")) {
					// if extension is of image then image is captured from the JPanel and saved
					BufferedImage img = getBufferedImage();
					if(file.exists()) {
						// If file already exists then asking user to overwrite it
	    				int check = JOptionPane.showConfirmDialog(null,"Do you want to replace the previously saved image?", "Confirm" , JOptionPane.YES_NO_OPTION);
						if(check == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null,"Previously saved image is overwritten!!!");
							ImageIO.write(img, "jpg", file);
							displayMessage("Image saved successfully.");
	    				}
						else {
							//If user select no save method is called again
							save();
						}
					}
					else {
						// If the file doesn't exists the image is saved
						ImageIO.write(img, "jpg", file);
						displayMessage("Image saved successfully.");
					}
					
				}
				else if(extension.equals("jpeg") ) {
					BufferedImage img = getBufferedImage();
					if(file.exists()) {
	    				int check = JOptionPane.showConfirmDialog(null,"Do you want to replace the previously saved image?", "Confirm" , JOptionPane.YES_NO_OPTION);
						if(check == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null,"Previously saved image is overwritten!!!");
							ImageIO.write(img, "jpeg", file);
							displayMessage("Image saved successfully.");
	    				}
						else {
							save();
						}
					}

					else {
						ImageIO.write(img, "jpeg", file);
						displayMessage("Image saved successfully.");
					}
				}
				else if(extension.equals("png")) {
					BufferedImage img = getBufferedImage();
					if(file.exists()) {
	    				int check = JOptionPane.showConfirmDialog(null,"Do you want to replace the previously saved image?", "Confirm" , JOptionPane.YES_NO_OPTION);
						if(check == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null,"Previously saved image is overwritten!!!");
							ImageIO.write(img, "png", file);
							displayMessage("Image saved successfully.");
	    				}
						else {
							save();
						}
					}

					else {
						ImageIO.write(img, "png", file);
						displayMessage("Image saved successfully.");
					}
				}
				
				else {
    				displayMessage("Wrong extension! Unable to save.");
				}
			}
			else {
				displayMessage("Unable to save!!!");
			}

		}
		catch(Exception e) {
			displayMessage("Error! Unable to save.");
		}
	}
	
	private void load() {
		//When load method is called filechooser is launched
		try {
			String extension = "";
    		JFileChooser filechooser = new JFileChooser("D:\\");
    		filechooser.setSelectedFile(new File("turtleGraphics"));
			int i = filechooser.showOpenDialog(this);
			if(i==JFileChooser.APPROVE_OPTION) {
				File file = filechooser.getSelectedFile();
				String filename = file.getName();
				int exe = filename.lastIndexOf('.');
				if(exe >=0 )
					//Getting the extension of file selected by user
					extension = filename.substring(exe+1).toLowerCase();
				
				if(extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")){
					//If extension is of image type then the image is load in the panel
					BufferedImage img = ImageIO.read(file);
					setBufferedImage(img);
					displayMessage("Image loaded successfully.");
				}
				
				else if(extension.equals("txt")) {
					//If the extension is of text type commands are loaded in a new frame containing a texxtArea
					JFrame frame = new JFrame("Commands");
					JTextArea textArea = new JTextArea();
					try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Reading from the file
					{
						String commands = reader.readLine();//Storing the first line from the file to new String commands
						
						try {
						      while(commands != null) 
						      {
						    	  //Adding the command to new textarea and reading next line
						    	  textArea.append(commands+"\n");
						    	  commands = reader.readLine();
						      }
						    } 
						catch (Exception e) {
						      displayMessage("Error! loading commands.");
						    }
					}
					JScrollPane scrollPane = new JScrollPane(textArea);// Adding text area to scrollpane
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);// Setting vertical scrooling to the command so that user can see all the commands in text area
					frame.add(scrollPane);
					frame.setSize(500,500);
					frame.setLocationRelativeTo(filechooser);
					frame.setVisible(true);
					
					//Asking whether the user wants to run the code or not
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to run this code?","Command Run",JOptionPane.YES_NO_OPTION);
					
					if(reply == JOptionPane.YES_OPTION){
						try (Scanner dataReader = new Scanner(file)) {
							//If the user clicks yes the commands are loaded and the frame is closed
							while(dataReader.hasNextLine()) {
								processCommand(dataReader.nextLine());
							}
							frame.dispose();
							displayMessage("Commands loaded sucessfully.");
						}
			        }
					else if(reply == JOptionPane.NO_OPTION) {
						//If user clicks no the commands are previewed only
						displayMessage("Commands previewed successfully");
					}
				}
				
				else
					displayMessage("Error! This file cannot be opened here.");
			}
		}
		catch (Exception e) {
			displayMessage("Error! File not found.");
		}
	}
	
	public void about() {
    	clear();
    	reset();
    	penDown();
    	super.about(); //Calling about method of parent class(LBUGraphics)
    	JLabel label = new JLabel("Shasank Shrestha");
    	label.setFont(new Font("SansSerif", Font.ITALIC, 36)); //Changing the font of label
    	label.setBounds(230,180,400,250);
    	label.setForeground(Color.white);
    	this.add(label);
    	try {
    		Thread.sleep(5000); // to pause the further execution which is to remove label
    	}
    	catch(InterruptedException e) {
    	}
    	this.remove(label);
    }
	
	public void square(int length) {
		for(int i=1; i<=4;i++) {
	    	forward(length);
	    	turnLeft();
		}
	}
	
	public void penColor(int red, int green, int blue) {
		//Getting the rgb value less than 255 and setting the color of the pen 
		setPenColour(new Color(red,green,blue));
		displayMessage("The colour of pen is changed.");
	}
	
	public void triangle(int length) {
		//As triangle has three side loop is run for 3 times
		for(int i=1; i<=3;i++) {
	    	forward(length);
	    	turnRight(180-60);// Rotating by (120 = 180-60)
		}
    }
	
	 public void triangle(int sideA, int sideB, int sideC) {
		//Getting the angle of triangle from the provided sides
    	double angleA = Math.acos((Math.pow(sideC, 2) + Math.pow(sideB, 2) - Math.pow(sideA, 2)) / (2 * sideC * sideB));
    	double angleB = Math.acos((Math.pow(sideA, 2) + Math.pow(sideC, 2) - Math.pow(sideB, 2)) / (2 * sideA * sideC));
    	double angleC = Math.acos((Math.pow(sideA, 2) + Math.pow(sideB, 2) - Math.pow(sideC, 2)) / (2 * sideA * sideB));
    	
    	forward(sideA);
    	turnLeft((int)(180 - Math.ceil(Math.toDegrees(angleC))));    	
    	forward(sideB);    	
    	turnLeft((int)(180 - Math.ceil(Math.toDegrees(angleA))));
    	forward(sideC);
    	turnLeft((int)(180 - Math.ceil(Math.toDegrees(angleB))));
    }
	 
	 public void createAccount() {
		 //Creating object of Account class and calling methods
	    	Account obj = new Account();
	    	obj.createAccount();
	    }
	 
	 public void login() {
	    	Account obj = new Account();
	    	obj.loginAccount();
	    }
	    
	 public void changePassword() {
	    	Account obj = new Account();
	    	obj.changeAccountPassword();
	    }
	 
	 public void delete() {
	    	Account obj = new Account();
	    	obj.deleteAccount();
	    }

	 	
	 public void move() {
	    	JOptionPane.showMessageDialog(null, "Press Enter or ESC to exit!!!");
	    	MoveTurtle obj = new MoveTurtle();
	    	obj.start();// Starting thread 
	    }
	    
	 public void hello(int parameter) {
		Color color = getPenColour();
		reset();
		penDown();
		setStroke(parameter);
		clear();
		
		setxPos(60);
		setyPos(150);
		forward(150);
		forward(-75);
		turnLeft();
		forward(100);
		turnLeft();
		forward(75);
		forward(-150);
		
		setPenColour(Color.green);
		setxPos(205);
		setyPos(150);
		turnLeft();
		turnLeft();
		forward(150);
		turnLeft();
		forward(100);
		forward(-100);
		turnLeft();
		forward(75);
		turnRight();
		forward(100);
		forward(-100);
		turnLeft();
		forward(75);
		turnRight();
		forward(100);
		
		setPenColour(Color.blue);
		setxPos(350);
		setyPos(150);
		turnRight();
		forward(150);
		turnLeft();
		forward(100);
		
		setPenColour(Color.yellow);
		setxPos(485);
		setyPos(150);
		turnRight();
		forward(150);
		turnLeft();
		forward(100);
		
		setPenColour(Color.white);
		setxPos(670);
		setyPos(225);
		circle(75);
		reset();
		setStroke(1);
		setPenColour(color);
		displayMessage("Hello! Welcome to the program.");
	 }
	 
	
	public void processCommand(String command) {
		String[] parameter = command.toLowerCase().split(" "); //Converting the command to lower case and splitting the command with " "(space) expression
		String param = parameter[0];
		int parameterLength = parameter.length; // Length of parameter
		int[] paramsArr = new int[parameterLength-1]; // Array created to store numeric parameter
		boolean flag = true; // Boolean flag to check flow of program
		
		
		if(param.equals("")) {
			// If no command is entered then it informs user to enter codes
			flag = false;
			displayMessage("Enter some commands!!!");
		}
		
		if(parameterLength > 1 && flag == true) {
			// Converting the parameter which is string type to integer 
			try {
				for(int i = 1; i < parameterLength; i++) {
					double data = Math.round(Double.parseDouble(parameter[i])); // Taking data as double and rounding the value so that we get the closest value user entered
					if(data >= 0) {//Checking if the user entered parameter is negative or not
						paramsArr[i-1] = (int) data;
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: You have entered negative parameter!");
						clearInterface(); // Clearing Interface after error message is shown via DialogBox
						flag = false; // Setting the flag false to stop the flow of program if negative parameter error occured
					}
				}
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: You have entered non numeric parameter");
				clearInterface();
				flag = false; // Setting the flag false to stop the flow of program if string parameter error occured
			}
		}
		
		if(flag) // When parameters are positive and are of type integer then only further process takes place
		{
			switch(param) {
			
			case "about":
				//Checking if about method is called with parameter or not
				if(parameterLength == 1) { 
					about();
				}
				else {
					displayMessage("Error: About command doesn't have any parameters!");
					flag = false; // Declaring flag false so that if method that doesn't contain parameter is provider with parameter do get added to commands arraylist
				}
				break;
				
			case "clear":
				if(parameterLength == 1) {
					clear();
					clearInterface(); // Added this so that after clear command only the textfield and "OK" buttons are visible in JPanel
				}
				else {
					displayMessage("Error: Clear command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "reset":
				if(parameterLength == 1) {
					reset();
					setPenColour(Color.red); //Setting everything to what it was in the beginning when reset command is entered
					setStroke(1);
					penDown();
					displayMessage("Turtle has been reset.");
				}
				else {
					displayMessage("Error: Reset command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "penup":
				if(parameterLength == 1) {
					penUp();
	    			displayMessage("Pen is up. The turtle won't draw.");
				}
				else {
					flag = false;
					displayMessage("Error: Penup command doesn't have any parameters!");
				}
				break;
				
			case "pendown":
				if(parameterLength == 1) {
					penDown();
	    			displayMessage("Pen is down. The turtle will draw.");
				}
				else {
					flag = false;
					displayMessage("Pendown command doesn't have any parameters!");
				}
				break;
			
			case "turnleft":
				// If no parameter provided with turnleft command, it will rotate by 90 degree
				if(parameterLength == 1) {
					turnLeft(); 
	    			displayMessage("Turtle turn left by 90 degree.");
				}
				// If parameter is provided, it will rotate by provided degree
				else if(parameterLength == 2) {
					turnLeft(paramsArr[0]); 
	    			displayMessage("Turtle turn left by " +paramsArr[0]+" degree.");
				}
				else {
					displayMessage("Error: Turnleft command accepts only one parameters!");
					flag = false;
				}
				break;
				
			case "turnright":
				if(parameterLength == 1) {
					turnRight();
	    			displayMessage("Turtle turn right by 90 degree.");
				}
				else if(parameterLength == 2) {
					turnRight(paramsArr[0]);
	    			displayMessage("Turtle turn right by " +paramsArr[0]+" degree.");
				}
				else {
					displayMessage("Error: Turnright command accepts only one parameters!");
					flag = false;
				}
				break;
				
			case "forward":
				//if forward command is entered without any parameter then turtle moves by 50 pixel
				if(parameterLength == 1) {
					forward(50);
	    			displayMessage("Turtle moved forward by 50 pixel.");
				}
				//this command will only run if forward command is provided with one parameter
				else if(parameterLength == 2) { 
	    			forward(paramsArr[0]);
	    			displayMessage("Turtle moved forward by "+paramsArr[0]+" pixel.");
	    		}
				else {
					displayMessage("Error: Forward command accepts only one parameters!");
					flag = false;
				}
				break;
				
			case "backward":
				//if backward command is entered without any parameter then turtle moves by 50 pixel
				if(parameterLength == 1) {
					forward(-50);
	    			displayMessage("Turtle moved backward by 50 pixel.");
				}
				else if(parameterLength == 2) {
					//backward command is ran by providing negative value in forward method
	    			forward(-paramsArr[0]);
	    			displayMessage("Turtle moved backward by "+paramsArr[0]+" pixel.");
	    		}
				else {
					displayMessage("Error: Backward command accepts only one parameters!");
					flag = false;
				}
				break;	
			
			case "black":
				if(parameterLength == 1) {
					//Sets the penColor to black
					setPenColour(Color.black); 
	    			displayMessage("Pen colour has been changed to black.");
				}
				else {
					displayMessage("Error: Black command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "green":
				if(parameterLength == 1) {
					//Sets the penColor to green
					setPenColour(Color.green);
	    			displayMessage("Pen colour has been changed to green.");
				}
				else {
					displayMessage("Error: Green command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "red":
				if(parameterLength == 1) {
					//Sets the penColor to red
					setPenColour(Color.red);
	    			displayMessage("Pen colour has been changed to red.");
				}
				else {
					displayMessage("Error: Red command doesn't have any parameters!");
					flag = false;
				}
				break;	
				
			
			case "white":
				if(parameterLength == 1) {
					//Sets the penColor to white
					setPenColour(Color.white);
	    			displayMessage("Pen colour has been changed to white.");
				}
				else {
					displayMessage("Error: White command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "blue":
				if(parameterLength == 1) {
					//Sets the penColor to blue
					setPenColour(Color.blue);
	    			displayMessage("Pen colour has been changed to blue.");
				}
				else {
					displayMessage("Error: Blue command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "orange":
				if(parameterLength == 1) {
					//Sets the penColor to orange
					setPenColour(Color.orange);
	    			displayMessage("Pen colour has been changed to orange.");
				}
				else {
					displayMessage("Error: Orange command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "pink":
				if(parameterLength == 1) {
					//Sets the penColor to pink
					setPenColour(Color.pink);
	    			displayMessage("Pen colour has been changed to pink.");
				}
				else {
					displayMessage("Error: Pink command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "yellow":
				if(parameterLength == 1) {
					//Sets the penColor to  yellow
					setPenColour(Color.yellow);
	    			displayMessage("Pen colour has been changed to yellow.");
				}
				else {
					displayMessage("Error: Yellow command doesn't have any parameters!");
					flag = false;
				}
				break;
				
			case "square":
				//this command will only run if square command is provided with parameter else error message is displayed
				if(parameterLength == 2) {
	    			square(paramsArr[0]); //Calls square method 
	    			displayMessage("Square of "+paramsArr[0]+" pixel drawn.");
	    		}
				else {
					displayMessage("Error: Square command accepts one parameter!");
					flag = false;
				}
				break;
				
			case "circle":
				//this command will only run if circle command is provided with parameter else error message is displayed
				if(parameterLength == 2) {
	    			circle(paramsArr[0]);
	    			displayMessage("Circle of "+paramsArr[0]+" radius drawn.");
	    		}
				else {
					displayMessage("Error: Circle command accepts one parameter!");
					flag = false;
				}
				break;
				
			case "pencolour":
	    		if(parameterLength == 1) {
	    			/*it is extra part if there is no parameter with pencolour command a color picker box is generated
	    		  from with user can pick a colour which is set as pencolor*/ 
		    		try {
		    			Color color = JColorChooser.showDialog(null, "Pick a colour", getPenColour());
		    			if(!color.equals(null))
		    				// This part of code only runs if color is picked
		    				setPenColour(color);
	    			}
	    			catch(Exception e) {
	    			}
		    		// After setting the color which color is set as pencolor is displayed
	    			if(getPenColour().equals(Color.red))
	    				displayMessage("The colour of the pen is Red.");
	    			else if(getPenColour().equals(Color.black))
	    				displayMessage("The colour of the pen is Black.");
	    			else if(getPenColour().equals(Color.green))
	    				displayMessage("The colour of the pen is Green.");
	    			else if(getPenColour().equals(Color.white))
	    				displayMessage("The colour of the pen is White.");
	    			else if(getPenColour().equals(Color.blue))
	    				displayMessage("The colour of the pen is Blue.");
	    			else if(getPenColour().equals(Color.orange))
	    				displayMessage("The colour of the pen is Orange.");
	    			else if(getPenColour().equals(Color.pink))
	    				displayMessage("The colour of the pen is Pink.");
	    			else if(getPenColour().equals(Color.yellow))
	    				displayMessage("The colour of the pen is Yellow.");
	    			else
	    				displayMessage("The colour of the pen is "+getPenColour());
	    		}
	    		else if(parameterLength == 4 && paramsArr[0] <= 255 && paramsArr[1] <=255 && paramsArr[2] <= 255)
	    			// When pencolor is provided with 3 parameter which is less than 255 penColour method is called
	    			penColor(paramsArr[0],paramsArr[1],paramsArr[2]);
	    		else {
					displayMessage("Error: You have entered invalid number of parameters!");
					flag = false;
	    		}
	    		break;
	    		
			case "penwidth":
	    		if(parameterLength == 1) {
	    			// Displays the current width of pen
	    			displayMessage("Pen width is "+getStroke()+" pixel.");
	    		}
	    		else if(parameterLength == 2) {
	    			// If provided with parameter sets the penWidth to provided value
	    			setStroke(paramsArr[0]);
	    			displayMessage("Pen width is changed to "+paramsArr[0]+" pixel.");
	    		}
	    		else {
					displayMessage("Error: Penwidth command accepts only one parameter!");
					flag = false;
	    		}
	    		break;
	    		
			case "triangle":
				if (parameterLength == 1)
					displayMessage("Error: You have to provide a numeric parameter here!");
				else if(parameterLength == 2) {
					// For Equilateral triangle
					triangle(paramsArr[0]);
					displayMessage("Triangle of "+paramsArr[0]+" pixel drawn.");
				}
				else if(parameterLength == 4) {
					// Checks if sum of two sides of triangle is greater than third or not, if yes triangle method with 3 parameter is called
					if(paramsArr[0]+paramsArr[1]>paramsArr[2] && paramsArr[1]+paramsArr[2]>paramsArr[0] && paramsArr[0]+paramsArr[2]>paramsArr[1]) {
						triangle(paramsArr[0], paramsArr[1], paramsArr[2] );
						displayMessage("Triangle of "+paramsArr[0]+", "+paramsArr[1]+", "+paramsArr[2]+" pixel is drawn.");
					}
					else {
						displayMessage("Sum of two sides of triangle should be always greater than third side.");
					}
				}
				else {
					displayMessage("Error: You have entered invalid number of parameters!");
					flag = false;
				}
				break;
				
				
			
			case "save":
				flag = false; //Setting the flag false, to not add save command in the commands arraylist
				if(parameterLength == 1) {
		    		save(); 
				}
				else
					displayMessage("Error: Save command doesn't have any parameters!");
				break;
				
			
			case "load":
				flag = false; //Setting the flag false, to not add load command in the commands arraylist
				if(parameterLength == 1) {
					load();
				}
				else {
					displayMessage("Error: Save command doesn't have any parameters!");
					flag = false;
				}
	    		break;
	    	
			case "hello":
				//This is extra part
				if (parameterLength == 1) {
					setPenColour(Color.red);
					hello(5);
				}
				else if(parameterLength == 2) {
					setPenColour(Color.red);
					int width = Integer.parseInt(parameter[1]);
					if(width > 50) {
						// if penwidth exceeds 50 the panel becomes small for full "HELLO" to fit
						flag = false;
						displayMessage("The width of pen cannot exceed 50 for this command.");
					}
					else
						hello(width);
				}
				else
	    			displayMessage("Error: Hello command accepts only one parameter!");
				break;
				
			case "create":
				flag = false; // This is extra database part so, flag is set false so that it is not added to commands arraylist
				if(parameterLength == 1) {
					createAccount();
				}
				else
					displayMessage("Error: Create command doesn't have any parameters!");
				break;
	    	
	    	case "login":
	    		flag = false; // This is extra database part so, flag is set false so that it is not added to commands arraylist
	    		if(parameterLength == 1) {
					login();
				}
				else
					displayMessage("Error: Login command doesn't have any parameters!");
				break;
	    	case "update":
	    		flag = false; // This is extra database part so, flag is set false so that it is not added to commands arraylist
	    		if(parameterLength == 1) {
	    			changePassword();
				}
				else
					displayMessage("Error: Login command doesn't have any parameters!");
				break;
				
	    	case "delete":
	    		flag = false; // This is extra database part so, flag is set false so that it is not added to commands arraylist
	    		if(parameterLength == 1) {
					delete();
				}
				else
					displayMessage("Error: Delete command doesn't have any parameters!");
				break;
				
	    	case "move":
	    		flag = false; // This is extra part so, flag is set false so that it is not added to commands arraylist
	    		if(parameterLength == 1) {
					move();
				}
				else
					displayMessage("Error: Move command doesn't have any parameters!");
				break;
				
	    	case "exit":
	    			MainFrame.dispose();
	    		break;
		
			default:
				// If the command doesn't match error message is displayed through dialogbox
				JOptionPane.showMessageDialog(null,"Error: You have entered invalid command!");
				clearInterface();
				flag = false;// Setting flag false to avoid invalid commands being added to commands arraylist
				
			}
		}
		
		
		
		if(flag) {
			/*If the command is valid and has correct number of parameter
			 then only the command is added to the commands arraylist*/
			commands.add(command);
		}
		
		boolean outofBoundFlag = false;	// this boolean is to check is the turtle moved out of panel
		
		if(getxPos() > FRAME_WIDTH) {
			// When the turtle gets out of frame width then its position is set to the (FRAME_WIDTH - 80)
    		setxPos(FRAME_WIDTH-80);
    		updateUI(); //Updating UI after setting turtle position
    		outofBoundFlag = true;
    	}
    	else if(getxPos() < 0) {
    		setxPos(30);
    		updateUI();
    		outofBoundFlag = true;
    	}
		
    	if(getyPos() > FRAME_HEIGHT) {
    		// When the turtle gets out of frame height then its position is set to the (FRAME_HEIGHT-80)
    		setyPos(FRAME_HEIGHT-80);
    		updateUI();
    		outofBoundFlag = true;
    	}
    	else if(getyPos() < 0) {
    		setyPos(30);
    		updateUI();
    		outofBoundFlag = true;
    	}
    	
    	if(outofBoundFlag)
    		// After turtle moved out of bounds and it position is corrected, out of bounds message is displayed to user
    		JOptionPane.showMessageDialog(null,"Turtle moved out of bounds!!!");
	}
	
	
	
	public class MoveTurtle extends Thread implements KeyListener{
		/* It is for the extra imagination part
		 MoveTurtle is sub class if GraphicsSystem*/
		
		private JFrame frame;
		public void run() {
			setGUIVisible(false); // to remove the text field from turtle graphics
			frame = new JFrame();
			JLabel l = new JLabel("Press ENTER to exit"); //JLabel to displam message to the user how to exit this mode
			l.setForeground(Color.white);
			frame.add(l);
			frame.setSize(50,80);
			frame.addKeyListener(this);
			frame.setAlwaysOnTop(true);
			frame.getContentPane().setBackground(Color.black); // Setting the background of new panel black so that it matches the color of GraphicsSystem panel
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter() {
				//Adding window listener so that if this is window is closed by pressing red 'x' button the turtule grapics GUI becomes visible
			    @Override
			    public void windowClosing(WindowEvent windowEvent) {
			    	setGUIVisible(true);
			    }
			});
		}
		
		//When user presses 'W','A','S','D' or "up","down","right","left" command the turtle moves and draws
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==10 || e.getKeyCode()==27) {
				// 10 is keyCode of "ENTER" and 27 is of "ESC" pressing either of this two buttons closes the frame and sets LBUGraphics visible
				frame.dispose();
				setGUIVisible(true);
			}
			switch(e.getKeyCode()) {
			
				case 37:
					turnRight(10);
					break;
					
				case 38:
					forward(10);
					break;
				
				case 39:
					turnLeft(10);
					break;
					
				case 40:
					forward(-10);
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyChar()) {
			
				case 'a':
					turnRight(10);
					break;
				
				case 'd':
					turnLeft(10);
					break;
					
				case 'w':
					penUp();
					forward(10);
					penDown();
					break;
				
				case 's':
					penUp();
					forward(-10);
					penDown();
					break;
			
			}
			
		}
	}
}
