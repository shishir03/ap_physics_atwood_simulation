package atwoodmachine;

import processing.core.PApplet;

public class RampAtwood extends HalfAtwood {
    private double theta;

    public RampAtwood(double m1, double m2, double M, double a, double u, double theta) {
        super(m1, m2, M, a, u);
        this.theta = theta;
    }

    @Override
    protected void calculateAcceleration() {
        double m1 = mass1.getMass();
        double m2 = mass2.getMass();
        double M = ply.getMass();
        double r = ply.getRadius();

        if(acceleration == -1) {
            if(M == 0) {
                double netForce = m2 * GRAVITY - frictionCoef * m1 * GRAVITY * Math.cos(theta);
                acceleration = netForce / (m1 + m2);
            } else {
                double netTorque = m2 * GRAVITY * r - frictionCoef * m1 * GRAVITY * Math.cos(theta) * r;
                acceleration = netTorque * r / (m1 * r*r + m2 * r*r + 0.5 * M * r*r);
            }
        }
    }

    public void draw(PApplet p) {
        mass1.draw(p, -theta);
        mass2.draw(p, 0);
        ply.draw(p);
        p.line((float)ply.getX(), (float)ply.getY() - (float)ply.getRadius(), (float)(mass1.getX() + Math.cos(theta) * mass1.getSize() / 2),
                (float)(mass1.getY() + Math.sin(theta) * mass1.getSize() / 2));
        p.line((float)(ply.getX() + ply.getRadius()), (float)ply.getY(), (float)mass2.getX(), (float)mass2.getY() - (float)mass2.getSize() / 2);
        p.triangle((float)ply.getX(), (float)(ply.getY() + ply.getSize()), -100, 1100, (float)ply.getX(), 1100);
    }
}