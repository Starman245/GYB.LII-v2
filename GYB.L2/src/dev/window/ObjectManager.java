package dev.window;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import dev.main.Handler;
import dev.util.Output;

public class ObjectManager {

	Handler handler;
	ArrayList<WindowObject> windowObjects;
	private int mouseX, mouseY;
	private int counter = 0;
	
	private Output output;
	
	public ObjectManager(Handler handler) {
		this.handler = handler;
		windowObjects = new ArrayList<WindowObject>();
	}
	
	public void tick() {
		getInput(windowObjects);
		for (WindowObject wo : windowObjects) {
			wo.tick();
		}
		//System.out.println(windowObjects.size());
	}
	
	public void render(Graphics g) {
		for (WindowObject wo : windowObjects) {
			wo.render(g);
		}
		drawConnections(windowObjects, g);
	}
	
	public void onMouseRelease(MouseEvent e) {
		
	}
	
	public void onMouseMove(MouseEvent e) {
		for (WindowObject wo : windowObjects) {
			wo.onMouseMove(e);
		}
	}
	
	public void addObject(WindowObject wo) {
		windowObjects.add(wo);
	}
	
	public void removeObject(WindowObject wo) {
		windowObjects.remove(wo);
	}
	
	public ArrayList<WindowObject> getObjects() {
		return windowObjects;
	}
	
	public void setObjects(ArrayList<WindowObject> windowObjects) {
		this.windowObjects = windowObjects;
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void onMouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (mouseX <= 480 && mouseY <= 480) {
				//System.out.println(mouseX + " " + mouseY);
				Point point = new Point(mouseX, mouseY, handler);
				addObject(point);
			}
			//System.out.println("Left Pressed");
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			int a = windowObjects.size();
			if (a > 0) {
				windowObjects.remove(a - 1);
			}
			//System.out.println("Right Pressed");
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			output = new Output(windowObjects);
			output.write();
			//System.out.println("Middle Pressed");
		}
	}
	
	void drawConnections(ArrayList<WindowObject> objects, Graphics g) {
		if (objects.size() > 1) {
			int s = objects.size();
			for (int c = 0; c < s; c++) {
				if (c != s - 1) {
					int x1 = (int) objects.get(c).getX();
					int y1 = (int) objects.get(c).getY();
					int x2 = (int) objects.get(c + 1).getX();
					int y2 = (int) objects.get(c + 1).getY();
					g.drawLine(x1, y1, x2, y2);
				}
			}
			//g.drawLine((int) objects.get(s - 1).x, (int) objects.get(s - 1).y, (int) objects.get(0).x, (int) objects.get(0).y);
		}
	}
	
	private void getInput(ArrayList<WindowObject> wobs) {
		if (handler.getKeyManager().delete) {
			System.out.println("Delete");
			int h = wobs.size();
			//if (h > 0) {
				for (int i = h; i >= 0; i--) {
					wobs.remove(i);
				}
			//}
			setObjects(wobs);
		}
		if (handler.getKeyManager().enter) {
			System.out.println("Enter");
		}
		if (handler.getKeyManager().backspace) {
			System.out.println("Backspace");
			int h = wobs.size();
			if (h > 0) {
				wobs.remove(h - 1);
			}
			setObjects(wobs);
		}
	}
	
}
