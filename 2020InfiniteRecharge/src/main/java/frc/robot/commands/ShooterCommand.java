/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShooterCommand extends CommandBase {
  /**
   * Creates a new ShooterCommand.
   */
  public ShooterCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(Robot.m_io.xbox1.getRawAxis(3) > 0.3) {
    //   Robot.m_shooterSubsystem.shootRPM(1.0);
    // } else {
    //   Robot.m_shooterSubsystem.shootRPM(0.0);
    // }
    // double ty = LimelightDefault.getTY() + 30;
    // if(ty > 50.8) {
    //   Robot.maxRPM = Robot.rpmLookup[0];
    // } else if(ty > 48.3) {
    //   Robot.maxRPM = Robot.rpmLookup[1];
    // } else if(ty > 46.14) {
    //   Robot.maxRPM = Robot.rpmLookup[2];
    // } else if(ty > 44.1) {
    //   Robot.maxRPM = Robot.rpmLookup[3];
    // } else if(ty > 42.31) {
    //   Robot.maxRPM = Robot.rpmLookup[4];
    // } else if(ty > 39.8) {
    //   Robot.maxRPM = Robot.rpmLookup[5];
    // } else if(ty > 37.26) {
    //   Robot.maxRPM = Robot.rpmLookup[6];
    // } else if(ty > 35.84) {
    //   Robot.maxRPM = Robot.rpmLookup[7];
    // } else if(ty > 34.63) {
    //   Robot.maxRPM = Robot.rpmLookup[8];
    // } else if(ty > 33.7) {
    //   Robot.maxRPM = Robot.rpmLookup[9];
    // } else if(ty > 32.5) {
    //   Robot.maxRPM = Robot.rpmLookup[10];
    // } else if(ty > 31.95) {
    //   Robot.maxRPM = Robot.rpmLookup[11];
    // } else if(ty > 31.45) {
    //   Robot.maxRPM = Robot.rpmLookup[12];
    // } else if(ty > 30.7) {
    //   Robot.maxRPM = Robot.rpmLookup[13];
    // } else {
    //   Robot.maxRPM = 5350;
    // }
    Robot.m_shooterSubsystem.shootRPM((Robot.m_io.xbox1.getRawAxis(3) > 0.3) ? 1.0 : 0.0);
    // Robot.m_shooterSubsystem.shoot((Robot.m_io.xbox1.getRawAxis(3) > 0.2) ? 0.8 : 0.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
