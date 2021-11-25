package com.company;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;


import com.company.sorting_algorithms.BubbleSort;
import com.company.sorting_algorithms.SortingAlgorithm;



public class GUIComponents {
    private GUIComponents() {}

    public static JPanel buttonsPanel, displayPanel;

    private static JButton newListButton;
    public static JSlider delaySlider;
    private static JButton randomizeListButton;
    private static JButton doSortButton;

    private static SortingAlgorithm algorithm;

    static {
        initButtonsPanel();
        displayPanel = new PaintSurface();
    }

    private static void initButtonsPanel() {
        buttonsPanel = new JPanel();

        buttonsPanel.setPreferredSize(new Dimension(Configs.BUTTONS_PANEL_WIDTH, Configs.BUTTONS_PANEL_HEIGHT));
        buttonsPanel.setBackground(Color.LIGHT_GRAY);


        initNewListButton();
        initDelaySlider();
        initRandomizeListButton();
        initDoSortButton();


        buttonsPanel.add(new JLabel("Sorting Delay (ms)"));
        buttonsPanel.add(delaySlider);

        buttonsPanel.add(newListButton);

        buttonsPanel.add(randomizeListButton);

        buttonsPanel.add(doSortButton);
    }

    private static void initNewListButton() {
        newListButton = new JButton("New List");

        newListButton.addActionListener((ActionEvent event) -> {
            int amount;

            try {
                String temp = JOptionPane.showInputDialog(null, "How many numbers?", "Enter Information", JOptionPane.INFORMATION_MESSAGE);
                if(temp != null && temp.length() > 0) {
                    amount = Integer.parseInt(temp);
                } else return;
            } catch(java.lang.NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(amount < 0 || amount > 1024) {
                JOptionPane.showMessageDialog(null, "Invalid Amount! 0 - 1024 only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NumbersList.generateList(amount);
            GUIComponents.displayPanel.repaint();
        });
    }

    private static void initDelaySlider() {
        delaySlider = new JSlider(JSlider.HORIZONTAL, Configs.MINIMUM_DELAY_VALUE, Configs.MAXIMUM_DELAY_VALUE, Configs.INITIAL_DELAY_VALUE);
        delaySlider.setMajorTickSpacing(Configs.TICK_SPACING);
        delaySlider.setMinorTickSpacing(10);
        delaySlider.setPaintLabels(true);
        delaySlider.setSnapToTicks(true);
        delaySlider.setBackground(Color.LIGHT_GRAY);

        delaySlider.addChangeListener((ChangeEvent event) -> {
            if(algorithm != null)
                algorithm.changeDelay(delaySlider.getValue() * 1000);
        });
    }

    private static void initRandomizeListButton() {
        randomizeListButton = new JButton("Randomize List");
        randomizeListButton.addActionListener((ActionEvent event) -> {
            NumbersList.randomizeList();
            GUIComponents.displayPanel.repaint();
        });
    }


    private static void initDoSortButton() {
        doSortButton = new JButton("Do Sort");

        doSortButton.addActionListener((ActionEvent event) -> {
            randomizeListButton.setEnabled(false);
            newListButton.setEnabled(false);
            doSortButton.setEnabled(false);

            algorithm = new BubbleSort();


            new Thread(() -> {
                algorithm.doSort(NumbersList.getList());

                randomizeListButton.setEnabled(true);
                newListButton.setEnabled(true);
                doSortButton.setEnabled(true);
            }).start();



            displayPanel.repaint();
        });
    }
}
