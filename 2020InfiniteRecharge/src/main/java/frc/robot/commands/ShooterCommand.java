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
    // Robot.m_shooterSubsystem.shootRPM(Utils.deadband(Robot.m_io.xbox1.getRawAxis(3), Double.parseDouble(Robot.m_values.getValue("deadband"))));
    if(Robot.m_io.xbox1.getRawAxis(3) > 0.3) {
      Robot.m_shooterSubsystem.shootRPM(1.0);
    }
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
