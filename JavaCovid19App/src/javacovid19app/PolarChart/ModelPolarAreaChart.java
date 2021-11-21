/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PolarChart;

import java.awt.Color;

/**
 *
 * @author IVS-P0005
 */
public class ModelPolarAreaChart {
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValues() {
        return values;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public ModelPolarAreaChart(Color color, String name, double values) {
        this.color = color;
        this.name = name;
        this.values = values;
    }

    public ModelPolarAreaChart() {
    }

    private Color color;
    private String name;
    private double values;
}

