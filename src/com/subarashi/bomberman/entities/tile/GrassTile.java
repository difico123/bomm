package com.subarashi.bomberman.entities.tile;


import com.subarashi.bomberman.entities.Entity;
import com.subarashi.bomberman.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
