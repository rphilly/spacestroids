package com.game.util;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SaveScore {

    private final static String save = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\scores.txt";

    public void write(String score) {
        if (Integer.parseInt(score) <= 0) {
            return;
        }

        try {
            FileWriter fw = new FileWriter(save, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(score);
            out.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Error: failed to write file " + e);
        }
    }

    private ArrayList<Integer> read() {
        ArrayList<Integer> scores = new ArrayList<>();

        File file = new File(save);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                scores.add(Integer.parseInt(str));
            }
        } catch (Exception e) {
            System.out.println("Error: failed to read file " + e);
        }
        scores.sort(Collections.reverseOrder());

        return scores;
    }

    public ArrayList<Integer> getHiscores() {
        ArrayList<Integer> scores = read();

        if (scores.size() > 10) {
            scores.subList(10, scores.size()).clear();
        }

        return scores;
    }
}
