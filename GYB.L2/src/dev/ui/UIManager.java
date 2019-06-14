package dev.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.main.Handler;

public class UIManager {

	Handler handler;
	private ArrayList<UIObject> uiObjects;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		uiObjects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for (UIObject uio : uiObjects) {
			uio.tick();
		}
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
