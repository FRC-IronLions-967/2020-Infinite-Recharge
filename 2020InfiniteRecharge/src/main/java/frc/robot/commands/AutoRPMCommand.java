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
    if(LimelightDefault.getTY() > 20.8) {
      Robot.maxRPM = Robot.rpmLookup[0];
    } else if(LimelightDefault.getTY() > 16.14) {
      Robot.maxRPM = Robot.rpmLookup[1];
    } else if(LimelightDefault.getTY() > 12.31) {
      Robot.maxRPM = Robot.rpmLookup[2];
    } else if(LimelightDefault.getTY() > 9.8) {
      Robot.maxRPM = Robot.rpmLookup[3];
    } else if(LimelightDefault.getTY() > 7.26) {
      Robot.maxRPM = Robot.rpmLookup[4];
    } else if(LimelightDefault.getTY() > 5.84) {
      Robot.maxRPM = Robot.rpmLookup[5];
    } else if(LimelightDefault.getTY() > 4.63) {
      Robot.maxRPM = Robot.rpmLookup[6];
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
