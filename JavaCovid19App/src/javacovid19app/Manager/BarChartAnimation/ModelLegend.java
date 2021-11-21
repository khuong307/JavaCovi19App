/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarChartAnimation;

import java.awt.Color;

/**
 *
 * @author IVS-P0005
 */
public class ModelLegend {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ModelLegend(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public ModelLegend() {
    }

    private String name;
    private Color color;
}