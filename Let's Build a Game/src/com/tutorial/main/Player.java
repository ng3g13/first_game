package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

	private Random r = new Random();
	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	
	}
	
	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	@Override
	public void tick(){
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision(); //check if we've collided with each call of tick
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.1f, handler)); //creates trail object at enemy x, y, fades alpha by 0.1 each call of tick()

	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){ //temp object is now BasicEnemy
				if(getBounds().intersects(tempObject.getBounds())){ //Determines whether or not this Rectangle and the specified Rectangle intersect. 'intersects' method of the Rectangle class
					//collision code
					HUD.HEALTH -= 2; //since HEALTH is a public static variable in HUD class.
				}
			}
			
		}
	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
	}

}
