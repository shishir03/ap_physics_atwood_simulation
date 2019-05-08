package atwoodmachine;

import materials.Mass;
import materials.Pulley;
import processing.core.PApplet;

public class AtwoodMachine {
    private Mass mass1;
    private Mass mass2;
    private Pulley ply;
    private double acceleration;
    private double t1;
    private double t2;

    public AtwoodMachine(double m1, double m2, double M, double a, double t1, double t2) {
        mass1 = new Mass(m1, 0, 0);
        mass2 = new Mass(m2, 0, 0);
        ply = new Pulley(M, 0, 0);
        acceleration = a;
        this.t1 = t1;
        this.t2 = t2;
    }

    public void calculateAcceleration() {
        double m1 = mass1.getMass();
        double m2 = mass2.getMass();

        if(acceleration == -1) {
            double netForce = m2 * 9.81 - m1 * 9.81;
            acceleration = netForce / (m1 + m2);
        }
    }

    public void act() {
        mass1.setAY(-acceleration);
        mass1.act();
        mass2.setAY(acceleration);
        mass2.act();
        ply.setAlpha(acceleration * ply.getRadius());
        ply.act();
    }

    public void draw(PApplet p) {
        mass1.draw(p);
        mass2.draw(p);
        ply.draw(p);
    }
}