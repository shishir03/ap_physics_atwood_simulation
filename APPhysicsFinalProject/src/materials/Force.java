package materials;

import processing.core.PApplet;

public class Force {
    private double x;
    private double y;
    private double fx;
    private double fy;
    private double theta;
    private double length;

    public Force(double x, double y, double fx, double fy) {
        this.x = x;
        this.y = y;
        this.fx = fx;
        this.fy = fy;
        theta = Math.atan(fy / fx);
        length = Math.sqrt(fx*fx + fy*fy);
    }

    public Force(double x, double y, float length, double theta) {
        this.x = x;
        this.y = y;
        fx = length * Math.cos(theta);
        fy = length * Math.sin(theta);
        this.theta = theta;
        this.length = length;
    }

    public void draw(PApplet p) {
        if(fx != 0 && fy != 0) {
            p.pushStyle();
            p.strokeWeight(5);
            p.stroke(0);
            p.line((float) x, (float) y, (float) x + (float) fx * 10, (float) y + (float) fy * 10);
            p.fill(0);
            p.triangle((float) (x + fx * 10), (float) (y + fy * 10), (float) (x + fx * 9 + 10 * Math.cos(Math.PI / 2 - theta)),
                    (float) (y + fy * 9 + 10 * Math.sin(Math.PI / 2) - theta), (float) (x + fx * 9 - 10 * Math.cos(Math.PI / 2 - theta)),
                    (float)(y + fy * 9 - 10 * Math.sin(Math.PI / 2) - theta));

            Force[] fc = getComponents();
            fc[0].draw(p);
            fc[1].draw(p);
            p.popStyle();
        }
    }

    private Force[] getComponents() {
        if(fy == 0 || fx == 0) return new Force[] {this};
        else {
            return new Force[] {
                    new Force(x, y, fx, 0),
                    new Force(x, y, 0, fy)
            };
        }
    }
}