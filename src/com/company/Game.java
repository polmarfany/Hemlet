package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel {

    private Image backgroundImage = Icon.getIcon("icon/fondo.jpg");

    public ArrayList<Items> itemsArrayList = new ArrayList<>();
    private static final int DIMENSIONS = 700;
    private static int points = 0;
    private static String pointer;
    private static int lifes = 3; //TODO nose si es millor posarho aqui o com a propietat de Mr, depen de que usi mes resources
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

    public void setLifes(int lifesMinus) {
        lifes = lifes - lifesMinus;
    }

    public void incrementPoints() {
        points = points + 5;
        if (points % 25 == 0) { //cada 25 punts, s'augmenta la dificultat, tant per HOLD TIME (creacio items) com per MOVE TIME (caiguda items)
            ItemsCreator.decreaseHoldTime();
            Items.disminuirMoveTime();
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
        g.drawImage(backgroundImage,0, 0, this.getWidth(), this.getHeight(), null);
        g.drawString(String.valueOf(points), 200, 20);
        g.drawString(String.valueOf(lifes), 250, 20);
        mr.paint(g2d);

        for (Items item : itemsArrayList) { //pintem els ITEMS
            item.paint(g2d);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        JFrame frame = new JFrame("Mini Polla");

        /*
        JLabel background = new JLabel(new ImageIcon(Icon.getIcon("icon/fondo.jpg")));
        frame.setContentPane(background);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;  //Intent de posar el background, funciona pero ni es reescala ni es mostra el GAME
*/
        frame.add(game);
        frame.setSize(DIMENSIONS, DIMENSIONS);
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