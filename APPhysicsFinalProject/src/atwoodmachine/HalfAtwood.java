package atwoodmachine;

import materials.Force;
import materials.Mass;
import materials.Pulley;
import processing.core.PApplet;

public class HalfAtwood extends AtwoodMachine {
    protected double frictionCoef;

    public HalfAtwood(double m1, double m2, double M, double a, double u) {
        super(m1, m2, M, a);
        t1 = 0;
        t2 = 0;
        ply = new Pulley(M, 800, 200);
        mass1 = new Mass(m1, 100, 200 - (float)ply.getRadius(), 150);
        mass2 = new Mass(m2, 800 + (float)ply.getRadius(), 350, 60);
        frictionCoef = u;
        mass1.setAX(acceleration);
        mass2.setAY(acceleration);
        calculateTension();
    }

    protected void calculateAcceleration() {
        double m1 = mass1.getMass();
        double m2 = mass2.getMass();
        double M = ply.getMass();
        double r = ply.getRadius();

        if(acceleration == -1) {
            if(M == 0) {
                double netForce = m2 * GRAVITY - frictionCoef * m1 * GRAVITY;
                acceleration = netForce / (m1 + m2);
            } else {
                double netTorque = m2 * GRAVITY * r - frictionCoef * m1 * GRAVITY * r;
                acceleration = netTorque * r / (m1 * r*r + m2 * r*r + 0.5 * M * r*r);
            }
        }
    }

    public void act() {
        if(mass1.getX() + mass1.getSize() / 2 >= ply.getX() - ply.getRadius() || mass2.getY() <= ply.getY() + 50) checkEnd();
        super.act();
    }

    public void draw(PApplet p) {
        super.draw(p);
        p.line(800, 200 - (float)ply.getRadius(), (float)(mass1.getX() + mass1.getSize() / 2), 200 - (float)ply.getRadius());
        p.line(800 + (float)ply.getRadius(), 200, 800 + (float)ply.getRadius(), (float)(mass2.getY() - mass2.getSize() / 2));
        p.rect(-100, (float)ply.getY(), (float)ply.getX() - 15 + 100, (float)ply.getY() - 15 + 1000);
        (new Force(mass1.getX(), mass1.getY() + mass1.getSize() / 2, frictionCoef * mass1.getMass() * GRAVITY, 0)).draw(p);
        (new Force(mass1.getX(), mass1.getY(), t1, 0)).draw(p);
        (new Force(mass1.getX(), mass1.getY(), 0.0, mass1.getMass() * GRAVITY)).draw(p);
        (new Force(mass1.getX(), mass1.getY(), 0.0, -mass1.getMass() * GRAVITY)).draw(p);
        (new Force(mass2.getX(), mass2.getY(), 0.0, mass2.getMass() * GRAVITY)).draw(p);
        (new Force(mass2.getX(), mass2.getY(), t2, 0)).draw(p);
    }

    public void checkEnd() {
        super.checkEnd();
        mass1.setVX(-1);
        mass2.setVY(-1);
    }

    public void stop() {
        mass1.setVX(0);
        mass1.setAX(0);
        mass2.setVY(0);
        mass2.setAY(0);
    }

    public void setAcceleration(double a) {
        mass1.setAX(a);
        mass2.setAY(a);
    }

    public void setVelocity(double v) {
        mass1.setVX(v);
        mass2.setVY(v);
    }

    @Override
    public void calculateTension() {
        double m1 = mass1.getMass();
        double m2 = mass2.getMass();

        t1 = m1 * acceleration + m1 * GRAVITY * frictionCoef;
        t2 = m2 * (GRAVITY - acceleration);
    }
}
