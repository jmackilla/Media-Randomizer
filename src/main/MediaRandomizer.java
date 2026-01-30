package main;

import component.Randomizer;

public class MediaRandomizer {

    public static void main(String[] args) {
        for (Paths p : Paths.values()) {
            new Randomizer(p.path, p.row, p.includeSubdirs);
        }
    }
}
