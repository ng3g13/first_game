package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	//Create a linked list of all the game objects.
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){	//update all the GameObject objects.
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){ //render all the GameObject objects.
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}		
	}
	
	public void clearEnemys(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				object.clear(); //if we found player, remove all GameObject then add the same player in the same x, y.
				if(Game.gameState != Game.STATE.End)
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			}
		}
	}
	
	public void addObject(GameObject object){ //add a new GameObject object to the linked list.
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){ //add a new GameObject object to the linked list.
		this.object.remove(object);
	}
	
}
