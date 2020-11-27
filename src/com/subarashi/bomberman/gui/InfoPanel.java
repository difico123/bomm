package com.subarashi.bomberman.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.subarashi.bomberman.Game;

public class InfoPanel extends JPanel {
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private JLabel livesLabel;
	private JLabel highScoreLabel;
	public static int _highScore;
	public String name;

	public InfoPanel(Game game) throws IOException{
		setLayout(new GridLayout(4,0));

		timeLabel = new JLabel(" " + game.getBoard().getTime());
		timeLabel.setForeground(Color.BLACK);
		timeLabel.setFont(new java.awt.Font("Tahoma", 1, 16));
		timeLabel.setHorizontalAlignment(JLabel.LEFT);
		timeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/clock.jpg")));
		
		pointsLabel = new JLabel(" " + game.getBoard().getPoints());
		pointsLabel.setForeground(Color.BLACK);
		pointsLabel.setFont(new java.awt.Font("Tahoma", 1, 16));
		pointsLabel.setHorizontalAlignment(JLabel.LEFT);
		pointsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/star.jpg")));
		
		livesLabel = new JLabel(" " + game.getBoard().getLives());
		livesLabel.setForeground(Color.BLACK);
		livesLabel.setFont(new java.awt.Font("Tahoma", 1, 16));
		livesLabel.setHorizontalAlignment(JLabel.LEFT);
		livesLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/heart.png")));

		HighScore();
		highScoreLabel = new JLabel(name + " SCORE: " + _highScore);
		highScoreLabel.setForeground(Color.BLACK);
		highScoreLabel.setFont(new java.awt.Font("Tahoma", 1, 16));
		highScoreLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/highscore.png")));
		highScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highScoreLabel.setHorizontalTextPosition(JLabel.CENTER);
		highScoreLabel.setVerticalTextPosition(JLabel.BOTTOM);

		add(timeLabel);
		add(pointsLabel);
		add(livesLabel);
		add(highScoreLabel);
		
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(200, 0));// kick thuoc rieng
	}
	
	public void setTime(int t) {
		timeLabel.setText(" " + t);
	}

	public void setLives(int t) {
		livesLabel.setText(" " + t);
	}

	public void setPoints(int t) {
		pointsLabel.setText(" " + t);
	}

	public void HighScore() throws IOException {
		String str = "";
		try {
			FileReader in = new FileReader("res/textures/highscore.txt");
			BufferedReader br = new BufferedReader(in);
			str = br.readLine();
			String[] tokens = str.split(Character.toString('@'), 2);
			name = "";
			name += tokens[0];
			_highScore = 0;
			_highScore = Integer.parseInt(tokens[1]);

		} catch (FileNotFoundException p) {
			System.out.println("An error occurred.");
			p.printStackTrace();
		}
	}

	public static int getHighScore() {
		return _highScore;
	}
}
