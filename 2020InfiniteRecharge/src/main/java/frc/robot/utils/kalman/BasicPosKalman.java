package frc.robot.utils.kalman;

import frc.robot.utils.matrix.*;
import frc.robot.utils.exceptions.*;

public class BasicPosKalman {
    //state matrix
    private Matrix x;
    //prediction state matrix
    private Matrix xp;
    //coefficient matrix for the state matrix in the prediction equation
    private Matrix a;
    //coeffecient matrix for the control matrix in the prediction equation
    private Matrix b;
    //control matrix for prediction equation
    private Matrix u;
    //prediction process covariance matrix
    private Matrix pp;
    //process covariance matrix
    private Matrix p;
    //kalman gain matrix
    private Matrix k;
    //final measurement state matrix
    private Matrix y;
    //measured state matrix
    private Matrix xm;
    //coeffecient for measured state matrix in measurement equation
    private Matrix c;

    //right now this configures the filter to track acceleration, velocity, and position in x and y directions
    public BasicPosKalman() {
        x = new Matrix(6, 1);
        xp = new Matrix(6, 1);
        a = new Matrix(new double[][] {{1, 0, .02, 0, .002, 0},
                                       {0, 1, 0, .02, 0, .002},
                                       {0, 0, 1, 0, .02, 0},
                                       {0, 0, 0, 1, 0, .02},
                                       {0, 0, 0, 0, 1, 0},
                                       {0, 0, 0, 0, 0, 1}});

        b = new Matrix(new double[][] {{0, 0, 0, 0},
                                       {0, 0, 0, 0},
                                       {.02, 0, .002, 0},
                                       {0, .02, 0, .002},
                                       {0, 0, .02, 0},
                                       {0, 0, 0, .02}});
        u = new Matrix(4, 1);
        //TODO replace the 1's in this matrix with the covariances of the different values
        p = new Matrix(new double[][] {{1, 0, 0, 0, 0, 0}, //x pos
                                       {0, 1, 0, 0, 0, 0}, //y pos
                                       {0, 0, 1, 0, 0, 0}, //x veloc
                                       {0, 0, 0, 1, 0, 0}, //y veloc
                                       {0, 0, 0, 0, 1, 0}, //x accel
                                       {0, 0, 0, 0, 0, 1}}); //y accel
        pp = new Matrix(6, 6);
        k = new Matrix(6, 1);
        y = new Matrix(6, 1);
        xm = new Matrix(6, 1);
        //we don't have a way to directly measure velocity so this matrix zeroes those from the measurement
        c = new Matrix(new double[][] {{0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 0, 0},
                                       {0, 0, 1, 0, 0, 0},
                                       {0, 0, 0, 1, 0, 0},
                                       {0, 0, 0, 0, 1, 0},
                                       {0, 0, 0, 0, 0, 1}});
    }

    public void predict() {
        //x_p = ax + bu = w (not including w just yet - it's a noise matrix)
        xp = MatrixOperations.add(MatrixOperations.multiply(a, x), MatrixOperations.multiply(b, u));
        //p_p = a * p * a^T + q (also not including q as it is a noise matrix)
        pp = MatrixOperations.multiply(a, MatrixOperations.multiply(p, a.transpose()));
    }

    //m is a matrix created with all of the values from the sensors
    public void measure(Matrix m) {
        
    }
}