package csc2a.designpatterns;

import csc2a.model.MyObj;
import csc2a.model.Sprite;

/**
 * 
 * AbstractVisitor interface
 * Used to define all of the render functions for your different GameObjects
 * @author  <YOUR DETAILS HERE>
 *
 */
public interface iRenderVisitor {
	/* TODO: render(YourGameObjectA a); method */
	public void draw(Sprite a) ;
	/* TODO: render(YourGameObjectB b); method */
	public void draw(MyObj obj);
	// ...
	
	/* TODO: render(YourGameObjectC c); method */
}
