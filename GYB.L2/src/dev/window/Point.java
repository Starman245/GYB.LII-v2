package dev.window;

import java.awt.Color;
import java.awt.Graphics;

import dev.main.Handler;

public class Point extends WindowObject {
	
	int x, y;

	public Point(float x, float y, Handler handler) {
		super(x, y, handler);
		this.x = (int) x;
		this.y = (int) y;
	}

	@Override
	protected void tick() {
		
	}

	@Override
	protected void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int) x, (int) y, (int) x + 1, (int) y + 1);
		g.drawLine((int) x, (int) y + 1, (int) x + 1, (int) y); 
		//System.out.println(x + " " + y);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
