/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProcessBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;

/**
 *
 * @author IVS-P0005
 */
public class LiquidProgress extends JProgressBar{
    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public int getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public Color getAnimateColor() {
        return animateColor;
    }

    public void setAnimateColor(Color animateColor) {
        this.animateColor = animateColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    private final LiquidProgressUI UI;
    private int borderSize = 5;
    private int spaceSize = 5;
    private Color animateColor = new Color(125, 216, 255);
    private Color borderColor = new Color(0, 178, 255);

    public LiquidProgress() {
        UI = new LiquidProgressUI(this);
        setOpaque(false);
        setFont(new Font(getFont().getFamily(), 1, 20));
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.WHITE);
        setForeground(new Color(0, 178, 255));
        setUI(UI);
        setStringPainted(true);
    }
    
    public void startAnimation() {
        UI.start();
    }

    public void stopAnimation() {
        UI.stop();
    }
}
