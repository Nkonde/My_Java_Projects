package csc2a.ui;

import java.util.ArrayList;

import csc2a.designpatterns.RenderGameObjectVisitor;
import csc2a.designpatterns.iRenderable;
import csc2a.model.GameObject;
import csc2a.model.GameObjectContainer;
import csc2a.model.MyObj;
import csc2a.model.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * 
 * Canvas used to render all of your GameObjects using the Visitor
 * This is the Client in the Visitor Design Pattern
 * @author  <Thabani>
 *
 */
public class GameCanvas extends Canvas{
	
	//Attributes
	private RenderGameObjectVisitor visitor = null;
	/* TODO: Store all of your GameObjects (Using GameObjectContainers) here */
	private ArrayList<iRenderable> objects = null;
	private GameObjectContainer<? extends GameObject> objs = null;
	//graphics context
	private GraphicsContext gc = null;
	
	/**
	 * Default Constructor
	 */
	public GameCanvas() {
		/*
		 * Construct your Game Canvas as you see fit
		 */
		visitor = new RenderGameObjectVisitor();
		objects = new ArrayList<>();
	}
	
	/* TODO: Set your GameObjects and redrawCanvas() */
	public void setObject(ArrayList<iRenderable> o) {
		objects = o;
		redrawCanvas();
	}
	
	public void setObject(GameObjectContainer<? extends GameObject> o) {
		objs = o;
		draw();
	}
	/**
	 * Method used to redraw the canvas whenever called
	 */
	public void redrawCanvas(){
		requestFocus();
		/* TODO: Get GraphicsContext */
		gc = getGraphicsContext2D();

		gc.setFill(Color.web("green", 0.34));
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		/* TODO: Set Visitor's GraphicsContext */
		visitor.setGraphicsContext(gc);
		/* TODO: Iterate through ALL GameObjects (Using GameObjectContainers) */
			for(iRenderable o: objects ) {
				/* TODO: Get EACH GameObject to accept() the Visitor */
				o.accept(visitor);
			}
			MyObj ob = new MyObj(0,0, 5);
			gc.drawImage(ob.getImg(), 0, 0, 100, 50);
	}	
	
	private void draw() {
		objs.forEach(o -> {
			o.accept(visitor);
		});
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	
	public void removeO(iRenderable i) {
		ArrayList<iRenderable> arr = new ArrayList<>();
		Sprite se = (Sprite)i;
		objects.forEach(o-> {
			Sprite s = (Sprite)o;
			if(!s.getType().equals(se.getType())) {
				arr.add(s);
			}
		});
		objects = arr;
	}
	

}
