package dev.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.display.Display;
import dev.input.KeyManager;
import dev.input.MouseManager;
import dev.tools.Tool;
import dev.ui.UILabel;
import dev.ui.UIManager;
import dev.window.ObjectManager;

public class Workstation implements Runnable {
	
	//Handler
	Handler handler;

	//Display
	public int width, height;
	String title;
	Display display;
	
	//Thread
	Thread thread;
	private boolean running = false;
	
	//Input
	KeyManager keyManager;
	MouseManager mouseManager;
	
	//Graphics
	private BufferStrategy bs;
	private Graphics g;
	
	//Tools
	private Tool point;
	private Tool line;
	private Tool linearDimension;
	private Tool angularDimension;
	private Tool currentTool;
	
	//UI
	private UIManager uiManager;
	
	//Objects in Window
	private ObjectManager objectManager;
	
	//Mouse Highlight
	int mouseWidth = 6;
	int mouseHeight = 6;
	int mouseBoundsX, mouseBoundsY;
	
	public Workstation(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		handler = new Handler(this);
		
		keyManager = new KeyManager(handler);
		uiManager = new UIManager(handler);
		objectManager = new ObjectManager(handler);
		mouseManager = new MouseManager(handler);
	}
	
	private void tick() {
		mouseManager.tick();
		uiManager.tick();
		objectManager.tick();
		keyManager.tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here! -----------------------------------------------------------------
		
		//Background
		g.setColor(new Color(248, 248, 255));
		g.fillRect(0, 0, width, height);
		
		// UIManager
		uiManager.render(g);
		
		//ObjectManager
		objectManager.render(g);
		
		//Mouse Highlight
		mouseManager.render(g);
		
		//End Drawing! ---------------------------------------------------------------
		bs.show();
		g.dispose();
	}
	
	private void init() {
		display = new Display(width, height, title);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 30;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks); // //-----------------------------------------------
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public UIManager getUIManager() {
		return uiManager;
	}
	
	public ObjectManager getObjectManager() {
		return objectManager;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public Tool getTool() {
		if (currentTool != null) {
			return currentTool;
		} else {
			return null;
		}
	}
		
	public void setTool(Tool tool) {
		currentTool = tool;
	}
	
}
