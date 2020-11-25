package com.subarashi.bomberman.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.subarashi.bomberman.Game;
import com.subarashi.bomberman.gui.menu.Menu;

public class Frame extends JFrame {
	
	public GamePanel _gamepane; // cai nay hien o cuoi frame
	private JPanel _containerpane;
	private InfoPanel _infopanel; // cai nay de hien thong tin time lives points o dau frame
	
	private Game _game;

	public Frame() throws Exception {
		setJMenuBar(new Menu(this));
		
		_containerpane = new JPanel(new BorderLayout());
		_gamepane = new GamePanel(this);
		_infopanel = new InfoPanel(_gamepane.getGame());
		
		_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		_containerpane.add(_gamepane, BorderLayout.PAGE_END); // game hien o cuoi frame
		
		_game = _gamepane.getGame();
		
		add(_containerpane);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
		_game.start();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Game Related
	|--------------------------------------------------------------------------
	 */
	public void newGame() throws Exception {
		_game.getBoard().newGame();
	}
	
	public void changeLevel(int i) throws Exception {
		_game.getBoard().changeLevel(i);
	}
	
	public void pauseGame() {
		_game.getBoard().gamePause();
	}
	
	public void resumeGame() {
		_game.getBoard().gameResume();
	}
	
	public boolean isRunning() {
		return _game.isRunning();
	}
	
	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	
	public void setLives(int lives) {
		_infopanel.setLives(lives);
	}
	
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}
	
}
