package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class AMVRandomizer {

    public static final String SWF_PATH = "W:\\STUFF FOLDER\\amv";  // path to any directory
    static final ArrayList<File> FILE_LIST = new ArrayList<>(Arrays
            .asList(Objects.requireNonNull(new File(SWF_PATH).listFiles())));
    static final JFrame frame = new JFrame();
    static final JLabel filename = new JLabel("");
    static JButton currentButton;
    static File previousFile;

    public static void main(String[] args) {
        //TODO: hide button boarders

        frame.setSize(599, 75); // not the real size, but a hack, see below
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("SWF Randomizer");
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); // good if you want no bar

        // other bars
/*        "JRootPane.NONE",
                "JRootPane.FRAME",
                "JRootPane.PLAIN_DIALOG",
                "JRootPane.INFORMATION_DIALOG",
                "JRootPane.ERROR_DIALOG",
                "JRootPane.COLOR_CHOOSER_DIALOG",
                "JRootPane.FILE_CHOOSER_DIALOG",
                "JRootPane.QUESTION_DIALOG",
                "JRootPane.WARNING_DIALOG"*/

        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setBounds(0, 40, 600, 40);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.black);

        buttonPanel.add(filename);
        JButton getRandomSwf = new JButton("Play");
        getRandomSwf.setBackground(Color.BLACK);
        getRandomSwf.setForeground(Color.GRAY);

        getRandomSwf.addActionListener(e -> {
            try {
                previousFile = openSwfAndReturnName();
                filename.setText(previousFile.getName());
                filename.setForeground(Color.GRAY);
                buttonPanel.add(filename);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(getRandomSwf);
        currentButton = new JButton("Replay");
        currentButton.setBackground(Color.BLACK);
        currentButton.setForeground(Color.GRAY);

        currentButton.addActionListener(e -> {
            try {
                if (previousFile != null) {
                    Desktop.getDesktop().open(previousFile);
                    buttonPanel.add(filename);
                } else {
                    previousFile = openSwfAndReturnName();
                    filename.setText(previousFile.getName());
                    filename.setForeground(Color.GRAY);
                    buttonPanel.add(filename);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(currentButton);

        JButton closeButton = new JButton("Exit");
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.GRAY);
        buttonPanel.add(closeButton);
        closeButton.addActionListener(e -> {
            try {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        frame.add(buttonPanel);
    }

    public static File openSwfAndReturnName() throws Exception {
        File currentSwf = getRandomFile();
        if (currentSwf.isDirectory()) { // remove directories when found
            FILE_LIST.remove(currentSwf);
            currentSwf = getRandomFile();
        }
        Desktop.getDesktop().open(currentSwf);
        FILE_LIST.remove(currentSwf);
        return currentSwf;
    }

    public static File getRandomFile() {
        Random rand = new Random();
        int index = rand.nextInt(FILE_LIST.size());
        return FILE_LIST.get(index);
    }
}
