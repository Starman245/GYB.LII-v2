package dev.input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.main.Handler;
import dev.ui.UIManager;
import dev.window.ObjectManager;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	
	private Rectangle mouseBounds;
	private int mouseWidth = 6, mouseHeight = 6;
	
	UIManager uiManager;
	ObjectManager objectManager;
	
	public MouseManager(Handler handler) {
		this.objectManager = handler.getObjectManager();
		this.uiManager = handler.getUIManager();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if (uiManager != null) {
			uiManager.onMouseRelease(e);
		}
		if (objectManager != null) {
			objectManager.onMouseRelease(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if (objectManager != null) {
			objectManager.onMouseClicked(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
		if (uiManager != null) {
			uiManager.onMouseRelease(e);
		}
		if (objectManager != null) {
			objectManager.onMouseRelease(e);
		}
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public void tick() {
		mouseBounds = new Rectangle(mouseX - 3, mouseY - 3, mouseWidth, mouseHeight);
		//System.out.println(mouseX + " " + mouseY);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(mouseX - 3, mouseY - 3, mouseWidth, mouseHeight);
	}
	
	public Rectangle getMouseBounds() {
		return mouseBounds;
	}
	
}
