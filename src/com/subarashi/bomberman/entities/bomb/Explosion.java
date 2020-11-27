package com.subarashi.bomberman.entities.bomb;

import com.subarashi.bomberman.Board;
import com.subarashi.bomberman.entities.AnimatedEntitiy;
import com.subarashi.bomberman.entities.Entity;
import com.subarashi.bomberman.entities.mob.Mob;
import com.subarashi.bomberman.graphics.Screen;
import com.subarashi.bomberman.graphics.Sprite;


public class Explosion extends AnimatedEntitiy {

	protected boolean _last = false;
	protected Board _board;
	
	public Explosion(int x, int y, int direction, boolean last, Board board) {
		_x = x;
		_y = y;
		_last = last; // cai nay la de kiem tra xem cai vi tri fire cua bom no cuoi cung
		_board = board;
		
		switch (direction) {
			case 0:
				if(last == false) {
					//_sprite = Sprite.explosion_vertical2;
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical,Sprite.explosion_vertical1, Sprite.explosion_vertical2, _animate, 2);
				}
				else {
					//_sprite = Sprite.explosion_vertical_top_last2;
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last,Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, _animate, 2);
				}
			break;
			case 1:
				if(last == false) {
					//_sprite = Sprite.explosion_horizontal2;
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal,Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, _animate, 2);
				}
				else {
					//_sprite = Sprite.explosion_horizontal_right_last2;
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, _animate, 2);
				}
				break;
			case 2:
				if(last == false) {
					//_sprite = Sprite.explosion_vertical2;
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical,Sprite.explosion_vertical1, Sprite.explosion_vertical2, _animate, 2);
				}
				else {
					//_sprite = Sprite.explosion_vertical_down_last2;
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last,Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, _animate, 2);
				}
				break;
			case 3: 
				if(last == false) {
					//_sprite = Sprite.explosion_horizontal2;
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal,Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, _animate, 2);
				}
				else {
					//_sprite = Sprite.explosion_horizontal_left_last2;
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, _animate, 2);
				}
				break;
		}
	}
	
	@Override
	public void render(Screen screen) {
		int xt = (int)_x * 16;
		int yt = (int)_y * 16;
		
		screen.renderEntity(xt, yt , this);
	}
	
	@Override
	public void update() {}

	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Mob) {
			((Mob)e).kill();
		}
		
		return true;
	}
	

}