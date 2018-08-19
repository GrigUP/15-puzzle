package com.grig.utills;

public class Swapper {
    int[][] massive;

    public Swapper(int[][] massive) {
        this.massive = massive;
    }

    public void swap(Coordinates coordinatesFirst, Coordinates coordinatesSecond) {
        int temp = massive[coordinatesFirst.getI()][coordinatesFirst.getJ()];
        massive[coordinatesFirst.getI()][coordinatesFirst.getJ()] = massive[coordinatesSecond.getI()][coordinatesSecond.getJ()];
        massive[coordinatesSecond.getI()][coordinatesSecond.getJ()] = temp;
    }
}
