package dev.window;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import dev.input.MouseManager;
import dev.main.Handler;

public abstract class WindowObject {
	
	protected float x, y;
	protected boolean hovering = false;
	protected Rectangle bounds;
	
	protected Handler handler;
	
	protected ClickListener clickListener;
	
	protected MouseManager mouseManager;
	
	public WindowObject(float x, float y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		
		bounds = new Rectangle((int) x, (int) y, 1, 1);
	}

	protected abstract void tick();

	protected abstract void render(Graphics g);
	
	public void onMouseRelease(MouseEvent e) {
		
	}
	
	public void onMouseMove(MouseEvent e) {
		if (handler.getMouseManager().getMouseBounds().intersects(bounds)) {
			hovering = true;
		} else {
			hovering = false;
		}
	}
	
	public void onMouseClick(MouseEvent e) {
		
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
}
