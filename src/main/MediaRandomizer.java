package main;

import component.Randomizer;

public class MediaRandomizer {

public static final int ROW_ONE = 0;

public static final int ROW_TWO = 40;

public static final int ROW_THREE = 80;

public static final int ROW_FOUR = 120;

public static final int ROW_FIVE = 160;

    public static void main(String[] args) {

        Randomizer randomizer = new Randomizer("W:\\STUFF FOLDER\\lel", ROW_ONE);
        randomizer.init();
        
        Randomizer randomizer2 = new Randomizer("W:\\STUFF FOLDER\\amv", ROW_TWO);
        randomizer2.init();

        Randomizer randomizer3 = new Randomizer("W:\\STUFF FOLDER\\funny", ROW_THREE);
        randomizer3.init();

        Randomizer randomizer4 = new Randomizer("W:\\STUFF FOLDER\\Clover videos", ROW_FOUR);
        randomizer4.init();

        Randomizer randomizer5 = new Randomizer("W:\\Videos", ROW_FIVE, true);
        randomizer5.init();
    }
}
