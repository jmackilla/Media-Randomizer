package component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Single directory file randomizer with GUI
 */
public class Randomizer {
    
    private File previousFile;

    public Randomizer(String path, int topMargin, boolean showExit) {
        init(path, topMargin, showExit);
    }

    public Randomizer(String path, int topMargin) {
        init(path, topMargin, false);
    }
    
    // TODO: I can tab between play and replay but I cannot hit enter to button press

    public void init(String path, int topMargin, boolean showExit) {
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.black);

        JLabel filename = new JLabel(path);
 
        JButton getRandomSwf = new JButton("Play");
        getRandomSwf.setBackground(Color.BLACK);
        getRandomSwf.setForeground(Color.GRAY);
        
        ArrayList<File> files = new ArrayList<>(Arrays
                .asList(Objects.requireNonNull(new File(path).listFiles()))); 

        getRandomSwf.addActionListener(e -> {
            try {
                previousFile = openSwfAndReturnName(files);
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
                    previousFile = openSwfAndReturnName(files);
                    filename.setText(previousFile.getName());
                    filename.setForeground(Color.GRAY);
                    buttonPanel.add(filename);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPanel.add(currentButton);

        Frame frame = createFrame(topMargin);
        if (showExit) {
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
        }

        buttonPanel.add(filename);
        frame.add(buttonPanel);

    }

    public JFrame createFrame(int topMargin) {
        JFrame frame = new JFrame();

        frame.setSize(600, 75); // not the real size, but a hack, see below
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE); // good if you want no bar

        //TODO: hide button boarders
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

        return frame;
    }

    public File openSwfAndReturnName(ArrayList<File> files) throws Exception {
        File currentSwf = getRandomFile(files);
        if (currentSwf.isDirectory()) { // remove directories when found
            files.remove(currentSwf);
            currentSwf = getRandomFile(files);
        }
        Desktop.getDesktop().open(currentSwf);
        files.remove(currentSwf);
        return currentSwf;
    }

    public File getRandomFile(ArrayList<File> files) {
        Random rand = new Random();
        int index = rand.nextInt(files.size());
        return files.get(index);
    }
}


