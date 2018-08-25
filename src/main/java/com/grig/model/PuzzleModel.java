package com.grig.model;

import com.grig.utills.Coordinates;
import com.grig.utills.Swapper;

import java.util.Random;

public class PuzzleModel {
    private Coordinates coordinatesNull;
    private int n;
    private int[][] massive;

    public PuzzleModel(int n) {
        this.n = n;
        massive = new int[n][n];
        fill();
    }

    private void fill() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    massive[i][j] = 0;
                    coordinatesNull = new Coordinates(i, j);
                    break;
                }
                massive[i][j] = ++count;
            }
        }
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d ", massive[i][j]);
            }
            System.out.println();
        }
    }

    public void shuffle(int num) {
        int totalElement = n*n-2;
        Random rnd = new Random();
        Swapper swpr = new Swapper(massive);
        for (int i = 0; i < num; i++) {
            for (int k = 0; k < totalElement; k++) {
                int index = rnd.nextInt(totalElement - 1);
                Coordinates coordinateFirst = new Coordinates(k % n, k / n);
                Coordinates coordinatesSecond = new Coordinates(index % n, index / n);

                swpr.swap(coordinateFirst, coordinatesSecond);
            }
        }
    }

    public boolean isGameOver() {
        int oldValue = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n-1 && j == n-1) break;

                if (massive[i][j] - oldValue != 1) return false;
                oldValue = massive[i][j];
            }
        }
        return true;
    }

    public int getN() {
        return n;
    }

    public int[][] getMassive() {
        return massive;
    }

    public Coordinates getCoordinatesNull() {
        return coordinatesNull;
    }
}
