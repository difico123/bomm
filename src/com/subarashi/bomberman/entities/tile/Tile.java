package com.subarashi.bomberman.entities.tile;

import com.subarashi.bomberman.entities.Entity;
import com.subarashi.bomberman.graphics.Screen;
import com.subarashi.bomberman.graphics.Sprite;
import com.subarashi.bomberman.level.Coordinates;

public abstract class Tile extends Entity {
	
	
	public Tile(int x, int y, Sprite sprite) {
		_x = x;
		_y = y;
		_sprite = sprite;
	}
	
	@Override
	public boolean collide(Entity e) throws Exception {
		return false;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderEntity( Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
