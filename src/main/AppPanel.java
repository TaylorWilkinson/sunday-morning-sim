package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * AppPanel:
 * The main panel class that initializes all of the gameStates, screens, and methods of interaction
 * Creator: Taylor Wilkinson
 */

public class AppPanel extends JPanel implements ActionListener {
	
	//window dimensions
	public static final int panW = 1000;
	public static final int panH = 700;
	
	//Screens and Game states
	Screen screen;
	private static gameState gstate;
	private enum gameState {
		START, SCREEN1, SCREEN2, SCREEN3
	}
	
	//button on start screen
	private JButton startButton;
	private JButton secondButton;
	private JButton thirdButton;
	private JButton replayButton;
	private boolean goToScreen1 = false;
	private boolean goToScreen2 = false;
	private boolean goToScreen3 = false;
	
	private JFrame frame;
	
	//arrow keys
	private boolean left, right;
	private double keyForce = 20;
	
	//Screen One booleans
	public static boolean kettleFull;
	public static boolean teaMade;
	public static boolean coffeeMade;
	//Screen Two booleans
	public static boolean clickToasterDown;
	public static boolean clickToasterDone;
	public static boolean toastDone;
	public static boolean openJamJar;
	public static boolean openPBJar;
	public static boolean pbToast;
	public static boolean jamToast;
	public static boolean teaCup;
	public static boolean coffeeCup;
	private int toastCount = 0;
	
