/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.utils.vision.LimelightDefault;

public class AutoRPMCommand extends CommandBase {
  private boolean finished = false;
  /**
   * Creates a new AutoRPMCommand.
   */
  public AutoRPMCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ty = LimelightDefault.getTY() + 30;
    if(ty > 50.8) {
      Robot.maxRPM = Robot.rpmLookup[0];
    } else if(ty > 48.3) {
      Robot.maxRPM = Robot.rpmLookup[1];
    } else if(ty > 46.14) {
      Robot.maxRPM = Robot.rpmLookup[2];
    } else if(ty > 44.1) {
      Robot.maxRPM = Robot.rpmLookup[3];
    } else if(ty > 42.31) {
      Robot.maxRPM = Robot.rpmLookup[4];
    } else if(ty > 39.8) {
      Robot.maxRPM = Robot.rpmLookup[5];
    } else if(ty > 37.26) {
      Robot.maxRPM = Robot.rpmLookup[6];
    } else if(ty > 35.84) {
      Robot.maxRPM = Robot.rpmLookup[7];
    } else if(ty > 34.63) {
      Robot.maxRPM = Robot.rpmLookup[8];
    }
    finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
