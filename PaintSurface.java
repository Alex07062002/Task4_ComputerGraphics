package com.company;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PaintSurface extends JPanel {
    private static final long serialVersionUID = 1L;

    public static int currentBarIndex = 0;

    public PaintSurface() {
        this.setPreferredSize(new Dimension(Configs.DISPLAY_PANEL_WIDTH, Configs.DISPLAY_PANEL_HEIGHT));

        this.setBackground(Color.PINK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(graphics);

        int[] listNumbers = NumbersList.getList();

        double x = 0, width = (double) Configs.DISPLAY_PANEL_WIDTH / listNumbers.length;
        for(int i = 0; i < listNumbers.length; i++) {
            if(currentBarIndex == i)
                graphics.setPaint(Color.RED);
            else
                graphics.setPaint(Color.BLACK);

            graphics.fillRect((int) x, Configs.APPLICATION_HEIGHT - listNumbers[i], (int) width + 1, listNumbers[i]);

            x += width;
        }

        graphics.dispose();
        g.dispose();
    }
}