	AppPanel(JFrame frame) {
		super();
		setPreferredSize(new Dimension(panW, panH));
		Dimension size= this.getPreferredSize();
		this.frame = frame;
				
		screen = new Screen();
		
		MyKeyListener mkl = new MyKeyListener();
		this.addKeyListener(mkl);
		this.setFocusable(true);
		
		//set start screen
		gstate = gameState.START;
		
		//set button for start screen
		startButton = new JButton("CLICK TO START");
		startButton.setFocusable(false);
		add(startButton, BorderLayout.SOUTH);
		startButton.setVisible(false);
		startButton.addActionListener(this);
		//
		secondButton = new JButton("TIME FOR TOAST!");
		secondButton.setFocusable(false);
		add(secondButton, BorderLayout.SOUTH);
		secondButton.setVisible(false);
		secondButton.addActionListener(this);
		//
		thirdButton = new JButton("LET'S KICK BACK!");
		thirdButton.setFocusable(false);
		add(thirdButton, BorderLayout.SOUTH);
		thirdButton.setVisible(false);
		thirdButton.addActionListener(this);
		//
		replayButton = new JButton("replay?");
		replayButton.setFocusable(false);
		add(replayButton, BorderLayout.SOUTH);
		replayButton.setVisible(false);
		replayButton.addActionListener(this);
		
		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseMotionListener());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//SET SCREEN
		switch(gstate){
		case START:
			screen.introScreen(g2d);
			startButton.setVisible(true);
			goToScreen1 = true;
			break;
			
		case SCREEN1:
			screen.screenOne(g2d);
			
			secondButton.setVisible(false);
			
			if (screen.kettle.currentImg == screen.kettle.fullKettleImg){
				kettleFull = true;
			}
			
			//CHECK IF ALL NECESSARY INGREDIENTS ON COUNTER
			if ((teaMade == true) && (kettleFull == true)) {
				screen.mug.currentMug=screen.mug.teaMug;
				secondButton.setVisible(true);
				goToScreen2 = true;
			} else if ((coffeeMade == true) && (kettleFull==true)) {
				screen.mug.currentMug = screen.mug.coffeeMug;
				secondButton.setVisible(true);
				goToScreen2 = true;
			}
			
			break;
			
		case SCREEN2:
			screen.screenTwo(g2d);
			
			//draw the proper drink
			if (teaMade == true) {
				screen.mug2.currentMug=screen.mug.teaMug;
				teaCup = true;
			} else if (coffeeMade == true) {
				screen.mug2.currentMug = screen.mug.coffeeMug;
				coffeeCup = true;
			}
			
			if ((openJamJar == true) && (toastDone == true)) {
				thirdButton.setVisible(true);
				goToScreen3 = true;
				jamToast = true;
			} else if ((openPBJar == true) && (toastDone == true)) {
				thirdButton.setVisible(true);
				goToScreen3 = true;
				pbToast = true;
			}
			
			break;
			
		case SCREEN3:
			screen.screenThree(g2d);
			
			if (pbToast == true) {
				screen.plateFinal.currentImg = screen.plate.toastWithPB;
			} else if (jamToast == true) {
				screen.plateFinal.currentImg = screen.plate.toastWithJam;
			}
			
			if (teaCup == true) {
				screen.mugFinal.currentMug = screen.mug2.teaMug;
			} else if (coffeeCup == true) {
				screen.mugFinal.currentMug = screen.mug2.coffeeMug;
			}
			
			reset();
			replayButton.setVisible(true);
			goToScreen1 = true;
			
			break;
		}
	}
	
	public class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e){
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			//SCREEN ONE
			//Cursor On Objects
			if (screen.mug.cursorOnObject(mouseX, mouseY)){
				screen.currentHeldObject = screen.mug;
			}
			
			if (screen.coffee.cursorOnObject(mouseX, mouseY)){
				screen.currentHeldObject = screen.coffee;
			}
			
			if (screen.tea.cursorOnObject(mouseX, mouseY)){
				screen.currentHeldObject = screen.tea;
			}
			
			if (screen.cone.cursorOnObject(mouseX, mouseY)){
				screen.currentHeldObject = screen.cone;
			}
			
			
			//SCREEN TWO
			//Toaster
			if (screen.toaster.cursorOnObject(mouseX, mouseY)){
				clickToasterDown = true;
				toastCount += 1;
				screen.currentHeldObject = screen.toaster;
				if (toastCount > 1) {
					clickToasterDown = false;
					clickToasterDone = true;
					toastCount = 2;
					screen.currentHeldObject = screen.toaster;
					screen.plate.currentImg = screen.plate.toastOnPlate;
					toastDone = true;
				}
			}
			
			//Cursor On Objects
			if (screen.jamJar.cursorOnObject(mouseX, mouseY)) {
				screen.currentHeldObject = screen.jamJar;
			}
			if (screen.pbJar.cursorOnObject(mouseX, mouseY)) {
				screen.currentHeldObject = screen.pbJar;
			}
			
			repaint();
		}
		
		public void mouseReleased(MouseEvent e){
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			//Once you set an object down, clicking around the screen
			//won't move the image to that spot
			
			//screen.currentHeldObject.resetLocation(mouseX, mouseY);
			//screen.currentHeldObject = null;
			
			
			//CHECK IF ALL NECESSARY INGREDIENTS ON COUNTER
			if (screen.mug.objectOnCounter() && screen.tea.objectOnCounter()) {
				teaMade = true;
				coffeeMade = false;
			} else if (screen.mug.objectOnCounter() && screen.coffee.objectOnCounter() && screen.cone.objectOnCounter()) {
				coffeeMade = true;
				teaMade = false;
			}
			
			////////////////////
			//SCREEN TWO
			//Toaster
			if (clickToasterDown == true){
				screen.toaster.currentImg = screen.toaster.cookingToaster;
			}
			if (clickToasterDone == true){
				screen.toaster.currentImg = screen.toaster.finishedToaster;
			}
			//moving jam or pb
			if (screen.jamJar.objectOnCounter()){
				screen.jamJar.currentImg = screen.jamJar.jamOpenImg;
				screen.plate.currentImg = screen.plate.toastWithJam;
				openJamJar = true;
				openPBJar = false;
			} else if (screen.pbJar.objectOnCounter()){
				screen.pbJar.currentImg = screen.pbJar.pbOpenImg;
				
				screen.plate.currentImg = screen.plate.toastWithPB;
				openPBJar = true;
				openJamJar = false;
			}

			repaint();
		}
		
		public void mouseDragged(MouseEvent e){
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			screen.currentHeldObject.resetLocation(mouseX, mouseY);
			
			repaint();
		}
	}
	
	public class MyMouseMotionListener extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e){
			double mouseX = e.getX();
			double mouseY = e.getY();
		}
	}
	
public class MyKeyListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				right = true;
				screen.kettle.move(keyForce);
			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
				left = true;
				screen.kettle.move(-keyForce);
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){ 
				right = false;
			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT){ 
				left = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (goToScreen1) {
			frame.dispose();
			frame = new App("Sunday Morning Simulator");
			gstate = gameState.SCREEN1;
		} else if (goToScreen2) {
			frame.dispose();
			frame = new App("Sunday Morning Simulator");
			gstate = gameState.SCREEN2;
		} else if (goToScreen3){
			frame.dispose();
			frame = new App("Sunday Morning Simulator");
			gstate = gameState.SCREEN3;
		}
	}
	
	public void reset() {
			screen.kettle.currentImg = screen.kettle.kettleImg;
			screen.mug.currentMug = screen.mug.mugImg;
			screen.jamJar.currentImg = screen.jamJar.jamImg;
			screen.pbJar.currentImg = screen.pbJar.pbImg;
			screen.toaster.currentImg = screen.toaster.startingToaster;
			screen.plate.currentImg = screen.plate.emptyPlate;
			
			kettleFull = false;
			teaMade = false;
			coffeeMade = false;
	}
		
}
