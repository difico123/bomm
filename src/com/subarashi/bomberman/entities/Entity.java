package com.subarashi.bomberman.entities;

import com.subarashi.bomberman.graphics.IRender;
import com.subarashi.bomberman.graphics.Screen;
import com.subarashi.bomberman.graphics.Sprite;
import com.subarashi.bomberman.level.Coordinates;

public abstract class Entity implements IRender {

	protected double _x, _y;
	protected boolean _removed = false;
	protected Sprite _sprite;
	
	@Override
	public abstract void update() throws Exception;
	
	@Override
	public abstract void render(Screen screen);
	
	public void remove() {
		_removed = true;
	}
	
	public boolean isRemoved() {
		return _removed;
	}
	
	public Sprite getSprite() {
		return _sprite;
	}
	
	public abstract boolean collide(Entity e) throws Exception;
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public int getXTile() {
		return Coordinates.pixelToTile(_x + _sprite.SIZE / 2); //plus half block for precision
	}
	
	public int getYTile() {
		return Coordinates.pixelToTile(_y - _sprite.SIZE / 2); //plus half block
	}
}
