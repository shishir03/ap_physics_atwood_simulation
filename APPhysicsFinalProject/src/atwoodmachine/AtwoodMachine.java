package atwoodmachine;

import materials.Mass;
import materials.Pulley;
import processing.core.PApplet;

public class AtwoodMachine {
    protected Mass mass1;
    protected Mass mass2;
    protected Pulley ply;
    protected double acceleration;
    protected static final double GRAVITY = 0.0981;
    protected double t1;
    protected double t2;

    public AtwoodMachine(double m1, double m2, double M, double a) {
        ply = new Pulley(M, 500, 200);
        mass1 = new Mass(m1, 500 - (float)ply.getRadius(), 450, 60);
        mass2 = new Mass(m2, 500 + (float)ply.getRadius(), 450, 60);
        acceleration = a;

        calculateAcceleration();
        calculateTension();
    }

    protected void calculateAcceleration() {

    }

    public void act() {
        mass1.act();
        mass2.act();
        ply.setOmega(mass2.getVy() / ply.getRadius());
        ply.act();
    }

    public void checkEnd() {
        ply.setOmega(0);
    }

    public void draw(PApplet p) {
        mass1.draw(p, 0);
        mass2.draw(p, 0);
        ply.draw(p);
    }

    public void calculateTension() {

    }

    public double getT1() {
        return t1;
    }

    public double getT2() {
        return t2;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getVelocity() {
        return mass2.getVy();
    }
}