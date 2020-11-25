package com.subarashi.bomberman.entities.mob;

import com.subarashi.bomberman.Board;
import com.subarashi.bomberman.entities.AnimatedEntitiy;
import com.subarashi.bomberman.graphics.Screen;

public abstract class Mob extends AnimatedEntitiy {
	
	protected Board _board;
	protected int _direction = -1;
	protected boolean _alive = true;
	protected boolean _moving = false;
	public int _timeAfter = 80;
	
	public Mob(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
	}
	
	@Override
	public abstract void update() throws Exception;
	
	@Override
	public abstract void render(Screen screen);
	
	protected abstract void calculateMove() throws Exception;
	
	protected abstract void move(double xa, double ya);
	
	public abstract void kill();
	
	protected abstract void afterKill() throws Exception;
	
	protected abstract boolean canMove(double x, double y) throws Exception;
	
	public boolean isAlive() {
		return _alive;
	}
	
	public boolean isMoving() {
		return _moving;
	}
	
	public int getDirection() {
		return _direction;
	}
}
