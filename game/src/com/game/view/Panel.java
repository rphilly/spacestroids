package com.game.view;

import com.game.state.Game;
import com.game.state.Menu;
import com.game.state.State;
import com.game.util.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private Thread thread;
    private boolean isRunning = false;

    private BufferedImage img;
    private Graphics2D g;

    private Mouse mouse;

    //Player player;

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        start();
    }

    private synchronized void start() { //Starts thread
        if (isRunning)
            return;
        isRunning = true;

        thread = new Thread(this, "GameThread"); //refers to run function
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

    public void initialise() {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        mouse = new Mouse(this);

        State gameState = new Game(this);
        State menuState = new Menu(this);

        State.setState(menuState);
    }

    @Override
    public void run() {
        initialise();

        while (isRunning) { //Game loop
            //System.out.println("running");
            render();
            draw();
            update();
            input(mouse);
        }

        stop();
    }

    public void update() {
        if (State.getState() != null) {
            State.getState().update();
        }
    }

    public void input(Mouse mouse) {
        //If exists,
        if (State.getState() != null) {
            State.getState().input(mouse);
        }
    }

    public void render() {
        if (g != null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        }

        if (State.getState() != null) {
            State.getState().render(g);
        }
    }

    public void draw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }

    public Mouse getMouseHandler() {
        return mouse;
    }
}
