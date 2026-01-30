package main;

public enum Paths {
    LEL("W:\\STUFF FOLDER\\lel", 0, false), // row one
    AMV("W:\\STUFF FOLDER\\amv", 40, false), // row two
    FUNNY("W:\\STUFF FOLDER\\funny", 80, false), // row three
    CLOVER("W:\\STUFF FOLDER\\Clover videos", 120, false), // row four
    VIDEOS("W:\\Videos", 160, true); // row five

    public final String path;
    public final int row;
    public final boolean includeSubdirs;

    Paths(String path, int row, boolean includeSubdirs) {
        this.path = path;
        this.row = row;
        this.includeSubdirs = includeSubdirs;
    }
}