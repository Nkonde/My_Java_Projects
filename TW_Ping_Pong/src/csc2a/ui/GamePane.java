package csc2a.ui;

import java.util.ArrayList;
import java.util.Random;


import csc2a.designpatterns.iRenderable;
import csc2a.model.GameObject;
import csc2a.model.GameObjectContainer;
import csc2a.model.MyObj;
import csc2a.model.Sprite;
import csc2a.util.KMBuffer;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * 
 * GamePane provides a custom container to manage all game interactions
 * and host the GameCanvas
 * @author <YOUR DETAILS HERE>
 *
 */
public class GamePane extends StackPane{
	
	//Attributes
	private GameCanvas canvas; //You need the canvas so the visitor can draw on it
	private AnimationTimer gameTimer; //Used if you want to make a game that runs at 60 frames per second
	private ArrayList<iRenderable> objects = null;
	private GameObjectContainer<? extends GameObject> objs = null;
	public int numbE =0;
	public boolean dead = false;
	private double t = 0;
	 iRenderable object = null;
	 Sprite player = null;
	 Sprite enemy = null;
	 int x =  0;
	 int y = 0;
	
	 public int getNumbE() {
		return numbE;
	}
	 public boolean isDead() {
		return dead;
	}
	 /**
	 * Default constructor
	 */
	public GamePane() {
		super();
		
		x = 400;
		y = 0;
		//Create the canvas to draw on
		canvas = new GameCanvas();	
		//new container of objects
		objects = new ArrayList<>();
		objs = new GameObjectContainer<GameObject>();
		/*
		 * Optional (but makes your life easier)
		 * 
		 * Set up KMBuffer as the event "listener"
		 * (You can remove this line if you prefer to handle your own events)
		 * 
		 */
		setUpEventListeners();
		/*
		 * End Optional
		 */
		
		
		/* TODO: Construct your GamePane as you see fit */
		
		/* TODO: Do you game logic (See Animation Timer below) */
		
		/*
		 * Animation Timer 
		 * 
		 * Animation timer is only needed if you want to have a game that runs at a set frame rate (~60fps) 
		 * 
		 * You can safely remove the ApplicationTimer if you would prefer to rather implement your own 
		 * event handlers to drive your game logic (then setup your event handlers for events such as: 
		 * 			this.setOnKeyPressed();
		 * 			this.setOnKeyReleased();		
		 * 			this.setOnMouseMoved();		
		 * 			this.setOnMousePressed();
		 * 			this.setOnMouseReleased();
		 * 			this.setOnMouseEntered();
		 * 			this.setOnMouseExited();
		 * 			this.setOnMouseDragged();  )
		 * 
		 (i.e. This object V V V ) */
		
		 player = new Sprite(10, 20, 30, 30, Color.web("black"), "player");
		 object = (iRenderable)player;
			objects.add(object);
		
			
		 for(int e=0; e< 5; e++) {
			 enemy = new Sprite(x, y, 50, 50, Color.RED, "enemy");
			 object = (iRenderable)enemy;
				objects.add(object);
				 y += 100;
		 }
		 
		 	
			
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				GamePane.this.requestFocus(); //This is important so that this class has focus (with all the event handlers) to intercept the Key and Mouse events
				/*
				 * Do your game logic here
				 */
			
				/* 
				 * HINT: Look up AnimationTimer
				 * See: https://docs.oracle.com/javase/8/javafx/api/javafx/animation/AnimationTimer.html
				 * it provides a handle method to perform operations 
				 * roughly 60 times per second (@ 60fps)
				 * 
				 * 
				 * Note: if you use the Event Handler Code from the KMBuffer to test for events such as:
				 * 
				 * 	Key pressed: 
				 * 		KMBuffer.isKeyPressed(KeyCode.UP); //.UP is for the Up Arrow Key
				 * 		For more see: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html */
				
				moveSprite(player); //move the player

				if(t > 2) {
					moveEnemies();
				}
				updateWorld();
				
				
				/* TODO: Set the GameObjects that your GameCanvas needs to draw */
				
				/* TODO: Redraw GameCanvas() */
				canvas.setObject(objects);
				
			}
		};
		gameTimer.start();
		
		
		t =0;
		this.getChildren().add(canvas);
		
		/* TODO: Finish setting up your GamePane */
		canvas.widthProperty().bind(this.widthProperty());
		canvas.heightProperty().bind(this.heightProperty());
		this.setPrefHeight(500);
		this.setPrefWidth(500);
	}
	/**
	 * method to move the enemies to random locations
	 */
	private void moveEnemies() {
		t+=0;
		objects.forEach(s -> {
			if(s instanceof Sprite) {
				Sprite enemy = (Sprite)s;
				if(enemy.getType().equals("enemy"))
					if(t > 5) {
						if(Math.random() < 0.3) {
							moveEnemy(enemy); //move the enemy to a random location
							//shoot(enemy);
						}
					}
				
			}
		});
		if(t > 5) {
			t=0;
		}
	}

	/**
	 * function to move game object
	 * @param &GameObject
	 */
	
	private void moveSprite(Sprite sprite) {
		//capturing of player location
		int x = (int)sprite.getXLocation();
		int y = (int)sprite.getYLocation();
		
		
		//moving of player
		if( KMBuffer.isKeyPressed(KeyCode.DOWN)) {
			y += 5;
			 if(isInWorld(x, y)) {
				 clearsprite(sprite);
				 sprite.setLocation(x, y);
			 }
		 }else if( KMBuffer.isKeyPressed(KeyCode.UP)) {
			 
			y -= 5;
			 if(isInWorld(x, y)) {
				 clearsprite(sprite);
				 sprite.setLocation(x, y);
			 }
		 }else if( KMBuffer.isKeyPressed(KeyCode.LEFT)) {
			 
			  x -= 5;
			 if(isInWorld(x, y)) {
				 clearsprite(sprite);
				 sprite.setLocation(x, y);
			 }
		 }else if( KMBuffer.isKeyPressed(KeyCode.RIGHT)) {
			 
			x += 5;
			 if(isInWorld(x, y)) {
				 clearsprite(sprite);
				 sprite.setLocation(x, y);
			 }
		 }else if(KMBuffer.isKeyPressed(KeyCode.SPACE)) {
			 shoot(sprite);
		 }
		
	}
	/**
	 * method to create bullet
	 */
	public void shoot(Sprite who) {
		Sprite bullet = new Sprite((int)who.getXLocation() + 13, (int)who.getYLocation()+((int)who.getH()/2)-2, 4, 07, Color.web("red"), who.getType()+"bullet");
		objects.add(bullet);
	}
	/**
	 * method to move Enemy
	 * into random locations 
	 * @param Sprite
	 */
	private void moveEnemy(Sprite enemy) {
		int x = (int)enemy.getXLocation();
		int y = (int)enemy.getYLocation();
		Random randNumb = new Random();
		int dir = randNumb.nextInt(200);
		while(dir < 0) {
			dir = randNumb.nextInt(200);
		}
		
			if(dir > 99) {
				if(dir%2 == 0) {
					y-= 15; // up
					if(isInWorld(x, y)) {
						clearsprite(enemy);
						enemy.setLocation(x, y);
					}
				}else {
					y+= 15; // down
					if(isInWorld(x, y)) {
						clearsprite(enemy);
						enemy.setLocation(x, y);
					}
				
				}
				
			}
			//removeInWorld(enemy);
	}
	

	/**
	 * method to move the bullet
	 * @param Sprite bullet
	 */
	private void updateWorld() {
		t += 0.26;
		//check if the 
		objects.forEach(o-> {
			if(o instanceof Sprite) {
				Sprite s = (Sprite)o;
				if(s.getType().equals("playerbullet")){
					moveTowardsDir(s, "right");
					//checking if the bullet intersects with any of the objects in the world
					objects.forEach(e-> {
						if(e instanceof Sprite) {
							Sprite se = (Sprite)e;
							if(se.getType().equals("enemy")) {
								for(int r=0; r< se.getH(); r++) {
									for(int c=0; c< se.getW(); c++) {
										if(s.getXLocation() == se.getXLocation()+c && s.getYLocation() == se.getYLocation()+r) {
											//kill both sprite and bullet
											s.destroyObject();
											se.destroyObject();
											numbE++;
										}
									}
								}
							}
						}
					});
				}else if(s.getType().equals("enemybullet")) {
					moveTowardsDir(s, "left");
					objects.forEach(e-> {
						if(e instanceof Sprite) {
							Sprite se = (Sprite)e;
							if(se.getType().equals("player")) {
								for(int r=0; r< se.getH(); r++) {
									for(int c=0; c< se.getW(); c++) {
										if(s.getXLocation() == se.getXLocation()+c && s.getYLocation() == se.getYLocation()+r) {
											//kill both sprite and bullet
											s.destroyObject();
											se.destroyObject();
											dead = true;
										}
									}
								}
							}
						}
					});
					
				}
			}
			
		});
			
	}
	

	/**
	 * met
	 * @param bullet
	 */
	
	private void moveTowardsDir(Sprite bullet, String dir) {
		int x = (int)bullet.getXLocation();
		int y = (int)bullet.getYLocation();
		
		int speed = 8;
		
			if(dir == "right") {
				x += speed; // right
				
					clearsprite(bullet);
					bullet.setLocation(x, y);
			
			}else if(dir == "left") {
				x -= speed; // left
				
					clearsprite(bullet);
					bullet.setLocation(x, y);
			
			}
		
	}
	
	/**
	 * method to update the world by clearing screen
	 */
	private void clearsprite(Sprite s) {
		 canvas.getGc().clearRect(s.getXLocation(), s.getYLocation(), s.getW(), s.getH());
		 if(s.getType().equals("playerbullet") && !isInWorld((int)s.getXLocation(), (int)s.getYLocation())) {
			s.destroyObject(); 
		 }
		 /*else if(s.getType().equals("enemybullet") && !isInWorld((int)s.getXLocation(), (int)s.getYLocation())) {
			 s.destroyObject();
		 }*/
	}
	
	/**
	 * method to check if player is in world
	 */
	private boolean isInWorld(int row, int col) {
		if(row < 0 || col < 0) return false;
		if(row >= this.getWidth() -25 || col >= this.getHeight() -25) return false;
		return true;
	}

	/**
	 * Method to set that the KMBuffer is responsible for handling key and mouse events
	 * (Use the KMBuffer's static methods in your GamePane to check for key and mouse events)
	 */
	private void setUpEventListeners() {
		/*--------------------------------------------------------------------
		 * 
		 * Event Handler Code
		 * 
		 * Code to set up the Keyboard and Mouse Events to be handled by the 
		 * provided KMBuffer in the util package
		 * 
		 * Note: If you want to, you can remove this code and handle events
		 * 		 yourself either in this Canvas or in your GamePane???
		 * 
		 *--------------------------------------------------------------------*/
		/*
		 * Set the event listeners to handle the key press and release events in the KMBuffer
		 */
		this.setOnKeyPressed(event    -> { KMBuffer.handleKeyPressed(event);    });
		this.setOnKeyReleased(event   -> { KMBuffer.handleKeyReleased(event);   });
		
		/*
		 * Set the event listeners to handle the mouse events in the KMBuffer
		 */
		this.setOnMouseMoved(event    -> { KMBuffer.handleMouseMoved(event);    });		
		this.setOnMousePressed(event  -> { KMBuffer.handleMousePressed(event);  });
		this.setOnMouseReleased(event -> { KMBuffer.handleMouseReleased(event); });
		this.setOnMouseEntered(event  -> { KMBuffer.handleMouseEntered(event);  });
		this.setOnMouseExited(event   -> { KMBuffer.handleMouseExited(event);   });
		//this.setOnMouseClicked(event -> {}); //You need to add an event handler to deal with this event in this Class
		
		/*--------------------------------------------------------------------
		 * 
		 * End of Event Handler Code
		 * 
		 *--------------------------------------------------------------------*/
	}
}
