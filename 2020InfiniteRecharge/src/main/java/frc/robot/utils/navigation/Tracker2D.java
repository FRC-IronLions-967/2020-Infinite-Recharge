/*
    This class was made to aid the Kalman filter, and tracks position, velocity, and acceleration in 2 dimensions
    I might rewrite this later because it feels a little to inflexible for my liking
*/

package frc.robot.utils.navigation;

public class Tracker2D {
    private double xPos;
    private double yPos;
    private double xVel;
    private double yVel;
    private double xAcc;
    private double yAcc;

    public Tracker2D(double xPos, double yPos, double xVel, double yVel, double xAcc, double yAcc) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.xAcc = xAcc;
        this.yAcc = yAcc;
    }

    //this array should have 6 elements and be in the form {xPos, yPos, xVel, yVel, xAcc, yAcc}
    //any shorter length and the values will be set to 0.0
    //if the length is longer, all values past index 5 will be ignored
    public Tracker2D(double[] values) {
        if(values.length < 6) {
            this.xPos = 0.0;
            this.yPos = 0.0;
            this.xVel = 0.0;
            this.yVel = 0.0;
            this.xAcc = 0.0;
            this.yAcc = 0.0;
        } else {
            this.xPos = values[0];
            this.yPos = values[1];
            this.xVel = values[2];
            this.yVel = values[3];
            this.xAcc = values[4];
            this.yAcc = values[5];
        }
    }

    public void update(double xPos, double yPos, double xVel, double yVel, double xAcc, double yAcc) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.xAcc = xAcc;
        this.yAcc = yAcc;
    }

    public void update(double[] values) {
        if(values.length < 6) {
            this.xPos = 0.0;
            this.yPos = 0.0;
            this.xVel = 0.0;
            this.yVel = 0.0;
            this.xAcc = 0.0;
            this.yAcc = 0.0;
        } else {
            this.xPos = values[0];
            this.yPos = values[1];
            this.xVel = values[2];
            this.yVel = values[3];
            this.xAcc = values[4];
            this.yAcc = values[5];
        }
    }

    public double getXPosition() {
        return xPos;
    }

    public double getYPosition() {
        return yPos;
    }

    public double getXVelocity() {
        return xVel;
    }

    public double getYVelocity() {
        return yVel;
    }

    public double getXAcceleration() {
        return xAcc;
    }

    public double getYAcceleration() {
        return yAcc;
    }
}