/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.Robot;

public class RumbleCommand extends CommandBase {
  private IO io;
  /**
   * Creates a new RumbleCommand.
   */
  public RumbleCommand() {
    io = IO.getInstance();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    io.getDriverController().setRumble(RumbleType.kLeftRumble, 0.5);
    io.getDriverController().setRumble(RumbleType.kRightRumble, 0.5);
    io.getManipulatorController().setRumble(RumbleType.kLeftRumble, 0.5);
    io.getManipulatorController().setRumble(RumbleType.kRightRumble, 0.5);
    // try {
    //   Thread.sleep(500);
    // } catch (InterruptedException e) {
    //   e.printStackTrace();
    // }
    io.getDriverController().setRumble(RumbleType.kLeftRumble, 0.0);
    io.getDriverController().setRumble(RumbleType.kRightRumble, 0.0);
    io.getManipulatorController().setRumble(RumbleType.kLeftRumble, 0.0);
    io.getManipulatorController().setRumble(RumbleType.kRightRumble, 0.0);
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
