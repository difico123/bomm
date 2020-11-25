package com.subarashi.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

import com.subarashi.bomberman.Game;
//import com.subarashi.bomberman.exceptions.BombermanException;

public class GamePanel extends JPanel {

	private Game _game;
	
	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		
		try {
				_game = new Game(frame);
			
			add(_game);
			
			_game.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			//TODO: so we got a error hum..
			System.exit(0);
		}
		setVisible(true);
		//setFocusable(true);
	}
	
	public void changeSize() {
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		revalidate();
		repaint();
	}
	
	public Game getGame() {
		return _game;
	}
	
}
