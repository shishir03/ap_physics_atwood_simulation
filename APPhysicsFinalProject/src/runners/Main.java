package runners;

import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {
    public static void main(String args[]) {
        DrawingSurface drawing = new DrawingSurface();
        PApplet.runSketch(new String[]{"DrawingSurface"}, drawing);
        PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
        PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
        JFrame window = (JFrame)canvas.getFrame();

        window.setSize(1000, 1000);
        window.setMinimumSize(new Dimension(100,100));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Atwood Machine Simulation");

        window.setVisible(true);
    }
}
