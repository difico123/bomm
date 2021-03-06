package com.subarashi.bomberman;

import java.awt.Graphics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.subarashi.bomberman.entities.Entity;
import com.subarashi.bomberman.entities.bomb.Bomb;
import com.subarashi.bomberman.entities.bomb.Explosion;
import com.subarashi.bomberman.entities.mob.Mob;
import com.subarashi.bomberman.entities.mob.Player;
import com.subarashi.bomberman.entities.tile.powerup.Powerup;
//import com.subarashi.bomberman.exceptions.LoadLevelException;
import com.subarashi.bomberman.graphics.IRender;
import com.subarashi.bomberman.graphics.Screen;
import com.subarashi.bomberman.gui.InfoPanel;
import com.subarashi.bomberman.gui.SaveName;
import com.subarashi.bomberman.input.Keyboard;
import com.subarashi.bomberman.level.FileLevel;
import com.subarashi.bomberman.level.Level;

import javax.swing.*;

public class Board implements IRender {

	public int _width, _height;
	protected Level _level;
	protected Game _game;
	protected Keyboard _input;
	protected Screen _screen;
	
	public Entity[] _entities;
	public List<Mob> _mobs = new ArrayList<Mob>();
	protected List<Bomb> _bombs = new ArrayList<Bomb>();
	
	private int _screenToShow = -1; //1:endgame, 2:changelevel, 3:paused
	
	private int _time = Game.TIME;
	private int _points = Game.POINTS;
	private int _lives = Game.LIVES;
	
	public Board(Game game, Keyboard input, Screen screen) throws Exception {
		_game = game;
		_input = input;
		_screen = screen;
		changeLevel(1); //start in level 1
	}
	
	/*
	|--------------------------------------------------------------------------
	| Render & Update
	|--------------------------------------------------------------------------
	 */
	@Override
	public void update() throws Exception {
		if( _game.isPaused() ) return;
		
		updateEntities();
		updateMobs();
		updateBombs();
		try {
			detectEndGame();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < _mobs.size(); i++) {
			Mob a = _mobs.get(i);
			if(((Entity)a).isRemoved()) _mobs.remove(i);
		}
	}


