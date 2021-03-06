/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class AimLeftCommand extends CommandBase {
  private double power;
  private SubsystemsInstance inst;
  private IO io;
  /**
   * Creates a new AimLeftCommand.
   */
  public AimLeftCommand(double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.power = power;
    inst = SubsystemsInstance.getInstance();
    io = IO.getInstance();
    addRequirements(inst.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    inst.m_driveSubsystem.move(power, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (!io.getDriverController().isAngleMatched("W"));
  }
}
