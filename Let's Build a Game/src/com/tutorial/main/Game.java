package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	//using a float system for SmartEnemy tick() method.
	public static final float WIDTH = 640, HEIGHT = WIDTH / 12*9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	//declare HUD
	private HUD hud;
	//declare spawner
	private Spawn spawner;
	
	public Game(){
		handler = new Handler();
		r = new Random();
		this.addKeyListener(new KeyInput(handler)); //object created from that class is then registered with a component 
		//using the component's addKeyListener method. A keyboard event is generated when a key is pressed, released, or typed. The relevant method in the listener object is then invoked, and the KeyEvent is passed to it.
		
		new Window((int)WIDTH, (int)HEIGHT, "Let's Build a Game!", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		
		
		//create GameObjects and add to LinkedList
		handler.addObject(new Player((int)(WIDTH/2 - 32), (int)(HEIGHT/2 -32), ID.Player, handler));
		
		
		//handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt((int)WIDTH), r.nextInt((int)HEIGHT), ID.BasicEnemy, handler));
		
		handler.addObject(new BasicEnemy(r.nextInt((int)WIDTH), r.nextInt((int)HEIGHT), ID.BasicEnemy, handler));

	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join(); //stops the thread
			running = false;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){	//the game loop
		this.requestFocus(); //doesn't require click on screen for keyboard focus.
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //getDrawGraphics() returns abstract Graphics object for graphics context.
		//I think, the graphics context is displayed?
		
		g.setColor(Color.black);
		g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);
		
		handler.render(g);
		
		hud.render(g); //render on top of everything
		
		g.dispose(); //Dispose the graphics context, release resources.
		bs.show(); //Makes the next available buffer visible by either copying the memory (blitting) or changing the display pointer (flipping).
		//used for getDrawGraphics() on next loop?
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else 
			return var;
	}

	public static void main(String args[]){
		new Game();
	}
	
	
	
}
