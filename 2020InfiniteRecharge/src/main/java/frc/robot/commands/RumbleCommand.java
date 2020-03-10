/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class RumbleCommand extends CommandBase {
  /**
   * Creates a new RumbleCommand.
   */
  public RumbleCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_io.xbox0.setRumble(RumbleType.kLeftRumble, 0.5);
    Robot.m_io.xbox0.setRumble(RumbleType.kRightRumble, 0.5);
    Robot.m_io.xbox1.setRumble(RumbleType.kLeftRumble, 0.5);
    Robot.m_io.xbox1.setRumble(RumbleType.kRightRumble, 0.5);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Robot.m_io.xbox0.setRumble(RumbleType.kLeftRumble, 0.0);
    Robot.m_io.xbox0.setRumble(RumbleType.kRightRumble, 0.0);
    Robot.m_io.xbox1.setRumble(RumbleType.kLeftRumble, 0.0);
    Robot.m_io.xbox1.setRumble(RumbleType.kRightRumble, 0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
