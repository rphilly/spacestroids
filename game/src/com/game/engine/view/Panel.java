package com.game.engine.view;

import com.game.state.Menu;
import com.game.state.State;
import com.game.util.FontLoader;
import com.game.util.MouseHandler;
import com.game.util.SpriteLoader;
import com.game.util.Vector2f;
import com.game.world.entity.Asteroid;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements Runnable {

    private final int width;
    private final int height;

    private Thread thread;
    private boolean isRunning = false;

    private BufferedImage img;
    private Graphics2D g2d;

    private MouseHandler mouse;

    public boolean showFPS = false;
    String fpsString = "";

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
        g2d = (Graphics2D) img.getGraphics();

        if (g2d == null) {
            System.out.println(this);
            return;
        }

        mouse = new MouseHandler(this);

        State menuState = new Menu(this);
        State.setState(menuState);
    }

    @Override
    public void run() {
        initialise();

        int framesPerSecond = 60;
        double timePerTick = 1e9 / framesPerSecond;
        double delta = 0;
        long currentFrameTime;
        long lastFrameTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (isRunning) { //Game loop
            currentFrameTime = System.nanoTime();
            delta += (currentFrameTime - lastFrameTime) / timePerTick;
            timer += currentFrameTime - lastFrameTime; //No. of nano seconds passed
            lastFrameTime = currentFrameTime;

            if (delta >= 1) {
                input(mouse);
                update();
                render();
                draw();
                ticks++;
                delta--;
            }

            if (timer >= 1e9) {
                double fps = ticks;

                if (showFPS) {
                    fpsString = String.format("FPS: %3.1f", fps);
                }

                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public void update() {
        if (State.getState() != null) {
            State.getState().update();
        }
    }

    public void input(MouseHandler mouse) {
        //If exists,
        if (State.getState() != null) {
            State.getState().input(mouse);
        }
    }

    public void render() {
        if (g2d != null) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, width, height);

            //Setup FPS
            if (showFPS) {
                g2d.setFont(new Font("Times New Roman", Font.BOLD, 14));
                g2d.setColor(Color.GREEN);
                g2d.drawString(fpsString, 40, 40);
            }
        }

        if (State.getState() != null) {
            State.getState().render(g2d);
        }
    }

    public void draw() {
        Graphics g = this.getGraphics();
        g.drawImage(img, 0, 0, width, height, null);
        g.dispose();
    }

    public MouseHandler getMouseHandler() {
        return mouse;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
