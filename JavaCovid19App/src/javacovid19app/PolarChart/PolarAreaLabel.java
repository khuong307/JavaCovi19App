/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PolarChart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author IVS-P0005
 */
public class PolarAreaLabel extends JLabel {
    public PolarAreaLabel() {
        setBorder(new EmptyBorder(3,25,3,3));
        setFont(new Font("Fredoka One", 0, 14));
        setForeground(new Color(250, 255, 255));
        
    }

    @Override
    public void paint(Graphics graph) {
        super.paint(graph);
        int size = getHeight() - 10;
        Graphics2D g2 = (Graphics2D) graph;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = (getHeight() - size) / 2;
        g2.setColor(getBackground());
        g2.fillOval(3, y, size, size);
        g2.dispose();
    }
}
