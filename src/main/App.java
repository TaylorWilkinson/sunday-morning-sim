package main;

import javax.swing.JFrame;

/*
 * App:
 * The main appplication screen.
 */

public class App extends JFrame {
	
	AppPanel apnl;
	
	App(String title){
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		AppPanel apnl = new AppPanel(this);
		this.add(apnl);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new App("Sunday Morning Simulator");
	}
}
