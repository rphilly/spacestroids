package com.game.core;

import com.game.player.Handler;
import com.game.player.Player;

import javax.swing.JPanel;
import java.awt.*;

public class Panel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private boolean isRunning = false;
    private Thread thread;

    Player player = new Player();

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        start();

        Handler mouse = new Handler();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        new Player();
        //player = new Player(50, 50, new Sprite("entity/player/player.png"));
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, width, height);

        player.draw(g2D);
        g2D.dispose();
    }

    private synchronized void start() { //Starts thread
        if (isRunning)
            return;

        isRunning = true;
        thread = new Thread(this); //refers to run
        thread.start(); //starts the thread
    }

    private synchronized void stop() {
        if (!isRunning)
            return;

        isRunning = false;
        try {
            thread.join(); //Joins threads together and wait til die
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {
        while (isRunning) { //Game loop

        }
        stop();
    }
}
