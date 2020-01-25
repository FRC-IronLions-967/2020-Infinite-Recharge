/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.utils.Utils;

public class ArcadeDriveLookupCommand extends CommandBase {
  /**
   * Creates a new ArcadeDriveLookupCommand.
   */
  public ArcadeDriveLookupCommand() {
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
    Robot.m_driveSubsystem.arcadeDriveLookup(Utils.deadband(Robot.m_io.xbox0.getRawAxis(1), Double.parseDouble(Robot.m_values.getValue("deadband"))), Utils.deadband(Robot.m_io.xbox0.getRawAxis(4), Double.parseDouble(Robot.m_values.getValue("deadband"))));
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
