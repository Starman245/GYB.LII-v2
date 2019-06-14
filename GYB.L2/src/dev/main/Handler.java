package dev.main;

import dev.input.KeyManager;
import dev.input.MouseManager;
import dev.ui.UIManager;
import dev.window.ObjectManager;

public class Handler {
	
	private Workstation workstation;

	public Handler(Workstation workstation) {
		this.workstation = workstation;
	}
	
	public int getWidth() {
		return workstation.getWidth();
	}
	
	public int getHeight() {
		return workstation.getHeight();
	}
	
	public UIManager getUIManager() {
		return workstation.getUIManager();
	}
	
	public KeyManager getKeyManager() {
		return workstation.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return workstation.getMouseManager();
	}
	
	public ObjectManager getObjectManager() {
		return workstation.getObjectManager();
	}
	
	public Workstation getWorkstation() {
		return workstation;
	}
	
	public void setWorkstation(Workstation workstation) {
		this.workstation = workstation;
	}
	
}
