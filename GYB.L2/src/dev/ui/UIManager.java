package dev.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.main.Handler;

public class UIManager {
	
	UILabel titleBlock;
	UILabel nameJM;
	UILabel nameJR;
	UILabel nameNS;
	UILabel instructionsI;
	UILabel instructionsII;
	UILabel instructionsIII;
	UILabel coords;
	
	int coordx, coordy, fx, fy;
	String sendx, sendy;

	Handler handler;
	private ArrayList<UIObject> uiObjects;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		uiObjects = new ArrayList<UIObject>();
		
		initialize();
	}
	
	private void initialize() {
		titleBlock = new UILabel(490, 55, 0, 0, "GYB.LII", 60);
		nameJM = new UILabel(490, 70, 0, 0, "Jacob Maguire", 10);
		nameJR = new UILabel(490, 80, 0, 0, "Jack Rollin", 10);
		nameNS = new UILabel(490, 90, 0, 0, "Nolan Stengel", 10);
		instructionsI = new UILabel(490, 110, 0, 0, "Left Click to add a point.", 14);
		instructionsII = new UILabel(490, 125, 0, 0, "Middle Click to save template.", 14);
		instructionsIII = new UILabel(490, 140, 0, 0, "Right Click to undo a point.", 14);
		
		uiObjects.add(titleBlock);
		uiObjects.add(nameJM);
		uiObjects.add(nameJR);
		uiObjects.add(nameNS);
		uiObjects.add(instructionsI);
		uiObjects.add(instructionsII);
		uiObjects.add(instructionsIII);
	}

	public void tick() {
		if (handler.getMouseManager().getMouseX() > 480) {
			coordx = 480;
		} else {
			coordx = handler.getMouseManager().getMouseX();
			coordy = handler.getMouseManager().getMouseY();
		}
		for (UIObject uio : uiObjects) {
			uio.tick();
		}
		
		coords = new UILabel(490, 470, 0, 0, "X: " + Integer.toString(coordx) + " Y: " + Integer.toString(coordy), 20);
	}
	
	public void render(Graphics g) {
		//Basic Colors
		g.setColor(new Color(200, 200, 200));
		g.drawRect(0, 0, 479, 479);
		g.drawRect(480, 0, 239, 479);
		g.setColor(new Color(220, 220, 220));
		g.fillRect(481, 1, 238, 478);
		
		//Grid
		g.setColor(new Color(200, 200, 200));
		for(int x = 0; x < 9; x++) {
			g.drawLine((60 * x), 0, (60 * x), 480);
		}
		for(int y = 0; y < 9; y++) {
			g.drawLine(480, (60 * y), 0, (60 * y));
		}
		
		//Origin
		g.setColor(Color.RED);
		g.drawLine(0, 0, 60, 0);
		g.drawLine(0, 0, 0, 60);
		g.drawLine(0, 0, 10, 10);
		
		//UIObjects
		for (UIObject uio : uiObjects) {
			uio.render(g);
		}

		coords.render(g);
	}
	
	public void onMouseRelease(MouseEvent e) {
		for(UIObject uio : uiObjects) {
			uio.onMouseRelease(e);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for (UIObject uio : uiObjects) {
			uio.onMouseMove(e);
		}
	}
	
	public void addUIObject(UIObject uio) {
		uiObjects.add(uio);
	}
	
	public void removeUIObject(UIObject uio) {
		uiObjects.remove(uio);
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	public ArrayList<UIObject> getUIObjects() {
		return uiObjects;
	}
	
	public void setUIObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}
}
