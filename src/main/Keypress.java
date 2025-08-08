package main;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Keypress {
    public static void main(String args[]) {
        try {
            Robot robot = new Robot();

            // Simulate a key press'
            while (true) {
                robot.delay(100);
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.delay(50);
                robot.keyRelease(KeyEvent.VK_SPACE);
                robot.delay(100);
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.delay(50);
                robot.keyRelease(KeyEvent.VK_SPACE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
