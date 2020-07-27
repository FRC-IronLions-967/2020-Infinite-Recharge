/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.kalman.BasicPosKalman;
import frc.robot.utils.navigation.Tracker2D;
import frc.robot.utils.matrix.*;

public class KalmanSubsystem extends SubsystemBase {
  private BasicPosKalman posKalman;
  private Tracker2D tracker;

  //the following matrices control the kalman filter, they will require some time and effort to tune, but will hopefully pay
  //dividends with their ability to track position with a very high degree of accuracy

  //modify the values here based on the starting errors, high values mean higher error, meaning that these values will change faster
  public static final Matrix p = new Matrix(new double[][] {{1.0, 0, 0, 0, 0, 0}, //x pos error
                                              {0, 1.0, 0, 0, 0, 0}, //y pos error
                                              {0, 0, 1.0, 0, 0, 0}, //x veloc error
                                              {0, 0, 0, 1.0, 0, 0}, //y veloc error
                                              {0, 0, 0, 0, 1.0, 0}, //x accel error
                                              {0, 0, 0, 0, 0, 1.0}}); //y accel error

  //modify the values here based on the starting position, velocity, and acceleration                                              
  public static final Matrix x = new Matrix(new double[][] {{0.0}, //pos on x axis
                                                    {0.0}, //pos on y axis
                                                    {0.0}, //units/sec on x axis
                                                    {0.0}, //units/sec on y axis
                                                    {0.0}, //accelerating at units/sec^2 on x axis
                                                    {0.0}}); //accelerating at units/sec^2 on y axis

  
  //modify the values here to reflect the relative errors in the sensors
  public static final Matrix r = new Matrix(new double[][] {{1.0, 0, 0, 0, 0, 0},
                                                    {0, 1.0, 0, 0, 0, 0},
                                                    {0, 0, 1.0, 0, 0, 0},
                                                    {0, 0, 0, 1.0, 0, 0},
                                                    {0, 0, 0, 0, 1.0, 0},
                                                    {0, 0, 0, 0, 0, 1.0}});

  /**
   * Creates a new KalmanSubsystem.
   */
  public KalmanSubsystem() {
    posKalman = new BasicPosKalman(x, p);

    //modify the starting position based off of the robot's starting position in the match
    tracker = new Tracker2D(new double[] {});
  }

  public Tracker2D getTracker() {
    return tracker;
  }

  //this does the update stuff, it runs at the same 50 Hz of main robot loop, but I believe it runs in a different thread
  //this also improves readability and keeps the kalman stuff in one place
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    posKalman.predict();
    //the matrix object created here needs to take in the values from the sensors to feed into the filter
    //TODO get the values here from the various sensors
    posKalman.measure(new Matrix(new double[][] {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}}), r);
    posKalman.update();
    tracker.update(posKalman.getX().getColumn(0));
  }
}
