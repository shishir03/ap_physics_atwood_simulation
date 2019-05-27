package gui;

import processing.core.PApplet;

import java.awt.geom.Rectangle2D;

public class Slider {
    private String label;
    private double value;
    private double minValue;
    private double maxValue;
    private float length;
    private Rectangle2D.Float sliderPosition;
    private float x, y;

    public Slider(double min, double max, float length, float x, float y, String label) {
        value = (min + max) / 2;
        minValue = min;
        maxValue = max;
        this.length = length;
        this.x = x;
        this.y = y;
        this.label = label;
        sliderPosition = new Rectangle2D.Float();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double newValue) {
        if(newValue > maxValue) {
            value = maxValue;
        } else if(newValue < minValue) {
            value = minValue;
        } else value = newValue;
    }

    public void draw(PApplet p) {
        p.pushStyle();
        p.fill(0);
        // p.textSize(20);
        p.text(label, x, y - 20);
        p.line(x, y, x + length, y);
        p.fill(255);
        sliderPosition = new Rectangle2D.Float((float)(x + length * (value - minValue) / (maxValue - minValue)), y - 10, 10, 20);
        p.rect(sliderPosition.x, sliderPosition.y, sliderPosition.width, sliderPosition.height);
        p.popStyle();
    }

    public boolean inSlider(double x, double y) {
        return sliderPosition.contains(x, y);
    }
}