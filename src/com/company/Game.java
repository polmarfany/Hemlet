package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel {
    public ArrayList<Items> itemsArrayList = new ArrayList<>();
    public static final int DIMENSIONS = 700;
    public static int points = 0;
    public static String pointer;
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
            ItemsCreator.decreaseHoldTime();
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

        g.drawString(String.valueOf(points), 200, 20);
        mr.paint(g2d); //pintem el MR

        for (Items item : itemsArrayList) { //pintem els ITEMS
            item.paint(g2d);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        JFrame frame = new JFrame("Mini Polla");
        JLabel background = new JLabel(new ImageIcon(Icon.getIcon("icon/fondo.jpg")));
        frame.setContentPane(background);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(game);
        frame.setSize(DIMENSIONS, DIMENSIONS );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(new ItemsCreator(game) ).start(); //creador items

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