package dev.util; 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import dev.window.WindowObject;

public class Output {
	
	ArrayList<WindowObject> array;
	
	public Output(ArrayList<WindowObject> array) {
		this.array = array;
	}
	
	public void write() {
		try {
            FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            for (int z = 0; z < array.size() - 1; z++) {
            	String a = "", b = "", c = "", d = "";
            	bufferedWriter.write(
            			Integer.toString((int) array.get(z).getX()) + " " +
            			Integer.toString((int) array.get(z).getY()) + " " + 
            			Integer.toString((int) array.get(z + 1).getX()) + " " +
            			Integer.toString((int) array.get(z + 1).getY())
            			);
            	bufferedWriter.newLine();
            	System.out.println(Integer.toString((int) array.get(z).getX()) + " " +
            			Integer.toString((int) array.get(z).getY()) + " " + 
            			Integer.toString((int) array.get(z + 1).getX()) + " " +
            			Integer.toString((int) array.get(z + 1).getY()));
            }
            	
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
