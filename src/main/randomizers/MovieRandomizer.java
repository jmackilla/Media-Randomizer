package main.randomizers;

import shared.Randomizer;
import javax.swing.*;
import java.awt.*;

public class MovieRandomizer {

    public static void main(String[] args) {
        Randomizer randomizer = new Randomizer("W:\\Videos", 80);
        randomizer.init();
    }
}