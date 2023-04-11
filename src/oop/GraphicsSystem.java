package oop;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;

import uk.ac.leedsbeckett.oop.LBUGraphics;

public class GraphicsSystem extends LBUGraphics{
	
	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 450;

	public GraphicsSystem(){
		JFrame MainFrame = new JFrame();
		MainFrame.setLayout(new FlowLayout());
		MainFrame.add(this);
		MainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		MainFrame.setVisible(true);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void about() {
		super.about();
		displayMessage("Shasank Shrestha");
		JLabel me = new JLabel("Shasank Shrestha");
		me.setForeground(Color.white);
		me.setBounds(FRAME_WIDTH/2-78, FRAME_HEIGHT/2-25, 150, 150);
		JLabel me2 = new JLabel("Shasank Shrestha");
		me2.setForeground(Color.gray);
		me2.setBounds(FRAME_WIDTH/2-68, FRAME_HEIGHT/2-15, 150, 150);
		JLabel me3 = new JLabel("Shasank Shrestha");
		me3.setForeground(Color.gray);
		me3.setBounds(FRAME_WIDTH/2-88, FRAME_HEIGHT/2-35, 150, 150);
		this.add(me);
		this.add(me2);
		this.add(me3);
		try {
			Thread.sleep(5000);
		}
		catch(InterruptedException err) {
			err.printStackTrace();
		}
		this.remove(me);
		this.remove(me2);
		this.remove(me3);
	}
	
	public void square(int length) {
    	forward(length);
    	turnLeft();
    	forward(length);
    	turnLeft();
    	forward(length);
    	turnLeft();
    	forward(length);
    	turnLeft();
	}
	
	public void penColor(int red, int green, int blue) {
		setPenColour(new Color(red,green,blue));
		displayMessage("The colour of pen is changed.");
	}
	
	public void triangle(int length) {
    	forward(length);
    	turnRight(180-60);
    	forward(length);
    	turnRight(180-60);
    	forward(length);
    	turnRight(180-60);
    	}
	
	 public void triangle(int a, int b, int c) {
	    	double A = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));
	    	double B = Math.acos((Math.pow(a, 2) + Math.pow(c, 2) - Math.pow(b, 2)) / (2 * a * c));
	    	double C = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b));
	    	
	    	forward(c);
	    	turnLeft((int)(180 - Math.ceil(Math.toDegrees(B))));    	
	    	forward(a);    	
	    	turnLeft((int)(180 - Math.ceil(Math.toDegrees(C))));
	    	forward(b);
	    	turnLeft((int)(180 - Math.ceil(Math.toDegrees(A))));
	    }
	 
	 public void hello(int parameter) {
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
		

		setxPos(350);
		setyPos(150);
		turnRight();
		forward(150);
		turnLeft();
		forward(100);
		
		setxPos(485);
		setyPos(150);
		turnRight();
		forward(150);
		turnLeft();
		forward(100);
		
		setxPos(670);
		setyPos(225);
		circle(75);
		reset();
	 }
	 
	
	public void processCommand(String command) {
		String[] parameter = command.toLowerCase().split(" ");
		String param = parameter[0];
		int parameterLength = parameter.length;
		int[] paramsArr = new int[parameterLength-1];
		boolean flag = true;

		if(param.equals("save") || param.equals("load") || param.equals("hello")) {
			flag = false;
			switch(param) {
			case "save":
				if(parameterLength == 1) {
					BufferedImage img = getBufferedImage();
					File f1 = new File("SavedImage.jpeg");
					if(f1.exists())
	    				JOptionPane.showMessageDialog(null,"Previously saved image is overwritten.");

					try {
						ImageIO.write(img, "jpeg", f1);
						displayMessage("Image saved successfully by Savedimage.jpeg name.");
					}
					catch(IOException e) {
	    				JOptionPane.showMessageDialog(null,"Error in saving image");
	    				e.printStackTrace();
					}
				}
				else if(parameterLength == 2) {
					BufferedImage img = getBufferedImage();
					File f1 = new File(parameter[1]+".jpeg");
					if(f1.exists())
	    				JOptionPane.showMessageDialog(null,"Previously saved image is overwritten.");

					try {
						ImageIO.write(img, "jpeg", f1);
						displayMessage("Image saved successfully by "+parameter[1]+".jpeg name.");
					}
					catch(IOException e) {
	    				JOptionPane.showMessageDialog(null,"Error in saving image");
	    				e.printStackTrace();
					}
				}
				else
	    			JOptionPane.showMessageDialog(null, "Error: You have entered invalid parameter!");
				break;
				
				
			case "load":
				if(parameterLength == 1) {
					if(new File("SavedImage.jpeg").exists()) {
						try {
							BufferedImage img = ImageIO.read(new File("SavedImage.jpeg"));
							setBufferedImage(img);
							displayMessage("Image loaded successfully");
						}
						catch(IOException e) {
		    				JOptionPane.showMessageDialog(null,"Error in loading image!");
		    				e.printStackTrace();
						}
					}
					else
    					JOptionPane.showMessageDialog(null,"Error: Save image before loading!");
				}
				
				else if(parameterLength == 2) {
					if(new File(parameter[1]+".jpeg").exists()) {
						try {
							BufferedImage img = ImageIO.read(new File(parameter[1]+".jpeg"));
							setBufferedImage(img);
							displayMessage(parameter[1]+".jpeg"+" Image loaded successfully");
						}
						catch(IOException e) {
		    				JOptionPane.showMessageDialog(null,"Error in loading image!");
		    				e.printStackTrace();
						}
					}
					else
    					JOptionPane.showMessageDialog(null,"Error: "+parameter[1] + ".jpeg image not found!");
				}
				else
	    			JOptionPane.showMessageDialog(null, "Error: You have entered invalid parameter!");
				break;

			
			case "hello":
				if (parameterLength == 1) {
					setPenColour(Color.red);
					hello(5);
				}
				else if(parameterLength == 2) {
					setPenColour(Color.red);
					int width = Integer.parseInt(parameter[1]);
					hello(width);
				}
				else if(parameterLength == 3) {
					setPenColour(Color.getColor(parameter[2]));
					int width = Integer.parseInt(parameter[1]);
					hello(width);
				}
				else
	    			JOptionPane.showMessageDialog(null, "Error: You have entered invalid parameter!");
				break;
			}
		}
		
		
		
		if(parameterLength > 1 && flag == true) {
			try {
				for(int i = 1; i < parameterLength; i++) {
					double data = Math.round(Double.parseDouble(parameter[i]));
					if(data >= 0) {
						paramsArr[i-1] = (int) data;
					}
					else {
						JOptionPane.showMessageDialog(null, "Error: You have entered negative parameter!");
						flag = false;
					}
				}
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: You have entered non numeric parameter");
				flag = false;
			}
		}
		
		if(flag) {
			switch(param) {
			
			case "about":
				if(parameterLength == 1) {
					about();
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "clear":
				if(parameterLength == 1) {
					clear();
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "reset":
				if(parameterLength == 1) {
					reset();
					setPenColour(Color.red);
					setStroke(0);
					displayMessage("Turtle position has been reset.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "penup":
				if(parameterLength == 1) {
					penUp();
	    			displayMessage("Pen is up. The turtle won't draw.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "pendown":
				if(parameterLength == 1) {
					penDown();
	    			displayMessage("Pen is down. The turtle will draw.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
			
			case "turnleft":
				if(parameterLength == 1) {
					turnLeft();
	    			displayMessage("Turtle turn left by 90 degree.");
				}
				else if(parameterLength == 2) {
					turnLeft(paramsArr[0]);
	    			displayMessage("Turtle turn left by " +paramsArr[0]+" degree.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
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
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "forward":
				if(parameterLength == 2) {
	    			forward(paramsArr[0]);
	    			displayMessage("Turtle moved forward by "+paramsArr[0]+" pixel.");
	    		}
				else
					displayMessage("Error: You have to provide valid numeric parameter here!");
				break;
				
			case "backward":
				if(parameterLength == 2) {
	    			forward(-1*paramsArr[0]);
	    			displayMessage("Turtle moved backward by "+paramsArr[0]+" pixel.");
	    		}
				else
					displayMessage("Error: You have to provide valid numeric parameter here!");
				break;	
			
			case "black":
				if(parameterLength == 1) {
					setPenColour(Color.black);
	    			displayMessage("Pen color has been changed to black.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "green":
				if(parameterLength == 1) {
					setPenColour(Color.green);
	    			displayMessage("Pen color has been changed to green.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "red":
				if(parameterLength == 1) {
					setPenColour(Color.red);
	    			displayMessage("Pen color has been changed to red.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;	
				
			
			case "white":
				if(parameterLength == 1) {
					setPenColour(Color.white);
	    			displayMessage("Pen color has been changed to white.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "blue":
				if(parameterLength == 1) {
					setPenColour(Color.blue);
	    			displayMessage("Pen color has been changed to blue.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "orange":
				if(parameterLength == 1) {
					setPenColour(Color.orange);
	    			displayMessage("Pen color has been changed to orange.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "pink":
				if(parameterLength == 1) {
					setPenColour(Color.pink);
	    			displayMessage("Pen color has been changed to pink.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "yellow":
				if(parameterLength == 1) {
					setPenColour(Color.yellow);
	    			displayMessage("Pen color has been changed to yellow.");
				}
				else
					displayMessage("Error: You have entered invalid parameter!");
				break;
				
			case "square":
				if(parameterLength == 2) {
	    			square(paramsArr[0]);
	    			displayMessage("Square of "+paramsArr[0]+" length drawn.");
	    		}
				else
					displayMessage("Error: You have to provide valid numeric parameter here!");
				break;
				
			case "pencolor":
	    		if(parameterLength == 1) {
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
	    				displayMessage("The color of the pen is "+getPenColour());
	    		}
	    		else if(parameterLength == 2 && paramsArr[0] <= 255) 
	    			penColor(paramsArr[0],0,0);
	    		else if(parameterLength == 3 && paramsArr[0] <= 255 && paramsArr[1] <=255)
	    			penColor(paramsArr[0],paramsArr[1],0);
	    		else if(parameterLength == 4 && paramsArr[0] <= 255 && paramsArr[1] <=255 && paramsArr[1] <= 255)
	    			penColor(paramsArr[0],paramsArr[1],paramsArr[2]);
	    		else
					displayMessage("Error: You have entered invalid parameter!");
	    		break;
	    		
			case "penwidth":
	    		if(parameterLength == 1) {
	    			displayMessage("Pen width is "+getStroke()+" pixel.");
	    		}
	    		else if(parameterLength == 2) {
	    			setStroke(paramsArr[0]);
	    			displayMessage("Pen width is changed to "+paramsArr[0]+" pixel.");
	    		}
	    		else
					displayMessage("Error: You have entered invalid parameter!");

	    		break;
	    		
			case "triangle":
				if (parameterLength == 1)
					displayMessage("Error: You have to provide a numeric parameter here!");
				else if(parameterLength == 2) {
					triangle(paramsArr[0]);
					displayMessage("Triangle of "+paramsArr[0]+" pixel drawn.");
				}
				else if(parameterLength == 4) {
					if(paramsArr[0]+paramsArr[1]>paramsArr[2] && paramsArr[1]+paramsArr[2]>paramsArr[0] && paramsArr[0]+paramsArr[2]>paramsArr[1]) {
						triangle(paramsArr[0], paramsArr[1], paramsArr[2] );
						displayMessage("Triangle of "+paramsArr[0]+", "+paramsArr[1]+", "+paramsArr[2]+" pixel is drawn.");
					}
					else {
						displayMessage("Sum of two sides of triangle should be always greater than third side.");
					}
				}
				else
					displayMessage("Error: You have to provide valid numeric parameter here!");
				break;
				
			default:
				JOptionPane.showMessageDialog(null,"Error: You have entered invalid command!");
				flag = false;
				
			}
		}
	}
}
