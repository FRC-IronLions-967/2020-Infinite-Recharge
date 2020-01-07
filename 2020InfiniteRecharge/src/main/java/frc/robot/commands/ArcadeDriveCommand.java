/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ArcadeDriveCommand extends CommandBase {
  /**
   * Creates a new ArcadeDriveCommand.
   */
  public ArcadeDriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO pull from yet to be created IO class with joystick values - need to talk to team about control scheme
    Robot.m_driveSubsystem.arcadeDrive(Robot.m_io.xbox0.getRawAxis(1), Robot.m_io.xbox0.getRawAxis(4));
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