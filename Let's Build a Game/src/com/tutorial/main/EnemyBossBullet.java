package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	
	private Handler handler;
	Random r = new Random();

	public EnemyBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5); //random number from -5 to +5 each time the constructor is called for a new object.
		velY = 5;
	}
	
	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	@Override
	public void tick(){
		x += velX;
		y += velY;
		//System.out.println(velX);
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.1f, handler)); //creates trail object at enemy x, y, fades alpha by 0.1 each call of tick()
	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
