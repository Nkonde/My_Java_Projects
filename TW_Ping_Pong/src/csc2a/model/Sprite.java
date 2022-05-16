package csc2a.model;

import csc2a.designpatterns.iRenderVisitor;
import csc2a.designpatterns.iRenderable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Sprite implements iRenderable{
	// dimensions and color of rect
	private int w;
	private int h;
	private Point2D location= null;
	private Color c;
	private String type = "";


	
	public Sprite(int x, int y, int w, int h, Color c, String type) {
		location = new Point2D(x, y);
		this.c = c;
		this.w = w;
		this.h = h;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}


	@Override
	public void accept(iRenderVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.draw(this);
	}


	public Paint getColor() {
		// TODO Auto-generated method stub
		return c;
	}

	public Point2D getLocation() {
		return location;
	}
	public double getXLocation() {
		// TODO Auto-generated method stub
		return location.getX();
	}


	public double getYLocation() {
		// TODO Auto-generated method stub
		return location.getY();
	}
	
	public void setLocation(int x, int y) {
		location = null;
		location = new Point2D(x, y);
	}
	
	public void destroyObject() {
		this.location = new Point2D(0,0);
		this.w = 0;
		this.h =0;
		this.c = null;
		this.type = "";
		
	}
}
