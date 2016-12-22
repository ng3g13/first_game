package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{ //this enemy follows the player
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
	
		for(int i = 0; i< handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player){
				player = handler.object.get(i);
			}
		}
		
		
	}
	
	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	@Override
	public void tick(){
		x += velX;
		y += velY;
		
		//for calculating distance between SmartEnemy and Player.
		float diffX = x - player.getX() -8;
		float diffY = y - player.getY() -8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y - player.getY())*(y - player.getY()));		
		velX = (-1.0f/distance) * diffX;
		velY = (-1.0f/distance) * diffY;	
		
		//System.out.println(velX);
		//System.out.println(diffY);
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.green, 16, 16, 0.1f, handler)); //creates trail object at enemy x, y, fades alpha by 0.1 each call of tick()
	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
