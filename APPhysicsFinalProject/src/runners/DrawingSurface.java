package runners;

import atwoodmachine.AtwoodMachine;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
    private AtwoodMachine am;

    public DrawingSurface() {
        am = new AtwoodMachine(0.7, 1, 0, -1, -1, -1);
        am.calculateAcceleration();
    }

    public void setup() {

    }

    public void draw() {
        am.draw(this);
        am.act();
    }
}
