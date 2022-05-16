package csc2a.model;

import csc2a.designpatterns.iRenderVisitor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MyObj extends GameObject{
	private int r =0;
	static Color c = Color.BLACK;
	private Image img = null;
	public MyObj(int x, int y, int r) {
		super(x, y, c);
		img = new Image(".\\images\\player.bmp");
	}
	@Override
	public void accept(iRenderVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.draw(this);
	}
	public Image getImg() {
		return img;
	}
	public int getR() {
		return r;
	}
}
