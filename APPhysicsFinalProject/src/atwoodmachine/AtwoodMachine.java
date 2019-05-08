package atwoodmachine;

import materials.Mass;
import materials.Pulley;

public class AtwoodMachine {
    private Mass mass1;
    private Mass mass2;
    private Pulley ply;

    public AtwoodMachine(double m1, double m2, double M) {
        mass1 = new Mass(m1, 0, 0);
        mass2 = new Mass(m2, 0, 0);
        ply = new Pulley(M, 0, 0);
    }
}