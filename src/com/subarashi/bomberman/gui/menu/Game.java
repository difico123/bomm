package com.subarashi.bomberman.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.subarashi.bomberman.gui.Frame;
import com.subarashi.bomberman.gui.InfoDialog;

public class Game extends JMenu {

	public Frame frame;
	private static int _topScore;
	public Game(Frame frame) {
		super("Game");
		this.frame = frame;
		
		/*
		 * New Game
		 */
		JMenuItem newgame = new JMenuItem("New Game");
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newgame.addActionListener(new MenuActionListener(frame));
		add(newgame);
		
		/*
		 * Scores
		 */
		JMenuItem scores = new JMenuItem("Top Scores");
		scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		scores.addActionListener(new MenuActionListener(frame));
		add(scores);
	}
	
	class MenuActionListener implements ActionListener {
		public Frame _frame;
		public MenuActionListener(Frame frame) {
			_frame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("New Game")) {
				  try {
					  _frame.newGame();
				  } catch (Exception exception) {
					  exception.printStackTrace();
				  }
			  }
			  
			  if(e.getActionCommand().equals("Top Scores")) {
				  String name = "";
				  try {
					  File ReadWordsFromFile = new File("res/textures/highscore.txt");
					  Scanner scanner = new Scanner(ReadWordsFromFile);
					  name = scanner.next();
					  setTopScore(scanner.nextInt());
					  scanner.close();
				  } catch (FileNotFoundException p) {
					  System.out.println("An error occurred.");
					  p.printStackTrace();
				  }
				  new InfoDialog(_frame, "Top Scores", "Name : " + name +"\nPoints : " + getTopScore(), JOptionPane.INFORMATION_MESSAGE);
			  }
		  }
		}
	public static int getTopScore() {
		return _topScore;
	}

	public void setTopScore(int topScore) {
		this._topScore = topScore;
	}
}