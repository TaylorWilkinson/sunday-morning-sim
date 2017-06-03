package main;

import static main.Util.random;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import background.items.Background;
import background.items.InfoBoard;
import background.items.SunSpotConcreteFactory;
import background.items.SunSpotFactory;
import background.items.SunSpotShape;
import background.items.TVScreen;
//import ddf.minim.AudioPlayer;
//import ddf.minim.Minim;
//import ddf.minim.*;
import processing.core.PApplet;
import sound.Sound;
import interactive.items.CoffeeCan;
import interactive.items.DripCone;
import interactive.items.Jam;
import interactive.items.Kettle;
import interactive.items.MoveableObject;
import interactive.items.Mugs;
import interactive.items.PeanutButter;
import interactive.items.Plate;
import interactive.items.TeaBox;
import interactive.items.Toaster;
import main.AppPanel.MyMouseListener;
import processing.core.PApplet;

/*
 * Screen:
 * The class that is in charge of drawing all the gameState screens
 * and instantiating all of the required objects.
 * Creator: Taylor Wilkinson
 */

public class Screen implements ActionListener {
	
	protected Font introFont;
	protected Font instructionFont;
	protected Font mainFont;
	protected Font smallFont;
	
	//BACKGROUND SCREEN IMAGES
	Background beginning;
	Background kitchen1;
	Background kitchen2;
	Background livingRoom;
	
	//TEXT BOXES
	InfoBoard introTextBox;
	InfoBoard screen1TextBox;
	InfoBoard screen2TextBox;
	
	//INTERACTIVE OBJECTS: KITCHEN ONE
	CoffeeCan coffee;
	TeaBox tea;
	Mugs mug;
	DripCone cone;
	Kettle kettle;
	//INTERACTIVE OBJECTS: KITCHEN TWO
	Mugs mug2;
	Jam jamJar;
	PeanutButter pbJar;
	Plate plate;
	Toaster toaster;
	
	//OBJECTS: LIVING ROOM
	Mugs mugFinal;
	Plate plateFinal;
	TVScreen tvScreen;
	
	MoveableObject currentHeldObject;
	
	//Font colors
	private Color introFontColor = new Color(250,201,184);
	private Color alternateFontColor = new Color(179,224,242);
	
	//Sunspots on introduction screen
	SunSpotFactory sunspotMaker;
	public SunSpotShape[] sunspots = new SunSpotShape[50];
	
	// *** if I can import Minim, use these***
//	private Minim minim;
//	private AudioPlayer bgMusic;
	
	
	public Screen() {
		//intro screen
		introFont = new Font ("Open Sans", Font.BOLD, 80);
		mainFont = new Font ("Open Sans", Font.PLAIN, 25);
		instructionFont = new Font ("Open Sans", Font.PLAIN, 15);
		smallFont = new Font ("Open Sans", Font.PLAIN, 10);
		
		//draws sun spots on introduction screen
		sunspotMaker = new SunSpotConcreteFactory();
		for (int i = 0; i < sunspots.length; i++) {
			int r = (int) (random(0, 2));
			if (r < 1) {
				sunspots[i] = sunspotMaker.createSunspot(1);
			} else {
				sunspots[i] = sunspotMaker.createSunspot(2);
			}
		}
		
		//background screens
		beginning = new Background(0, 0, AppPanel.panW, AppPanel.panH, "assets/beginning2.png");
		kitchen1 = new Background(0, 0, AppPanel.panW, AppPanel.panH, "assets/kitchen1.png");
		kitchen2 = new Background(0, 0, AppPanel.panW, AppPanel.panH, "assets/kitchen2.png");
		livingRoom = new Background(0, 0, AppPanel.panW, AppPanel.panH, "assets/livingRoom.png");
		
		introTextBox = new InfoBoard(200, 425, 0.25, 0.15, "assets/infoPanel.png");
		
		//SCREEN ONE - Make a drinK!
		//Text Box
		screen1TextBox = new InfoBoard(775, 5, 0.15, 0.11, "assets/infoVerticalPanel.png");
		//shelved items
		coffee = new CoffeeCan(50, 131, 0.25, "assets/coffeeCanister.png");
		tea = new TeaBox(250, 132, 0.25, "assets/teaCanister.png");
		cone = new DripCone(470, 170, 0.25, "assets/dripCone.png");
		mug = new Mugs(600, 180, 0.25, "assets/mug.png", "assets/cuppaTea.png", "assets/cuppaCoffee.png");
		//counter items
		kettle = new Kettle(30, 500, 1, 1, 0.20, "assets/emptyKettle.png", "assets/fullKettle.png");
		
		//SCREEN TWO - Make some toast!
		//Text Box
		screen2TextBox = new InfoBoard(20, 20, 0.15, 0.15, "assets/infoPanel.png");
		//shelved
		jamJar = new Jam(600, 140, 0.25, "assets/jam.png", "assets/jamOpen.png");
		pbJar = new PeanutButter(800, 140, 0.25, "assets/peanutButter.png", "assets/peanutButterOpen.png");
		//counter
		mug2 = new Mugs(50, 580, 0.27, "assets/mug.png", "assets/cuppaTea.png", "assets/cuppaCoffee.png");
		plate = new Plate(200, 625, 0.22, "assets/plate.png", "assets/plateWithToast.png", "assets/toastWithJam.png", "assets/toastWithPB.png");
		toaster = new Toaster(800, 500, 0.20, "assets/toasterUntoasted.png", "assets/toasterFull.png", "assets/toasterToasted.png");
		
		//SCREEN THREE
		//your meal on the table
		plateFinal = new Plate(300, 500, 0.22, "assets/plate.png", "assets/plateWithToast.png", "assets/toastWithJam.png", "assets/toastWithPB.png");
		mugFinal = new Mugs(700, 475, 0.27, "assets/mug.png", "assets/cuppaTea.png", "assets/cuppaCoffee.png");
		tvScreen = new TVScreen(283, 50, 0.239);
		
//		minim = new Minim(new PApplet());
//		bgMusic = minim.loadFile("assets/toby_fox-Hotel.wav");
	}
	