	@Override
	public void render(Screen screen) {
		if( _game.isPaused() ) return;

		int x0 = 0; //tile precision, -> left X
		int x1 = ( screen.getWidth() ) / Game.TILES_SIZE; // -> right X
		int y0 = 0;
		int y1 = (screen.getHeight()) / Game.TILES_SIZE; //render one tile plus to fix black margins

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				_entities[x + y * _level.getWidth()].render(screen); // hien thi tat ca cac entities len man hinh
			}
		}
		
		renderBombs(screen);
		renderMobs(screen);
		
	}
	
	/*
	|--------------------------------------------------------------------------
	| ChangeLevel
	|--------------------------------------------------------------------------
	 */
	public void newGame() throws Exception {
		resetProperties();
		changeLevel(1);
	}
	
	@SuppressWarnings("static-access")
	private void resetProperties() {
		_points = Game.POINTS;
		_lives = Game.LIVES;
		Player._powerups.clear();
		
		_game.playerSpeed = 1.0;
		_game.bombRadius = 1;
		_game.bombRate = 1;
	}

	public void restartLevel() throws Exception {
		changeLevel(_level.getLevel());
	}
	
	public void nextLevel() throws Exception {
		changeLevel(_level.getLevel() + 1);
	}
	
	public void changeLevel(int level) throws Exception {
		if(level > 5) {
			endGame();
			return;
		}
		_time = Game.TIME;
		_screenToShow = 2;
		_game.resetScreenDelay();
		_game.pause();
		_mobs.clear();
		_bombs.clear();

		try {
			_level = new FileLevel("levels/Level" + level + ".txt", this);

			_entities = new Entity[_level.getHeight() * _level.getWidth()];
			_level.createEntities();
		} catch (IOException e) {
			endGame();
		}
	}
	
	public boolean isPowerupUsed(int x, int y, int level) {
		Powerup p;
		for (int i = 0; i < Player._powerups.size(); i++) {
			p = Player._powerups.get(i);
			if(p.getX() == x && p.getY() == y && level == p.getLevel())
				return true;
		}
		
		return false;
	}
	
	/*
	|--------------------------------------------------------------------------
	| Detections
	|--------------------------------------------------------------------------
	 */
	protected void detectEndGame() throws Exception {
		if(_time <= 0)
			restartLevel();
	}
	
	public void endGame() {
		_screenToShow = 1;
		_game.resetScreenDelay();
		_game.pause();

		if(InfoPanel.getHighScore() != _points) {
			ImageIcon icon = new ImageIcon("res/textures/user.png");
			String n = (String)JOptionPane.showInputDialog(null, "YOUR POINTS: " + _points,
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE, icon,null,null);
			try {
				FileWriter myWriter = new FileWriter("res/textures/highscore.txt",false);
				myWriter.write(n + "@" + _points);
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}
	
	public boolean detectNoEnemies() {
		int total = 0;
		for (int i = 0; i < _mobs.size(); i++) {
			if(_mobs.get(i) instanceof Player == false)
				++total;
		}
		return total == 0;
	}
	
	/*
	|--------------------------------------------------------------------------
	| Pause & Resume
	|--------------------------------------------------------------------------
	 */
	public void gamePause() {
		_game.resetScreenDelay();
		if(_screenToShow <= 0)
			_screenToShow = 3;
		_game.pause();
	}
	
	public void gameResume() {
		_game.resetScreenDelay();
		_screenToShow = -1;
		_game.run();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Screens
	|--------------------------------------------------------------------------
	 */
	public void drawScreen(Graphics g) {
		switch (_screenToShow) {
			case 1:
				_screen.drawEndGame(g, _points);
				break;
			case 2:
				_screen.drawChangeLevel(g, _level.getLevel());
				break;
			case 3:
				_screen.drawPaused(g);
				break;
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| Getters And Setters
	|--------------------------------------------------------------------------
	 */
	public Entity getEntity(double x, double y, Mob m) {
		
		Entity res = null;
		
		res = getExplosionAt((int)x, (int)y);
		if( res != null) return res;
		
		res = getBombAt(x, y);
		if( res != null) return res;
		
		res = getMobAtExcluding((int)x, (int)y, m);
		if( res != null) return res;
		
		res = getEntityAt((int)x, (int)y);
		
		return res;
	}
	
	public List<Bomb> getBombs() {
		return _bombs;
	}
	
	public Bomb getBombAt(double x, double y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			if(b.getX() == (int)x && b.getY() == (int)y)
				return b;
		}
		
		return null;
	}
	
	public Mob getMobAt(double x, double y) {
		Iterator<Mob> itr = _mobs.iterator();
		
		Mob cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur.getXTile() == x && cur.getYTile() == y)
				return cur;
		}
		
		return null;
	}
	
	public Player getPlayer() {
		Iterator<Mob> itr = _mobs.iterator();
		
		Mob cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur instanceof Player)
				return (Player) cur;
		}
		return null;
	}
	
	public Mob getMobAtExcluding(int x, int y, Mob a) {
		Iterator<Mob> itr = _mobs.iterator();
		
		Mob cur;
		while(itr.hasNext()) {
			cur = itr.next();
			if(cur == a) {
				continue;
			}
			
			if(cur.getXTile() == x && cur.getYTile() == y) {
				return cur;
			}
				
		}
		
		return null;
	}
	
	public Explosion getExplosionAt(int x, int y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			
			Explosion e = b.explosionAt(x, y);
			if(e != null) {
				return e;
			}
				
		}
		
		return null;
	}
	
	public Entity getEntityAt(double x, double y) {
		return _entities[(int)x + (int)y * _level.getWidth()];
	}
	
	/*
	|--------------------------------------------------------------------------
	| Adds and Removes
	|--------------------------------------------------------------------------
	 */
	public void addEntitie(int pos, Entity e) {
		_entities[pos] = e;
	}
	
	public void addMob(Mob e) {
		_mobs.add(e);
	}
	
	public void addBomb(Bomb e) {
		_bombs.add(e);
	}
	
	/*
	|--------------------------------------------------------------------------
	| Renders
	|--------------------------------------------------------------------------
	 */
	protected void renderEntities(Screen screen) {
		for (int i = 0; i < _entities.length; i++) {
			_entities[i].render(screen);
		}
	}
	
	protected void renderMobs(Screen screen) {
		Iterator<Mob> itr = _mobs.iterator();
		
		while(itr.hasNext())
			itr.next().render(screen);
	}
	
	protected void renderBombs(Screen screen) {
		Iterator<Bomb> itr = _bombs.iterator();
		
		while(itr.hasNext())
			itr.next().render(screen);
	}
	

	
	/*
	|--------------------------------------------------------------------------
	| Updates
	|--------------------------------------------------------------------------
	 */
	protected void updateEntities() throws Exception {
		if( _game.isPaused() ) return;
		for (int i = 0; i < _entities.length; i++) {
			_entities[i].update();
		}
	}
	
	protected void updateMobs() throws Exception {
		if( _game.isPaused() ) return;
		Iterator<Mob> itr = _mobs.iterator();
		
		while(itr.hasNext() && !_game.isPaused())
			itr.next().update();
	}
	
	protected void updateBombs() {
		if( _game.isPaused() ) return;
		Iterator<Bomb> itr = _bombs.iterator();
		while(itr.hasNext())
			itr.next().update();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Getters & Setters
	|--------------------------------------------------------------------------
	 */
	public Keyboard getInput() {
		return _input;
	}
	
	public Level getLevel() {
		return _level;
	}
	
	public Game getGame() {
		return _game;
	}
	
	public int getShow() {
		return _screenToShow;
	}
	
	public void setShow(int i) {
		_screenToShow = i;
	}
	
	public int getTime() {
		return _time;
	}
	
	public int getLives() {
		return _lives;
	}

	public int subtractTime() {
		if(_game.isPaused())
			return this._time;
		else
			return this._time--;
	}

	public int getPoints() {
		return _points;
	}

	public void addPoints(int points) {
		this._points += points;
	}

	public void addLives(int lives) {
		this._lives += lives;
	}
	
	public int getWidth() {
		return _level.getWidth();
	}

	public int getHeight() {
		return _level.getHeight();
	}
	
}
