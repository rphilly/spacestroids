package com.game.state;

import com.game.engine.view.Panel;
import com.game.state.ui.UiButton;
import com.game.util.MouseHandler;
import com.game.util.SpriteLoader;
import com.game.util.Vector2f;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Hiscores extends State {

    public Hiscores(Panel panel) {
        super(panel);
        panel.getMouseHandler().setUiManager(uiManager);

        //Back
        uiManager.addObject(new UiButton((float) panel.getWidth() / 2 - 70, 520, 140, 45, () -> {
            setState(new Menu(panel));
        }));
    }

    @Override
    public void input(MouseHandler mouse) {
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        uiManager.render(g2d);

        SpriteLoader.drawFont(g2d, "HISCORES", new Vector2f((float) panel.getWidth() / 2, 100), 0.75f,22, 0);
        SpriteLoader.drawFont(g2d, "Back", new Vector2f((float) panel.getWidth() / 2, 529), 0.5f,14, 0);

        ArrayList<String> scoreList = getHiscores();
        g2d.setColor(Color.white);
        for (int i = 0; i < scoreList.size(); i++) {
            g2d.drawString(i + 1 + ". " + scoreList.get(i), 600, 265 + i * 25);
        }
    }

    private final static String save = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\scores.txt";

    public static void write(String score, int round) {
        String record = Name.name + ", " + score + ", " + round;

        HashSet<String> list = new HashSet<>(read());
        list.add(record);

        if (Integer.parseInt(score) <= 0) {
            return;
        }

        try {
            FileWriter fw = new FileWriter(save, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for (String line : list) {
                out.println(line);
            }
            out.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error: failed to write file " + e);
        }
    }

    private static ArrayList<String> read() {
        ArrayList<String> scores = new ArrayList<>();

        File file = new File(save);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                scores.add(str);
            }
        } catch (Exception e) {
            System.out.println("Error: failed to read file " + e);
        }

        return scores;
    }

    public ArrayList<String> getHiscores() {
        ArrayList<String> scores = read();

        if (scores.size() > 10) {
            scores.subList(10, scores.size()).clear();
        }

        return scores;
    }
}
