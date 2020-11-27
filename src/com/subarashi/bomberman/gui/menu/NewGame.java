package com.subarashi.bomberman.gui.menu;

import com.subarashi.bomberman.gui.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGame extends JMenu{
    private JButton newGame;
    private Frame frame;
    public NewGame(Frame frame) {
        this.frame = frame;
        newGame = new JButton("New Game");
        newGame.addActionListener(new MenuActionListener(frame));
        add(newGame);
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

