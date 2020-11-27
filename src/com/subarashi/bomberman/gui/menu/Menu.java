package com.subarashi.bomberman.gui.menu;

import javax.swing.*;

import com.subarashi.bomberman.gui.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {
	public Menu(Frame frame) {
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new MenuActionListener(frame));
		add( newGame );
		//add( new GameMenu(frame) );
		add( new Options(frame) );
		add( new Help(frame) );
	}

	class MenuActionListener implements ActionListener {
		public Frame _frame;

		public MenuActionListener(Frame frame) {
			this._frame = frame;
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
		}
	}
}
