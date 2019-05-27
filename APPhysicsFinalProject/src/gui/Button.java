package gui;

import processing.core.PApplet;
import processing.core.PConstants;

import java.awt.geom.Rectangle2D;

public class Button extends Rectangle2D.Float {
    private String text;

    public Button(float x, float y, float width, float height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void draw(PApplet p) {
        p.pushStyle();
        p.rect(x, y, width, height);
        // p.textMode(PConstants.CENTER);
        p.textSize(height / 2);
        p.fill(0);
        p.text(text, 0.05f*width + x, 0.75f*height + y);
        p.popStyle();
    }
}