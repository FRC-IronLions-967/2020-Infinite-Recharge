/*
    This class is supposed to use a Kalman filter to track position along with x and y velocity and acceleration
    If anyone reading this actually has experience with this, I realize this is probably a complete abhorration of
    a Kalman filter, and I'm sorry
*/

package frc.robot.utils.kalman;

import frc.robot.utils.matrix.*;
import frc.robot.utils.exceptions.*;
import org.apache.commons.math3.linear.*;

public class BasicPosKalman {
    //state matrix
    private Matrix x;

    //prediction state matrix
    private Matrix xp;

    //coefficient matrix for the state matrix in the prediction equation
    private Matrix a;

    //coefficient matrix for the control matrix in the prediction equation - unused as I'm not using a control matrix
    //private Matrix b;
    //control matrix for prediction equation - unused
    // private Matrix u;

    //prediction process covariance matrix
    private Matrix pp;

    //process covariance matrix
    private Matrix p;

    //kalman gain matrix
    private Matrix k;

    //final measurement state matrix
    private Matrix y;

    //coefficient for measured state matrix in measurement equation
    private Matrix c;

    //coefficient matrix used for calculating kalman gain
    private Matrix h;

    //identity matrix
    private Matrix i;

    //right now this configures the filter to track acceleration, velocity, and position in x and y directions
    public BasicPosKalman(Matrix init, Matrix initErr) {
        x = init;
        // u = new Matrix(new double[][] {{x.getElement(2, 0)}, {x.getElement(3, 0)}, {x.getElement(4, 0)}, {x.getElement(5, 0)}});
        xp = new Matrix(6, 1);
        a = new Matrix(new double[][] {{1, 0, .02, 0, .002, 0},
                                       {0, 1, 0, .02, 0, .002},
                                       {0, 0, 1, 0, .02, 0},
                                       {0, 0, 0, 1, 0, .02},
                                       {0, 0, 0, 0, 1, 0},
                                       {0, 0, 0, 0, 0, 1}});

        // b = new Matrix(new double[][] {{.02, 0, .002, 0},
                                    //    {0, .02, 0, .002},
                                    //    {0, 0, .02, 0},
                                    //    {0, 0, 0, .02},
                                    //    {0, 0, 0, 0},
                                    //    {0, 0, 0, 0}});
        p = initErr;
        pp = new Matrix(6, 6);
        k = new Matrix(6, 1);
        y = new Matrix(6, 1);

        //we don't have a way to directly measure position so this matrix zeroes those from the measurement
        c = new Matrix(new double[][] {{0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 0, 0},
                                       {0, 0, 1, 0, 0, 0},
                                       {0, 0, 0, 1, 0, 0},
                                       {0, 0, 0, 0, 1, 0},
                                       {0, 0, 0, 0, 0, 1}});

        h = new Matrix(new double[][] {{1, 0, 0, 0, 0, 0},
                                       {0, 1, 0, 0, 0, 0},
                                       {0, 0, 1, 0, 0, 0},
                                       {0, 0, 0, 1, 0, 0},
                                       {0, 0, 0, 0, 1, 0},
                                       {0, 0, 0, 0, 0, 1}});

        i = new Matrix(new double[][] {{1, 0, 0, 0, 0, 0},
                                       {0, 1, 0, 0, 0, 0},
                                       {0, 0, 1, 0, 0, 0},
                                       {0, 0, 0, 1, 0, 0},
                                       {0, 0, 0, 0, 1, 0},
                                       {0, 0, 0, 0, 0, 1}});
    }

    public Matrix getX() {
        return x;
    }

    public void predict() throws DimensionMismatchException {
        //there is a control matrix in a kalman filter, but because we are tracking the control variables i'm not using it
        //might change if this becomes an issue
        //xp = MatrixOperations.add(MatrixOperations.multiply(a, x), MatrixOperations.multiply(b, u));

        //x_p = ax + bu = w (not including w just yet - it's a noise matrix)
        xp = MatrixOperations.multiply(a, x);

        //p_p = a * p * a^T + q (also not including q as it is a noise matrix)
        pp = MatrixOperations.multiply(a, MatrixOperations.multiply(p, a.transpose()));
    }

    //m is a matrix created with all of the values from the sensors
    //r is a matrix that holds the covariances of all of the sensor data
    //calculates the measured position and the kalman gain
    public void measure(Matrix xm, Matrix r) throws DimensionMismatchException {
        y = MatrixOperations.multiply(c, xm);

        //i split this into three lines because it was even more unreadable the other way
        //the equation is: (pp*h)/((h*pp*h^T) + r)
        k = MatrixOperations.add(MatrixOperations.multiply(MatrixOperations.multiply(h, pp), h.transpose()), r);

        Matrix inverse = new Matrix(MatrixUtils.inverse(new Array2DRowRealMatrix(k.getMat())).getData());

        k = MatrixOperations.multiply(inverse, MatrixOperations.multiply(pp, h));

    }

    public void update() throws DimensionMismatchException {
        x = MatrixOperations.add(xp, MatrixOperations.multiply(k, MatrixOperations.subtract(y, MatrixOperations.multiply(h, x))));
        System.out.println("Final x matrix:");
        x.printMatrix();
        // u = new Matrix(new double[][] {{x.getElement(2, 0)}, {x.getElement(3, 0)}, {x.getElement(4, 0)}, {x.getElement(5, 0)}});
        p = MatrixOperations.multiply(MatrixOperations.subtract(i, MatrixOperations.multiply(k, h)), p);
        System.out.println("Final p matrix:");
        p.printMatrix();
    }
}