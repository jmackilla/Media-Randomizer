package shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class Randomizer {

    // TODO: add keybindings to play next file
    private final int topMargin;
    ArrayList<File> fileList;
    private final JFrame frame = new JFrame();
    private File previousFile;

    public Randomizer(String path, int topMargin) {
        this.fileList = new ArrayList<>(Arrays
                .asList(Objects.requireNonNull(new File(path).listFiles())));
        this.topMargin = topMargin;        
    }

    public void init() {
        //TODO: hide button boarders

        frame.setSize(599, 75); // not the real size, but a hack, see below
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        frame.setBounds(0, topMargin, 600, 40);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.black);

        JLabel filename = new JLabel("");

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

        JButton currentButton;

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

    public File openSwfAndReturnName() throws Exception {
        File currentSwf = getRandomFile();
        if (currentSwf.isDirectory()) { // remove directories when found
            fileList.remove(currentSwf);
            currentSwf = getRandomFile();
        }
        Desktop.getDesktop().open(currentSwf);
        fileList.remove(currentSwf);
        return currentSwf;
    }

    public File getRandomFile() {
        Random rand = new Random();
        int index = rand.nextInt(fileList.size());
        return fileList.get(index);
    }
}