	public void introScreen(Graphics2D g2d){
		//Sound.play("assets/toby_fox-hotel.wav");
		beginning.drawBackground(g2d);
		
		//sunspots over the intro image
		for (int i=0; i<sunspots.length; i++){
			sunspots[i].display(g2d);
		}
		
		//Title
		g2d.setFont(introFont);
		g2d.setColor(introFontColor);
		g2d.drawString("SUNDAY MORNING", 120, 250);
		g2d.drawString("SIMULATOR", 240, 315);
		
		//text box
		introTextBox.drawBox(g2d);
		
		g2d.setFont(instructionFont);
		g2d.setColor(Color.black);
		g2d.drawString("Oh glorious day.", 430, 480);
		g2d.drawString("You've woken up, and you don't have work or class to worry about today.", 235, 500);
		g2d.drawString("Why don't you make yourself breakfast? Coffee or tea, maybe some toast.", 235, 520);
		g2d.drawString("You can control objects on the screen by using the arrow keys,", 280, 540);
		g2d.drawString("or by clicking and dragging objects with the mouse.", 315, 560);
		
		g2d.setFont(mainFont);
		g2d.setColor(alternateFontColor);
		g2d.drawString("press the button above to start the day", 260, 600);
	}
	
	public void screenOne(Graphics2D g2d){
		//first kitchen screen - Make your drink!
		kitchen1.drawBackground(g2d);
		
		//text box
		screen1TextBox.drawBox(g2d);
		g2d.setFont(mainFont);
		g2d.setColor(alternateFontColor);
		g2d.drawString("CAFFEINE", 830, 40);
		g2d.drawString("TIME!", 850, 65);
	
		g2d.setFont(smallFont);
		g2d.setColor(Color.black);
		g2d.drawString("First, you can choose coffee or tea", 795, 90);
		g2d.drawString("by bringing the required ingredients", 795, 100);
		g2d.drawString("off the shelf and down to the counter.", 795, 110);
		g2d.drawString("Then, fill the kettle with water.", 795, 130);
		g2d.drawString("You can move it with the arrow keys.", 795, 140);
		g2d.drawString("COFFEE:", 795, 160);
		g2d.drawString("- canister of ground coffee", 795, 170);
		g2d.drawString("- drip cone, with the filter inside", 795, 180);
		g2d.drawString("- a mug", 795, 190);
		g2d.drawString("TEA:", 795, 210);
		g2d.drawString("- tin of tea bags", 795, 220);
		g2d.drawString("- a mug", 795, 230);
		
		coffee.drawObjects(g2d);
		tea.drawObjects(g2d);
		cone.drawObjects(g2d);
		mug.drawObjects(g2d);
		kettle.drawObjects(g2d);
	}
	
	public void screenTwo(Graphics2D g2d){
		//second kitchen screen - make your toast!
		kitchen2.drawBackground(g2d);
		
		//text box
		screen2TextBox.drawBox(g2d);
		g2d.setFont(mainFont);
		g2d.setColor(alternateFontColor);
		g2d.drawString("TOAST", 160, 60);
		g2d.drawString("TIME!", 166, 85);
		
		g2d.setFont(smallFont);
		g2d.setColor(Color.black);
		g2d.drawString("Why are we even up if we don't have food?", 50, 110);
		g2d.drawString("Let's make some quick, easy, satisfying toast.", 50, 120);
		g2d.drawString("Click the toaster to press the button down,", 50, 140);
		g2d.drawString("and again to pop the toast up.", 50, 150);
		g2d.drawString("But we can't just have dry toast. We aren't heathens.", 50, 170);
		g2d.drawString("Click on the jam or the peanut butter,", 50, 180);
		g2d.drawString("and I'll fIGURE SOMETHING OUT.", 50, 190);
		
		mug2.drawObjects(g2d);	
		plate.drawObjects(g2d);
		toaster.drawObjects(g2d);
		jamJar.drawObjects(g2d);
		pbJar.drawObjects(g2d);
	}
	
	public void screenThree(Graphics2D g2d){
		//living room screen
		//eat, drink, and be merry with cartoons
		livingRoom.drawBackground(g2d);
		mugFinal.drawObjects(g2d);
		plateFinal.drawObjects(g2d);
		tvScreen.display(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < sunspots.length; i++) {
			sunspots[i].move();
		}
	}

}
