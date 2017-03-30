package com.retrochicken.engine.ui;

import java.awt.Rectangle;

import com.retrochicken.engine.Renderer;
import com.retrochicken.engine.fx.Font;

public class PlainText implements UIElement {
	
	private Font font;
	private String text = "";
	
	private int color;
	private float x, y;
	
	private String name;
	private int nameColor;
	
	public PlainText(Font font, String name, int nameColor, String text, int color, float x, float y) {
		this.font = font;
		this.text = text;
		this.color = color;
		this.x = x;
		this.y = y;
		this.name = name;
		this.nameColor = nameColor;
	}
	
	@Override
	public int getBottom() {
		return (int)(y + font.getHeight(text));
	}

	@Override
	public int getTop() {
		return (int)y;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void update(Rectangle bounds) {
		update();
	}

	@Override
	public void render(Renderer renderer) {
		renderer.drawString(name, nameColor, (int)x, (int)y);
		renderer.drawString(text, color, (int)(x + font.getWidth(name.toUpperCase())), (int)y);
	}

	@Override
	public int getRight() {
		return (int)(x + font.getWidth(text) + font.getWidth(name));
	}

	@Override
	public int getLeft() {
		return (int)x;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public void render(Renderer renderer, Rectangle bounds) {
		renderer.drawString(name, nameColor, (int)x, (int)y, bounds);
		renderer.drawString(text, color, (int)(x + font.getWidth(name.toUpperCase())), (int)y, bounds);
	}
}
