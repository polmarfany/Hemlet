package com.company;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;

public class Game extends JPanel {

    public ArrayList<Items> itemsArrayList = new ArrayList<>();
    public static final int DIMENSIONS = 700;
    private Mr mr = new Mr(this);

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {mr.keyReleased(e);}
        });
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        mr.paint(g2d);

        for (Items item : itemsArrayList) {
            item.paint(g2d);
        }
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public Mr getMr() { //per a poder handlejar els efectes dels items directament des de les seves classes
        return mr;
    }

    public void destroyItem(Items item) {
        itemsArrayList.remove(item);
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        JFrame frame = new JFrame("Mini Polla");
        frame.add(game);
        frame.setSize(DIMENSIONS, DIMENSIONS );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(new ItemsCreator(game) ).start();

        while (true) {
            game.repaint();
            Thread.sleep(10);
        }
    }
}