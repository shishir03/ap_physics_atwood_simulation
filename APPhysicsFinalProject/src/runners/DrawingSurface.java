package runners;

import atwoodmachine.AtwoodMachine;
import atwoodmachine.HalfAtwood;
import atwoodmachine.StandardAtwood;
import gui.Button;
import gui.Slider;
import processing.core.PApplet;

public class DrawingSurface extends PApplet {
    private AtwoodMachine am;
    private StandardAtwood sm;
    private HalfAtwood hm;
    private boolean stopped;
    private Button halfAtwood;
    private Button standardAtwood;
    private Button stopStart;
    private Button reset;
    private Button setDefault;
    private Slider m1;
    private Slider m2;
    private Slider M;

    public DrawingSurface() {
        stopped = true;
        halfAtwood = new Button(500, 15, 85, 25, "Half Atwood");
        standardAtwood = new Button(600, 15, 115, 25, "Standard Atwood");
        stopStart = new Button(730, 15, 75, 25, "Play/Pause");
        reset = new Button(820, 15, 40, 25, "Reset");
        setDefault = new Button(875, 15, 100, 25, "Restore Values");
        m1 = new Slider(100, 2000, 140, 15, 35, "Mass 1");
        m2 = new Slider(100, 2000, 140, 170, 35, "Mass 2");
        M = new Slider(0, 2000, 140, 325, 35, "Pulley mass");
        m1.setValue(700);
        m2.setValue(1000);
        M.setValue(0);
        hm = new HalfAtwood(m1.getValue(), m2.getValue(), M.getValue(),-1, 0);
        sm = new StandardAtwood(m1.getValue(), m2.getValue(), M.getValue(), -1);
        am = hm;
    }

    public void setup() {
        background(255);
    }

    public void draw() {
        if(!stopped) {
            am.act();
        }

        pushStyle();
        background(255);
        am.draw(this);
        halfAtwood.draw(this);
        standardAtwood.draw(this);
        stopStart.draw(this);
        reset.draw(this);
        setDefault.draw(this);
        m1.draw(this);
        m2.draw(this);
        M.draw(this);
        fill(0);
        text("Acceleration: " + Math.abs(Math.round(am.getAcceleration() * 10000.0) / 100.0) + " m/s", 850, 100);
        text("t1: " + Math.abs(Math.round(am.getT1() * 10000.0) / 10000.0) + " N", 850, 120);
        text("t2: " + Math.abs(Math.round(am.getT2() * 10000.0) / 10000.0) + " N", 850, 140);
        popStyle();
    }

    public void mouseClicked() {
        if(halfAtwood.contains(mouseX, mouseY)) {
            am = hm;
            stopAtwood();
            reset();
        } else if(standardAtwood.contains(mouseX, mouseY)) {
            am = sm;
            stopAtwood();
            reset();
        } else if(stopStart.contains(mouseX, mouseY)) stopAtwood();
        else if(reset.contains(mouseX, mouseY)) reset();
        else if(setDefault.contains(mouseX, mouseY)) {
            m1.setValue(700);
            m2.setValue(1000);
            M.setValue(0);
            reset();
        }
    }

    public void mouseDragged() {
        if(m1.inSlider(pmouseX, pmouseY)) {
            double difference = (mouseX - pmouseX) * 1900 / 140.0;
            m1.setValue(m1.getValue() + difference);
        } else if(m2.inSlider(pmouseX, pmouseY)) {
            double difference = (mouseX - pmouseX) * 1900 / 140.0;
            m2.setValue(m2.getValue() + difference);
        } else if(M.inSlider(pmouseX, pmouseY)) {
            double difference = (mouseX - pmouseX) * 1900 / 140.0;
            M.setValue(M.getValue() + difference);
        }
    }

    public void mouseReleased() {
        if(mouseX != pmouseX || mouseY != pmouseY) reset();
    }

    private void reset() {
        boolean whichMachine = am == hm;
        hm = new HalfAtwood(m1.getValue(), m2.getValue(), M.getValue(),-1, 0);
        sm = new StandardAtwood(m1.getValue(), m2.getValue(), M.getValue(), -1);
        if(whichMachine) am = hm;
        else am = sm;
        stopped = true;
    }

    private void stopAtwood() {
        stopped = !stopped;
    }
}