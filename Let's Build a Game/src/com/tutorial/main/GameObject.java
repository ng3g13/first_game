package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//Superclass for all game objects eg. player, enemy, particles etc..
public abstract class GameObject {

	//using a float system for SmartEnemy tick() method.
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	//abstract classes to be implemented by subclasses
	public abstract void tick(); 
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	
	public ID getId(){
		return id;
	}
	
	public void setvelX(int velX){
		this.velX = velX;
	}
	
	public void setvelY(int velY){
		this.velY = velY;
	}
	
	public float getvelX(){
		return velX;
	}
	
	public float getvelY(){
		return velY;
	}
	
	
}
