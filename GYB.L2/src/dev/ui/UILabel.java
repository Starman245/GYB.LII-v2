package dev.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UILabel extends UIObject {
	
	Font font;
	
	int x, y, font_size;
	String text;

	public UILabel(float x, float y, int width, int height, String text, int font_size) {
		super(x, y, width, height);
		this.x = (int) x;
		this.y = (int) y;
		this.text = text;
		font = new Font("Helvetica", Font.BOLD, font_size);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(text, x, y);
	}

}
