package com.company;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class AppGUI {
    public AppGUI() {
        NumbersList.generateList(Configs.INITIAL_LIST_STARTING_VALUE);

        initGUI();
    }

    private void initGUI() {
        JFrame window = new JFrame();

        window.getContentPane().setLayout(new FlowLayout());

        window.getContentPane().add(GUIComponents.buttonsPanel);
        window.getContentPane().add(GUIComponents.displayPanel);

        window.setTitle(Configs.APPLICATION_NAME);
        window.setResizable(false);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}

