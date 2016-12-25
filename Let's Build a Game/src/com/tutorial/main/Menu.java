package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;



public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e){
		//get x, y coordinates of mouse.
		int mx = e.getX();
		int my = e.getY();		
		if(game.gameState == STATE.Menu){
		
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)){
				game.gameState = STATE.Game;
				//clear all GameObjects, player hasn't been rendered yet so clearEnemys() can be used. Add player class first so clearEnemys() works.
				handler.addObject(new Player((int)(Game.WIDTH/2 - 32), (int)(Game.HEIGHT/2 -32), ID.Player, handler));
				handler.clearEnemys();
				//create GameObjects and add to LinkedList
				handler.addObject(new BasicEnemy(r.nextInt((int)Game.WIDTH), r.nextInt((int)Game.HEIGHT), ID.BasicEnemy, handler));			
				handler.addObject(new BasicEnemy(r.nextInt((int)Game.WIDTH), r.nextInt((int)Game.HEIGHT), ID.BasicEnemy, handler));
			}
			
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				game.gameState = STATE.Help;
			}
					
			//quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				System.exit(1);
			}
		}

		
		//back button for help
		if(game.gameState == STATE.Help){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu;
				return; //return to stop us accidently hitting the quit button in menu.
			}
		}

		//Try again button
		if(game.gameState == STATE.End){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				//clear all GameObjects, player hasn't been rendered yet so clearEnemys() can be used. Add player class first so clearEnemys() works.
				handler.addObject(new Player((int)(Game.WIDTH/2 - 32), (int)(Game.HEIGHT/2 -32), ID.Player, handler));
				handler.clearEnemys();
				//create GameObjects and add to LinkedList
				handler.addObject(new BasicEnemy(r.nextInt((int)Game.WIDTH), r.nextInt((int)Game.HEIGHT), ID.BasicEnemy, handler));			
				handler.addObject(new BasicEnemy(r.nextInt((int)Game.WIDTH), r.nextInt((int)Game.HEIGHT), ID.BasicEnemy, handler));
			}
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e){

	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		//check if mouse in in range of dimensions defined by x, y, width and height for an area.
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else{
				return false;
			}
		} else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){ 
		if(game.gameState == STATE.Menu){

			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setColor(Color.white);		
			g.setFont(fnt);
			g.drawString("Wave", 240, 70);
		
			g.setFont(fnt2);

			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
		
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
		
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		}else if(game.gameState == STATE.Help){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setColor(Color.white);		
			g.setFont(fnt);
			g.drawString("Help", 240, 70);
			
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt2);
			g.drawString("Back", 270, 390);
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);

		}else if(game.gameState == STATE.End){
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setColor(Color.white);		
			g.setFont(fnt);
			g.drawString("Game Over", 180, 70);
			
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt2);
			g.drawString("Try Again", 245, 390);
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);

		} 
	}
	
}
