package com.subarashi.bomberman.level;

import com.subarashi.bomberman.Board;
//import com.subarashi.bomberman.exceptions.LoadLevelException;

public abstract class Level implements ILevel {

	protected int _width, _height, _level;
	protected String[] _lineTiles;
	protected Board _board;

	public Level(String path, Board board) throws Exception {
		loadLevel(path);
		_board = board;
	}

	@Override
	public abstract void loadLevel(String path) throws Exception;
	
	public abstract void createEntities();

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public int getLevel() {
		return _level;
	}

}
