package com.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		
		if(scoreKeep >= 100){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			

			
			
			
				if(hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt((int)Game.WIDTH - 50), r.nextInt((int)Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}else if(hud.getLevel() == 6){
					handler.addObject(new SmartEnemy(r.nextInt((int)Game.WIDTH - 50), r.nextInt((int)Game.HEIGHT - 50), ID.SmartEnemy, handler));										
				} else if(hud.getLevel() == 10){
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH/2) - 48, -120, ID.EnemyBoss, handler));
				}
				
		}
	}
	
}
