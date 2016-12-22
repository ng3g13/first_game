package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4]; //used to stop sticky keys.
	
	public KeyInput(Handler handler){
		this.handler = handler;
		
		keyDown[0] = false; //w
		keyDown[1] = false; //s
		keyDown[2] = false; //d
		keyDown[3] = false;	//a	
	}

	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode(); //set key to value corresponding to key pressed
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.Player) {
				//key events for Player
				if(key == KeyEvent.VK_W) {tempObject.setvelY(-5);	keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setvelY(5);	keyDown[1] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setvelX(5);	keyDown[2] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setvelX(-5);	keyDown[3] = true;}
			}
			
	
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.Player) {
				//key events for Player
				if(key == KeyEvent.VK_W) keyDown[0]=false;//tempObject.setvelY(0);
				if(key == KeyEvent.VK_S) keyDown[1]=false;//tempObject.setvelY(0);
				if(key == KeyEvent.VK_D) keyDown[2]=false;//tempObject.setvelX(0);
				if(key == KeyEvent.VK_A) keyDown[3]=false;//tempObject.setvelX(0);
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setvelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setvelX(0);
			}
			

			
			
		}
	}
	
}
