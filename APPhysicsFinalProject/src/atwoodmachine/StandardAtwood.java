package atwoodmachine;

import processing.core.PApplet;

public class StandardAtwood extends AtwoodMachine {
    public StandardAtwood(double m1, double m2, double M, double a) {
        super(m1, m2, M, a);
        mass1.setAY(-acceleration);
        mass2.setAY(acceleration);
    }

    protected void calculateAcceleration() {
        double m1 = mass1.getMass();
        double m2 = mass2.getMass();
        double M = ply.getMass();
        double r = ply.getRadius();

        if(acceleration == -1) {
            if(M == 0) {
                double netForce = m2 * GRAVITY - m1 * GRAVITY;
                acceleration = netForce / (m1 + m2);
            } else {
                double netTorque = m2 * GRAVITY * r - m1 * GRAVITY * r;
                acceleration = netTorque * r / (m1 * r*r + m2 * r*r + 0.5 * M * r*r);
            }
        }
    }

    public void act() {
        if(mass1.getY() <= ply.getY() + 100) checkEnd1();
        else if(mass2.getY() <= ply.getY() + 100) checkEnd2();
        super.act();
    }

    public void stop() {
        mass1.setVX(0);
        mass1.setAX(0);
        mass2.setVY(0);
        mass2.setAY(0);
    }

    public void setAcceleration(double a) {
        mass1.setAY(-a);
        mass2.setAY(a);
    }

    public void setVelocity(double v) {
        mass1.setVY(-v);
        mass2.setVY(v);
    }

    public void draw(PApplet p) {
        super.draw(p);
        p.line(500 - (float)ply.getRadius(), 200, 500 - (float)ply.getRadius(), (float)(mass1.getY() - mass1.getSize() / 2));
        p.line(500 + (float)ply.getRadius(), 200, 500 + (float)ply.getRadius(), (float)(mass2.getY() - mass2.getSize() / 2));
    }

    public void checkEnd1() {
        super.checkEnd();
        mass1.setVY(1);
        mass2.setVY(-1);
    }

    public void checkEnd2() {
        super.checkEnd();
        mass1.setVY(-1);
        mass2.setVY(1);
    }

    @Override
    public void calculateTension() {
        double m1 = mass1.getMass();
        double m2 = mass2.getMass();

        t1 = m1 * (GRAVITY + acceleration);
        t2 = m2 * (GRAVITY - acceleration);
    }
}