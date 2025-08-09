package main;

import component.Randomizer;

public class MediaRandomizer {

public static final int ROW_ONE = 0;
public static final int ROW_TWO = 40;
public static final int ROW_THREE = 80;
public static final int ROW_FOUR = 120;
public static final int ROW_FIVE = 160;

    public static void main(String[] args) {
        new Randomizer("W:\\STUFF FOLDER\\lel", ROW_ONE);
        new Randomizer("W:\\STUFF FOLDER\\amv", ROW_TWO);
        new Randomizer("W:\\STUFF FOLDER\\funny", ROW_THREE);
        new Randomizer("W:\\STUFF FOLDER\\Clover videos", ROW_FOUR);
        new Randomizer("W:\\Videos", ROW_FIVE, true);
    }
}
