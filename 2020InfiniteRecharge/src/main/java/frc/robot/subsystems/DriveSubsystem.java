/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//TODO test all features and review code for potential errors that could arise during operation

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.commands.AutoAimCommand;
import frc.robot.commands.RumbleCommand;
import frc.robot.utils.values.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveSubsystem extends SubsystemBase implements Subsystem {
  /**
   * Creates a new DriveSubsystem.
   */
  public static boolean lastAimSuccessful = false;
  
  public CANSparkMax rightMaster;
  public CANSparkMax rightSlave;
  public CANSparkMax leftMaster;
  public CANSparkMax leftSlave;

  private IO io;

  //Drive lookup table(might be automatically generated in the future).
  private double lookup[] = {0, 0, 0,  0.1, 0.10009, 0.10036, 0.10081, 
		0.10144, 0.10225, 0.10324, 0.10441, 0.10576, 0.10729, 
		0.109, 0.11089, 0.11296, 0.11521, 0.11764, 0.12025, 
		0.12304, 0.12601, 0.12916, 0.13249, 0.136, 0.13969, 0.14356, 
		0.14761, 0.15184, 0.15625, 0.16084, 0.16561, 0.17056, 0.17569, 0.181, 
		0.18649, 0.19216, 0.19801, 0.20404, 0.21025, 0.21664, 0.22321, 
		0.22996, 0.23689, 0.244, 0.25129, 0.25876, 
		0.26641, 0.27424, 0.28225, 0.29044, 0.29881, 0.30736, 0.31609, 
		0.325, 0.33409, 0.34336, 0.35281, 0.36244, 0.37225, 0.38224, 
		0.39241, 0.40276, 0.41329, 0.424, 0.43489, 0.44596, 0.45721, 
		0.46864, 0.48025, 0.49204, 0.50401, 0.51616, 
		0.52849, 0.541, 0.55369, 0.56656, 0.57961, 0.59284, 0.60625, 0.61984, 
		0.63361, 0.64756, 0.66169, 0.676, 0.69049, 0.70516, 0.72001, 0.73504, 0.75025, 0.76564, 
		0.78121, 0.79696, 0.81289, 0.829, 0.84529, 0.86176, 0.87841, 0.89524, 0.91225, 
    0.92944, 0.94681, 0.96436, 0.98209, 1.0};
    
  private TurningFunction turningFunction;

  double MAX;

  public DriveSubsystem() {
    io = IO.getInstance();

    //Assigns the robot IDs from the robotMap.properties file
    rightMaster = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("rightMaster")), MotorType.kBrushless);
    rightSlave = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("rightSlave")), MotorType.kBrushless);
    leftMaster = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("leftMaster")), MotorType.kBrushless);
    leftSlave = new CANSparkMax(Integer.parseInt(Robot.m_robotMap.getValue("leftSlave")), MotorType.kBrushless);

    //set the ramp rate of all of the motor controllers
    double rate = Double.parseDouble(Robot.m_values.getValue("rampRate"));
    rightMaster.setOpenLoopRampRate(rate);
    rightSlave.setOpenLoopRampRate(rate);
    leftMaster.setOpenLoopRampRate(rate);
    leftSlave.setOpenLoopRampRate(rate);

    //set slaves to follow master motor controllers
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

    //one side will need to be inverted, unknown which one as of now
    rightMaster.setInverted(false);
    rightSlave.setInverted(false);

    //defensive code, Talons had issues with this and I'd rather not go through that again
    leftMaster.setInverted(true);
    leftSlave.setInverted(true);

    turningFunction = new TurningFunction(Double.parseDouble(Robot.m_values.getValue("deadband")), Double.parseDouble(Robot.m_values.getValue("minPower")), Double.parseDouble(Robot.m_values.getValue("aimPower")), Double.parseDouble(Robot.m_values.getValue("highCutoff")));
    MAX = Double.parseDouble(Robot.m_values.getValue("MAX"));
  }

  //class convenience method to move the robot to save space in the different drive methods
  public void move(double r, double l) {

    //defensive code to prevent the values being passed to move from exceeding the accepted ranges on the motor controllers
    r = (r > MAX) ? MAX :  r;
    r = (r < -(MAX)) ? -(MAX) : r;
    l = (l > MAX) ? MAX : l;
    l = (l < -(MAX)) ? -(MAX) : l;

    //set motor powers, slaves do not need to be called as they were set to follow the master in the class constructor
    rightMaster.set(r);
    leftMaster.set(l);
  }

  //method to be called from the arcade drive command
  public void arcadeDrive(double x, double y) {
    double r, l;

    //set the values of r and l based off of x and y axes - may need to switch addition and subtraction
    r = x - y;
    l = x + y;

    move(r, l);
  }

  public void arcadeDriveLookup(double x, double y) {
    double r, l;

    //"I have no clue how this works ask Nathan" - Owen
    r = ((x > 0) ? turningFunction.getTable()[(int) Math.floor(Math.abs(x) * 100)] : -turningFunction.getTable()[(int) Math.floor(Math.abs(x) * 100)]) - ((y > 0) ? turningFunction.getTable()[(int) Math.floor(Math.abs(y) * 100)] : -turningFunction.getTable()[(int) Math.floor(Math.abs(y) * 100)]);
    l = ((x > 0) ? turningFunction.getTable()[(int) Math.floor(Math.abs(x) * 100)] : -turningFunction.getTable()[(int) Math.floor(Math.abs(x) * 100)]) + ((y > 0) ? turningFunction.getTable()[(int) Math.floor(Math.abs(y) * 100)] : -turningFunction.getTable()[(int) Math.floor(Math.abs(y) * 100)]);
    SmartDashboard.putNumber("rightPower", r);
    SmartDashboard.putNumber("leftPower", l);
    move(r, l);
  }

  //method to be called in the event of a tank drive
  public void tankDrive(double r, double l) {
    //this is pretty self explanatory
    move(r, l);
  }

  public void tankDriveLookup(double r, double l) {
    r = ((r > 0)) ? lookup[(int) Math.floor(Math.abs(r) * 100)] : -lookup[(int) Math.floor(Math.abs(r) * 100)];
    l = ((l > 0)) ? lookup[(int) Math.floor(Math.abs(l) * 100)] : -lookup[(int) Math.floor(Math.abs(l) * 100)];
    
    move(r, l);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(io.getDriverController().isButtonPressed("A")) {
      CommandScheduler.getInstance().schedule(new AutoAimCommand(System.currentTimeMillis()));
    }

    if(lastAimSuccessful) {
      new RumbleCommand().initialize();
      lastAimSuccessful = false;
    }

    SmartDashboard.putNumber("Right Speed", this.getRightSpeed());
    SmartDashboard.putNumber("Left Speed", this.getLeftSpeed());
  }

  //I (Nathan) think this in miles/hr but I don't exactly remember
  //remember to document your code, kids
  public double getRightSpeed() {
    return rightMaster.getEncoder().getVelocity() * 0.001255;
  }

  public double getLeftSpeed() {
    return leftMaster.getEncoder().getVelocity() * 0.001255;
  }

}
