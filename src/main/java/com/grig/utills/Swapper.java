package com.grig.utills;

public class Swapper {
    int[][] massive;

    public Swapper(int[][] massive) {
        this.massive = massive;
    }

    public void swap(Coordinates coordinatesFirst, Coordinates coordinatesSecond) {
        int temp = massive[coordinatesFirst.getJ()][coordinatesFirst.getI()];
        massive[coordinatesFirst.getJ()][coordinatesFirst.getI()] = massive[coordinatesSecond.getJ()][coordinatesSecond.getI()];
        massive[coordinatesSecond.getJ()][coordinatesSecond.getI()] = temp;
    }
}
