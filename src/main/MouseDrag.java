package main;

import java.awt.*;

public class MouseDrag {

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            // Simulate a key press'
            while (true) {
                robot.mouseWheel(3);
                Thread.sleep(300);
                if (scanner()) {
                    break;
                }
                Thread.sleep(300);
                if (scanner()) {
                    break;
                }
                Thread.sleep(300);
                if (scanner()) {
                    break;
                }
                Thread.sleep(300);
                if (scanner()) {
                    break;
                }
                Thread.sleep(300);
                if (scanner()) {
                    break;
                }
                robot.mouseWheel(-6);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean scanner() {
        return false;
    }
}
