package com.company;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel {

    public ArrayList<Items> itemsArrayList = new ArrayList<>();
    public static final int DIMENSIONS = 700;
    public static int points = 0;
    public static int lifes = 3; //TODO nose si es millor posarho aqui o com a propietat de Mr, depen de que usi mes resources
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

    public Mr getMr() { //per a poder handlejar els efectes dels items directament des de les seves classes
        return mr;
    }

    public void augmentPoints() {
        points = points + 5;
        if (points % 20 == 0) {

        }
    }

    public void incrementLifes() {
        lifes = lifes + 1;
    }

    public void destroyItem(Items item) {
        itemsArrayList.remove(item);
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

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        JFrame frame = new JFrame("Mini Polla");
        frame.add(game);
        frame.setSize(DIMENSIONS, DIMENSIONS );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(new ItemsCreator(game) ).start();

        while (lifes > 0) {
            game.repaint();
            Thread.sleep(50);
        }
        game.gameOver();
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}