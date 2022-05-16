package csc2a.designpatterns;

import csc2a.model.MyObj;
import csc2a.model.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 * @author  <YOUR DETAILS HERE>
 *
 */
public class RenderGameObjectVisitor implements iRenderVisitor{
	
	//Attributes
	GraphicsContext gc = null;
	
	/**
	 * Mutator for the GraphicsContext from the GameCanvas
	 * Used to set gc so the Visitor can draw things on the Canvas
	 * @param gc the GraphicsContext from the GameCanvas
	 */
	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;
	}
	
	
	/* TODO: render(YourGameObjectA a){} method */
	public void draw(Sprite a) {
		gc.setFill(a.getColor());
		//draw the rect
		gc.fillRect(a.getXLocation(), a.getYLocation(), a.getW(), a.getH());
		DropShadow ds = new DropShadow();
		ds.setColor(Color.web("yellow"));
	
		gc.setEffect(ds);
		
	}

	
	/* TODO: render(YourGameObjectB b){} method */
	public void draw(MyObj obj) {
		gc.setFill(obj.getColor());
		gc.fillOval(obj.getXLocation(), obj.getYLocation(), obj.getR(), obj.getR());
	}
	
	// ...
	
	/* TODO: render(YourGameObjectC m){} method */
	

}
