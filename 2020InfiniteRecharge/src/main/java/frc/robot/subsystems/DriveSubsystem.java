/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//TODO test all features and review code for potential errors that could arise during operation

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase implements Subsystem {
  /**
   * Creates a new DriveSubsystem.
   */
  private CANSparkMax rightMaster;
  private CANSparkMax rightSlave;
  private CANSparkMax leftMaster;
  private CANSparkMax leftSlave;

  private double MAX = 1.0;

  private static final double TRIGGER_THRESHOLD = 0.2;

  public DriveSubsystem() {
    // setDefaultCommand();

    //create new motor controller objects for the drive
    rightMaster = new CANSparkMax(0, MotorType.kBrushless);
    rightSlave = new CANSparkMax(1, MotorType.kBrushless);
    leftMaster = new CANSparkMax(2, MotorType.kBrushless);
    leftSlave = new CANSparkMax(3, MotorType.kBrushless);

    //set slaves to follow master motor controllers
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

    //one side will need to be inverted, unknown which one as of now
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);

    //defensive code, Talons had issues with this and I'd rather not go through that again
    leftMaster.setInverted(false);
    leftSlave.setInverted(false);
  }

  //class convenience method to move the robot to save space in the different drive methods
  private void move(double r, double l) {
    //set max to either full or reduced based on driver trigger for increased control
    MAX = (Robot.m_io.xbox0.getRawAxis(6) >= TRIGGER_THRESHOLD) ? 0.6 : 1.0;

    //defensive code to prevent the values being passed to move from exceeding the accepted ranges on the motor controllers
    r = (r > MAX) ? MAX :  r;
    r = (r < -MAX) ? -MAX : r;
    l = (l > MAX) ? MAX : l;
    l = (l < -MAX) ? -MAX : l;

    //set motor powers, slaves do not need to be called as they were set to follow the master in the class constructor
    rightMaster.set(r);
    leftMaster.set(l);
  }

  //method to be called from the arcade drive command
  public void arcadeDrive(double x, double y) {
    double r, l;

    //set the values of r and l based off of x and y axes - may need to switch addition and subtraction, untested
    r = x + y;
    l = x - y;

    move(r, l);
  }

  //method to be called in the event of a tank drive
  public void tankDrive(double r, double l) {
    //this is pretty self explanatory
    move(r, l);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
