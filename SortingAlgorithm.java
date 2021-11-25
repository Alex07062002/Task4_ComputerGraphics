package com.company.sorting_algorithms;
import com.company.PaintSurface;
import com.company.GUIComponents;

public interface SortingAlgorithm {

    void doSort(int[] nums);

    void changeDelay(int delay);

    static void setCurrentBar(int currentBarIndex) {
        PaintSurface.currentBarIndex = currentBarIndex;
    }

    static void sleepFor(int delay) {
        long timeElapsed;
        final long startTime = System.nanoTime();

        do {
            timeElapsed = System.nanoTime() - startTime;
        } while(timeElapsed < delay);

        GUIComponents.displayPanel.repaint();
    }
}
